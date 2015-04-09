
package edu.pitt.apollo.service.apolloservice.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.library_service_types.v3_0_0.SetReleaseVersionMessage;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="setReleaseVersionForLibraryItemMessage" type="{http://library-service-types.apollo.pitt.edu/v3_0_0/}SetReleaseVersionMessage"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "setReleaseVersionForLibraryItemMessage"
})
@XmlRootElement(name = "setReleaseVersionForLibraryItem")
public class SetReleaseVersionForLibraryItem {

    @XmlElement(required = true)
    protected SetReleaseVersionMessage setReleaseVersionForLibraryItemMessage;

    /**
     * Gets the value of the setReleaseVersionForLibraryItemMessage property.
     * 
     * @return
     *     possible object is
     *     {@link SetReleaseVersionMessage }
     *     
     */
    public SetReleaseVersionMessage getSetReleaseVersionForLibraryItemMessage() {
        return setReleaseVersionForLibraryItemMessage;
    }

    /**
     * Sets the value of the setReleaseVersionForLibraryItemMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetReleaseVersionMessage }
     *     
     */
    public void setSetReleaseVersionForLibraryItemMessage(SetReleaseVersionMessage value) {
        this.setReleaseVersionForLibraryItemMessage = value;
    }

}
