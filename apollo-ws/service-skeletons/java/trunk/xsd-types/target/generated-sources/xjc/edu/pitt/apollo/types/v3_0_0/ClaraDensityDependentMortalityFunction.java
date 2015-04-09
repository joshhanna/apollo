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
 * <p>Java class for ClaraDensityDependentMortalityFunction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClaraDensityDependentMortalityFunction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://types.apollo.pitt.edu/v3_0_0/}MortalityFunction"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="omega" type="{http://types.apollo.pitt.edu/v3_0_0/}NonNegativeDouble"/&gt;
 *         &lt;element name="sigma" type="{http://types.apollo.pitt.edu/v3_0_0/}NonNegativeDouble"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaraDensityDependentMortalityFunction", propOrder = {
    "description",
    "omega",
    "sigma"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class ClaraDensityDependentMortalityFunction
    extends MortalityFunction
{

    protected String description;
    protected double omega;
    protected double sigma;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the omega property.
     * 
     */
    public double getOmega() {
        return omega;
    }

    /**
     * Sets the value of the omega property.
     * 
     */
    public void setOmega(double value) {
        this.omega = value;
    }

    /**
     * Gets the value of the sigma property.
     * 
     */
    public double getSigma() {
        return sigma;
    }

    /**
     * Sets the value of the sigma property.
     * 
     */
    public void setSigma(double value) {
        this.sigma = value;
    }

}
