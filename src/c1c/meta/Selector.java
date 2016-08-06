/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta;

import c1c.meta.generated.MetaObject;
import c1c.meta.generated.impl.MetaObjectImpl;
import c1c.meta.generated.impl.MetaVertualDirectory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author psyriccio
 */
public class Selector {

    private MetaObject base;
    private final List<MetaObject> selection;
    private final boolean virtDirCreation;

    private Selector go(String part) {
        if (part.isEmpty()) {
            rebaseSelection();
        }
        if (part.equals("*") || part.startsWith("~")) {
            if(part.equals("*")) {
                this.selection.clear();
                this.selection.addAll(base.getChildrens());
                this.base = new MetaVertualDirectory(
                        this.toString(), 
                        this.toString(), 
                        null, 
                        selection);
                return this;
            }
            if(part.startsWith("~")) {
                this.selection.clear();
                this.selection.addAll(
                        base.getChildrens().stream()
                        .filter((ch) -> ch.getName().matches(part.substring(1)))
                        .collect(Collectors.toList())
                );
                this.base = new MetaVertualDirectory(
                        this.toString(), 
                        this.toString(), 
                        null, 
                        selection);
                return this;
            }
        } else {
            base = base.getChildrens().stream()
                    .filter((ch) -> ch.getName().equals(part))
                    .findFirst().orElse(
                            virtDirCreation 
                                    ? new MetaVertualDirectory(part, base.getFullName() + "." + part, base, null) 
                                    : MetaObjectImpl.EMPTY
                    );
            rebaseSelection();
        }
        return this;
    }

    private void rebaseSelection() {
        this.selection.clear();
        this.selection.add(base);
    }

    public Selector(MetaObject base) {
        this.base = base;
        this.selection = new ArrayList<>();
        this.virtDirCreation = false;
    }

    public Selector(MetaObject base, boolean virtualDirCreationMode) {
        this.base = base;
        this.selection = new ArrayList<>();
        this.virtDirCreation = virtualDirCreationMode;
    }
    
    public List<MetaObject> getSelection() {
        return selection;
    }

    public Selector select(String expr) {
        if(expr == null || expr.isEmpty()) {
            return go("");
        }
        String[] parts = expr.split("\\.", 2);
        if (parts.length == 0) {
            return go("");
        }
        if (parts.length == 1) {
            return go(expr);
        }
        return go(parts[0]).select(parts[1]);
    }

}
