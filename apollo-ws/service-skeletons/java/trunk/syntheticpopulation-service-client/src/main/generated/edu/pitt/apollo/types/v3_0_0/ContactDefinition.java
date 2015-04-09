
package edu.pitt.apollo.types.v3_0_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ContactDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContactDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apolloLabel" type="{http://types.apollo.pitt.edu/v3_0_0/}ContactDefinitionEnum"/>
 *         &lt;element name="investigationLabel" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="investigationDefinition" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="referenceId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactDefinition", propOrder = {
    "apolloLabel",
    "investigationLabel",
    "investigationDefinition",
    "referenceId"
})
public class ContactDefinition {

    @XmlElement(required = true)
    protected ContactDefinitionEnum apolloLabel;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String investigationLabel;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String investigationDefinition;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger referenceId;

    /**
     * Gets the value of the apolloLabel property.
     * 
     * @return
     *     possible object is
     *     {@link ContactDefinitionEnum }
     *     
     */
    public ContactDefinitionEnum getApolloLabel() {
        return apolloLabel;
    }

    /**
     * Sets the value of the apolloLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactDefinitionEnum }
     *     
     */
    public void setApolloLabel(ContactDefinitionEnum value) {
        this.apolloLabel = value;
    }

    /**
     * Gets the value of the investigationLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestigationLabel() {
        return investigationLabel;
    }

    /**
     * Sets the value of the investigationLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestigationLabel(String value) {
        this.investigationLabel = value;
    }

    /**
     * Gets the value of the investigationDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestigationDefinition() {
        return investigationDefinition;
    }

    /**
     * Sets the value of the investigationDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestigationDefinition(String value) {
        this.investigationDefinition = value;
    }

    /**
     * Gets the value of the referenceId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the value of the referenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReferenceId(BigInteger value) {
        this.referenceId = value;
    }

}