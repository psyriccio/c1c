/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 *
 * @author psyriccio
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(buildMethodName = "__", builderMethodName = "__")
public class Block implements CodeProducer {

    private @Singular("statement") @Getter List<String> body = new ArrayList<>();

    @Override
    public String produce() {
        return body.stream().map((str) -> str.concat("\n")).reduce("", String::concat);
    }
    
}
