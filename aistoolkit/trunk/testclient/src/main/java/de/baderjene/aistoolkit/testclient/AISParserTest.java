package de.baderjene.aistoolkit.testclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import de.baderjene.aistoolkit.aisparser.AISObserver;
import de.baderjene.aistoolkit.aisparser.AISParser;
import de.baderjene.aistoolkit.aisparser.message.Message;

public class AISParserTest implements AISObserver {

	public static void main(final String[] args) throws UnknownHostException, IOException {
		new AISParserTest();
	}

	public AISParserTest() {
		final AISParser parser = new AISParser();
		parser.register(this);
		final Scanner scanner = new Scanner(AISParserTest.class.getResourceAsStream("/nmea.dump"));
		while (scanner.hasNext()) {
			parser.process(scanner.nextLine());
		}
	}

	@Override
	public void update(final Message message) {}

}
