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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SoftwareLicenseIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SoftwareLicenseIdentification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="licenseLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="licenseVersion" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="licenseName" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="attributionNotice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoftwareLicenseIdentification", propOrder = {
    "licenseLocation",
    "licenseVersion",
    "licenseName",
    "attributionNotice"
})
public class SoftwareLicenseIdentification {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String licenseLocation;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String licenseVersion;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String licenseName;
    @XmlElement(required = true)
    protected String attributionNotice;

    /**
     * Gets the value of the licenseLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseLocation() {
        return licenseLocation;
    }

    /**
     * Sets the value of the licenseLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseLocation(String value) {
        this.licenseLocation = value;
    }

    /**
     * Gets the value of the licenseVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseVersion() {
        return licenseVersion;
    }

    /**
     * Sets the value of the licenseVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseVersion(String value) {
        this.licenseVersion = value;
    }

    /**
     * Gets the value of the licenseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseName() {
        return licenseName;
    }

    /**
     * Sets the value of the licenseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseName(String value) {
        this.licenseName = value;
    }

    /**
     * Gets the value of the attributionNotice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributionNotice() {
        return attributionNotice;
    }

    /**
     * Sets the value of the attributionNotice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributionNotice(String value) {
        this.attributionNotice = value;
    }

}
