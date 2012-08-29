package de.baderjene.aistoolkit.aisparser;

import java.util.ArrayList;
import java.util.List;

import de.baderjene.aistoolkit.aisparser.message.Message;

/**
 * Abstract AIS publisher. Is responsible for handling the AIS observers and gets inherited from the AIS parser.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
abstract class AbstractAISPublisher {

    private final List<AISObserver> observers = new ArrayList<AISObserver>();

    /**
     * Registers a new AIS observer.
     * 
     * @param observer AIS observer
     */
    public void register(final AISObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregisters an AIS observer.
     * 
     * @param observer AIS observer
     */
    public void unregister(final AISObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies AIS observers about new messages.
     * 
     * @param message AIS message
     */
    protected void notifyObservers(final Message message) {
        for (final AISObserver observer : observers) {
            observer.update(message);
        }
    }

}
