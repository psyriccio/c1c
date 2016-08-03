package c1c.meta.generated.impl;

import c1c.meta.generated.Catalog;
import c1c.meta.generated.Conf;
import c1c.meta.generated.Document;
import c1c.meta.generated.Enum;
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

    public MetaObjectImpl() {
        this.id = UUID.randomUUID();
        this.typeReferences = new ArrayList<>();
    }

    public MetaObjectImpl(UUID id) {
        this.id = id;
        this.typeReferences = new ArrayList<>();
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
        HashMap<String, MetaObject> hm = ALL.getOrDefault(getRoot().getID(), new HashMap<>());
        hm.put(getID(), this);
        ALL.put(getRoot().getID(), hm);
        getChildrens().stream().forEach((MetaObject child) -> {
            if(prcProgressConsumer != null) {
                double cntr = counter;
                double cnt = count;
                prcProgressConsumer.accept((int) Math.round((cntr/cnt)*100));
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

}
