package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Abstract superclass for all AIS message parsers. Contains the methods to parse the binary payload.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public abstract class AbstractMessageParser {

    private final RawMessage rawMessage;
    private final String binary;

    /**
     * Constructs a new message parser with an unparsed AIS message.
     * 
     * @param rawMessage The raw (unparsed) message.
     */
    public AbstractMessageParser(final RawMessage rawMessage) {
        this.rawMessage = rawMessage;
        binary = rawMessage.getBinary();
    }

    /**
     * Parses the raw message that was committed to the constructor.
     * 
     * @return Parsed message
     * @throws AISParserException when some error occured while parsing the message
     */
    public abstract Message parse() throws AISParserException;

    public RawMessage getRawMessage() {
        return rawMessage;
    }

    /**
     * Extracts a part of the binary string.
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the extracted string
     * @throws AISParserException when the starting or ending index exceeds the length of the binary payload
     */
    private String extract(final int begin, final int end) throws AISParserException {
        if (begin > binary.length() || end + 1 > binary.length()) {
            throw new AISParserException();
        }
        return binary.substring(begin, end + 1);
    }

    /**
     * Parses a boolean from the binary string.
     * 
     * @param position The index of the bit
     * @return true if the bit is 1, false if the bit is 0
     * @throws AISParserException when the index exceeds the length of the binary payload
     */
    protected boolean parseBoolean(final int position) throws AISParserException {
        return "1".equals(extract(position, position));
    }

    /**
     * Parses an integer from the binary string.
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the parsed integer
     * @throws AISParserException when the starting or ending index exceeds the length of the binary payload
     */
    protected int parseInteger(final int begin, final int end) throws AISParserException {
        return Integer.parseInt(extract(begin, end), 2);
    }

    /**
     * Parses a signed integer of the binary string. Signed integers are represented as two's complements (see
     * http://de.wikipedia.org/wiki/Zweierkomplement#Formale_Umwandlung_aus_dem_Bin.C3.A4rsystem)
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the parsed signed integer
     * @throws AISParserException when the starting or ending index exceeds the length of the binary payload
     */
    protected int parseSignedInteger(final int begin, final int end) throws AISParserException {
        final boolean minus = binary.charAt(begin) == '1';
        final String value = extract(begin, end);
        if (minus) {
            return (int) -(Math.pow(2, value.length()) - Math.abs(Integer.parseInt(value, 2)));
        } else {
            return Integer.parseInt(value.substring(1), 2);
        }
    }

    /**
     * Parses text from the binary string.
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the parsed text
     * @throws AISParserException when the starting or ending index exceeds the length of the binary payload
     */
    protected String parseText(final int begin, final int end) throws AISParserException {
        final StringBuilder builder = new StringBuilder();
        final int length = end + 1 - begin;
        if (length % 6 != 0) {
            throw new AISParserException();
        }
        final int count = length / 6;
        for (int i = 0; i < count; i++) {
            final int start = begin + i * 6;
            final int current = parseInteger(start, start + 5);
            builder.append(ASCIIMapping.getChar(current));
        }
        while (builder.length() > 0 && builder.charAt(builder.length() - 1) == '@') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString().replace(" \\", "Z");
    }

}
