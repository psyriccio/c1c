package c1c.meta.generated.impl;

import c1c.meta.C1;
import c1c.meta.Selector;
import c1c.meta.generated.Catalog;
import c1c.meta.generated.Conf;
import c1c.meta.generated.Document;
import c1c.meta.generated.Enum;
import c1c.meta.generated.MetaComparationResult;
import c1c.meta.generated.MetaObject;
import c1c.meta.generated.MetaObjectClass;
import c1c.meta.generated.Property;
import c1c.meta.generated.TabularSection;
import c1c.meta.generated.Type;
import c1c.meta.generated.TypeDescription;
import c1c.meta.generated.Value;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public class MetaObjectImpl implements MetaObject {

    public static final MetaObject EMPTY;
    public static final HashMap<String, HashMap<String, MetaObject>> ALL;
    public static final HashMap<String, MetaObject> ROOT;

    public static void registerConfiguration(Conf conf, Consumer<Integer> prcProgressConsumer) {
        HashMap<String, MetaObject> all = ALL.getOrDefault(conf.getID(), new HashMap<>());
        all.put(conf.getID(), conf);
        ALL.put(conf.getID(), all);
        ROOT.put(conf.getID(), conf);
        conf.propagateParenthood(prcProgressConsumer);
    }

    public static void unregisterConfiguration(Conf conf) {
        Optional.ofNullable(
                ALL.get(
                        conf.getID()
                )
        ).ifPresent(
                (HashMap<String, MetaObject> hmap) -> hmap.clear()
        );
        ROOT.remove(conf.getID());
    }

    static {
        EMPTY = new MetaObjectImpl(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        ALL = new HashMap<>();
        ROOT = new HashMap<>();
    }

    private final UUID id;
    private String rootID = "";
    private MetaObject parent = EMPTY;
    private final List<MetaObject> typeReferences;
    private final List<String> marks;

    public MetaObjectImpl() {
        this.id = UUID.randomUUID();
        this.typeReferences = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    public MetaObjectImpl(UUID id) {
        this.id = id;
        this.typeReferences = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    @Override
    public String getID() {
        return id.toString();
    }

    @Override
    public Optional<Conf> asConfOpt() {
        return Optional.ofNullable(this instanceof Conf ? (Conf) this : null);
    }

    @Override
    public Optional<Catalog> asCatalogOpt() {
        return Optional.ofNullable(this instanceof Catalog ? (Catalog) this : null);
    }

    @Override
    public Optional<Document> asDocumentOpt() {
        return Optional.ofNullable(this instanceof Document ? (Document) this : null);
    }

    @Override
    public Optional<Property> asPropertyOpt() {
        return Optional.ofNullable(this instanceof Property ? (Property) this : null);
    }

    @Override
    public Optional<TabularSection> asTabularSectionOpt() {
        return Optional.ofNullable(this instanceof TabularSection ? (TabularSection) this : null);
    }

    @Override
    public Optional<Type> asTypeOpt() {
        return Optional.ofNullable(this instanceof Type ? (Type) this : null);
    }

    @Override
    public Optional<TypeDescription> asTypeDescriptionOpt() {
        return Optional.ofNullable(this instanceof TypeDescription ? (TypeDescription) this : null);
    }

    @Override
    public MetaObjectClass getObjClass() {
        return (this instanceof Conf) ? MetaObjectClass.Conf
                : (this instanceof Enum) ? MetaObjectClass.Enum
                        : (this instanceof Value) ? MetaObjectClass.EnumValue
                                : (this instanceof Property) ? MetaObjectClass.Property
                                        : (this instanceof Catalog) ? MetaObjectClass.Catalog
                                                : (this instanceof Document) ? MetaObjectClass.Document
                                                        : (this instanceof Type) ? MetaObjectClass.Type
                                                                : (this instanceof TypeDescription) ? MetaObjectClass.TypeDescription
                                                                        : (this instanceof TabularSection) ? MetaObjectClass.TabularSection
                                                                                : (this instanceof Catalog) ? MetaObjectClass.Catalog : null;
    }

    @Override
    public Conf asConf() {
        return (Conf) this;
    }

    public Enum asEnum() {
        return (Enum) this;
    }

    public Value asValue() {
        return (Value) this;
    }

    @Override
    public Catalog asCatalog() {
        return (Catalog) this;
    }

    @Override
    public Document asDocument() {
        return (Document) this;
    }

    @Override
    public Property asProperty() {
        return (Property) this;
    }

    @Override
    public TabularSection asTabularSection() {
        return (TabularSection) this;
    }

    @Override
    public Type asType() {
        return (Type) this;
    }

    @Override
    public TypeDescription asTypeDescription() {
        return (TypeDescription) this;
    }

    @Override
    public MetaObject getEMPTY() {
        return EMPTY;
    }

    @Override
    public List<MetaObject> getChildrens() {
        return (this.getObjClass() == MetaObjectClass.Conf ? Lists.newArrayList(this.asConf().getCatalogs(), this.asConf().getDocuments())
                : this.getObjClass() == MetaObjectClass.Enum ? Lists.newArrayList(this.asEnum().getValues())
                        : this.getObjClass() == MetaObjectClass.Catalog ? Lists.newArrayList(this.asCatalog().getProperties(), this.asCatalog().getTabularSections())
                                : this.getObjClass() == MetaObjectClass.Document ? Lists.newArrayList(this.asDocument().getProperties(), this.asDocument().getTabularSections())
                                        : this.getObjClass() == MetaObjectClass.TabularSection ? Lists.newArrayList(this.asTabularSection().getProperties())
                                                : this.getObjClass() == MetaObjectClass.Property ? Lists.newArrayList(Lists.newArrayList(this.asProperty().getTypeDescription()))
                                                        : this.getObjClass() == MetaObjectClass.TypeDescription ? Lists.newArrayList(this.asTypeDescription().getTypes())
                                                                : this.getObjClass() == MetaObjectClass.Type ? Lists.newArrayList(new ArrayList<MetaObject>())
                                                                        : Lists.newArrayList(new ArrayList<MetaObject>()))
                .stream().flatMap((lst) -> lst instanceof List ? ((List<MetaObject>) lst).stream() : lst instanceof MetaObject ? Stream.of((MetaObject) lst) : null)
                .peek((MetaObject obj) -> obj.setParent(this))
                .collect(Collectors.toList());
    }

    @Override
    public void setParent(MetaObject parent) {
        this.parent = parent;
    }

    @Override
    public MetaObject getParent() {
        return parent;
    }

    @Override
    public void propagateParenthood() {
        propagateParenthood(null);
    }

    public void propagateParenthoodInternal(Consumer<Integer> prcProgressConsumer, int count, int counter) {
        if(this.getObjClass() == MetaObjectClass.Catalog) {
            Property propName = new PropertyImpl();
            propName.setFullName(this.getFullName() + ".Наименование");
            propName.setDescription("Наименование");
            propName.setName("Наименование");
            propName.setParent(this);
            TypeDescription typeDescrName = new TypeDescriptionImpl();
            typeDescrName.setQLength(this.asCatalog().getDescriptionLength());
            Type typeName = new TypeImpl();
            typeName.setName("Строка");
            typeDescrName.getTypes().add(typeName);
            propName.setTypeDescription(typeDescrName);
            this.asCatalog().getProperties().add(propName);
            //
            Property propCode = new PropertyImpl();
            propCode.setFullName(this.getFullName() + ".Код");
            propCode.setDescription("Код");
            propCode.setName("Код");
            propCode.setParent(this);
            TypeDescription typeDescrCode = new TypeDescriptionImpl();
            typeDescrCode.setQLength(this.asCatalog().getCodeLength());
            Type typeCode = new TypeImpl();
            typeCode.setName(this.asCatalog().getCodeType());
            typeDescrCode.getTypes().add(typeCode);
            propCode.setTypeDescription(typeDescrCode);
            this.asCatalog().getProperties().add(propCode);
            if(this.asCatalog().isHierarchical()) {
                Property propParent = new PropertyImpl();
                propParent.setFullName(this.getFullName() + ".Родитель");
                propParent.setDescription("Родитель");
                propParent.setName("Родитель");
                propParent.setParent(this);
                TypeDescription typeDescrParent = new TypeDescriptionImpl();
                Type typeParent = new TypeImpl();
                typeParent.setName(this.asCatalog().getFullName());
                typeDescrParent.getTypes().add(typeParent);
                propParent.setTypeDescription(typeDescrParent);
                this.asCatalog().getProperties().add(propParent);
            }
        }
        HashMap<String, MetaObject> hm = ALL.getOrDefault(getRoot().getID(), new HashMap<>());
        hm.put(getID(), this);
        ALL.put(getRoot().getID(), hm);
        getChildrens().stream().forEach((MetaObject child) -> {
            if (child instanceof Type) {
                Type tp = (Type) child;
                MetaObject typeRef = C1.findObjFullName(getRoot().asConf(), tp.getFullName()).orElse(getEMPTY());
                if (typeRef != getEMPTY()) {
                    MetaObject current = child;
                    while (current != getEMPTY()) {
                        current.getTypeReferences().add(typeRef);
                        current = current.getParent();
                    }
                }
            }
            if (prcProgressConsumer != null) {
                double cntr = counter;
                double cnt = count;
                prcProgressConsumer.accept((int) Math.round((cntr / cnt) * 100));
            }
            child.propagateParenthoodInternal(prcProgressConsumer, count, counter + 1);
        });
    }

    @Override
    public void propagateParenthood(Consumer<Integer> prcProgressConsumer) {
        propagateParenthoodInternal(prcProgressConsumer, ALL.size(), 0);
    }

    @Override
    public MetaObject getRoot() {
        MetaObject _root = this;
        if (rootID.isEmpty()) {
            MetaObject cand = getParent();
            while (cand != EMPTY) {
                _root = cand;
                cand = cand.getParent();
            }
            rootID = _root.getID();
        } else {
            _root = ROOT.get(rootID);
        }
        return _root;
    }

    @Override
    public String toString() {
        return this.getObjClass() == MetaObjectClass.Conf ? this.asConf().getName() + " (Конфигурация)"
                : this.getObjClass() == MetaObjectClass.Enum ? this.asEnum().getName() + " (Перечисление)"
                        : this.getObjClass() == MetaObjectClass.EnumValue ? this.asValue().getName() + " (Значение)"
                                : this.getObjClass() == MetaObjectClass.Catalog ? this.asCatalog().getName() + " (Справочник)"
                                        : this.getObjClass() == MetaObjectClass.Document ? this.asDocument().getName() + " (Документ)"
                                                : this.getObjClass() == MetaObjectClass.Property ? this.asProperty().getName() + " (Свойство)"
                                                        : this.getObjClass() == MetaObjectClass.TabularSection ? this.asTabularSection().getName() + " (Табличная часть)"
                                                                : this.getObjClass() == MetaObjectClass.TypeDescription ? this.asTypeDescription().getID() + " (Описание типов)"
                                                                        : this.getObjClass() == MetaObjectClass.Type ? this.asType().getName() + " (Тип)"
                                                                                : this.getObjClass() == MetaObjectClass.VirtualDirectory ? "[" + ((MetaVertualDirectory) this).getName() + "]"
                                                                                        : this.getID() + " (?)";
    }

    @Override
    public boolean isRef() {
        return (this.getClass().equals(MetaRef.class));
    }

    @Override
    public String getName() {
        return "<EMPTY>";
    }

    @Override
    public HashMap<String, MetaObject> getALL() {
        return MetaObjectImpl.ALL.get(getRoot().getID());
    }

    @Override
    public String getFullName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "<ABSTRACT>";
    }

    @Override
    public List<MetaObject> getTypeReferences() {
        return this.typeReferences;
    }

    @Override
    public MetaComparationResult compareTo(MetaObject out) {
        if (this.getObjClass() != out.getObjClass()) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_CLASS);
        }

        MetaComparationResult result = null;

        switch (this.getObjClass()) {
            case Conf:
                result = this.compareAsConfTo(out.asConf());
                break;
            case Enum:
                result = this.compareAsEnumTo(out.asEnum());
                break;
            case EnumValue:
                result = this.compareAsValueTo(out.asValue());
                break;
            case Catalog:
                result = this.compareAsCatalogTo(out.asCatalog());
                break;
            case Document:
                result = this.compareAsDocumentTo(out.asDocument());
                break;
            case Property:
                result = this.compareAsPropertyTo(out.asProperty());
                break;
            case TabularSection:
                result = this.compareAsTabularSectionTo(out.asTabularSection());
                break;
            case TypeDescription:
                result = this.compareAsTypeDescriptionTo(out.asTypeDescription());
                break;
            case Type:
                result = this.compareAsTypeTo(out.asType());
                break;
            default:
                result = null;
        }

        return result;
    }

    @Override
    public MetaComparationResult compareAsConfTo(Conf out) {
        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
    }

    @Override
    public MetaComparationResult compareAsEnumTo(Enum out) {
        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
    }

    @Override
    public MetaComparationResult compareAsValueTo(Value out) {
        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
    }

    @Override
    public MetaComparationResult compareAsCatalogTo(Catalog out) {
        if (!this.asCatalog().getCodeLength().equals(out.getCodeLength())
                || !this.asCatalog().getCodeType().equals(out.getCodeType())
                || !this.asCatalog().getLevelCount().equals(out.getLevelCount())) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        } else {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
        }
    }

    @Override
    public MetaComparationResult compareAsDocumentTo(Document out) {
        if (!this.asDocument().getCodeLength().equals(out.getCodeLength())
                || !this.asDocument().getCodeType().equals(out.getCodeType())) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        } else {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
        }
    }

    @Override
    public MetaComparationResult compareAsPropertyTo(Property out) {
        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
    }

    @Override
    public MetaComparationResult compareAsTabularSectionTo(TabularSection out) {
        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);
    }

    @Override
    public MetaComparationResult compareAsTypeDescriptionTo(TypeDescription out) {
        HashMap<String, Type> inTypes = new HashMap<>();
        HashMap<String, Type> outTypes = new HashMap<>();
        this.asTypeDescription().getTypes().forEach((tp) -> inTypes.put(tp.getName(), tp));
        out.getTypes().forEach((tp) -> outTypes.put(tp.getName(), tp));

        if (inTypes.size() != outTypes.size()) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        }

        if (!inTypes.keySet().containsAll(outTypes.keySet())
                || !outTypes.keySet().containsAll(inTypes.keySet())) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        }

        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);

    }

    @Override
    public MetaComparationResult compareAsTypeTo(Type out) {

        if (!this.asType().getName().equals(out.getName())) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        }

        if (this.asType().getTypeReferences().size() != out.getTypeReferences().size()) {
            return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.DIFF_MAIN);
        }

        return new MetaComparationResult(this, out, MetaComparationResult.ComparationState.EQU);

    }

    @Override
    public void mark(String mark) {
        this.marks.add(mark);
    }

    @Override
    public void unmark(String mark) {
        this.marks.remove(mark);
    }

    @Override
    public boolean isMarkedBy(String mark) {
        return this.marks.contains(mark);
    }

    @Override
    public Selector select(String expr) {
        return new Selector(this).select(expr);
    }

    @Override
    public Selector selectVD(String expr) {
        return new Selector(this, true).select(expr);
    }
    
}
