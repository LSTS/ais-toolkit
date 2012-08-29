package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * This class parses the common informations that every AIS message has. If the message type is supported, the message gets redirected to
 * the specific message type parser.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public class MessageParser extends AbstractMessageParser {

    /**
     * Constructs a new parser for AIS messages.
     * 
     * @param message An unparsed AIS message
     */
    public MessageParser(final RawMessage message) {
        super(message);
    }

    /**
     * Parses the common message informations that every AIS message has and routes the message to the specific message type parser if the
     * message type is supported
     * 
     * @return The parsed message or null if the message type is not supported or some error occured while parsing the message
     */
    @Override
    public Message parse() {

        Message parsedMessage = null;

        try {

            final int type = parseInteger(0, 5);

            final RawMessage rawMessage = getRawMessage();
            rawMessage.setType(type);

            final AbstractMessageParser parser = ParserFactory.create(rawMessage);
            if (parser != null) {

                final int repeatIndicator = parseInteger(6, 7);
                final int sourceMmsi = parseInteger(8, 37);

                rawMessage.setRepeatIndicator(repeatIndicator);
                rawMessage.setSourceMmsi(sourceMmsi);

                parsedMessage = parser.parse();
            }

        } catch (final AISParserException e) {
            // only for debugging purposes
            // e.printStackTrace();
        }

        return parsedMessage;

    }

}
