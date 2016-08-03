/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta;

import c1c.meta.generated.MetaObject;
import c1c.meta.generated.Conf;
import c1c.meta.generated.impl.JAXBContextFactory;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author psyriccio
 */
public class C1 {

    private static Consumer<Exception> exceptionsConsumer;
    private static JAXBContext jaxbContext = null;
    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;

    private static void inintJAXBContext() throws JAXBException {
        if (jaxbContext == null) {
            jaxbContext = JAXBContextFactory.createContext(
                    "c1c.meta.generated",
                    c1c.meta.generated.MetaObject.class.getClassLoader(),
                    new HashMap());
        }
        marshaller = jaxbContext.createMarshaller();
        unmarshaller = jaxbContext.createUnmarshaller();
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
            if (marshaller == null) {
                inintJAXBContext();
            }
            marshaller.marshal(obj, file);
        } catch (JAXBException ex) {
            if (exceptionsConsumer != null) {
                exceptionsConsumer.accept(ex);
            }
        }
    }

    public static Optional<MetaObject> unmarshall(File file) {
        return unmarshall(file, null);
    }

    public static Optional<MetaObject> unmarshall(File file, Consumer<Integer> progressPercentageConsumer) {
        try {
            if (unmarshaller == null) {
                inintJAXBContext();
            }
            unmarshaller.setListener(new Unmarshaller.Listener() {

                private int objCount = 0;
                private int objCounter = 0;
                private int percent = 0;
                
                @Override
                public void beforeUnmarshal(Object target, Object parent) {
                    if(parent instanceof Conf) {
                        Conf conf = (Conf) parent;
                        if(objCount == 0) objCount = conf.getCatalogsCount().intValue() + conf.getEnumsCount().intValue() + conf.getDocumentsCount().intValue();
                        double ocr = objCounter;
                        double oc = objCount;
                        percent = (int) Math.round((ocr / oc) * 100);
                        objCounter++;
                    }
                    if(progressPercentageConsumer != null) progressPercentageConsumer.accept(percent);
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
    
    
    public static Optional<Conf> loadConfiguration(File file) throws JAXBException {
        Optional<MetaObject> mopt = unmarshall(file);
        Optional<Conf> copt = mopt.get().asConfOpt();
        Conf conf = copt.get();
        if (conf != null) {
            registerConfiguration(conf);
        }
        return Optional.ofNullable(conf);
    }

    public static void registerConfiguration(Conf conf) {
        c1c.meta.generated.impl.MetaObjectImpl.registerConfiguration(conf);
    }

    public static void registerConfiguration(Optional<Conf> conf) {
        conf.ifPresent((cfg) -> registerConfiguration(cfg));
    }

    public static HashMap<String, MetaObject> getALL(Conf conf) {
        return c1c.meta.generated.impl.MetaObjectImpl.ALL.get(conf);
    }

    public static Optional<MetaObject> findObjFullName(Conf conf, String fullName) {
        return Optional.ofNullable(
                conf.getALL().values().stream()
                .filter((obj) -> obj.getFullName().equals(fullName))
                .findFirst().orElseGet(() -> {
                    if (exceptionsConsumer != null) {
                        exceptionsConsumer.accept(
                                new RuntimeException("Cant find object " + fullName));
                    }
                    return null;
                })
        );

    }

    public static void setExceptionsConsumer(Consumer<Exception> exceptionsConsumer) {
        C1.exceptionsConsumer = exceptionsConsumer;
    }

}
