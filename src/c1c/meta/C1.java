/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta;

import c1c.meta.generated.MetaObject;
import c1c.meta.generated.Conf;
import c1c.meta.generated.impl.JAXBContextFactory;
import c1c.meta.generated.impl.MetaObjectImpl;
import c1c.meta.generated.impl.MetaVertualDirectory;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.Setter;

/**
 *
 * @author psyriccio
 */
public class C1 {

    private static @Setter Consumer<Exception> exceptionsConsumer;
    private static JAXBContext jaxbContext = null;
    private static HashMap<String, MetaObject> referencedFunctions = new HashMap<>();

    private static void inintJAXBContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContextFactory.createContext(
                    "c1c.meta.generated",
                    c1c.meta.generated.MetaObject.class.getClassLoader(),
                    new HashMap());
        }
    }

    public static <T> Stream<T> streamWith(T obj) {
        return Lists.newArrayList(obj).stream();
    }

    public static JAXBContext getJAXBContext() throws JAXBException {
        inintJAXBContext();
        return jaxbContext;
    }

    public static void marshall(MetaObject obj, File file) {
        try {
            inintJAXBContext();
            jaxbContext.createMarshaller().marshal(obj, file);
        } catch (JAXBException ex) {
            if (exceptionsConsumer != null) {
                exceptionsConsumer.accept(ex);
            }
        }
    }

    public static Optional<MetaObject> unmarshall(File file) {
        try {
            inintJAXBContext();
            return Optional.ofNullable((MetaObject) jaxbContext.createUnmarshaller().unmarshal(file));
        } catch (JAXBException ex) {
            if (exceptionsConsumer != null) {
                exceptionsConsumer.accept(ex);
            }
        }

        return Optional.ofNullable(null);

    }

    public static Optional<MetaObject> unmarshall(File file, Consumer<Integer> progressPercentageConsumer) {
        try {
            inintJAXBContext();
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setListener(new Unmarshaller.Listener() {

                private int objCount = 0;
                private int objCounter = 0;
                private int percent = 0;

                @Override
                public void beforeUnmarshal(Object target, Object parent) {
                    if (parent instanceof Conf) {
                        Conf conf = (Conf) parent;
                        if (objCount == 0) {
                            objCount = conf.getCatalogsCount().intValue() + conf.getEnumsCount().intValue() + conf.getDocumentsCount().intValue();
                        }
                        double ocr = objCounter;
                        double oc = objCount;
                        percent = (int) Math.round((ocr / oc) * 100);
                        objCounter++;
                    }
                    if (progressPercentageConsumer != null) {
                        progressPercentageConsumer.accept(percent);
                    }
                    super.beforeUnmarshal(target, parent);
                }

            });
            Optional<MetaObject> res = Optional.ofNullable((MetaObject) unmarshaller.unmarshal(file));
            unmarshaller.setListener(null);
            return res;
        } catch (JAXBException ex) {
            if (exceptionsConsumer != null) {
                exceptionsConsumer.accept(ex);
            }
            return Optional.empty();
        }
    }

    public static Optional<Conf> loadConfiguration(File file, Consumer<Integer> prcConsumer) throws JAXBException {
        Optional<MetaObject> mopt = unmarshall(file, prcConsumer);
        Optional<Conf> copt = mopt.get().asConfOpt();
        Conf conf = copt.get();
        if (conf != null) {
            registerConfiguration(conf, prcConsumer);
        }
        return Optional.ofNullable(conf);
    }

    public static Optional<Conf> loadConfiguration(File file) throws JAXBException {
        return loadConfiguration(file, null);
    }

    public static void registerConfiguration(Conf conf, Consumer<Integer> prcProgressConsumer) {
        c1c.meta.generated.impl.MetaObjectImpl.registerConfiguration(conf, null);
    }

    public static void registerConfiguration(Conf conf) {
        c1c.meta.generated.impl.MetaObjectImpl.registerConfiguration(conf, null);
    }

    public static void registerConfiguration(Optional<Conf> conf) {
        conf.ifPresent((cfg) -> registerConfiguration(cfg));
    }

    public static HashMap<String, MetaObject> getALL(Conf conf) {
        return c1c.meta.generated.impl.MetaObjectImpl.ALL.get(conf);
    }

    public static Optional<MetaObject> findObjFullName(Conf conf, String fullName) {
        return Optional.ofNullable(
                (fullName.contains("(") && fullName.contains(")"))
                ? (referencedFunctions.put(
                        fullName,
                        new MetaVertualDirectory(
                                fullName,
                                fullName,
                                conf,
                                null
                        )
                ) != MetaObjectImpl.EMPTY
                        ? referencedFunctions.getOrDefault(fullName, MetaObjectImpl.EMPTY) : MetaObjectImpl.EMPTY)
                : (conf.getALL().values().stream()
                .filter((obj) -> obj.getFullName().equals(fullName))
                .findFirst().orElseGet(() -> {
                    if (exceptionsConsumer != null) {
                        exceptionsConsumer.accept(
                                new RuntimeException("Cant find object " + fullName));
                    }
                    return null;
                }))
        );

    }

}
