//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 05:21:12 PM EDT 
//


package edu.pitt.apollo.types.v3_0_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DevelopmentalStageEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DevelopmentalStageEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="egg"/&gt;
 *     &lt;enumeration value="larval"/&gt;
 *     &lt;enumeration value="pupal"/&gt;
 *     &lt;enumeration value="larvalAndPupal"/&gt;
 *     &lt;enumeration value="adultForm"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DevelopmentalStageEnum")
@XmlEnum
public enum DevelopmentalStageEnum {

    @XmlEnumValue("egg")
    EGG("egg"),
    @XmlEnumValue("larval")
    LARVAL("larval"),
    @XmlEnumValue("pupal")
    PUPAL("pupal"),
    @XmlEnumValue("larvalAndPupal")
    LARVAL_AND_PUPAL("larvalAndPupal"),
    @XmlEnumValue("adultForm")
    ADULT_FORM("adultForm");
    private final String value;

    DevelopmentalStageEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DevelopmentalStageEnum fromValue(String v) {
        for (DevelopmentalStageEnum c: DevelopmentalStageEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
