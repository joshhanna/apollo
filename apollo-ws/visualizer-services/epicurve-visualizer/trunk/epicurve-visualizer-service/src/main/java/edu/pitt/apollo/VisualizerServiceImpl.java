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

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import edu.pitt.apollo.service.visualizerservice.VisualizerServiceEI;
import edu.pitt.apollo.types.RunStatus;
import edu.pitt.apollo.types.RunStatusEnum;
import edu.pitt.apollo.types.VisualizerConfiguration;
import edu.pitt.apollo.types.VisualizerOutputResource;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

@WebService(targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/",
portName = "VisualizerServiceEndpoint",
serviceName = "VisualizerService",
endpointInterface = "edu.pitt.apollo.service.visualizerservice.VisualizerServiceEI")
class VisualizerServiceImpl implements VisualizerServiceEI {

    @Override
    @WebResult(name = "runStatus", targetNamespace = "")
    @RequestWrapper(localName = "getRunStatus", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/", className = "edu.pitt.apollo.service.visualizerservice.GetRunStatus")
    @WebMethod(action = "http://service.apollo.pitt.edu/visualizerservice/getRunStatus")
    @ResponseWrapper(localName = "getRunStatusResponse", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/", className = "edu.pitt.apollo.service.visualizerservice.GetRunStatusResponse")
    public RunStatus getRunStatus(
            @WebParam(name = "runId", targetNamespace = "") String runId) {
        RunStatus rs = new RunStatus();

        List<String> runIds = new ArrayList(Arrays.asList(runId.split(":")));
//        rs.setMessage("it worked");
//        rs.setStatus(RunStatusEnum.COMPLETED);

//        // find the run directory
        ImageGenerator ig = null;
        try {
            ig = new ImageGenerator(runIds);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Exception creatingImageGenerator: " + ex.getMessage());
        }

        String runDirectory = ig.getRunDirectory();
        String finishedFilePath = runDirectory + File.separator + ImageGeneratorRunnable.FINISHED_FILE;
        File finishedFile = new File(finishedFilePath);
        System.out.println("finished file path: " + finishedFile.getAbsolutePath());
        if (finishedFile.exists()) {
            rs.setMessage("Run with ID " + runId + " is completed");
            rs.setStatus(RunStatusEnum.COMPLETED);
        } else {
            // check started file
            String startedFilePath = runDirectory + File.separator + ImageGeneratorRunnable.STARTED_FILE;
            File startedFile = new File(startedFilePath);
            if (startedFile.exists()) {
                rs.setMessage("Still running with run ID " + runId);
                rs.setStatus(RunStatusEnum.RUNNING);
            } else {
                System.out.println("finished file path: " + finishedFile.getAbsolutePath());
                rs.setMessage("Run with ID " + runId + " has not been requested yet");
                rs.setStatus(RunStatusEnum.FAILED);
            }
        }
        return rs;
    }

    @Override
    @RequestWrapper(localName = "run", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/", className = "edu.pitt.apollo.service.visualizerservice.Run")
    @WebMethod(action = "http://service.apollo.pitt.edu/visualizerservice/run")
    @ResponseWrapper(localName = "runResponse", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/", className = "edu.pitt.apollo.service.visualizerservice.RunResponse")
    public void run(
            @WebParam(name = "visualizerConfiguration", targetNamespace = "") VisualizerConfiguration visualizerConfiguration,
            @WebParam(mode = Mode.OUT, name = "runId", targetNamespace = "") Holder<String> runId,
            @WebParam(mode = Mode.OUT, name = "visualizerOutputResource", targetNamespace = "") Holder<List<VisualizerOutputResource>> visualizerOutputResource) {

        List<String> runIds = new ArrayList(Arrays.asList(visualizerConfiguration.getVisualizationOptions().getRunId().split(":")));

        ImageGenerator ig = null;

        visualizerOutputResource.value = new ArrayList<VisualizerOutputResource>();

        try {
            ig = new ImageGenerator(runIds, visualizerOutputResource.value);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }

        // now the image URLs have been set, so start a thread to generate the images
        ImageGeneratorRunnable igRunnable = new ImageGeneratorRunnable();
        igRunnable.setImageGenerator(ig);
        Thread thread = new Thread(igRunnable);
        thread.start();

        runId.value = visualizerConfiguration.getVisualizationOptions().getRunId(); // use the same runId as in the request

    }
}
