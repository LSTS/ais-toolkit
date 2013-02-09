package de.baderjene.ais.parser;

/**
 * This class holds the basic information of an ais message
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class Message {

    private String binary;
    private int aisMessageType;
    private int repeatIndicator;
    private int mmsi;

    public String getBinary() {
        return binary;
    }

    public void setBinary(final String binary) {
        this.binary = binary;
    }

    public int getAisMessageType() {
        return aisMessageType;
    }

    public void setAisMessageType(final int aisMessageType) {
        this.aisMessageType = aisMessageType;
    }

    public int getRepeatIndicator() {
        return repeatIndicator;
    }

    public void setRepeatIndicator(final int repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(final int mmsi) {
        this.mmsi = mmsi;
    }

}
