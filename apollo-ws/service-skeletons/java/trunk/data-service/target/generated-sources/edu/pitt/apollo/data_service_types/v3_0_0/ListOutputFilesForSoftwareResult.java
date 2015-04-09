
package edu.pitt.apollo.data_service_types.v3_0_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatus;


/**
 * <p>Java class for ListOutputFilesForSoftwareResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListOutputFilesForSoftwareResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="methodCallStatus" type="{http://services-common.apollo.pitt.edu/v3_0_0/}MethodCallStatus"/>
 *         &lt;element name="outputFiles" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOutputFilesForSoftwareResult", propOrder = {
    "methodCallStatus",
    "outputFiles"
})
public class ListOutputFilesForSoftwareResult {

    @XmlElement(required = true)
    protected MethodCallStatus methodCallStatus;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected List<String> outputFiles;

    /**
     * Gets the value of the methodCallStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MethodCallStatus }
     *     
     */
    public MethodCallStatus getMethodCallStatus() {
        return methodCallStatus;
    }

    /**
     * Sets the value of the methodCallStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodCallStatus }
     *     
     */
    public void setMethodCallStatus(MethodCallStatus value) {
        this.methodCallStatus = value;
    }

    /**
     * Gets the value of the outputFiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outputFiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutputFiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOutputFiles() {
        if (outputFiles == null) {
            outputFiles = new ArrayList<String>();
        }
        return this.outputFiles;
    }

}
