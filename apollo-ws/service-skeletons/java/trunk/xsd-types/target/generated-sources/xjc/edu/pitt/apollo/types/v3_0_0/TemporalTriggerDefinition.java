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
 * <p>Java class for TemporalTriggerDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemporalTriggerDefinition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://types.apollo.pitt.edu/v3_0_0/}TriggerDefinition"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="timeScale" type="{http://types.apollo.pitt.edu/v3_0_0/}TimeScaleEnum"/&gt;
 *         &lt;element name="timeSinceTimeScaleZero" type="{http://types.apollo.pitt.edu/v3_0_0/}FixedDuration"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemporalTriggerDefinition", propOrder = {
    "timeScale",
    "timeSinceTimeScaleZero"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class TemporalTriggerDefinition
    extends TriggerDefinition
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TimeScaleEnum timeScale;
    @XmlElement(required = true)
    protected FixedDuration timeSinceTimeScaleZero;

    /**
     * Gets the value of the timeScale property.
     * 
     * @return
     *     possible object is
     *     {@link TimeScaleEnum }
     *     
     */
    public TimeScaleEnum getTimeScale() {
        return timeScale;
    }

    /**
     * Sets the value of the timeScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeScaleEnum }
     *     
     */
    public void setTimeScale(TimeScaleEnum value) {
        this.timeScale = value;
    }

    /**
     * Gets the value of the timeSinceTimeScaleZero property.
     * 
     * @return
     *     possible object is
     *     {@link FixedDuration }
     *     
     */
    public FixedDuration getTimeSinceTimeScaleZero() {
        return timeSinceTimeScaleZero;
    }

    /**
     * Sets the value of the timeSinceTimeScaleZero property.
     * 
     * @param value
     *     allowed object is
     *     {@link FixedDuration }
     *     
     */
    public void setTimeSinceTimeScaleZero(FixedDuration value) {
        this.timeSinceTimeScaleZero = value;
    }

}
