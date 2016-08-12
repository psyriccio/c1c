/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta.generated;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author psyriccio
 */
@AllArgsConstructor
@Builder
public class MetaComparationResult {

    public enum ComparationState {
        EQU, DIFF_CLASS, DIFF_MAIN, DIFF_SUB
    }

    private final @Getter MetaObject in;
    private final @Getter MetaObject out;
    private final @Getter boolean equals;
    private final @Getter ComparationState state;
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

}
