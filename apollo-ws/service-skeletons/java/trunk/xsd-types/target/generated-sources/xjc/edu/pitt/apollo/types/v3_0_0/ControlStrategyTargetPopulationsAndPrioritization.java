//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for ControlStrategyTargetPopulationsAndPrioritization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ControlStrategyTargetPopulationsAndPrioritization"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="controlStrategyNamedPrioritizationScheme" type="{http://types.apollo.pitt.edu/v3_0_0/}NamedPrioritizationSchemeEnum"/&gt;
 *           &lt;element name="controlStrategyTargetPopulationsAndPrioritization" type="{http://types.apollo.pitt.edu/v3_0_0/}TargetPriorityPopulation" maxOccurs="unbounded"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ControlStrategyTargetPopulationsAndPrioritization", propOrder = {
    "controlStrategyNamedPrioritizationScheme",
    "controlStrategyTargetPopulationsAndPrioritization"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class ControlStrategyTargetPopulationsAndPrioritization {

    @XmlSchemaType(name = "string")
    protected NamedPrioritizationSchemeEnum controlStrategyNamedPrioritizationScheme;
    protected List<TargetPriorityPopulation> controlStrategyTargetPopulationsAndPrioritization;

    /**
     * Gets the value of the controlStrategyNamedPrioritizationScheme property.
     * 
     * @return
     *     possible object is
     *     {@link NamedPrioritizationSchemeEnum }
     *     
     */
    public NamedPrioritizationSchemeEnum getControlStrategyNamedPrioritizationScheme() {
        return controlStrategyNamedPrioritizationScheme;
    }

    /**
     * Sets the value of the controlStrategyNamedPrioritizationScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedPrioritizationSchemeEnum }
     *     
     */
    public void setControlStrategyNamedPrioritizationScheme(NamedPrioritizationSchemeEnum value) {
        this.controlStrategyNamedPrioritizationScheme = value;
    }

    /**
     * Gets the value of the controlStrategyTargetPopulationsAndPrioritization property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controlStrategyTargetPopulationsAndPrioritization property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getControlStrategyTargetPopulationsAndPrioritization().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TargetPriorityPopulation }
     * 
     * 
     */
    public List<TargetPriorityPopulation> getControlStrategyTargetPopulationsAndPrioritization() {
        if (controlStrategyTargetPopulationsAndPrioritization == null) {
            controlStrategyTargetPopulationsAndPrioritization = new ArrayList<TargetPriorityPopulation>();
        }
        return this.controlStrategyTargetPopulationsAndPrioritization;
    }

}
