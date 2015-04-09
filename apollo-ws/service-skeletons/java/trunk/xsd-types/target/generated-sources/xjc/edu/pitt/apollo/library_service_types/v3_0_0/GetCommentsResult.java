//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.library_service_types.v3_0_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatus;


/**
 * <p>Java class for GetCommentsResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCommentsResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://services-common.apollo.pitt.edu/v3_0_0/}MethodCallStatus"/&gt;
 *         &lt;element name="commitComment" type="{http://library-service-types.apollo.pitt.edu/v3_0_0/}CommentFromLibrary"/&gt;
 *         &lt;element name="reviewerComments" type="{http://library-service-types.apollo.pitt.edu/v3_0_0/}CommentFromLibrary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="releaseVersionComments" type="{http://library-service-types.apollo.pitt.edu/v3_0_0/}CommentFromLibrary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCommentsResult", propOrder = {
    "status",
    "commitComment",
    "reviewerComments",
    "releaseVersionComments"
})
public class GetCommentsResult {

    @XmlElement(required = true)
    protected MethodCallStatus status;
    @XmlElement(required = true)
    protected CommentFromLibrary commitComment;
    protected List<CommentFromLibrary> reviewerComments;
    protected List<CommentFromLibrary> releaseVersionComments;

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
     * Gets the value of the commitComment property.
     * 
     * @return
     *     possible object is
     *     {@link CommentFromLibrary }
     *     
     */
    public CommentFromLibrary getCommitComment() {
        return commitComment;
    }

    /**
     * Sets the value of the commitComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentFromLibrary }
     *     
     */
    public void setCommitComment(CommentFromLibrary value) {
        this.commitComment = value;
    }

    /**
     * Gets the value of the reviewerComments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reviewerComments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReviewerComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentFromLibrary }
     * 
     * 
     */
    public List<CommentFromLibrary> getReviewerComments() {
        if (reviewerComments == null) {
            reviewerComments = new ArrayList<CommentFromLibrary>();
        }
        return this.reviewerComments;
    }

    /**
     * Gets the value of the releaseVersionComments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the releaseVersionComments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReleaseVersionComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentFromLibrary }
     * 
     * 
     */
    public List<CommentFromLibrary> getReleaseVersionComments() {
        if (releaseVersionComments == null) {
            releaseVersionComments = new ArrayList<CommentFromLibrary>();
        }
        return this.releaseVersionComments;
    }

}
