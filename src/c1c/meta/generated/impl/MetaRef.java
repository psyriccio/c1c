/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta.generated.impl;

import c1c.meta.generated.MetaObjectClass;
import c1c.meta.generated.MetaObject;
import c1c.meta.generated.Catalog;
import c1c.meta.generated.Conf;
import c1c.meta.generated.Document;
import c1c.meta.generated.Enum;
import c1c.meta.generated.Property;
import c1c.meta.generated.TabularSection;
import c1c.meta.generated.Type;
import c1c.meta.generated.TypeDescription;
import c1c.meta.generated.Value;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import org.reflections.ReflectionUtils;

/**
 *
 * @author psyriccio
 */
public class MetaRef implements MetaObject {

    public class RefProcessingInfo {

        private final MetaObject obj;
        private final String methodName;
        private final String resultType;
        private Object result;

        public RefProcessingInfo(MetaObject obj, String methodName, String resultType) {
            this.obj = obj;
            this.methodName = methodName;
            this.resultType = resultType;
        }

        public String getMethodName() {
            return methodName;
        }

        public String getResultType() {
            return resultType;
        }

        public MetaObject getObj() {
            return obj;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }
        
    }

    private final String id;
    private final MetaObject destination;
    private final Function<RefProcessingInfo, RefProcessingInfo> refFunc;

    private Object refPrc(Object defResult) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[stackTrace.length - 1];
        String callerMethodName = caller.getMethodName();
        Class callerClz = this.getClass();
        String resultType = ReflectionUtils
                .getMethods(
                        callerClz,
                        (mtd) -> mtd.getName().equals(callerMethodName))
                .stream().map(
                        (mtd) -> mtd.getReturnType().getName())
                .findFirst().orElse("");
        if (refFunc != null) {
            RefProcessingInfo res = new RefProcessingInfo(destination, callerMethodName, resultType);
            res.setResult(defResult);
            return refFunc.apply(res).getResult();
        } else {
            return defResult;
        }
    }

    public MetaRef(MetaObject destination) {
        this.id = UUID.randomUUID().toString();
        this.destination = destination;
        this.refFunc = null;
    }

    public MetaRef(String id, MetaObject destination) {
        this.id = id;
        this.destination = destination;
        this.refFunc = null;
    }

    public MetaRef(MetaObject destination, Function<RefProcessingInfo, RefProcessingInfo> refFunc) {
        this.id = UUID.randomUUID().toString();
        this.destination = destination;
        this.refFunc = refFunc;
    }

    public String getRefID() {
        return id;
    }

    @Override
    public String getID() {
        return (String) refPrc(destination.getID());
    }

    @Override
    public Optional<Conf> asConfOpt() {
        return (Optional<Conf>) refPrc(destination.asConfOpt());
    }

    @Override
    public Optional<Catalog> asCatalogOpt() {
        return (Optional<Catalog>) refPrc(destination.asCatalogOpt());
    }

    @Override
    public Optional<Document> asDocumentOpt() {
        return (Optional<Document>) refPrc(destination.asDocumentOpt());
    }

    @Override
    public Optional<Property> asPropertyOpt() {
        return (Optional<Property>) refPrc(destination.asPropertyOpt());
    }

    @Override
    public Optional<TabularSection> asTabularSectionOpt() {
        return (Optional<TabularSection>) refPrc(destination.asTabularSectionOpt());
    }

    @Override
    public Optional<TypeDescription> asTypeDescriptionOpt() {
        return (Optional<TypeDescription>) refPrc(destination.asTypeDescriptionOpt());
    }

    @Override
    public Optional<Type> asTypeOpt() {
        return (Optional<Type>) refPrc(destination.asTypeOpt());
    }

    @Override
    public Conf asConf() {
        return (Conf) refPrc(destination.asConf());
    }

    @Override
    public Catalog asCatalog() {
        return (Catalog) refPrc(destination.asCatalog());
    }

    @Override
    public Document asDocument() {
        return (Document) refPrc(destination.asDocument());
    }

    @Override
    public Property asProperty() {
        return (Property) refPrc(destination.asProperty());
    }

    @Override
    public TabularSection asTabularSection() {
        return (TabularSection) refPrc(destination.asTabularSection());
    }

    @Override
    public TypeDescription asTypeDescription() {
        return (TypeDescription) refPrc(destination.asTypeDescription());
    }

    @Override
    public Type asType() {
        return (Type) refPrc(destination.asType());
    }

    @Override
    public Enum asEnum() {
        return (Enum) refPrc(destination.asEnum());
    }

    @Override
    public Value asValue() {
        return (Value) refPrc(destination.asValue());
    }
    
    @Override
    public MetaObjectClass getObjClass() {
        return (MetaObjectClass) refPrc(destination.getObjClass());
    }

    @Override
    public MetaObject getEMPTY() {
        return (MetaObject) refPrc(destination.getEMPTY());
    }

    @Override
    public List<MetaObject> getChildrens() {
        return (List<MetaObject>) refPrc(destination.getChildrens());
    }

    @Override
    public void setParent(MetaObject parent) {
        destination.setParent(parent);
    }

    @Override
    public MetaObject getParent() {
        return (MetaObject) refPrc(destination.getParent());
    }

    @Override
    public void propagateParenthood() {
        destination.propagateParenthood();
    }

    @Override
    public MetaObject getRoot() {
        return (MetaObject) refPrc(destination.getRoot());
    }

    @Override
    public String getName() {
        return (String) refPrc(destination.getName());
    }

    @Override
    public String getDescription() {
        return (String) refPrc(destination.getDescription());
    }

    @Override
    public String getFullName() {
        return (String) refPrc(destination.getFullName());
    }
    
    @Override
    public HashMap<String, MetaObject> getALL() {
        return destination.getALL();
    }

    @Override
    public List<MetaObject> getTypeReferences() {
        return (List<MetaObject>) refPrc(destination.getTypeReferences());
    }
    
    @Override
    public boolean isRef() {
        return true;
    }

}
