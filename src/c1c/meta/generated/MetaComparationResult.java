/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author psyriccio
 */
public class MetaComparationResult {

    public enum ComparationState {
        EQU, DIFF_CLASS, DIFF_MAIN, DIFF_SUB
    }

    private final MetaObject in;
    private final MetaObject out;
    private final boolean equals;
    private final ComparationState state;
    private final HashMap<String, MetaComparationResult> subResults;

    public MetaComparationResult(MetaObject in, MetaObject out, ComparationState state) {
        this.in = in;
        this.out = out;
        this.equals = (state == ComparationState.EQU);
        this.state = state;
        this.subResults = new HashMap<>();
    }

    public MetaComparationResult(MetaObject in, MetaObject out, ComparationState state, HashMap<String, MetaComparationResult> subResults) {
        this.in = in;
        this.out = out;
        this.state = subResults.values().stream()
                .map((itm) -> itm.getState())
                .allMatch((st) -> st == ComparationState.EQU) ? state : ComparationState.DIFF_SUB;
        this.equals = (state == ComparationState.EQU);
        this.subResults = new HashMap<>();
        this.subResults.putAll(subResults);
    }

    public boolean isEquals() {
        return equals;
    }

    public ComparationState getState() {
        return state;
    }

    public MetaObject getIn() {
        return in;
    }

    public MetaObject getOut() {
        return out;
    }

}
