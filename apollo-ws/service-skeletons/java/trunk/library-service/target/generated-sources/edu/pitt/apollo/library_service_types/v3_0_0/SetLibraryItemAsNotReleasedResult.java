
package edu.pitt.apollo.library_service_types.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatus;


/**
 * <p>Java class for SetLibraryItemAsNotReleasedResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetLibraryItemAsNotReleasedResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://services-common.apollo.pitt.edu/v3_0_0/}MethodCallStatus"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetLibraryItemAsNotReleasedResult", propOrder = {
    "status",
    "version"
})
public class SetLibraryItemAsNotReleasedResult {

    @XmlElement(required = true)
    protected MethodCallStatus status;
    protected int version;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link MethodCallStatus }
     *     
     */
    public MethodCallStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodCallStatus }
     *     
     */
    public void setStatus(MethodCallStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

}
