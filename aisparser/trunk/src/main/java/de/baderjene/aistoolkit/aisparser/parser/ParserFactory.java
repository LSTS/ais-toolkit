package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Factory for message parsers.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public final class ParserFactory {

    /**
     * Private constructor for utility class.
     */
    private ParserFactory() {
    }

    /**
     * Creates a parser for supported message types.
     * 
     * @param rawMessage An unparsed AIS message
     * @return A new parser for the given message or null if the message type is not supported
     */
    public static AbstractMessageParser create(final RawMessage rawMessage) {

        AbstractMessageParser parser = null;

        final int type = rawMessage.getType();

        switch (type) {
            case 1:
                parser = new Message01Parser(rawMessage);
                break;
            case 2:
                parser = new Message02Parser(rawMessage);
                break;
            case 3:
                parser = new Message03Parser(rawMessage);
                break;
            case 5:
                parser = new Message05Parser(rawMessage);
                break;
            default:
                break;
        }

        return parser;
    }

}
