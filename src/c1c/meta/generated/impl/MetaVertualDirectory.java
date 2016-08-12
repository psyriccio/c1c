/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta.generated.impl;

import c1c.meta.generated.MetaObject;
import c1c.meta.generated.MetaObjectClass;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author psyriccio
 */
public class MetaVertualDirectory extends MetaObjectImpl {

    @Getter private final String name;
    @Getter private String description;
    
    @Getter private final MetaObject parent;
    @Getter private final List<MetaObject> childrens;

    public MetaVertualDirectory(String name, String fullName, MetaObject parent, List<MetaObject> childs) {
        this.name = name;
        if(parent != null) {
            try {
                this.description = (String) parent.getClass().getMethod("getFullName", new Class[]{}).invoke(parent, new Object[]{}) + ".[" + name + "]";
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                this.description = "<EMPTY>.[" + name + "]";
                Logger.getLogger(MetaVertualDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.description = fullName;
        }
        this.parent = parent;
        this.childrens = childs;

    }
    
    @Override
    public MetaObjectClass getObjClass() {
        return MetaObjectClass.VirtualDirectory;
    }
    
}
