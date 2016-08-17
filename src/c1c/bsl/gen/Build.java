/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Map;

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
        for (String st : statements) {
            bld.statement(st);
        }
        return bld.__();
    }

    public static Module.ModuleBuilder module() {
        return Module.__();
    }

    public static CodeProducer module(CodeProducer headDef, CodeProducer staticDef, CodeProducer... members) {
        Module.ModuleBuilder mod = Module.__()
                .variablesSection(headDef);
        mod.staticSection(staticDef);
        for (CodeProducer memb : members) {
            mod.def(memb);
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
        for (CodeProducer part : bodyParts) {
            prc.statement(part);
        }
        return prc.__();
    }

    public static CodeProducer proc(String name, String[] args, boolean isFunc, boolean exported, String... bodyParts) {
        Procedure.ProcedureBuilder prc = Procedure.__()
                .name(name)
                .params(args)
                .export(exported)
                .type(isFunc ? Procedure.Type.Func : Procedure.Type.Proc);
        for (String part : bodyParts) {
            prc.statement(block(part));
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
        for (CodeProducer part : bodyParts) {
            fr.statement(part);
        }
        return fr.__();
    }

    public static CodeProducer forEach(String varName, String container, String... bodyParts) {
        ForEach.ForEachBuilder fr = ForEach.__()
                .varName(varName)
                .container(container);
        for (String part : bodyParts) {
            fr.statement(block(part));
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
        for (CodeProducer part : bodyParts) {
            frl.statement(part);
        }
        return frl.__();
    }

    public static CodeProducer forLoop(String varName, String from, String to, String... bodyParts) {
        ForLoop.ForLoopBuilder frl = ForLoop.__()
                .varName(varName)
                .from(from).to(to);
        for (String part : bodyParts) {
            frl.statement(block(part));
        }
        return frl.__();
    }

    public static IfThenElse.IfThenElseBuilder ifThenElse(String condition) {
        return IfThenElse.__().condition(condition);
    }

    public static CodeProducer ifThen(String condition, CodeProducer... bodyParts) {
        IfThenElse.IfThenElseBuilder ite = IfThenElse.__().condition(condition);
        for (CodeProducer part : bodyParts) {
            ite.posStatement(part);
        }
        return ite.__();
    }

    public static CodeProducer ifThen(String condition, String... bodyParts) {
        IfThenElse.IfThenElseBuilder ite = IfThenElse.__().condition(condition);
        for (String part : bodyParts) {
            ite.posStatement(block(part));
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

    public static CodeProducer ifThenElse(String condition, CodeProducer posBody, ElseIf... elseIfParts) {
        IfThenElse.IfThenElseBuilder eib = IfThenElse.__().condition(condition).posStatement(posBody);
        for (ElseIf ei : elseIfParts) {
            eib.elseif(ei);
        }
        return eib.__();
    }

    public static TryCatch.TryCatchBuilder tryCatch() {
        return TryCatch.__();
    }

    public static CodeProducer trySuppress(CodeProducer... bodyParts) {
        TryCatch.TryCatchBuilder tsp = TryCatch.__();
        for (CodeProducer part : bodyParts) {
            tsp.statement(part);
        }
        return tsp.__();
    }

    public static CodeProducer trySuppress(String... bodyParts) {
        TryCatch.TryCatchBuilder tsp = TryCatch.__();
        for (String part : bodyParts) {
            tsp.statement(block(part));
        }
        return tsp.__();
    }

    public static CodeProducer tryCatch(CodeProducer body, CodeProducer catchBody) {
        return TryCatch.__()
                .statement(body)
                .catchStatement(catchBody)
                .__();
    }

    public static CodeProducer tryCatch(String body, CodeProducer catchBody) {
        return TryCatch.__()
                .statement(block(body))
                .catchStatement(catchBody)
                .__();
    }

    public static CodeProducer tryCatch(CodeProducer body, String catchBody) {
        return TryCatch.__()
                .statement(body)
                .catchStatement(block(catchBody))
                .__();
    }
    
    public static CodeProducer tryCatch(String body, String catchBody) {
        return TryCatch.__()
                .statement(block(body))
                .catchStatement(block(catchBody))
                .__();
    }
    
    public static WhileLoop.WhileLoopBuilder whileLoop(String condition) {
        return WhileLoop.__().condition(condition);
    }

    public static CodeProducer whileLoop(String condition, CodeProducer... bodyParts) {
        WhileLoop.WhileLoopBuilder wl = WhileLoop.__().condition(condition);
        for (CodeProducer part : bodyParts) {
            wl.statement(part);
        }
        return wl.__();
    }

    public static CodeProducer whileLoop(String condition, String... bodyParts) {
        WhileLoop.WhileLoopBuilder wl = WhileLoop.__().condition(condition);
        for (String part : bodyParts) {
            wl.statement(block(part));
        }
        return wl.__();
    }

    public static CodeProducer comment(CodeProducer... comments) {
        Comment.CommentBuilder cm = Comment.__();
        for (CodeProducer line : comments) {
            cm.line(line);
        }
        return cm.__();
    }

    public static CodeProducer comment(String... comments) {
        Comment.CommentBuilder cm = Comment.__();
        for (String line : comments) {
            cm.line(block(line));
        }

        return cm.__();
    }

    public static ElseIf elseIf(String condition, CodeProducer... bodyParts) {
        ElseIf.ElseIfBuilder elif = ElseIf.__().condition(condition);
        for (CodeProducer part : bodyParts) {
            elif.statement(part);
        }
        return elif.__();
    }

    public static ElseIf elseIf(String condition, String... bodyParts) {
        ElseIf.ElseIfBuilder elif = ElseIf.__().condition(condition);
        for (String part : bodyParts) {
            elif.statement(block(part));
        }
        return elif.__();
    }

    public static String[] args(String... arguments) {
        return arguments;
    }

    public static CodeProducer ppIfThen(String condition, String... bodyParts) {
        PrePrcIf.PrePrcIfBuilder ite = PrePrcIf.__().condition(condition);
        for (String part : bodyParts) {
            ite.posStatement(block(part));
        }
        return ite.__();
    }

    public static CodeProducer ppIfThenElse(String condition, CodeProducer posBody, CodeProducer negBody) {
        return PrePrcIf.__()
                .condition(condition)
                .posStatement(posBody)
                .negStatement(negBody)
                .__();
    }

    public static CodeProducer _return(String value) {
        return block("Возврат " + value + ";");
    }

    public static CodeProducer structConstruct(String name, Map<String, Object> structure) {
        try {
            return block(Module.TPL.structConstruct(name, structure));
        } catch (TemplateException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getTemplateSourceName() + " >> " + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getMessage());
        }
    }
    
}
