package de.baderjene.aistoolkit.aisparser;

import de.baderjene.aistoolkit.aisparser.message.Message;

/**
 * Interface for AIS observers. You have to implement this interface and register your observer at the AIS parser in order to receive parsed
 * messages.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public interface AISObserver {

    /**
     * Receive parsed messages.
     * 
     * @param message Parsed AIS message
     */
    void update(Message message);

}
