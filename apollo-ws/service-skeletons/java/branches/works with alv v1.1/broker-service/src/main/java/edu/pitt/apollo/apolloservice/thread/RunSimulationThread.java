package edu.pitt.apollo.apolloservice.thread;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.xml.ws.WebServiceException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import edu.pitt.apollo.apolloservice.error.ApolloServiceErrorHandler;
import edu.pitt.apollo.apolloservice.translatorservice.TranslatorServiceAccessor;
import edu.pitt.apollo.db.ApolloDatabaseException;
import edu.pitt.apollo.db.ApolloDatabaseKeyNotFoundException;
import edu.pitt.apollo.service.simulatorservice.v2_1_0.SimulatorServiceEI;
import edu.pitt.apollo.service.simulatorservice.v2_1_0.SimulatorServiceV202;
import edu.pitt.apollo.types.v2_1_0.RunSimulationMessage;
import edu.pitt.apollo.types.v2_1_0.SoftwareIdentification;

/**
 * 
 * Author: Nick Millett Email: nick.millett@gmail.com Date: Apr 3, 2014 Time:
 * 11:56:31 AM Class: RunSimulationThread IDE: NetBeans 6.9.1
 */
public class RunSimulationThread extends RunApolloServiceThread {

	private final RunSimulationMessage message;

	public RunSimulationThread(RunSimulationMessage message) {
		super();
		this.message = message;
	}

	@Override
	public void run() {
		try {
			// first call the translator and translate the runSimulationMessage
			boolean translatorRunSuccessful = TranslatorServiceAccessor.runTranslatorAndReturnIfRunWasSuccessful(runId, message, dbUtils);
			if (!translatorRunSuccessful) {
				return;
			}

            // once the translator has finished, call the simulator and start
			// the simulation
			SoftwareIdentification simulatorIdentification = message.getSimulatorIdentification();
			String url = null;
			try {

				url = dbUtils.getUrlForSoftwareIdentification(simulatorIdentification);
				SimulatorServiceEI simulatorPort = null;
				try {
					simulatorPort = new SimulatorServiceV202(new URL(url)).getSimulatorServiceEndpoint();
				} catch (Exception e) {
					ApolloServiceErrorHandler.writeErrorToErrorFile(
							"Unable to get simulator port for url: " + url + "\n\tError was: " + e.getMessage(),
							runId);
					return;
				}

				// disable chunking for ZSI
				Client simulatorClient = ClientProxy.getClient(simulatorPort);
				HTTPConduit simulatorHttp = (HTTPConduit) simulatorClient.getConduit();
				HTTPClientPolicy simulatorHttpClientPolicy = new HTTPClientPolicy();
				simulatorHttpClientPolicy.setConnectionTimeout(36000);
				simulatorHttpClientPolicy.setAllowChunking(false);
				simulatorHttp.setClient(simulatorHttpClientPolicy);
				try {
					simulatorPort.runSimulation(runId /*, message*/);
				} catch (WebServiceException e) {
					ApolloServiceErrorHandler.writeErrorToErrorFile("Error calling runSimulation(): " + "\n\tError was: " + e.getMessage(),
							runId);
					return;
				}
			} catch (ApolloDatabaseKeyNotFoundException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile(
						"Apollo database key not found attempting to get URL for simulator: "
						+ simulatorIdentification.getSoftwareName() + ", version: "
						+ simulatorIdentification.getSoftwareVersion() + ", developer: "
						+ simulatorIdentification.getSoftwareDeveloper() + " for run id " + runId + ": "
						+ ex.getMessage(), runId);
				return;
			} catch (ClassNotFoundException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile(
						"ClassNotFoundException attempting to get URL for simulator: "
						+ simulatorIdentification.getSoftwareName() + ", version: "
						+ simulatorIdentification.getSoftwareVersion() + ", developer: "
						+ simulatorIdentification.getSoftwareDeveloper() + " for run id " + runId + ": "
						+ ex.getMessage(), runId);
				return;
			} catch (MalformedURLException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile(
						"MalformedURLException attempting to create port for simulator: "
						+ simulatorIdentification.getSoftwareName() + ", version: "
						+ simulatorIdentification.getSoftwareVersion() + ", developer: "
						+ simulatorIdentification.getSoftwareDeveloper() + " for run id " + runId + ". URL was: " + url
						+ ". Error message was: " + ex.getMessage(), runId);
				return;
			} catch (SQLException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile(
						"SQLException attempting to get URL for simulator: " + simulatorIdentification.getSoftwareName()
						+ ", version: " + simulatorIdentification.getSoftwareVersion() + ", developer: "
						+ simulatorIdentification.getSoftwareDeveloper() + " for run id " + runId + ": "
						+ ex.getMessage(), runId);
				return;
			}
			try {
				dbUtils.updateLastServiceToBeCalledForRun(runId, simulatorIdentification);
			} catch (ApolloDatabaseKeyNotFoundException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile("Apollo database key not found attempting to update last service"
						+ " call for run id " + runId + ": " + ex.getMessage(), runId);
				return;
			} catch (SQLException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile("SQLException attempting to update last service" + " call for run id " + runId
						+ ": " + ex.getMessage(), runId);
				return;
			} catch (ClassNotFoundException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile("ClassNotFoundException attempting to update last service" + " call for run id "
						+ runId + ": " + ex.getMessage(), runId);
				return;
			} catch (ApolloDatabaseException ex) {
				ApolloServiceErrorHandler.writeErrorToErrorFile("ApolloDatabaseException attempting to update last service" + " call for run id "
						+ runId + ": " + ex.getMessage(), runId);
				return;
			}
		} catch (IOException e) {
			logger.error("Error writing error file!: " + e.getMessage());
		}
	}

	@Override
	public void setAuthenticationPasswordFieldToBlank() {
		message.getAuthentication().setRequesterPassword("");
	}
}
