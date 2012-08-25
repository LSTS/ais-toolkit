package de.baderjene.aistoolkit.aisreceiver;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.baderjene.aistoolkit.aisparser.AISObserver;
import de.baderjene.aistoolkit.aisparser.AISParser;
import de.baderjene.aistoolkit.aisparser.PositionReportDTO;
import de.baderjene.aistoolkit.aisparser.VesselDataDTO;
import de.baderjene.aistoolkit.aisreceiver.persistence.Persistence;

/**
 * Receiver for ais messages.
 * 
 * @author Patrick Gotthard
 * 
 */
@Component
public final class Receiver implements AISObserver, Runnable {

    private static final Logger LOG = Logger.getLogger(Receiver.class);

    @Autowired
    private Persistence persistence;

    /**
     * The constructor starts the receiver thread.
     */
    public Receiver() {
        LOG.debug("Starting server");
        new Thread(this).start();
    }

    @Override
    public void run() {
        LOG.debug("Opening server socket");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (final IOException e) {
            LOG.error("Error while opening server socket", e);
            return;
        }

        while (true) {
            try {
                final AISParser parser = new AISParser();
                parser.register(this);
                new Thread(new Handler(parser, serverSocket.accept())).start();
                LOG.debug("Antenna connected");
            } catch (final IOException e) {
                LOG.error("IOException with antenna", e);
            }
        }
    }

    @Override
    public void update(final PositionReportDTO report) {
        LOG.debug("Received position report");
        persistence.save(report);
    }

    @Override
    public void update(final VesselDataDTO data) {
        LOG.debug("Received vessel data");
        persistence.save(data);
    }

}
