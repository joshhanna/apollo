
package edu.pitt.apollo.service.syntheticpopulationservice.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatus;


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
 *         &lt;element name="runStatus" type="{http://services-common.apollo.pitt.edu/v3_0_0/}MethodCallStatus"/>
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
    "runStatus"
})
@XmlRootElement(name = "getRunStatusResponse")
public class GetRunStatusResponse {

    @XmlElement(required = true)
    protected MethodCallStatus runStatus;

    /**
     * Gets the value of the runStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MethodCallStatus }
     *     
     */
    public MethodCallStatus getRunStatus() {
        return runStatus;
    }

    /**
     * Sets the value of the runStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodCallStatus }
     *     
     */
    public void setRunStatus(MethodCallStatus value) {
        this.runStatus = value;
    }

}
