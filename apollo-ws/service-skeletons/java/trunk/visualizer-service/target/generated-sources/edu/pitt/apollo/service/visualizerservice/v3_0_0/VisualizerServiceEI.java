package edu.pitt.apollo.service.visualizerservice.v3_0_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.3
 * 2015-04-08T14:09:18.542-04:00
 * Generated source version: 2.7.3
 * 
 */
@WebService(targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/v3_0_0/", name = "VisualizerServiceEI")
@XmlSeeAlso({ObjectFactory.class, edu.pitt.apollo.visualizer_service_types.v3_0_0.ObjectFactory.class, edu.pitt.apollo.types.v3_0_0.ObjectFactory.class, edu.pitt.apollo.services_common.v3_0_0.ObjectFactory.class})
public interface VisualizerServiceEI {

    @WebMethod(action = "http://service.apollo.pitt.edu/visualizerservice/v3_0_0/runVisualization")
    @RequestWrapper(localName = "runVisualization", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/v3_0_0/", className = "edu.pitt.apollo.service.visualizerservice.v3_0_0.RunVisualization")
    @ResponseWrapper(localName = "runVisualizationResponse", targetNamespace = "http://service.apollo.pitt.edu/visualizerservice/v3_0_0/", className = "edu.pitt.apollo.service.visualizerservice.v3_0_0.RunVisualizationResponse")
    public void runVisualization(
        @WebParam(name = "visualizationRunId", targetNamespace = "")
        java.math.BigInteger visualizationRunId,
        @WebParam(name = "runVisualizationMessage", targetNamespace = "")
        edu.pitt.apollo.visualizer_service_types.v3_0_0.RunVisualizationMessage runVisualizationMessage
    );
}
