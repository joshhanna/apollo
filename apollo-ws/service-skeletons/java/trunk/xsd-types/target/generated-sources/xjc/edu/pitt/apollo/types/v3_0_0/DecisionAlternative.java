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
 * <p>Java class for DecisionAlternative complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DecisionAlternative"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="singleStrategy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="combinationStrategy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SequentialCombinationStrategy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DecisionAlternative", propOrder = {
    "singleStrategy",
    "combinationStrategy",
    "sequentialCombinationStrategy"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class DecisionAlternative {

    @XmlElement(required = true)
    protected String singleStrategy;
    @XmlElement(required = true)
    protected String combinationStrategy;
    @XmlElement(name = "SequentialCombinationStrategy", required = true)
    protected String sequentialCombinationStrategy;

    /**
     * Gets the value of the singleStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleStrategy() {
        return singleStrategy;
    }

    /**
     * Sets the value of the singleStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleStrategy(String value) {
        this.singleStrategy = value;
    }

    /**
     * Gets the value of the combinationStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombinationStrategy() {
        return combinationStrategy;
    }

    /**
     * Sets the value of the combinationStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombinationStrategy(String value) {
        this.combinationStrategy = value;
    }

    /**
     * Gets the value of the sequentialCombinationStrategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequentialCombinationStrategy() {
        return sequentialCombinationStrategy;
    }

    /**
     * Sets the value of the sequentialCombinationStrategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequentialCombinationStrategy(String value) {
        this.sequentialCombinationStrategy = value;
    }

}
