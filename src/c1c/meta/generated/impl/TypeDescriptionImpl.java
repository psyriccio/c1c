//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.02 at 05:21:01 PM MSK 
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
import c1c.meta.generated.Type;
import c1c.meta.generated.TypeDescription;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "types"
})
@XmlRootElement(name = "TypeDescription")
public class TypeDescriptionImpl
    extends MetaObjectImpl
    implements TypeDescription
{

    @XmlElement(name = "Type", required = true, type = TypeImpl.class)
    protected List<Type> types;
    @XmlAttribute(name = "QDate")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String qDate;
    @XmlAttribute(name = "QDigits")
    protected BigDecimal qDigits;
    @XmlAttribute(name = "QLength")
    protected BigDecimal qLength;
    @XmlAttribute(name = "QLengthVariable")
    protected Boolean qLengthVariable;
    @XmlAttribute(name = "QNotNegative")
    protected Boolean qNotNegative;
    @XmlAttribute(name = "QPrecission")
    protected BigDecimal qPrecission;

    public List<Type> getTypes() {
        if (types == null) {
            types = new ArrayList<Type>();
        }
        return this.types;
    }

    public String getQDate() {
        return qDate;
    }

    public void setQDate(String value) {
        this.qDate = value;
    }

    public BigDecimal getQDigits() {
        return qDigits;
    }

    public void setQDigits(BigDecimal value) {
        this.qDigits = value;
    }

    public BigDecimal getQLength() {
        return qLength;
    }

    public void setQLength(BigDecimal value) {
        this.qLength = value;
    }

    public Boolean isQLengthVariable() {
        return qLengthVariable;
    }

    public void setQLengthVariable(Boolean value) {
        this.qLengthVariable = value;
    }

    public Boolean isQNotNegative() {
        return qNotNegative;
    }

    public void setQNotNegative(Boolean value) {
        this.qNotNegative = value;
    }

    public BigDecimal getQPrecission() {
        return qPrecission;
    }

    public void setQPrecission(BigDecimal value) {
        this.qPrecission = value;
    }

}
