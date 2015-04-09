
package edu.pitt.apollo.service.apolloservice.v3_0_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.library_service_types.v3_0_0.GetCommentsResult;


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
 *         &lt;element name="getCommentsForLibraryItemResult" type="{http://library-service-types.apollo.pitt.edu/v3_0_0/}GetCommentsResult"/>
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
    "getCommentsForLibraryItemResult"
})
@XmlRootElement(name = "getCommentsForLibraryItemResponse")
public class GetCommentsForLibraryItemResponse {

    @XmlElement(required = true)
    protected GetCommentsResult getCommentsForLibraryItemResult;

    /**
     * Gets the value of the getCommentsForLibraryItemResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetCommentsResult }
     *     
     */
    public GetCommentsResult getGetCommentsForLibraryItemResult() {
        return getCommentsForLibraryItemResult;
    }

    /**
     * Sets the value of the getCommentsForLibraryItemResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCommentsResult }
     *     
     */
    public void setGetCommentsForLibraryItemResult(GetCommentsResult value) {
        this.getCommentsForLibraryItemResult = value;
    }

}
