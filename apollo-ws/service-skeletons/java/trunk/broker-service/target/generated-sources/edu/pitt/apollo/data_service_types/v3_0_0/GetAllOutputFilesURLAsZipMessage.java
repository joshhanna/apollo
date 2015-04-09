
package edu.pitt.apollo.data_service_types.v3_0_0;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import edu.pitt.apollo.services_common.v3_0_0.Authentication;
import edu.pitt.apollo.services_common.v3_0_0.SoftwareIdentification;


/**
 * <p>Java class for GetAllOutputFilesURLAsZipMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllOutputFilesURLAsZipMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authentication" type="{http://services-common.apollo.pitt.edu/v3_0_0/}Authentication"/>
 *         &lt;element name="softwareIdentification" type="{http://services-common.apollo.pitt.edu/v3_0_0/}SoftwareIdentification"/>
 *         &lt;element name="runId" type="{http://services-common.apollo.pitt.edu/v3_0_0/}RunIdentification"/>
 *         &lt;element name="fileNames" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllOutputFilesURLAsZipMessage", propOrder = {
    "authentication",
    "softwareIdentification",
    "runId",
    "fileNames"
})
public class GetAllOutputFilesURLAsZipMessage {

    @XmlElement(required = true)
    protected Authentication authentication;
    @XmlElement(required = true)
    protected SoftwareIdentification softwareIdentification;
    @XmlElement(required = true)
    protected BigInteger runId;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected List<String> fileNames;

    /**
     * Gets the value of the authentication property.
     * 
     * @return
     *     possible object is
     *     {@link Authentication }
     *     
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Authentication }
     *     
     */
    public void setAuthentication(Authentication value) {
        this.authentication = value;
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

    /**
     * Gets the value of the runId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRunId() {
        return runId;
    }

    /**
     * Sets the value of the runId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRunId(BigInteger value) {
        this.runId = value;
    }

    /**
     * Gets the value of the fileNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFileNames() {
        if (fileNames == null) {
            fileNames = new ArrayList<String>();
        }
        return this.fileNames;
    }

}
