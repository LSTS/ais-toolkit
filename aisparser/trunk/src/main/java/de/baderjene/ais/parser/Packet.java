package de.baderjene.ais.parser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This class is used to hold the basic information of an ais packet.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class Packet implements Comparable<Packet> {

    private String raw;
    private String messageType;
    private int totalPackets;
    private int currentPacket;
    private Integer seqNum;
    private String channel;
    private String data;
    private int padBits;
    private int checksum;
    private String binary;

    public String getRaw() {
        return raw;
    }

    public void setRaw(final String raw) {
        this.raw = raw;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    public int getTotalPackets() {
        return totalPackets;
    }

    public void setTotalPackets(final int totalPackets) {
        this.totalPackets = totalPackets;
    }

    public int getCurrentPacket() {
        return currentPacket;
    }

    public void setCurrentPacket(final int currentPacket) {
        this.currentPacket = currentPacket;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(final Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public int getPadBits() {
        return padBits;
    }

    public void setPadBits(final int padBits) {
        this.padBits = padBits;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(final int checksum) {
        this.checksum = checksum;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(final String binary) {
        this.binary = binary;
    }

    @Override
    public int compareTo(final Packet packet) {
        return getCurrentPacket() - packet.getCurrentPacket();
    }

    @Override
    public boolean equals(final Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof Packet) {
            final Packet packet = (Packet) obj;
            final EqualsBuilder builder = new EqualsBuilder();
            builder.append(raw, packet.raw);
            equals = builder.build();
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(hashCode());
        return builder.build();
    }

}
