package de.baderjene.aistoolkit.aisparser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.baderjene.aistoolkit.aisparser.InvalidChecksumException;
import de.baderjene.aistoolkit.aisparser.InvalidPacketException;
import de.baderjene.aistoolkit.aisparser.MessageAssembler;
import de.baderjene.aistoolkit.aisparser.Packet;
import de.baderjene.aistoolkit.aisparser.PacketParser;


/**
 * Test class for the message assembler.
 * 
 * @author Patrick Gotthard
 * 
 */
public class MessageAssemblerTest {

    private Packet packet1;
    private Packet packet2;
    private Packet packet3;

    /**
     * Sets up the test class
     * 
     * @throws InvalidPacketException when the packet is not valid ais packet.
     * @throws InvalidChecksumException when the checksum of the packet is invalid.
     */
    @Before
    public void setup() throws InvalidPacketException, InvalidChecksumException {
        packet1 = new PacketParser().parse("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0*72");
        packet2 = new PacketParser().parse("!AIVDM,2,1,4,A,53aqG402??J50984000Dm0U8D000000000000017CP?==4EmNFQ1@TR00000,0*14");
        packet3 = new PacketParser().parse("!AIVDM,2,2,4,A,00000000000,2*20");
    }

    /**
     * Tests whether the assembling of packets works correctly.
     */
    @Test
    public void testMessageAssembling() {

        MessageAssembler assembler = new MessageAssembler();
        assembler.addPacket(packet1);
        Assert.assertEquals(true, assembler.isMessageComplete());

        assembler = new MessageAssembler();
        assembler.addPacket(packet2);
        Assert.assertEquals(false, assembler.isMessageComplete());
        assembler.addPacket(packet3);
        Assert.assertEquals(true, assembler.isMessageComplete());
    }

}
