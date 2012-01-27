package de.baderjene.ais.parser;

import java.util.TreeSet;

/**
 * The MessageAssembler is used to assemble messages of one ore more parts.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class MessageAssembler {

    private final TreeSet<Packet> packets = new TreeSet<Packet>();

    /**
     * Adds a packet to the message.
     * 
     * @param packet the packet which should be added
     */
    public void addPacket(final Packet packet) {
        packets.add(packet);
    }

    /**
     * Check whether the message is complete.
     * 
     * @return wheter the message is complete
     */
    public boolean isMessageComplete() {
        return packets.first().getTotalPackets() == packets.size();
    }

    /**
     * Generate the binary string of the data parts.
     * 
     * @return the binary representation of the data string
     */
    private String generateBinary() {
        final StringBuilder builder = new StringBuilder();
        for (final Object obj : packets.toArray()) {
            final Packet packet = (Packet) obj;
            builder.append(packet.getBinary());
        }
        return builder.toString();
    }

    /**
     * Assemble all parts to a message.
     * 
     * @return the assembled message
     */
    public Message assemble() {
        final String binary = generateBinary();
        final BinaryParser parser = new BinaryParser(binary);
        final Message message = new Message();
        message.setBinary(generateBinary());
        message.setAisMessageType(parser.parseInteger(0, 5));
        message.setRepeatIndicator(parser.parseInteger(6, 7));
        message.setMmsi(parser.parseInteger(8, 37));
        return message;
    }

}
