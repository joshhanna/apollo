package edu.pitt.apollo.timeseriesvisualizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.pitt.apollo.GlobalConstants;
import edu.pitt.apollo.timeseriesvisualizer.exception.TimeSeriesVisualizerException;
import edu.pitt.apollo.timeseriesvisualizer.types.TimeSeriesCurveTypeEnum;
import edu.pitt.apollo.timeseriesvisualizer.types.TimeSeriesContainerList;
import edu.pitt.apollo.timeseriesvisualizer.types.TimeSeriesCurveTypeList;
import edu.pitt.apollo.timeseriesvisualizer.utilities.DatabaseUtility;
import edu.pitt.apollo.timeseriesvisualizer.utilities.VisualizerChartUtility;
import edu.pitt.apollo.types.v2_0_2.ApolloSoftwareTypeEnum;
import edu.pitt.apollo.types.v2_0_2.RunIdentificationAndLabel;
import edu.pitt.apollo.types.v2_0_2.SoftwareIdentification;

/**
 * 
 * Author: Nick Millett Email: nick.millett@gmail.com Date: Feb 12, 2013 Time:
 * 6:03:46 PM Class: ImageGenerator IDE: NetBeans 6.9.1
 */
public class ImageGenerator {

	private static final Logger logger = LoggerFactory.getLogger(ImageGenerator.class);

	public static final String SOFTWARE_NAME = "TimeSeriesVisualizer";
	private static final String VISUALIZER_PROPERTIES_FILENAME = "visualizer.properties";
	private static final String IMAGE_FILES_DIRECTORY_PARAM = "image_files_directory";
	private static final String IMAGE_FILE_TYPE_PARAM = "image_file_type";
	private static final String IMAGE_BASE_URL_PARAM = "image_base_url";
	private static final String IMAGE_FILES_DIRECTORY, IMAGE_FILE_TYPE, IMAGE_BASE_URL;
	private static final String INFECTION_STATES_IMAGE_NAME = "prevalence";
	private static final String INCIDENCE_IMAGE_NAME = "incidence";
	private static final String NEWLY_DECEASED_IMAGE_NAME = "newly_deceased";
	private static final String TREATMENT_COUNTS_IMAGE_NAME = "treatment";
	private static final String DISEASE_STATES_IMAGE_NAME = "disease_states";

	private List<BigInteger> runIds;
	private Map<BigInteger, String> runIdLabelMap;
	private final String infectionStatesImagePath;
	private final String incidenceImagePath;
	private final String newlyDeceasedImagePath;
	private final String treatmentImagePath;
	private final String diseaseStatesImagePath;
	private String combinedIncidenceImagePath;
	private final boolean multipleRunsSpecified;
	private final BigInteger visualizerRunId;
//	private final boolean multiVaccChart = false;
	private final DatabaseUtility dbUtil;
	private static final String APOLLO_DIR;

	public ImageGenerator(List<RunIdentificationAndLabel> initialRunIds,
			SoftwareIdentification visualizerSoftwareId, BigInteger visualizerRunId)
			throws TimeSeriesVisualizerException {

		dbUtil = new DatabaseUtility(visualizerSoftwareId);
		setRunIdsAndLabels(initialRunIds); // need to do this first

		this.visualizerRunId = visualizerRunId;
		multipleRunsSpecified = (runIds.size() > 1);
		// get the visualizer ID from the run ID
		String runDirectory = getRunDirectory(visualizerRunId);

		// set file paths and urls
		if (!multipleRunsSpecified) {
			// the first runId is either the only runId, or the no vacc model
			infectionStatesImagePath = runDirectory + File.separator + INFECTION_STATES_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
//			if (multiVaccChart) {
//				combinedIncidenceImagePath = runDirectory + File.separator + INCIDENCE_IMAGE_NAME + "."
//						+ IMAGE_FILE_TYPE;
//			} else {
			incidenceImagePath = runDirectory + File.separator + INCIDENCE_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
			treatmentImagePath = runDirectory + File.separator + TREATMENT_COUNTS_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
			newlyDeceasedImagePath = runDirectory + File.separator + NEWLY_DECEASED_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
			diseaseStatesImagePath = runDirectory + File.separator + DISEASE_STATES_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
//			}
		} else {
			infectionStatesImagePath = null;
			incidenceImagePath = null;
			treatmentImagePath = null;
			newlyDeceasedImagePath = null;
			diseaseStatesImagePath = null;
			combinedIncidenceImagePath = runDirectory + File.separator + INCIDENCE_IMAGE_NAME + "." + IMAGE_FILE_TYPE;
		}
	}

