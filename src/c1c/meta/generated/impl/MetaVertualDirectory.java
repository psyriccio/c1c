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

/**
 *
 * @author psyriccio
 */
public class MetaVertualDirectory extends MetaObjectImpl {

    private final String name;
    private String fullName;
    private final MetaObject parent;
    private final List<MetaObject> childs;

    public MetaVertualDirectory(String name, String fullName, MetaObject parent, List<MetaObject> childs) {
        this.name = name;
        try {
            this.fullName = (String) parent.getClass().getMethod("getFullName", new Class[]{}).invoke(parent, new Object[]{}) + ".[" + name + "]";
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            this.fullName = "<EMPTY>.[" + name + "]";
            Logger.getLogger(MetaVertualDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.parent = parent;
        this.childs = childs;

    }
    
    @Override
    public MetaObjectClass getObjClass() {
        return MetaObjectClass.VirtualDirectory;
    }

    @Override
    public List<MetaObject> getChildrens() {
        return childs;
    }

    @Override
    public MetaObject getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return fullName;
    }
    
}
