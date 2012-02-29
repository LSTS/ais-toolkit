package de.baderjene.ais.receiver;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;

import de.baderjene.ais.parser.AISParser;
import de.baderjene.ais.parser.InvalidChecksumException;
import de.baderjene.ais.parser.InvalidPacketException;

/**
 * This class is used to handle ais antenna connections.
 * 
 * @author Patrick Gotthard
 * 
 */
public final class Handler implements Runnable {

    private static final Logger LOG = Logger.getLogger(Handler.class);
    private final AISParser parser;
    private final Scanner scanner;

    /**
     * Initialize the handler with an AIS parser and the socket.
     * 
     * @param parser the AIS parser
     * @param socket the socket
     * @throws IOException when something goes wrong with the communication between server and antenna
     */
    public Handler(final AISParser parser, final Socket socket) throws IOException {
        this.parser = parser;
        scanner = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String packet = null;
            try {
                packet = scanner.nextLine();
                LOG.debug("Received ais packet: " + packet);
                parser.process(packet);
            } catch (final InvalidChecksumException e) {
                LOG.error("Wrong checksum: " + packet);
            } catch (final InvalidPacketException e) {
                LOG.error("Invalid packet: " + packet);
            }
        }
    }
}
