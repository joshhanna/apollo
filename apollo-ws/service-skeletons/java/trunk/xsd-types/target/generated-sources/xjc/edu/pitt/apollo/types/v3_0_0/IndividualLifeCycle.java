//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.codehaus.jackson.annotate.JsonTypeInfo;


/**
 * <p>Java class for IndividualLifeCycle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualLifeCycle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="speciesId" type="{http://types.apollo.pitt.edu/v3_0_0/}NcbiTaxonId"/&gt;
 *         &lt;element name="lifeStagesWithDurationsAndMortalities" type="{http://types.apollo.pitt.edu/v3_0_0/}LifeStageWithDurationAndMortality" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualLifeCycle", propOrder = {
    "speciesId",
    "lifeStagesWithDurationsAndMortalities"
})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.CLASS, property = "class")
public class IndividualLifeCycle {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String speciesId;
    @XmlElement(required = true)
    protected List<LifeStageWithDurationAndMortality> lifeStagesWithDurationsAndMortalities;

    /**
     * Gets the value of the speciesId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpeciesId() {
        return speciesId;
    }

    /**
     * Sets the value of the speciesId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpeciesId(String value) {
        this.speciesId = value;
    }

    /**
     * Gets the value of the lifeStagesWithDurationsAndMortalities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lifeStagesWithDurationsAndMortalities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLifeStagesWithDurationsAndMortalities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LifeStageWithDurationAndMortality }
     * 
     * 
     */
    public List<LifeStageWithDurationAndMortality> getLifeStagesWithDurationsAndMortalities() {
        if (lifeStagesWithDurationsAndMortalities == null) {
            lifeStagesWithDurationsAndMortalities = new ArrayList<LifeStageWithDurationAndMortality>();
        }
        return this.lifeStagesWithDurationsAndMortalities;
    }

}
