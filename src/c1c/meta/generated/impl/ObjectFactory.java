//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.02 at 05:21:01 PM MSK 
//


package c1c.meta.generated.impl;

import javax.xml.bind.annotation.XmlRegistry;
import c1c.meta.generated.Catalog;
import c1c.meta.generated.Conf;
import c1c.meta.generated.Document;
import c1c.meta.generated.Enum;
import c1c.meta.generated.Property;
import c1c.meta.generated.TabularSection;
import c1c.meta.generated.Type;
import c1c.meta.generated.TypeDescription;
import c1c.meta.generated.Value;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the c1c.meta.generated.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: c1c.meta.generated.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Enum }
     * 
     */
    public EnumImpl createEnum() {
        return new EnumImpl();
    }

    /**
     * Create an instance of {@link Value }
     * 
     */
    public ValueImpl createValue() {
        return new ValueImpl();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public TypeImpl createType() {
        return new TypeImpl();
    }

    /**
     * Create an instance of {@link TabularSection }
     * 
     */
    public TabularSectionImpl createTabularSection() {
        return new TabularSectionImpl();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public PropertyImpl createProperty() {
        return new PropertyImpl();
    }

    /**
     * Create an instance of {@link TypeDescription }
     * 
     */
    public TypeDescriptionImpl createTypeDescription() {
        return new TypeDescriptionImpl();
    }

    /**
     * Create an instance of {@link Conf }
     * 
     */
    public ConfImpl createConf() {
        return new ConfImpl();
    }

    /**
     * Create an instance of {@link Catalog }
     * 
     */
    public CatalogImpl createCatalog() {
        return new CatalogImpl();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public DocumentImpl createDocument() {
        return new DocumentImpl();
    }

}
