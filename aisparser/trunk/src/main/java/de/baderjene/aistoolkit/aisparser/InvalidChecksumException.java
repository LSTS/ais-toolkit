package de.baderjene.aistoolkit.aisparser;

/**
 * Throw this exception when the checksum of an ais packet is invalid.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class InvalidChecksumException extends Exception {

    private static final long serialVersionUID = -7497752734903129942L;

    /**
     * Creates an exception for an invalid checksum.
     * 
     * @param raw the raw message
     */
    public InvalidChecksumException(final String raw) {
        super("Invalid checksum for packet: " + raw);
    }

}
