package de.baderjene.aistoolkit.aisparser;

/**
 * Throw this exception when an invalid packet should be parsed.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class InvalidPacketException extends Exception {

    private static final long serialVersionUID = 1549886303433439202L;

    /**
     * Create an exception for invalid ais messages.
     * 
     * @param raw the raw message
     */
    public InvalidPacketException(final String raw) {
        super("Invalid ais packet: " + raw);
    }

}
