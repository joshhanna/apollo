//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.services_common.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRecord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="softwareIdentification" type="{http://services-common.apollo.pitt.edu/v3_0_0/}SoftwareIdentification"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRecord", propOrder = {
    "url",
    "softwareIdentification"
})
@XmlSeeAlso({
    ServiceRegistrationRecord.class
})
public class ServiceRecord {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;
    @XmlElement(required = true)
    protected SoftwareIdentification softwareIdentification;

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the softwareIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareIdentification }
     *     
     */
    public SoftwareIdentification getSoftwareIdentification() {
        return softwareIdentification;
    }

    /**
     * Sets the value of the softwareIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareIdentification }
     *     
     */
    public void setSoftwareIdentification(SoftwareIdentification value) {
        this.softwareIdentification = value;
    }

}
