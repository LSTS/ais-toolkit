package de.baderjene.aistoolkit.aisparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses raw ais data packets.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class PacketParser {

    private static final Logger LOG = LoggerFactory.getLogger(PacketParser.class);

    /**
     * Parse an ais packet.
     * 
     * @param raw the raw ais packet
     * @return the parsed packet
     * @throws InvalidPacketException when the packet is no ais packet
     * @throws InvalidChecksumException when the checksum is invalid
     */
    public Packet parse(final String raw) throws InvalidPacketException, InvalidChecksumException {

        LOG.debug("Processing input: {}", raw);

        LOG.debug("Validating packet via RegEx");
        final Matcher matcher = Pattern.compile("\\!([A-Z]{5},\\d,\\d,\\d*,.*?,\\d)\\*([0-9A-F]{2})").matcher(raw);
        if (matcher.find()) {
            LOG.debug("RegEx validation passed");
        } else {
            LOG.warn("RegEx validation failed {}", raw);
            throw new InvalidPacketException(raw);
        }

        LOG.debug("Validating checksum");
        final int currentChecksum = calcChecksum(matcher.group(1));
        final int expectedChecksum = Integer.parseInt(matcher.group(2), 16);
        if (currentChecksum == expectedChecksum) {
            LOG.debug("Checksum validation passed");
        } else {
            LOG.warn("Checksum validation failed {}", raw);
            throw new InvalidChecksumException(raw);
        }

        LOG.debug("Parsing packet");
        final String[] token = raw.split("[,*]");
        final String messageType = token[0];
        final int totalPackets = Integer.parseInt(token[1]);
        final int currentPacket = Integer.parseInt(token[2]);
        Integer seqNum = null;
        if (StringUtils.isNotBlank(token[3])) {
            seqNum = Integer.parseInt(token[3]);
        }
        final String channel = token[4];
        final String data = token[5];
        final int fillBits = Integer.parseInt(token[6]);

        LOG.debug("Creating packet object from data");
        final Packet packet = new Packet();
        packet.setRaw(raw);
        packet.setMessageType(messageType);
        packet.setTotalPackets(totalPackets);
        packet.setCurrentPacket(currentPacket);
        packet.setSeqNum(seqNum);
        packet.setChannel(channel);
        packet.setData(data);
        packet.setPadBits(fillBits);
        packet.setChecksum(currentChecksum);
        packet.setBinary(generateBinaryData(token[5]));
        LOG.debug("Packet object created");
        return packet;
    }

    /**
     * Calculates the checksum of the packet.
     * 
     * @param data The data for creating the checksum
     * @return Checksum of the data
     */
    private int calcChecksum(final String data) {
        int checksum = 0;
        for (final char c : data.toCharArray()) {
            checksum ^= c;
        }
        return checksum;
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
