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
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for IndoorResidualSprayingVectorControlStrategy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndoorResidualSprayingVectorControlStrategy"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://types.apollo.pitt.edu/v3_0_0/}VectorControlStrategy"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="coverRadius" type="{http://types.apollo.pitt.edu/v3_0_0/}Distance"/&gt;
 *         &lt;element name="fractionOfAdultsAffected" type="{http://types.apollo.pitt.edu/v3_0_0/}Fraction"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndoorResidualSprayingVectorControlStrategy", propOrder = {
    "coverRadius",
    "fractionOfAdultsAffected"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class IndoorResidualSprayingVectorControlStrategy
    extends VectorControlStrategy
{

    @XmlElement(required = true)
    protected Distance coverRadius;
    protected double fractionOfAdultsAffected;

    /**
     * Gets the value of the coverRadius property.
     * 
     * @return
     *     possible object is
     *     {@link Distance }
     *     
     */
    public Distance getCoverRadius() {
        return coverRadius;
    }

    /**
     * Sets the value of the coverRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link Distance }
     *     
     */
    public void setCoverRadius(Distance value) {
        this.coverRadius = value;
    }

    /**
     * Gets the value of the fractionOfAdultsAffected property.
     * 
     */
    public double getFractionOfAdultsAffected() {
        return fractionOfAdultsAffected;
    }

    /**
     * Sets the value of the fractionOfAdultsAffected property.
     * 
     */
    public void setFractionOfAdultsAffected(double value) {
        this.fractionOfAdultsAffected = value;
    }

}
