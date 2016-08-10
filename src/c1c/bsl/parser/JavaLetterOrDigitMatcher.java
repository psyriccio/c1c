/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl.parser;

/**
 *
 * @author psyriccio
 */
public class JavaLetterOrDigitMatcher extends AbstractJavaCharacterMatcher {

    public JavaLetterOrDigitMatcher() {
        super("LetterOrDigit");
    }

    @Override
    protected boolean acceptChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
