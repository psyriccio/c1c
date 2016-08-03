// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaObject.java

package c1c.meta.generated;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

// Referenced classes of package c1c.meta:
//            MetaObjectClass

public interface MetaObject {

    public String getID();
    
    public Optional<Conf> asConfOpt();
    public Optional<Catalog> asCatalogOpt();
    public Optional<Document> asDocumentOpt();
    public Optional<Property> asPropertyOpt();
    public Optional<TabularSection> asTabularSectionOpt();
    public Optional<TypeDescription> asTypeDescriptionOpt();
    public Optional<Type> asTypeOpt();
    
    public Conf asConf();
    public Enum asEnum();
    public Value asValue();
    public Catalog asCatalog();
    public Document asDocument();
    public Property asProperty();
    public TabularSection asTabularSection();
    public TypeDescription asTypeDescription();
    public Type asType();
    
    public MetaObjectClass getObjClass();

    public MetaObject getEMPTY(); 
    
    public List<MetaObject> getChildrens();
    public void setParent(MetaObject parent);
    public MetaObject getParent();
    
    public MetaObject getRoot();
    
    public void propagateParenthood();
    public void propagateParenthood(Consumer<Integer> percentageProgressConsumer);
    public void propagateParenthoodInternal(Consumer<Integer> percentageProgressConsumer, int count, int counter);
    
    @Override
    public String toString();
 
    public boolean isRef();
    
    public String getName();
    public String getDescription();
    public String getFullName();
    
    public HashMap<String, MetaObject> getALL();
    
    public List<MetaObject> getTypeReferences();

    public MetaComparationResult compareTo(MetaObject out);
    public MetaComparationResult compareAsConfTo(Conf out);
    public MetaComparationResult compareAsEnumTo(Enum out);
    public MetaComparationResult compareAsValueTo(Value out);
    public MetaComparationResult compareAsCatalogTo(Catalog out);
    public MetaComparationResult compareAsDocumentTo(Document out);
    public MetaComparationResult compareAsPropertyTo(Property out);
    public MetaComparationResult compareAsTabularSectionTo(TabularSection out);
    public MetaComparationResult compareAsTypeDescriptionTo(TypeDescription out);
    public MetaComparationResult compareAsTypeTo(Type out);
    
}
