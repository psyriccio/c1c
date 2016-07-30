//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.29 at 09:40:03 PM MSK 
//


package c1c.meta.generated.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import c1c.meta.generated.Catalog;
import c1c.meta.generated.Conf;
import c1c.meta.generated.Document;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catalogs",
    "documents"
})
@XmlRootElement(name = "Conf")
public class ConfImpl
    extends MetaObjectImpl
    implements Conf
{

    @XmlElement(name = "Catalog", required = true, type = CatalogImpl.class)
    protected List<Catalog> catalogs;
    @XmlElement(name = "Document", required = true, type = DocumentImpl.class)
    protected List<Document> documents;
    @XmlAttribute(name = "Name", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String name;

    public List<Catalog> getCatalogs() {
        if (catalogs == null) {
            catalogs = new ArrayList<Catalog>();
        }
        return this.catalogs;
    }

    public List<Document> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<Document>();
        }
        return this.documents;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
    
    @Override
    public String getDescription() {
        return this.getName();
    }

}
