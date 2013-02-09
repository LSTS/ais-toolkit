package de.baderjene.ais.parser;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the packet parser.
 * 
 * @author Patrick Gotthard
 * 
 */
public class PacketParserTest {

    private final PacketParser parser = new PacketParser();

    /**
     * Tests wheter the parser throws an exception for invalid packets.
     * 
     * @throws InvalidPacketException when a packet contains no valid ais data
     * @throws InvalidChecksumException when the checksum is not correct
     */
    @Test(expected = InvalidPacketException.class)
    public void parseInvalidPacketTest() throws InvalidPacketException, InvalidChecksumException {
        parser.parse("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0");
    }

    /**
     * Tests wheter the parser throws an exception for invalid checksums.
     * 
     * @throws InvalidPacketException when a packet contains no valid ais data
     * @throws InvalidChecksumException when the checksum is not correct
     */
    @Test(expected = InvalidChecksumException.class)
    public void parseInvalidChecksumPacketTest() throws InvalidPacketException, InvalidChecksumException {
        parser.parse("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0*71");
    }

    /**
     * Tests wheter the parser works correctly.
     * 
     * @throws InvalidPacketException when a packet contains no valid ais data
     * @throws InvalidChecksumException when the checksum is not correct
     */
    @Test
    public void parseValidPacketTest() throws InvalidPacketException, InvalidChecksumException {
        final Packet packet = parser.parse("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0*72");
        Assert.assertEquals("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0*72", packet.getRaw());
        Assert.assertEquals("!AIVDM", packet.getMessageType());
        Assert.assertEquals(1, packet.getTotalPackets());
        Assert.assertEquals(1, packet.getCurrentPacket());
        Assert.assertEquals(0, packet.getSeqNum());
        Assert.assertEquals("A", packet.getChannel());
        Assert.assertEquals("13:<SD0018PfV>bO7INHNFfn04q0", packet.getData());
        Assert.assertEquals(0, packet.getPadBits());
        Assert.assertEquals(Integer.parseInt("72", 16), packet.getChecksum());

    }
}
