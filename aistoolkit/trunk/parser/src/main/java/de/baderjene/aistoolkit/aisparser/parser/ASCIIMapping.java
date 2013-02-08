package de.baderjene.aistoolkit.aisparser.parser;

import java.util.ArrayList;
import java.util.List;

import de.baderjene.aistoolkit.aisparser.AISParserException;

/**
 * This class contains the mapping for ASCII chars.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public final class ASCIIMapping {

    private static final List<String> MAPPING = new ArrayList<String>();

    /**
     * Private constructor for utility class.
     */
    private ASCIIMapping() {
    }

    static {
        MAPPING.add("@"); // 00
        MAPPING.add("A"); // 01
        MAPPING.add("B"); // 02
        MAPPING.add("C"); // 03
        MAPPING.add("D"); // 04
        MAPPING.add("E"); // 05
        MAPPING.add("F"); // 06
        MAPPING.add("G"); // 07
        MAPPING.add("H"); // 08
        MAPPING.add("I"); // 09
        MAPPING.add("J"); // 10
        MAPPING.add("K"); // 11
        MAPPING.add("L"); // 12
        MAPPING.add("M"); // 13
        MAPPING.add("N"); // 14
        MAPPING.add("O"); // 15
        MAPPING.add("P"); // 16
        MAPPING.add("Q"); // 17
        MAPPING.add("R"); // 18
        MAPPING.add("S"); // 19
        MAPPING.add("T"); // 20
        MAPPING.add("U"); // 21
        MAPPING.add("V"); // 22
        MAPPING.add("W"); // 23
        MAPPING.add("X"); // 24
        MAPPING.add("Y"); // 25
        MAPPING.add("Z"); // 26
        MAPPING.add("["); // 27
        MAPPING.add("\\"); // 28
        MAPPING.add("]"); // 29
        MAPPING.add("^"); // 30
        MAPPING.add("_"); // 31
        MAPPING.add(" "); // 32
        MAPPING.add("!"); // 33
        MAPPING.add("\""); // 34
        MAPPING.add("#"); // 35
        MAPPING.add("$"); // 36
        MAPPING.add("%"); // 37
        MAPPING.add("&"); // 38
        MAPPING.add("`"); // 49
        MAPPING.add("("); // 40
        MAPPING.add(")"); // 41
        MAPPING.add("*"); // 42
        MAPPING.add("+"); // 43
        MAPPING.add(","); // 44
        MAPPING.add("-"); // 45
        MAPPING.add("."); // 46
        MAPPING.add("/"); // 47
        MAPPING.add("0"); // 48
        MAPPING.add("1"); // 49
        MAPPING.add("2"); // 50
        MAPPING.add("3"); // 51
        MAPPING.add("4"); // 52
        MAPPING.add("5"); // 53
        MAPPING.add("6"); // 54
        MAPPING.add("7"); // 55
        MAPPING.add("8"); // 56
        MAPPING.add("9"); // 57
        MAPPING.add(":"); // 58
        MAPPING.add(";"); // 59
        MAPPING.add("<"); // 60
        MAPPING.add("="); // 61
        MAPPING.add(">"); // 62
        MAPPING.add("?"); // 63
    }

    /**
     * Returns the mapped ASCII character.
     * 
     * @param index Index
     * @return Mapped ASCII character
     * @throws AISParserException when no character is mapped to the given index
     */
    public static String getChar(final int index) throws AISParserException {
        String c = null;
        if (index < 0 || index > MAPPING.size() - 1) {
            throw new AISParserException();
        } else {
            c = MAPPING.get(index);
        }
        return c;
    }
}
