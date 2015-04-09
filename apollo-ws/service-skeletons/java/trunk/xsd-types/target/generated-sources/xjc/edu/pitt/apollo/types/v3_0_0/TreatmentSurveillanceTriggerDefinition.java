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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for TreatmentSurveillanceTriggerDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatmentSurveillanceTriggerDefinition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://types.apollo.pitt.edu/v3_0_0/}TriggerDefinition"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="reactiveControlStrategyTest" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="reactiveControlStrategyThreshold" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="reactiveControlStrategyOperator" type="{http://types.apollo.pitt.edu/v3_0_0/}OperatorEnum"/&gt;
 *         &lt;element name="unitOfMeasureForThreshold" type="{http://types.apollo.pitt.edu/v3_0_0/}UnitOfMeasureEnum"/&gt;
 *         &lt;element name="treatmentSurveillanceCapability" type="{http://types.apollo.pitt.edu/v3_0_0/}TreatmentSurveillanceCapability"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatmentSurveillanceTriggerDefinition", propOrder = {
    "reactiveControlStrategyTest",
    "reactiveControlStrategyThreshold",
    "reactiveControlStrategyOperator",
    "unitOfMeasureForThreshold",
    "treatmentSurveillanceCapability"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class TreatmentSurveillanceTriggerDefinition
    extends TriggerDefinition
{

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String reactiveControlStrategyTest;
    protected int reactiveControlStrategyThreshold;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected OperatorEnum reactiveControlStrategyOperator;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected UnitOfMeasureEnum unitOfMeasureForThreshold;
    @XmlElement(required = true)
    protected TreatmentSurveillanceCapability treatmentSurveillanceCapability;

    /**
     * Gets the value of the reactiveControlStrategyTest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReactiveControlStrategyTest() {
        return reactiveControlStrategyTest;
    }

    /**
     * Sets the value of the reactiveControlStrategyTest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReactiveControlStrategyTest(String value) {
        this.reactiveControlStrategyTest = value;
    }

    /**
     * Gets the value of the reactiveControlStrategyThreshold property.
     * 
     */
    public int getReactiveControlStrategyThreshold() {
        return reactiveControlStrategyThreshold;
    }

    /**
     * Sets the value of the reactiveControlStrategyThreshold property.
     * 
     */
    public void setReactiveControlStrategyThreshold(int value) {
        this.reactiveControlStrategyThreshold = value;
    }

    /**
     * Gets the value of the reactiveControlStrategyOperator property.
     * 
     * @return
     *     possible object is
     *     {@link OperatorEnum }
     *     
     */
    public OperatorEnum getReactiveControlStrategyOperator() {
        return reactiveControlStrategyOperator;
    }

    /**
     * Sets the value of the reactiveControlStrategyOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatorEnum }
     *     
     */
    public void setReactiveControlStrategyOperator(OperatorEnum value) {
        this.reactiveControlStrategyOperator = value;
    }

    /**
     * Gets the value of the unitOfMeasureForThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link UnitOfMeasureEnum }
     *     
     */
    public UnitOfMeasureEnum getUnitOfMeasureForThreshold() {
        return unitOfMeasureForThreshold;
    }

    /**
     * Sets the value of the unitOfMeasureForThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitOfMeasureEnum }
     *     
     */
    public void setUnitOfMeasureForThreshold(UnitOfMeasureEnum value) {
        this.unitOfMeasureForThreshold = value;
    }

    /**
     * Gets the value of the treatmentSurveillanceCapability property.
     * 
     * @return
     *     possible object is
     *     {@link TreatmentSurveillanceCapability }
     *     
     */
    public TreatmentSurveillanceCapability getTreatmentSurveillanceCapability() {
        return treatmentSurveillanceCapability;
    }

    /**
     * Sets the value of the treatmentSurveillanceCapability property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreatmentSurveillanceCapability }
     *     
     */
    public void setTreatmentSurveillanceCapability(TreatmentSurveillanceCapability value) {
        this.treatmentSurveillanceCapability = value;
    }

}
