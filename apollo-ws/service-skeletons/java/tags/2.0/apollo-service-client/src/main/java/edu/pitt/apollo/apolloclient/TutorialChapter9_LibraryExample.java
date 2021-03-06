package edu.pitt.apollo.apolloclient;

import java.net.MalformedURLException;
import java.util.ArrayList;

import edu.pitt.apollo.types.v2_0.AddLibraryItemResult;
import edu.pitt.apollo.types.v2_0.Authentication;
import edu.pitt.apollo.types.v2_0.FixedStartTime;
import edu.pitt.apollo.types.v2_0.GetLibraryItemResult;
import edu.pitt.apollo.types.v2_0.IndividualTreatmentControlStrategy;
import edu.pitt.apollo.types.v2_0.Infection;
import edu.pitt.apollo.types.v2_0.InfectionAcquisition;
import edu.pitt.apollo.types.v2_0.InfectiousDiseaseControlStrategy;
import edu.pitt.apollo.types.v2_0.InfectiousDiseaseScenario;
import edu.pitt.apollo.types.v2_0.RunAndSoftwareIdentification;
import edu.pitt.apollo.types.v2_0.RunSimulationMessage;
import edu.pitt.apollo.types.v2_0.Vaccination;

public class TutorialChapter9_LibraryExample extends
		TutorialChapter8_RunSimulationWithVaccinationControlStrategyExample {

	public String infectionUuid = "";
	public String infectiousDiseaseScenarioUuid = "";
	public String vaccinationControlStrategyUuid = "";

	public TutorialChapter9_LibraryExample() throws MalformedURLException {
		super();
	}

	protected Authentication getAuthentication() {
		Authentication auth = new Authentication();
		auth.setRequesterId("TutorialUser");
		auth.setRequesterPassword("TutorialPassword");
		return auth;
	}

	public void saveItems() {
		RunSimulationMessage runSimulationMessage = getRunSimulationMessage();

		InfectiousDiseaseScenario infectiousDiseaseScenario = runSimulationMessage.getInfectiousDiseaseScenario();
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("tutorial");

		Infection infection = infectiousDiseaseScenario.getInfections().get(0);

		AddLibraryItemResult result = getPort().addLibraryItem(getAuthentication(), infection,
				"The Infection instance of the InfectiousDiseaseScenario from the tutorial..", "Tutorial", "Infection",
				labels);
		infectionUuid = result.getUuid();
		System.out.println("Uuid returned for newly saved Infection instance: " + result.getUuid());

		InfectiousDiseaseControlStrategy vaccinationControlStrategy = infectiousDiseaseScenario
				.getInfectiousDiseaseControlStrategies().get(0);

		result = getPort().addLibraryItem(getAuthentication(), vaccinationControlStrategy,
				"The Vaccination Control Strategy used in the tutorial", "Tutorial",
				"InfectiousDiseaseControlStrategy", labels);

		vaccinationControlStrategyUuid = result.getUuid();
		System.out.println("Uuid returned for newly saved VaccinationControlStrategy instance: " + result.getUuid());

		result = getPort()
				.addLibraryItem(getAuthentication(), infectiousDiseaseScenario,
						"The Infectious Disease Scenario used in the tutorial", "Tutorial",
						"InfectiousDiseaseScenario", labels);

		infectiousDiseaseScenarioUuid = result.getUuid();
		System.out.println("Uuid returned for newly saved InfectionDiseaseScenario instance: " + result.getUuid());
		System.out.println();

	}

	public void loadInfection() {
		System.out.println("Retrieving Infection instance from Library using Uuid: " + infectionUuid + "\n");
		GetLibraryItemResult infectionItem = getPort().getLibraryItem(infectionUuid);
		Infection infection = (Infection) infectionItem.getCuratedLibraryItemContainer().getApolloIndexableItem();
		System.out.println("Infection instance retrieved from the library!  Here are the attributes:");
		System.out.println("\tHost Taxon ID: " + infection.getHostTaxonID());
		System.out.println("\tPathogen Taxon ID: " + infection.getPathogenTaxonID().getNcbiTaxonId());
		System.out.println("\tInfectiousPeriodDuration: " + infection.getInfectiousPeriodDuration().getValue());
		System.out.println("\tLatentPeriodDuration: " + infection.getLatentPeriodDuration().getValue());

		InfectionAcquisition infectionAcquisition = infection.getInfectionAcquisition().get(0);
		System.out.println("\tInfectionAcquisition:");
		System.out.println("\t\tSusceptible Host Taxon ID: " + infectionAcquisition.getSusceptibleHostTaxonID());
		System.out.println("\t\tPathogen Taxon ID: " + infectionAcquisition.getPathogenTaxonID().getNcbiTaxonId());
		System.out.println("\t\tBasic Reproduction Number: " + infectionAcquisition.getBasicReproductionNumber());
		System.out.println();
	}

	public void loadVaccinationControlStrategy() {
		GetLibraryItemResult vaccinationControlStrategyItem = getPort().getLibraryItem(vaccinationControlStrategyUuid);
		IndividualTreatmentControlStrategy vaccinationControlStrategy = (IndividualTreatmentControlStrategy) vaccinationControlStrategyItem
				.getCuratedLibraryItemContainer().getApolloIndexableItem();
		System.out.println("VaccinationControlStrategy instance retrieved from the library:");
		System.out.println("\tDescription: " + vaccinationControlStrategy.getDescription());
		System.out.println("\tReactive End Point Fraction: "
				+ vaccinationControlStrategy.getControlStrategyReactiveEndPointFraction());
		System.out.println("\tCompliance: " + vaccinationControlStrategy.getControlStrategyCompliance().getValue());
		System.out.println("\tResponse Delay: "
				+ vaccinationControlStrategy.getControlStrategyResponseDelay().getValue());
		System.out.println("\tControl Strategy Start Time: "
				+ ((FixedStartTime) vaccinationControlStrategy.getControlStrategyStartTime())
						.getStartTimeRelativeToScenarioDate());

		Vaccination vaccination = (Vaccination) vaccinationControlStrategy.getIndividualTreatment();
		System.out.println("\tNum Doses in Treatment Course:" + vaccination.getNumDosesInTreatmentCourse());
		System.out.println("\t...\n");
	}

	public InfectiousDiseaseScenario loadInfectiousDiseaseScenario() {
		System.out.println("Retrieving InfectiousDiseaseScenario instance from Library using Uuid: "
				+ infectiousDiseaseScenarioUuid + "\n");
		GetLibraryItemResult infectionDiseaseScenarioLibraryItem = getPort().getLibraryItem(infectiousDiseaseScenarioUuid);
		InfectiousDiseaseScenario infectiousDiseaseScenario = (InfectiousDiseaseScenario) infectionDiseaseScenarioLibraryItem
				.getCuratedLibraryItemContainer().getApolloIndexableItem();
		System.out.println("The existing Basic Reproduction Number in the InfectionDiseaseScenario is: "
				+ infectiousDiseaseScenario.getInfections().get(0).getInfectionAcquisition().get(0)
						.getBasicReproductionNumber());
		return infectiousDiseaseScenario;
	}

	private InfectiousDiseaseScenario setR0(InfectiousDiseaseScenario infectiousDiseaseScenario, Double value) {
		System.out.println("Changing the Basic Reproduction Number to 1.7...");
		infectiousDiseaseScenario.getInfections().get(0).getInfectionAcquisition().get(0)
				.setBasicReproductionNumber(1.7);
		return infectiousDiseaseScenario;
	}

	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		TutorialChapter9_LibraryExample tutorialChapter9 = new TutorialChapter9_LibraryExample();
		tutorialChapter9.saveItems();
		tutorialChapter9.loadInfection();
		tutorialChapter9.loadVaccinationControlStrategy();
		InfectiousDiseaseScenario infectiousDiseaseScenario = tutorialChapter9.loadInfectiousDiseaseScenario();
		InfectiousDiseaseScenario infectiousDiseaseScenarioWithHighR0 = tutorialChapter9.setR0(infectiousDiseaseScenario, 1.7);
		
		
		TutorialChapter8_RunSimulationWithVaccinationControlStrategyExample tutorialChapter8 = 
				new TutorialChapter8_RunSimulationWithVaccinationControlStrategyExample();
		RunSimulationMessage runSimulationMessageWithNormalR0 = tutorialChapter8.getRunSimulationMessage();
		RunAndSoftwareIdentification normalR0runAndSoftwareId = 
				tutorialChapter8.runSimulationAndDisplayResults(runSimulationMessageWithNormalR0);
		
		RunSimulationMessage runSimulationMessageWithHighR0 = tutorialChapter8.getRunSimulationMessage();
		runSimulationMessageWithHighR0.setInfectiousDiseaseScenario(infectiousDiseaseScenarioWithHighR0);
		RunAndSoftwareIdentification highR0RunAndSoftwareId =
				tutorialChapter8.runSimulationAndDisplayResults(runSimulationMessageWithHighR0); 
                
        tutorialChapter8.createIncidenceVisualizationForMultipleSimulations(
        		normalR0runAndSoftwareId.getRunId(), highR0RunAndSoftwareId.getRunId());
	}

}
