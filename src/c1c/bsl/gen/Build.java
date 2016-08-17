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
        Module.ModuleBuilder mod = Module.__()
                .headBlock(headDef);
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
        return Procedure.__()
                .name(name)
                .params(args)
                .export(exported)
                .type(isFunc ? Procedure.Type.Func : Procedure.Type.Proc);
    } 

    public static CodeProducer proc(String name, String[] args, boolean isFunc, boolean exported, CodeProducer... bodyParts) {
        Procedure.ProcedureBuilder prc = Procedure.__()
                .name(name)
                .params(args)
                .export(exported)
                .type(isFunc ? Procedure.Type.Func : Procedure.Type.Proc);
        for(CodeProducer part : bodyParts) {
            prc.statement(part);
        }
        return prc.__();
    } 
 
    public static ForEach.ForEachBuilder forEach(String varName, String container) {
        return ForEach.__().varName(varName).container(container);
    }
    
    public static CodeProducer forEach(String varName, String container, CodeProducer... bodyParts) {
        ForEach.ForEachBuilder fr = ForEach.__()
                .varName(varName)
                .container(container);
        for(CodeProducer part : bodyParts) {
            fr.statement(part);
        }
        return fr.__();
    }
    
    public static ForLoop.ForLoopBuilder forLoop(String varName, String from, String to) {
        return ForLoop.__().varName(varName).from(from).to(to);
    }
    
    public static CodeProducer forLoop(String varName, String from, String to, CodeProducer... bodyParts) {
        ForLoop.ForLoopBuilder frl = ForLoop.__()
                .varName(varName)
                .from(from).to(to);
        for(CodeProducer part : bodyParts) {
            frl.statement(part);
        }
        return frl.__();
    }
    
    public static IfThenElse.IfThenElseBuilder ifThenElse(String condition) {
        return IfThenElse.__().condition(condition);
    }
    
    public static CodeProducer ifThen(String condition, CodeProducer... bodyParts) {
        IfThenElse.IfThenElseBuilder ite = IfThenElse.__().condition(condition);
        for(CodeProducer part : bodyParts) {
            ite.posStatement(part);
        }
        return ite.__();
    }
    
    public static CodeProducer ifThenElse(String condition, CodeProducer posBody, CodeProducer negBody) {
        return IfThenElse.__()
                .condition(condition)
                .posStatement(posBody)
                .negStatement(negBody)
                .__();
    }
    
    public static TryCatch.TryCatchBuilder tryCatch() {
        return TryCatch.__();
    }
    
    public static CodeProducer trySuppress(CodeProducer... bodyParts) {
        TryCatch.TryCatchBuilder tsp = TryCatch.__();
        for(CodeProducer part : bodyParts) {
            tsp.statement(part);
        }
        return tsp.__();
    }
    
    public static CodeProducer tryCatch(CodeProducer body, CodeProducer catchBody) {
        return TryCatch.__()
                .statement(body)
                .catchStatement(catchBody)
                .__();
    }
 
    public static WhileLoop.WhileLoopBuilder whileLoop(String condition) {
        return WhileLoop.__().condition(condition);
    }
    
    public static CodeProducer whileLoop(String condition, CodeProducer... bodyParts) {
        WhileLoop.WhileLoopBuilder wl = WhileLoop.__().condition(condition);
        for(CodeProducer part : bodyParts) {
            wl.statement(part);
        }
        return wl.__();
    }
    
}
