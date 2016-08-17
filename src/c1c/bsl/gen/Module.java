/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.SneakyThrows;

/**
 *
 * @author psyriccio
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "__", builderMethodName = "__")
public class Module implements CodeProducer {

    public static CodeProducer NEWLINE = () -> "\n";
    public static List<CodeProducer> LNEWLINE = Lists.newArrayList(NEWLINE);

    public static CodeTemplateProcessor TPL;

    private @Getter @Setter String name;
    private @Singular("headBlock") @Getter List<CodeProducer> headContent = new ArrayList<>();
    private @Singular("member") @Getter List<CodeProducer> content = new ArrayList<>();
    private @Singular("staticBlock") @Getter List<CodeProducer> footerContent = new ArrayList<>();

    public void acceptChild(CodeProducer child) {
        content.add(child);
    }

    @Override
    @SneakyThrows
    public String produce() {
        Module.TPL = new CodeTemplateProcessor();
        return Lists.newArrayList(
                headContent, LNEWLINE,
                content, LNEWLINE,
                footerContent, LNEWLINE
        ).stream()
                .flatMap((sublst) -> sublst.stream())
                .map((prc) -> prc.produce())
                .reduce("", String::concat);
    }

    public Procedure.ProcedureBuilder proc() {
        Procedure prc = new Procedure();
        return prc.toBuilder();
    }

}
