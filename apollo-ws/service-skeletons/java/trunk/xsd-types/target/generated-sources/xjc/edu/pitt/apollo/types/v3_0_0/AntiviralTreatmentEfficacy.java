//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for AntiviralTreatmentEfficacy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AntiviralTreatmentEfficacy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://types.apollo.pitt.edu/v3_0_0/}TreatmentEfficacy"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="efficacy" type="{http://types.apollo.pitt.edu/v3_0_0/}Probability"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AntiviralTreatmentEfficacy", propOrder = {
    "efficacy"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class AntiviralTreatmentEfficacy
    extends TreatmentEfficacy
{

    protected double efficacy;

    /**
     * Gets the value of the efficacy property.
     * 
     */
    public double getEfficacy() {
        return efficacy;
    }

    /**
     * Sets the value of the efficacy property.
     * 
     */
    public void setEfficacy(double value) {
        this.efficacy = value;
    }

}
