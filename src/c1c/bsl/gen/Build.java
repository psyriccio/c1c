/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

/**
 *
 * @author psyriccio
 */
public class Build {

    public static Block.BlockBuilder block() {
        return Block.__();
    }
    
    public static CodeProducer block(String statement) {
        return Block.__().statement(statement).__();
    }
    
    public static CodeProducer block(String... statements) {
        Block.BlockBuilder bld = Block.__();
        for(String st : statements) {
            bld.statement(st);
        }
        return bld.__();
    }
    
    public static Module.ModuleBuilder module() {
        return Module.__();
    }
    
    public static CodeProducer module(CodeProducer headDef, CodeProducer staticDef, CodeProducer... members) {
        Module.ModuleBuilder mod = Module.__();
        mod.headBlock(headDef);
        mod.staticBlock(staticDef);
        for(CodeProducer memb : members) {
            mod.member(memb);
        }
        return mod.__();
    }
    
    public static Procedure.ProcedureBuilder proc() {
        return Procedure.__();
    }
    
    public static Procedure.ProcedureBuilder proc(String name, String[] args, boolean isFunc, boolean exported) {
        Procedure.ProcedureBuilder prc = Procedure.__();
        prc.name(name).params(args).export(exported).type(isFunc ? Procedure.Type.Func : Procedure.Type.Proc);
        return prc;
    } 

    public static CodeProducer proc(String name, String[] args, boolean isFunc, boolean exported, CodeProducer... bodyParts) {
        Procedure.ProcedureBuilder prc = Procedure.__();
        prc.name(name).params(args).export(exported).type(isFunc ? Procedure.Type.Func : Procedure.Type.Proc);
        for(CodeProducer part : bodyParts) {
            prc.statement(part);
        }
        return prc.__();
    } 
    
}
