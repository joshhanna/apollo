//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for InfectionAcquisitionFromContaminatedThing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InfectionAcquisitionFromContaminatedThing"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="abioticEcosystemType" type="{http://types.apollo.pitt.edu/v3_0_0/}AbioticEcosystemEnum"/&gt;
 *         &lt;element name="transmissionProbability" type="{http://types.apollo.pitt.edu/v3_0_0/}ProbabilisticParameter"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfectionAcquisitionFromContaminatedThing", propOrder = {
    "abioticEcosystemType",
    "transmissionProbability"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class InfectionAcquisitionFromContaminatedThing {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AbioticEcosystemEnum abioticEcosystemType;
    @XmlElement(required = true)
    protected ProbabilisticParameter transmissionProbability;

    /**
     * Gets the value of the abioticEcosystemType property.
     * 
     * @return
     *     possible object is
     *     {@link AbioticEcosystemEnum }
     *     
     */
    public AbioticEcosystemEnum getAbioticEcosystemType() {
        return abioticEcosystemType;
    }

    /**
     * Sets the value of the abioticEcosystemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbioticEcosystemEnum }
     *     
     */
    public void setAbioticEcosystemType(AbioticEcosystemEnum value) {
        this.abioticEcosystemType = value;
    }

    /**
     * Gets the value of the transmissionProbability property.
     * 
     * @return
     *     possible object is
     *     {@link ProbabilisticParameter }
     *     
     */
    public ProbabilisticParameter getTransmissionProbability() {
        return transmissionProbability;
    }

    /**
     * Sets the value of the transmissionProbability property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProbabilisticParameter }
     *     
     */
    public void setTransmissionProbability(ProbabilisticParameter value) {
        this.transmissionProbability = value;
    }

}
