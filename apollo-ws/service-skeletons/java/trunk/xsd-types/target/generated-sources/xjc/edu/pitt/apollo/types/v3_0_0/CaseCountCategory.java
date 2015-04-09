//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for CaseCountCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CaseCountCategory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="categoryDefinition" type="{http://types.apollo.pitt.edu/v3_0_0/}CategoryDefinition"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *           &lt;element name="arrayAxis" type="{http://types.apollo.pitt.edu/v3_0_0/}ArrayAxis"/&gt;
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
@XmlType(name = "CaseCountCategory", propOrder = {
    "categoryDefinition",
    "count",
    "arrayAxis"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class CaseCountCategory {

    @XmlElement(required = true)
    protected CategoryDefinition categoryDefinition;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger count;
    protected ArrayAxis arrayAxis;

    /**
     * Gets the value of the categoryDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryDefinition }
     *     
     */
    public CategoryDefinition getCategoryDefinition() {
        return categoryDefinition;
    }

    /**
     * Sets the value of the categoryDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryDefinition }
     *     
     */
    public void setCategoryDefinition(CategoryDefinition value) {
        this.categoryDefinition = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCount(BigInteger value) {
        this.count = value;
    }

    /**
     * Gets the value of the arrayAxis property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayAxis }
     *     
     */
    public ArrayAxis getArrayAxis() {
        return arrayAxis;
    }

    /**
     * Sets the value of the arrayAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayAxis }
     *     
     */
    public void setArrayAxis(ArrayAxis value) {
        this.arrayAxis = value;
    }

}
