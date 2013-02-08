package de.baderjene.aistoolkit.aisreceiver;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import de.baderjene.aistoolkit.aisparser.AISParser;

/**
 * This class is used to handle AIS antenna connections.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public final class Handler implements Runnable {

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
            parser.process(scanner.nextLine());
        }
    }
}
