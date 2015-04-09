package edu.pitt.apollo.service.translatorservice.v3_0_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.3
 * 2015-04-08T14:09:10.957-04:00
 * Generated source version: 2.7.3
 * 
 */
@WebService(targetNamespace = "http://service.apollo.pitt.edu/translatorservice/v3_0_0/", name = "TranslatorServiceEI")
@XmlSeeAlso({edu.pitt.apollo.types.v3_0_0.ObjectFactory.class, edu.pitt.apollo.services_common.v3_0_0.ObjectFactory.class, edu.pitt.apollo.simulator_service_types.v3_0_0.ObjectFactory.class, ObjectFactory.class})
public interface TranslatorServiceEI {

    @WebMethod(action = "http://service.apollo.pitt.edu/translatorservice/v3_0_0/translateRun")
    @RequestWrapper(localName = "translateRun", targetNamespace = "http://service.apollo.pitt.edu/translatorservice/v3_0_0/", className = "edu.pitt.apollo.service.translatorservice.v3_0_0.TranslateRun")
    @ResponseWrapper(localName = "translateRunResponse", targetNamespace = "http://service.apollo.pitt.edu/translatorservice/v3_0_0/", className = "edu.pitt.apollo.service.translatorservice.v3_0_0.TranslateRunResponse")
    public void translateRun(
        @WebParam(name = "runId", targetNamespace = "")
        java.math.BigInteger runId
    );
}
