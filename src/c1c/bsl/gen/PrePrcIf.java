/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import freemarker.template.TemplateException;
import java.io.IOException;
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
public class PrePrcIf implements CodeProducer {

    private @Getter @Setter String condition;
    private @Singular("posStatement") @Getter @Setter List<CodeProducer> posBody;
    private @Singular("negStatement") @Getter @Setter List<CodeProducer> negBody;

    @Override
    public String produce() {
        String[] aPosBody = posBody.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        String[] aNegBody = negBody.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        try {
            return Module.TPL.prePrcIf(condition, aPosBody, aNegBody);
        } catch (TemplateException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getTemplateSourceName() + " >> " + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getMessage());
        }
    }

}
