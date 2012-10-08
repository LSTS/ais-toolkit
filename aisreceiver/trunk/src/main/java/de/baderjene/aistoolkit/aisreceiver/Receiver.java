package de.baderjene.aistoolkit.aisreceiver;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.baderjene.aistoolkit.aisparser.AISObserver;
import de.baderjene.aistoolkit.aisparser.AISParser;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.Message01;
import de.baderjene.aistoolkit.aisparser.message.Message05;
import de.baderjene.aistoolkit.aisreceiver.persistence.Persistence;

/**
 * Receiver for AIS messages.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
@Component
public final class Receiver implements AISObserver, Runnable {

    @Autowired
    private Persistence persistence;

    /**
     * The constructor starts the receiver thread.
     */
    public Receiver() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (final IOException e) {
            return;
        }

        while (true) {
            try {
                final AISParser parser = new AISParser();
                parser.register(this);
                System.out.println("waiting for connection...");
                new Thread(new Handler(parser, serverSocket.accept())).start();
                System.out.println("Antenna connected.");
            } catch (final IOException e) {
            }
        }
    }

    @Override
    public void update(final Message message) {
        //System.out.println("Message received");
        final int type = message.getType();
        if (type == 1 || type == 2 || type == 3) {
            //System.out.println("saving message. type: 1 or 2 or 3");
            persistence.save((Message01) message);
        } else if (type == 5) {
            //System.out.println("saving message. type: 5");
            persistence.save((Message05) message);
        }
    }

}
