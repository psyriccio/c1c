/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.meta.generated;

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

    public MetaComparationResult(MetaObject in, MetaObject out, ComparationState state) {
        this.in = in;
        this.out = out;
        this.equals = (state == ComparationState.EQU);
        this.state = state;
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
