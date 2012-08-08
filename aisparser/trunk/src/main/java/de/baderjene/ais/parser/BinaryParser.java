package de.baderjene.ais.parser;

/**
 * This class is used to parse the binary data string of the AIS message.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class BinaryParser {

    private final String[] asciiMapping = { "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z", "[", " \\", "]", "^", "_", " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3",
        "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?" };

    private final String binary;

    /**
     * Intialize the parser with the binary data string.
     * 
     * @param binary the binary representation of the AIS data
     */
    public BinaryParser(final String binary) {
        this.binary = binary;
    }

    /**
     * Extracts a part of the binary string.
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the extracted string
     */
    private String extract(final int begin, int end) {
        if (end > binary.length()) {
            end = binary.length() - 1;
        }
        return binary.substring(begin, end + 1);
    }

    /**
     * Parses a boolean from the binary string.
     * 
     * @param position The index of the bit
     * @return true if the bit is 1, false if the bit is 0
     */
    public boolean parseBoolean(final int position) {
        return "1".equals(extract(position, position));
    }

    /**
     * Parses an integer from the binary string.
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the parsed integer
     */
    public int parseInteger(final int begin, final int end) {
        return Integer.parseInt(extract(begin, end), 2);
    }

    /**
     * Parses a signed integer of the binary string. Signed integers are represented as two's complements (see
     * http://de.wikipedia.org/wiki/Zweierkomplement#Formale_Umwandlung_aus_dem_Bin.C3.A4rsystem)
     * 
     * @param begin the beginning index
     * @param end the ending index
     * @return the parsed signed integer
     */
    public int parseSignedInteger(final int begin, final int end) {
        final boolean minus = binary.charAt(0) == '1';
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
     */
    public String parseText(final int begin, final int end) {
        try {
            final StringBuilder ascii = new StringBuilder();
            final int count = (end + 1 - begin) / 6;
            for (int i = 0; i < count; i++) {
                final int current = parseInteger(begin + i * 6, begin + i * 6 + 5);
                ascii.append(asciiMapping[current]);
            }
            while (ascii.charAt(ascii.length() - 1) == '@') {
                ascii.deleteCharAt(ascii.length() - 1);
            }
            // some conversions did not deliver the right result
            // should be checked
            return ascii.toString().replace(" \\", "Z");
        } catch (final IndexOutOfBoundsException e) {
            return "";
        }
    }
}
