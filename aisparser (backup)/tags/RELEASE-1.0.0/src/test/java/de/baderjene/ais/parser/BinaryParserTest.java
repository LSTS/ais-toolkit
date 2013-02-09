package de.baderjene.ais.parser;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test class for the binary parser.
 * 
 * @author Patrick Gotthard
 * 
 */
public class BinaryParserTest {

    private final BinaryParser parser = new BinaryParser("101101010001001010010");

    /**
     * Tests the parsing of boolean values.
     */
    @Test
    public void parseBooleanTest() {
        Assert.assertEquals(true, parser.parseBoolean(0));
        Assert.assertEquals(false, parser.parseBoolean(1));
    }

    /**
     * Tests the parsing of integers.
     */
    @Test
    public void parseIntegerTest() {
        // 1011
        Assert.assertEquals(11, parser.parseInteger(0, 3));
        // 101000
        Assert.assertEquals(40, parser.parseInteger(5, 10));
    }

    /**
     * Tests the parsing of signed integers.
     */
    public void parseSignedItegerTest() {
        // 101101 (Zweierkomplement)
        Assert.assertEquals(-3, parser.parseInteger(0, 5));
    }

}