	public final String getRunDirectory(BigInteger visualizerRunId) {
		return IMAGE_FILES_DIRECTORY + File.separator + visualizerRunId;
	}

	private String getURLForImage(String imageName) {
		if (IMAGE_BASE_URL.charAt(IMAGE_BASE_URL.length() - 1) == '/') {
			return IMAGE_BASE_URL + visualizerRunId + "/" + imageName + "." + IMAGE_FILE_TYPE;
		} else {
			return IMAGE_BASE_URL + "/" + visualizerRunId + "/" + imageName + "." + IMAGE_FILE_TYPE;
		}

	}

	private void setRunIdsAndLabels(List<RunIdentificationAndLabel> initialRunIdsAndLabels)
			throws TimeSeriesVisualizerException {
		runIds = new ArrayList<BigInteger>();
		runIdLabelMap = new HashMap<BigInteger, String>();
		for (RunIdentificationAndLabel runIdAndLabel : initialRunIdsAndLabels) {
			BigInteger runId = runIdAndLabel.getRunIdentification();
			runIds.add(runId);
			runIdLabelMap.put(runId, runIdAndLabel.getRunLabel());
		}
	}

	public void createTimeSeriesImages() throws TimeSeriesVisualizerException {

		String chartXAxisLabel = "simulation time step (days)"; // this is a hack for now, should change in the future
		TimeSeriesCurveTypeList timeSeriesCurveTypesToUse = new TimeSeriesCurveTypeList();

		if (runIds.size() == 1) {
			String simulatorName = dbUtil.getSimulatorSoftwareNameForRun(runIds.get(0)).toLowerCase();
			if (simulatorName.equals("flute")) {
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.NEWLY_LATENT);
				timeSeriesCurveTypesToUse.setTitleForIncidenceChart("Incidence of newly latent over time");

			} else if (simulatorName.equals("anthrax")) {
				chartXAxisLabel = "simulation time step (hours)";
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.SUSCEPTIBLE);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.LATENT);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.RECOVERED);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.NEWLY_DECEASED);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.PROPHYLACTICS_GIVEN);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.ASYMPTOMATIC);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.SYMPTOMATIC);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.FULMINANT);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.DEAD);

				timeSeriesCurveTypesToUse.setTitleForInfectionStatesChart("Infection states over time");
				timeSeriesCurveTypesToUse.setTitleForNewlyDeceasedChart("Count of newly deceased over time");
				timeSeriesCurveTypesToUse.setTitleForTreatmentChart("Prophylactics given over time");
				timeSeriesCurveTypesToUse.setTitleForDiseaseStatesChart("Disease states over time");
			} else {
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.SUSCEPTIBLE);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.LATENT);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.INFECTIOUS);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.RECOVERED);
				timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.NEWLY_LATENT);

				timeSeriesCurveTypesToUse.setTitleForInfectionStatesChart("Infection states over time");
				timeSeriesCurveTypesToUse.setTitleForIncidenceChart("Incidence of newly latent over time");
			}
		} else {
			timeSeriesCurveTypesToUse.add(TimeSeriesCurveTypeEnum.NEWLY_LATENT);
		}

		timeSeriesCurveTypesToUse.processAddedCurveTypes();

		boolean createCombinedIncidenceChart = multipleRunsSpecified;
		generateImages(createCombinedIncidenceChart, timeSeriesCurveTypesToUse, chartXAxisLabel);
	}

	// private void
	// adjustGlobalEpidemicSimulatorIncidence(TimeSeriesContainerList
	// imageSeriesMap) throws TimeSeriesVisualizerException {
	//
	// // Map<String, double[]> incidenceMap =
	// incidenceContainer.getIncidenceTimeSeriesMap();
	// if (imageSeriesMap.size() > 1) {
	// // only need to adjust if there is more than 1 incidence curve (combined
	// incidence)
	// int targetRunLength = 0;
	// String simulatorKey = null;
	// for (String runId : imageSeriesMap.keySet()) {
	// if (!runId.contains("Global_Epidemic_Simulator")) {
	// targetRunLength = imageSeriesMap.get(runId).getSeriesLength();
	// // all of the non GES simulators should have the same length, so it
	// doesn't matter if this is set multiple times
	// } else {
	// simulatorKey = runId;
	// }
	// }
	//
	// if (simulatorKey != null) {
	//
	// Double[] series =
	// imageSeriesMap.get(simulatorKey).getSeries(TimeSeriesCurveTypeEnum.NEWLY_LATENT);
	// Double[] newSeries = new Double[targetRunLength];
	//
	// // if (series.length < targetRunLength) {
	// // System.arraycopy(series, 0, newSeries, 0, series.length);
	// // // the rest of the newSeries array already contains zeros
	// // } else
	// if (series.length > targetRunLength) {
	// System.arraycopy(series, 0, newSeries, 0, newSeries.length);
	// }
	//
	// imageSeriesMap.get(simulatorKey).setSeries(TimeSeriesCurveTypeEnum.NEWLY_LATENT,
	// newSeries);
	// }
	//
	// }
	// }
	private void generateImages(boolean generateCombinedIncidence, TimeSeriesCurveTypeList timeSeriesCurveTypeList,
			String chartXAxisLabel) throws TimeSeriesVisualizerException {

		TimeSeriesContainerList timeSeriesContainerList = dbUtil.retrieveTimeSeriesFromDatabaseFiles(runIds,
				timeSeriesCurveTypeList);

		// adjustGlobalEpidemicSimulatorIncidence(imageSeriesMap);
		VisualizerChartUtility chartUtility = new VisualizerChartUtility(timeSeriesContainerList);

		logger.info("Creating images...");
		Map<String, String> resourceMap = new HashMap<String, String>();
		if (timeSeriesCurveTypeList.listContainsInfectionStateCurveTypes()) {
			chartUtility.createTimeSeriesChart(infectionStatesImagePath, chartXAxisLabel,
					timeSeriesCurveTypeList.getTitleForInfectionStatesChart(), TimeSeriesCurveTypeEnum.CURVE_TYPES_FOR_INFECTION_STATES_CHART);

			resourceMap.put(INFECTION_STATES_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(INFECTION_STATES_IMAGE_NAME));
		}
		if (timeSeriesCurveTypeList.listContainsDiseaseStatesCurveTypes()) {
			chartUtility.createTimeSeriesChart(diseaseStatesImagePath, chartXAxisLabel,
					timeSeriesCurveTypeList.getTitleForDiseaseStatesChart(), TimeSeriesCurveTypeEnum.CURVE_TYPES_FOR_DISEASE_STATES_CHART);

			resourceMap.put(DISEASE_STATES_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(DISEASE_STATES_IMAGE_NAME));
		}
		if (timeSeriesCurveTypeList.listContainsTreatmentCurveTypes()) {
			chartUtility.createTimeSeriesChart(treatmentImagePath, chartXAxisLabel,
					timeSeriesCurveTypeList.getTitleForTreatmentChart(), TimeSeriesCurveTypeEnum.CURVE_TYPES_FOR_TREATMENT_COUNTS_CHART);

			resourceMap.put(TREATMENT_COUNTS_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(TREATMENT_COUNTS_IMAGE_NAME));
		}
		if (timeSeriesCurveTypeList.listContainsNewlyDeceasedCurveTypes()) {
			chartUtility.createTimeSeriesChart(newlyDeceasedImagePath, chartXAxisLabel,
					timeSeriesCurveTypeList.getTitleForNewlyDeceasedChart(), TimeSeriesCurveTypeEnum.CURVE_TYPES_FOR_NEWLY_DECEASED_CHART);

			resourceMap.put(NEWLY_DECEASED_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(NEWLY_DECEASED_IMAGE_NAME));
		}
		if (timeSeriesCurveTypeList.listContainsIncidenceCurveTypes()) {
			chartUtility.createTimeSeriesChart(incidenceImagePath, chartXAxisLabel,
					timeSeriesCurveTypeList.getTitleForIncidenceChart(), TimeSeriesCurveTypeEnum.CURVE_TYPES_FOR_INCIDENCE_CHART);

			resourceMap.put(INCIDENCE_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(INCIDENCE_IMAGE_NAME));
		} else if (generateCombinedIncidence) { // can't generate incidence and
			// comnined incidence
			chartUtility.createCombinedIncidenceTimeSeriesChart(combinedIncidenceImagePath, runIdLabelMap);
			resourceMap.put(INCIDENCE_IMAGE_NAME + "." + IMAGE_FILE_TYPE, getURLForImage(INCIDENCE_IMAGE_NAME));
		}

		dbUtil.insertURLsIntoDatabase(resourceMap, visualizerRunId);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, TimeSeriesVisualizerException {

		SoftwareIdentification visualizerSoftwareId = new SoftwareIdentification();
		visualizerSoftwareId.setSoftwareDeveloper("UPitt");
		visualizerSoftwareId.setSoftwareName("Time Series Visualizer");
		visualizerSoftwareId.setSoftwareVersion("1.0");
		visualizerSoftwareId.setSoftwareType(ApolloSoftwareTypeEnum.VISUALIZER);

		List<RunIdentificationAndLabel> runIdsAndLabels = new ArrayList<RunIdentificationAndLabel>();

		// Map<String, String> runIdSeriesLabels = new HashMap<String,
		// String>();
		// runIdSeriesLabels.put("3", "FluTE");
		// runIdSeriesLabels.put("3", "SEIR");
		RunIdentificationAndLabel runIdAndLabel = new RunIdentificationAndLabel();
		runIdAndLabel.setRunIdentification(new BigInteger("1"));
		runIdAndLabel.setRunLabel("LABEL 1");

		runIdsAndLabels.add(runIdAndLabel);
//		runIdAndLabel = new RunIdentificationAndLabel();
//		runIdAndLabel.setRunIdentification(new BigInteger("2"));
//		runIdAndLabel.setRunLabel("LABEL 2");
//		runIdsAndLabels.add(runIdAndLabel);

		ImageGenerator generator = new ImageGenerator(runIdsAndLabels, visualizerSoftwareId, new BigInteger(
				"9"));
		try {
			generator.createTimeSeriesImages();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	static {
		Map<String, String> env = System.getenv();
		String apolloDir = env.get(GlobalConstants.APOLLO_WORKDIR_ENVIRONMENT_VARIABLE);
		if (apolloDir != null) {
			if (!apolloDir.endsWith(File.separator)) {
				apolloDir += File.separator;
			}
			APOLLO_DIR = apolloDir;
			logger.info(GlobalConstants.APOLLO_WORKDIR_ENVIRONMENT_VARIABLE + " is now:" + APOLLO_DIR);

			boolean errored = false;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(APOLLO_DIR + VISUALIZER_PROPERTIES_FILENAME);
			} catch (FileNotFoundException e) {
				logger.info("Error initializing Visualizer Service.  Can not find file \""
						+ VISUALIZER_PROPERTIES_FILENAME + " \" in directory \"" + APOLLO_DIR
						+ "\". Error message is " + e.getMessage());
				errored = true;
			}

			Properties properties = new Properties();
			try {
				properties.load(fis);
			} catch (IOException e) {
				logger.info("Error initializing Visualizer Service.  Unable to read file \""
						+ VISUALIZER_PROPERTIES_FILENAME + " \" in directory \"" + APOLLO_DIR
						+ "\". Error message is " + e.getMessage());
				errored = true;
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.info("Error initializing Visualizer Service.  Unable to close file \""
						+ VISUALIZER_PROPERTIES_FILENAME + " \" in directory \"" + APOLLO_DIR
						+ "\". Error message is " + e.getMessage());
				errored = true;
			}

			IMAGE_FILES_DIRECTORY = errored ? "" : properties.getProperty(IMAGE_FILES_DIRECTORY_PARAM);
			IMAGE_FILE_TYPE = errored ? "" : properties.getProperty(IMAGE_FILE_TYPE_PARAM);
			IMAGE_BASE_URL = errored ? "" : properties.getProperty(IMAGE_BASE_URL_PARAM);
		} else {
			logger.error(GlobalConstants.APOLLO_WORKDIR_ENVIRONMENT_VARIABLE
					+ " environment variable not found!");
			APOLLO_DIR = "";
			IMAGE_FILES_DIRECTORY = "";
			IMAGE_FILE_TYPE = "";
			IMAGE_BASE_URL = "";
		}
		logger.info(SOFTWARE_NAME + " will use the following IMAGE_FILES_DIRECTORY: " + IMAGE_FILES_DIRECTORY);
		logger.info(SOFTWARE_NAME + " will use the following IMAGE_FILE_TYPE: " + IMAGE_FILE_TYPE);
		logger.info(SOFTWARE_NAME + " will use the following IMAGE_BASE_URL" + IMAGE_BASE_URL);

	}
}
