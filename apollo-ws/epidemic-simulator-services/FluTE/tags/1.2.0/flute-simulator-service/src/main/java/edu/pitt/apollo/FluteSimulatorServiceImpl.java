/* Copyright 2012 University of Pittsburgh
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package edu.pitt.apollo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.pitt.apollo.flute.utils.FileUtils;
import java.io.IOException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import edu.pitt.apollo.flute.utils.QueueThread;
import edu.pitt.apollo.flute.utils.RunIdProperties;
import edu.pitt.apollo.flute.utils.RunIdStoreThread;
import edu.pitt.apollo.flute.utils.RunUtils;
import edu.pitt.apollo.flute.utils.SimulatorThread;
import edu.pitt.apollo.service.simulatorservice.SimulatorServiceEI;
import edu.pitt.apollo.types.BatchRunResult;
import edu.pitt.apollo.types.BatchRunSimulatorConfiguration;
import edu.pitt.apollo.types.RunStatus;
import edu.pitt.apollo.types.RunStatusEnum;
import edu.pitt.apollo.types.SimulatorConfiguration;
import edu.pitt.apollo.types.SoftwareIdentification;
import edu.pitt.apollo.types.SupportedPopulationLocation;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

@WebService(targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", portName = "SimulatorServiceEndpoint", serviceName = "SimulatorService", endpointInterface = "edu.pitt.apollo.service.simulatorservice.SimulatorServiceEI")
public class FluteSimulatorServiceImpl implements SimulatorServiceEI {

    // queue for simulator threads
    private static final int MAX_NUM_SIMULATOR_THREADS = 10; // note that each sinulator thread requires a seperate connection to the flute server
    private static final int MAX_QUEUE_SIZE = 200;
    private static int currentRunIdNumber;
    private static int numRunningSimulatorThreads;
    private static Thread storeThread;
    private static Queue<Thread> simulatorThreadQueue;
    private static List<String> queuedThreads = new ArrayList<String>();
    // executor for the simulator threads
    // private static ExecutorService simulatorExecutor =
    // Executors.newFixedThreadPool(5);
    // private static ExecutorService batchExecutor =
    // Executors.newFixedThreadPool(1);
    // this map will associate the md5 sim config hash with a runID string more
    // easily readable

    static {
//		// create and load the runID map

        // initialize simulator thread queue
        simulatorThreadQueue = new LinkedList<Thread>();
        numRunningSimulatorThreads = 0;

        try {
            currentRunIdNumber = Integer.parseInt(RunUtils.getNextId());
            storeThread = new RunIdStoreThread(false);
            storeThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {

                try {
                    RunUtils.writeCurrentId(currentRunIdNumber);
                } catch (IOException ex) {
                    System.err.println("IO exception writing current ID to file");
                }
            }
        }));
    }

    @Override
    @WebResult(name = "runStatus", targetNamespace = "")
    @RequestWrapper(localName = "getRunStatus", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetRunStatus")
    @WebMethod(action = "http://service.apollo.pitt.edu/simulatorservice/getRunStatus")
    @ResponseWrapper(localName = "getRunStatusResponse", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetRunStatusResponse")
    public RunStatus getRunStatus(
            @WebParam(name = "runId", targetNamespace = "") String runId) {
        try {
            // System.out.println("Getting run status for run id " + runId);
            return RunUtils.getStatus(runId);
        } catch (IOException e) {
            RunStatus rs = new RunStatus();
            rs.setMessage("Error getting runStatus from web service, error is: "
                    + e.getMessage());
            rs.setStatus(RunStatusEnum.FAILED);
            return rs;
        }
    }

    @Override
    @WebResult(name = "supportedPopluationLocations", targetNamespace = "")
    @RequestWrapper(localName = "getSupportedLocations", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetSupportedLocations")
    @WebMethod
    @ResponseWrapper(localName = "getSupportedLocationsResponse", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetSupportedLocationsResponse")
    public List<SupportedPopulationLocation> getSupportedLocations() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @WebResult(name = "runId", targetNamespace = "")
    @RequestWrapper(localName = "run", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.Run")
    @WebMethod(action = "http://service.apollo.pitt.edu/simulatorservice/run")
    @ResponseWrapper(localName = "runResponse", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.RunResponse")
    public String run(
            @WebParam(name = "simulatorConfiguration", targetNamespace = "") SimulatorConfiguration simulatorConfiguration) {

        // get simulator config JSON
        String simConfigJson = getJSONString(simulatorConfiguration);

        // hash the JSON
//        String simConfigHash = RunUtils.getMd5HashFromBytes(baos.toByteArray());
        String simConfigHash = RunUtils.getMd5HashFromString(simConfigJson);

        // get a run ID
        SoftwareIdentification sid = simulatorConfiguration.getSimulatorIdentification();
        RunIdProperties runIdProps = getOrAddRunId(simConfigHash, sid);
        String runId = runIdProps.getRunId();
        // hash the run ID to avoid issues with certain characters
//        String runIdHash = RunUtils.getMd5HashFromBytes(runId.getBytes());
        String runIdHash = RunUtils.getMd5HashFromString(runId);

        // add the runId hash to the queued threads
        queuedThreads.add(runIdHash);

        Thread worker = new SimulatorThread(simulatorConfiguration,
                simConfigHash, runId, runIdHash, simConfigJson,
                !runIdProps.isRunIdInDatabase(), false, true);
        QueueThread queueThread = new QueueThread(worker);
        queueThread.start();

        return runId;
    }

    @Override
    @WebResult(name = "batchRun", targetNamespace = "")
    @RequestWrapper(localName = "batchRun", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.BatchRun")
    @WebMethod(action = "http://service.apollo.pitt.edu/simulatorservice/batchRun")
    @ResponseWrapper(localName = "batchRunResponse", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.BatchRunResponse")
    public BatchRunResult batchRun(
            @WebParam(name = "batchRunSimulatorConfiguration", targetNamespace = "") BatchRunSimulatorConfiguration batchRunSimulatorConfiguration) {

//        SoftwareIdentification sid = batchRunSimulatorConfiguration.getSoftwareIdentification();
//
//        String runId = "-1";
//        // try {
//        runId = sid.getSoftwareDeveloper() + "_" + sid.getSoftwareName() + "_"
//                + sid.getSoftwareVersion() + "_" + incrementRunId();
//
//        String md5RunIdHash = RunUtils.getMd5HashFromBytes(runId.getBytes());
//
//        BatchRunResult result = new BatchRunResult();
//
//        result.setCompletedFile("https://betaweb.rods.pitt.edu/jdl50_web_dav-source/"
//                + md5RunIdHash + "_results.txt");
//        // result.setErrorFile(md5RunIdHash + "_errors.txt");
//        result.setRunId(runId);
//
//        BatchThread bt = new BatchThread(
//                batchRunSimulatorConfiguration.getBatchConfigurationFile(),
//                result, runId, md5RunIdHash);
//        bt.start();
//        // bt.run();
//
//        System.out.println("RunId is :" + runId);
//
//        return result;
        // } catch (IOException e1) {
        return null;
        // }
    }

    @Override
    @WebResult(name = "getConfigurationFileForRun", targetNamespace = "")
    @RequestWrapper(localName = "getConfigurationFileForRun", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetConfigrationFileForRun")
    @WebMethod(action = "http://service.apollo.pitt.edu/simulatorservice/getConfigurationFileForRun")
    @ResponseWrapper(localName = "getConfigurationFileForRun", targetNamespace = "http://service.apollo.pitt.edu/simulatorservice/", className = "edu.pitt.apollo.service.simulatorservice.GetConfigrationFileForRunResponse")
    public String getConfigurationFileForRun(String runId) {

//        String[] splitRunId = runId.split("::");
//        String runIdPart = splitRunId[0];
//        String configFileType = splitRunId[1];
//        String runIdHash = RunUtils.getMd5HashFromBytes(runId.getBytes());
        String runIdHash = RunUtils.getMd5HashFromString(runId);
        String configText = null;
        try {
            configText = FileUtils.loadConfigurationFile(runIdHash);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return configText;
    }

    // public static void shutdownAll() {
    // simulatorExecutor.shutdown();
    // batchExecutor.shutdown();
    // }
    private static synchronized void addRunToQueuedList(String md5Hash) {
        if (!queuedThreads.contains(md5Hash)) { // only need to add it if it is
            // not already in the list
            queuedThreads.add(md5Hash);
        }
    }

    public static synchronized void removeRunFromQueuedList(String md5Hash) {
        queuedThreads.remove(md5Hash);
    }

    public static synchronized boolean isRunQueued(String md5Hash) {
        return queuedThreads.contains(md5Hash);
    }

    public static synchronized void simulatorRunFinished() {
        numRunningSimulatorThreads--;
    }

    public static synchronized int incrementRunId() {

        currentRunIdNumber++;
        return currentRunIdNumber;
    }

    public static synchronized int getCurrentRunId() {
        return currentRunIdNumber;
    }

    public static synchronized boolean addSimulatorThread(Thread runnable) {

        if (simulatorThreadQueue.size() < MAX_QUEUE_SIZE) {

            simulatorThreadQueue.add(runnable);
            runSimulatorThreads(); // try to run a new thread

            return true;
        } else {
            return false;
        }
    }

    public static synchronized void runSimulatorThreads() {
        // start as many runs as possible
        while (numRunningSimulatorThreads < MAX_NUM_SIMULATOR_THREADS && simulatorThreadQueue.size() > 0) {

            Thread thread = simulatorThreadQueue.poll();
            numRunningSimulatorThreads++;
            thread.start();
        }
    }

    public static String formatJSONString(String jsonString) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

//    public static ByteArrayOutputStream getJSONBytes(
//            SimulatorConfiguration simConfig) {
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
//            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            mapper.writeValue(baos, simConfig);
//
//            return baos;
//        } catch (IOException ex) {
//            System.err.println("IO Exception JSON encoding and getting string from SimulatorConfiguration");
//            return null;
//        }
//    }
    public static String getJSONString(SimulatorConfiguration simConfig) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mapper.writeValue(baos, simConfig);

            return baos.toString();
        } catch (IOException ex) {
            System.err.println("IO Exception JSON encoding and getting bytes from SimulatorConfiguration");
            return null;
        }
    }

    public static synchronized RunIdProperties getOrAddRunId(String simConfigHash, SoftwareIdentification sid) {

        // not using a cache anymore since that is done in apollo service
        RunIdProperties runIdProps = new RunIdProperties();
        String newRunId = sid.getSoftwareDeveloper() + "_"
                + sid.getSoftwareName() + "_" + sid.getSoftwareVersion()
                + "_" + incrementRunId();

        runIdProps.setRunId(newRunId);
        runIdProps.setRunIdInDatabase(false);
        return runIdProps;

    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        FluteSimulatorServiceImpl s = new FluteSimulatorServiceImpl();
        BatchRunSimulatorConfiguration c = new BatchRunSimulatorConfiguration();
        c.setAcceptCachedResults(true);
        c.setSoftwareIdentification(new SoftwareIdentification());
        c.getSoftwareIdentification().setSoftwareDeveloper("DevName");
        c.getSoftwareIdentification().setSoftwareName("Fake Simulator");
        c.getSoftwareIdentification().setSoftwareVersion("1.0");
        c.setBatchConfigurationFile("http://localhost:8080/apollo/test_3.json");
        BatchRunResult result = s.batchRun(c);

        while (true) {
            RunStatusEnum t = s.getRunStatus(result.getRunId()).getStatus();
            if (t == RunStatusEnum.COMPLETED) {
                System.out.println("Time: "
                        + (System.currentTimeMillis() - startTime));
                break;
            }
        }
        // System.out.println(s.getConfigurationFileForRun("0_FRED_2.0.6_3007"));

        System.exit(0);
    }
}