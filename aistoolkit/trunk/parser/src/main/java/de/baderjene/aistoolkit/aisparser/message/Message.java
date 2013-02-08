package de.baderjene.aistoolkit.aisparser.message;

/**
 * Superclass for all AIS messages. Stores the common details that every AIS message has.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Message {

    private int type;
    private int repeatIndicator;
    private int sourceMmsi;

    public int getType() {
        return type;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public int getRepeatIndicator() {
        return repeatIndicator;
    }

    public void setRepeatIndicator(final int repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }

    public int getSourceMmsi() {
        return sourceMmsi;
    }

    public void setSourceMmsi(final int mmsi) {
        sourceMmsi = mmsi;
    }

}
