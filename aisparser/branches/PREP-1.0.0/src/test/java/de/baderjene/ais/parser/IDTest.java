package de.baderjene.ais.parser;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the ID class.
 * 
 * @author Patrick Gotthard
 * 
 */
public class IDTest {

    /**
     * Tests the correct functionality of the equals method.
     */
    @Test
    public void equalTest() {

        final ID id1 = new ID();
        id1.setChannel("A");
        id1.setSeqNum(1);

        final ID id2 = new ID();
        id2.setChannel("A");
        id2.setSeqNum(1);

        final ID id3 = new ID();
        id3.setChannel("B");
        id3.setSeqNum(2);

        Assert.assertTrue(id1.equals(id2));
        Assert.assertFalse(id1.equals(id3));
        Assert.assertFalse(id1.equals(null));
        Assert.assertFalse(id1.equals(new Object()));
    }

    /**
     * Tests the correct functionality of the hashcode method.
     */
    @Test
    public void hashCodeTest() {

        final ID id1 = new ID();
        id1.setChannel("A");
        id1.setSeqNum(1);

        final ID id2 = new ID();
        id2.setChannel("A");
        id2.setSeqNum(1);

        final ID id3 = new ID();
        id3.setChannel("B");
        id3.setSeqNum(2);

        Assert.assertEquals(1, id1.hashCode());
        Assert.assertEquals(2, id3.hashCode());
    }

}
