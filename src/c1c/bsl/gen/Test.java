/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.gen;

import c1c.bsl.BSLFormatter;
import static c1c.bsl.gen.Build.*;

/**
 *
 * @author psyriccio
 */
public class Test {

    public static void main(String[] args) {
        CodeProducer mod = module(
                block("Перем обырвалг;"),
                block("обырвалг = Неопределено;"),
                proc(
                        "Тест",
                        new String[]{"Арг1", "Арг2"},
                        false,
                        true,
                        block(
                                "Сообщить(обырвалг);",
                                "Предупреждение(\"Ололо!\");"
                        )
                )
        );

        System.out.println(BSLFormatter.format(mod.produce()));

    }

}
