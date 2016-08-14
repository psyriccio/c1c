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
        "(?i)(?u)(.*\\s+)?функция\\s+.*(.*).*",
        "(?i)(?u)(.*\\s+)?процедура\\s+.*(.*).*",
        "(?i)(?u)(.*\\s+)?если(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?#если(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?для\\s+.*цикл(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?пока\\s+.*цикл(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?попытка(\\s+.*)?"
    };

    public static String[] INDENT_DEC_TOKENS = new String[]{
        "(?i)(?u)(.*\\s+)?конецфункции(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?конецпроцедуры(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?конецесли\\s*;+.*",
        "(?i)(?u)(.*\\s+)?#конецесли(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?конеццикла\\s*;+.*",
        "(?i)(?u)(.*\\s+)?конецпопытки\\s*;+.*"
    };

    public static String[] INDENT_TOG_TOKENS = new String[]{
        "(?i)(?u)(.*\\s+)?иначе(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?иначеесли(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?#иначе(\\s+.*)?",
        "(?i)(?u)(.*\\s+)?исключение(\\s+.*)?"
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
        return text.replaceAll("^[ \\t]+", "").replaceAll("[ \\t]+", " ");
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
