package de.baderjene.aistoolkit.testclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AISReceiverTest {

	public static void main(final String[] args) throws UnknownHostException, IOException {
		final Socket socket = new Socket("localhost", 8000);
		final Scanner scanner = new Scanner(AISReceiverTest.class.getResourceAsStream("/nmea.dump"));
		final PrintWriter printer = new PrintWriter(socket.getOutputStream());
		while (scanner.hasNext()) {
			printer.println(scanner.nextLine());
		}
	}

}
