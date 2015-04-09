
package edu.pitt.apollo.service.apolloservice.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.services_common.v3_0_0.RunResult;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="runSimulationsResult" type="{http://services-common.apollo.pitt.edu/v3_0_0/}RunResult"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "runSimulationsResult"
})
@XmlRootElement(name = "runSimulationsResponse")
public class RunSimulationsResponse {

    @XmlElement(required = true)
    protected RunResult runSimulationsResult;

    /**
     * Gets the value of the runSimulationsResult property.
     * 
     * @return
     *     possible object is
     *     {@link RunResult }
     *     
     */
    public RunResult getRunSimulationsResult() {
        return runSimulationsResult;
    }

    /**
     * Sets the value of the runSimulationsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RunResult }
     *     
     */
    public void setRunSimulationsResult(RunResult value) {
        this.runSimulationsResult = value;
    }

}
