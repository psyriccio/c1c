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
public class TryCatch implements CodeProducer {

    private @Getter @Setter String condition;
    private @Singular("statement") @Getter @Setter List<CodeProducer> body;
    private @Singular("catchStatement") @Getter @Setter List<CodeProducer> catchBody;
    
    @Override
    public String produce() {
        String[] aBody = body.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        String[] aCatchBody = catchBody.stream().map((prc) -> prc.produce()).collect(Collectors.toList()).toArray(new String[0]);
        try {
            return Module.TPL.tryCatch(aBody, aCatchBody);
        } catch (TemplateException ex ) {
            throw new RuntimeException("Exeption while processing template: " + ex.getTemplateSourceName() + " >> " + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("Exeption while processing template: " + ex.getMessage());
        }
    }
    
}
