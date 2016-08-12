/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c1c.bsl;

/**
 *
 * @author psyriccio
 */
public class BSLFormatter {

    private static String getIndent(int level) {

        String res = "";
        for (int k = 0; k < level; k++) {
            res += "\t";
        }

        return res;

    }

    public static String[] INDENT_INC_TOKENS = new String[]{
        "((.*\\W+)|(\\W?))функция\\s+.*(.*).*",
        "((.*\\W+)|(\\W?))процедура\\s+.*(.*).*",
        "((.*\\W+)|(\\W?))если(\\W+.*)?",
        "((.*\\W+)|(\\W?))#если(\\W+.*)?",
        "((.*\\W+)|(\\W?))для\\s+.*цикл(\\W+.*)?",
        "((.*\\W+)|(\\W?))пока\\s+.*цикл(\\W+.*)?",
        "((.*\\W+)|(\\W?))попытка(\\W+.*)?"
    };

    public static String[] INDENT_DEC_TOKENS = new String[]{
        "((.*\\W+)|(\\W?))конецфункции(\\W+.*)?",
        "((.*\\W+)|(\\W?))конецпроцедуры(\\W+.*)?",
        "((.*\\W+)|(\\W?))конецесли\\W*;(.*)?",
        "((.*\\W+)|(\\W?))#конецесли\\W*",
        "((.*\\W+)|(\\W?))конеццикла\\W*;(.*)?",
        "((.*\\W+)|(\\W?))конецпопытки\\W*;+(.*)?"
    };

    public static String[] INDENT_TOG_TOKENS = new String[]{
        "((.*\\W+)|(\\W?))иначе(\\W+.*)?",
        "((.*\\W+)|(\\W?))иначеесли(\\W+.*)?",
        "((.*\\W+)|(\\W?))#иначе(\\W+.*)?",
        "((.*\\W+)|(\\W?))исключение(\\W+.*)?"
    };

    public static boolean anyMatchIgnoreCase(String sample, String[] patterns) {
        String smp = sample.toLowerCase();
        for (String pattern : patterns) {
            if (smp.matches(pattern)) {
                return true;
            }
        }

        return false;

    }

    public static String trimWhiteSpaces(String text) {
        return text.replaceAll("^[\\h]+", "").replaceAll("[\\h]+", " ");
    }

    public static String format(String text) {

        String buf = "";
        int ind = 0;
        String[] lines = trimWhiteSpaces(text).split("\n");
        for (String line : lines) {
            boolean matchDec = anyMatchIgnoreCase(line, INDENT_DEC_TOKENS);
            boolean matchInc = anyMatchIgnoreCase(line, INDENT_INC_TOKENS);
            boolean matchTog = anyMatchIgnoreCase(line, INDENT_TOG_TOKENS);

            if (matchTog) {
                matchInc = true;
                matchDec = true;
            } else if (matchInc && matchDec && (!matchTog)) {
                matchInc = false;
                matchDec = false;
            }

            if (matchDec) {
                ind--;
            }

            buf += getIndent(ind) + line + "\n";

            if (matchInc) {
                ind++;
            }

        }

        return buf;
    }

}
