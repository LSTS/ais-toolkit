package de.baderjene.ais.parser;

import org.apache.commons.lang3.StringUtils;

/**
 * Parses raw ais data packets.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class PacketParser {

    /**
     * Parse an ais packet.
     * 
     * @param raw the raw ais packet
     * @return the parsed packet
     * @throws InvalidPacketException when the packet is no ais packet
     * @throws InvalidChecksumException when the checksum is invalid
     */
    public Packet parse(final String raw) throws InvalidPacketException, InvalidChecksumException {
        final String[] token = raw.split("[,*]");
        if (!isAisPacket(token)) {
            throw new InvalidPacketException(raw);
        }
        if (!isChecksumValid(raw)) {
            throw new InvalidChecksumException(raw);
        }

        final Packet packet = new Packet();
        packet.setRaw(raw);
        packet.setMessageType(token[0]);
        packet.setTotalPackets(Integer.parseInt(token[1]));
        packet.setCurrentPacket(Integer.parseInt(token[2]));
        if (!token[3].isEmpty()) {
            packet.setSeqNum(Integer.parseInt(token[3]));
        }
        packet.setChannel(token[4]);
        packet.setData(token[5]);
        packet.setPadBits(Integer.parseInt(token[6]));
        packet.setChecksum(Integer.parseInt(token[7], 16));
        packet.setBinary(generateBinaryData(token[5]));
        return packet;
    }

    /**
     * Checks whether the data is a valid ais packet.
     * 
     * @param token the splitted packet
     * @return whether the packet is a valid ais packet
     */
    private boolean isAisPacket(final String[] token) {
        return token.length == 8 && "!AIVDM".equals(token[0]);
    }

    /**
     * Control the checksum of the ais packet.
     * 
     * @param raw the raw packet
     * @return whether the cecksum is valid
     */
    private boolean isChecksumValid(final String raw) {
        final String checkString = raw.substring(1, raw.indexOf("*"));
        final int validChecksum = Integer.parseInt(raw.substring(raw.indexOf('*') + 1), 16);
        int checksum = 0;
        for (final char c : checkString.toCharArray()) {
            checksum ^= c;
        }
        return validChecksum == checksum;
    }

    /**
     * Converts a 6 Bit encoded string to binary.
     * 
     * @param data the data part of the ais packet
     * @return the binary representation of the data part
     */
    private String generateBinaryData(final String data) {
        final StringBuilder builder = new StringBuilder();
        for (int i : data.toCharArray()) {
            if (i > 96) {
                i -= 56;
            } else {
                i -= 48;
            }
            final String tmp = Integer.toBinaryString(i);
            builder.append(StringUtils.leftPad(tmp, 6, '0'));
        }
        return builder.toString();
    }

}
