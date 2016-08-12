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
        "\\W*функция\\s+.*(.*).*$",
        "\\W*процедура\\s+.*(.*).*$",
        "\\W*если\\s+.*$",
        "\\W*#если\\s+.*$",
        "\\W*для\\s+.*цикл\\W*$",
        "\\W*пока\\s+.*цикл\\W*$",
        "\\W*попытка\\W*$"
    };

    public static String[] INDENT_DEC_TOKENS = new String[]{
        "\\W*конецфункции\\W*$",
        "\\W*конецпроцедуры\\W*$",
        "\\W*конецесли[\\s;]+$",
        "\\W*#конецесли\\W*$",
        "\\W*конеццикла[\\s;]+$",
        "\\W*конецпопытки[\\s;]+$"
    };

    public static String[] INDENT_TOG_TOKENS = new String[]{
        "\\W*иначе\\W*$",
        "\\W*#иначе\\W*$",
        "\\W*исключение\\W*$"
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
