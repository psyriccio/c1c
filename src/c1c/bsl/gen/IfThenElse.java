/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 *
 * @author psyriccio
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "__", builderMethodName = "__")
public class IfThenElse implements CodeProducer {

    private @Getter @Setter String condition;
    private @Singular("posStatement") @Getter @Setter List<CodeProducer> posBody;
    private @Singular("negStatement") @Getter @Setter List<CodeProducer> negBody;
    private @Singular("elseif") @Getter @Setter List<ElseIf> elseIfParts;

    @Override
    public String produce() {
        String[] aPosBody = posBody.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        String[] aNegBody = negBody.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        HashMap<String, String[]> elseIfMap = new HashMap<>();
        if (elseIfParts != null) {
            for (ElseIf ei : elseIfParts) {
                String[] elseIfBody = ei.getBody().stream()
                        .map((itm) -> itm.produce())
                        .collect(Collectors.toList())
                        .toArray(new String[0]);
                if(ei.getCondition().isEmpty()) {
                    aNegBody = Lists.newArrayList(Arrays.asList(aNegBody), Arrays.asList(elseIfBody)).stream()
                            .flatMap((itm) -> itm.stream())
                            .collect(Collectors.toList())
                            .toArray(new String[0]);
                } else {
                    elseIfMap.put(ei.getCondition(), elseIfBody);
                }
            }
        }
        try {
            if (elseIfMap.size() > 0) {
                return Module.TPL.ifThenElse(condition, aPosBody, elseIfMap, aNegBody);

            } else {
                return Module.TPL.ifThenElse(condition, aPosBody, aNegBody);
            }
        } catch (TemplateException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getTemplateSourceName() + " >> " + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getMessage());
        }
    }

}
