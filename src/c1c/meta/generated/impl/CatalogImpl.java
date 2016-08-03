//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.03 at 10:13:24 AM MSK 
//


package c1c.meta.generated.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import c1c.meta.generated.Catalog;
import c1c.meta.generated.Property;
import c1c.meta.generated.TabularSection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "properties",
    "tabularSections"
})
@XmlRootElement(name = "Catalog")
public class CatalogImpl
    extends MetaObjectImpl
    implements Catalog
{

    @XmlElement(name = "Property", type = PropertyImpl.class)
    protected List<Property> properties;
    @XmlElement(name = "TabularSection", type = TabularSectionImpl.class)
    protected List<TabularSection> tabularSections;
    @XmlAttribute(name = "CodeLength", required = true)
    protected BigDecimal codeLength;
    @XmlAttribute(name = "CodeType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String codeType;
    @XmlAttribute(name = "Description", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String description;
    @XmlAttribute(name = "DescriptionLength", required = true)
    protected BigDecimal descriptionLength;
    @XmlAttribute(name = "FullName", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String fullName;
    @XmlAttribute(name = "Hierarchical", required = true)
    protected boolean hierarchical;
    @XmlAttribute(name = "LevelCount", required = true)
    protected BigDecimal levelCount;
    @XmlAttribute(name = "ListDescription", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String listDescription;
    @XmlAttribute(name = "Name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute(name = "ObjectDescription", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String objectDescription;
    @XmlAttribute(name = "Synonym", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String synonym;

    public List<Property> getProperties() {
        if (properties == null) {
            properties = new ArrayList<Property>();
        }
        return this.properties;
    }

    public List<TabularSection> getTabularSections() {
        if (tabularSections == null) {
            tabularSections = new ArrayList<TabularSection>();
        }
        return this.tabularSections;
    }

    public BigDecimal getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(BigDecimal value) {
        this.codeLength = value;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String value) {
        this.codeType = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public BigDecimal getDescriptionLength() {
        return descriptionLength;
    }

    public void setDescriptionLength(BigDecimal value) {
        this.descriptionLength = value;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String value) {
        this.fullName = value;
    }

    public boolean isHierarchical() {
        return hierarchical;
    }

    public void setHierarchical(boolean value) {
        this.hierarchical = value;
    }

    public BigDecimal getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(BigDecimal value) {
        this.levelCount = value;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String value) {
        this.listDescription = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String value) {
        this.objectDescription = value;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String value) {
        this.synonym = value;
    }

}
