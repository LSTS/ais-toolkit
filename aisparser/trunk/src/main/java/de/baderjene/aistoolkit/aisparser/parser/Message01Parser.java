package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.Message01;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Parser for AIS messages of type 1.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Message01Parser extends AbstractMessageParser {

    /**
     * Constructs a new parser for AIS messages of type 1.
     * 
     * @param message An unparsed AIS message
     */
    public Message01Parser(final RawMessage message) {
        super(message);
    }

    @Override
    public Message parse() throws AISParserException {

        final RawMessage baseMessage = getRawMessage();

        final int type = baseMessage.getType();
        final int repeatIndicator = baseMessage.getRepeatIndicator();
        final int sourceMmsi = baseMessage.getSourceMmsi();

        final int navigationStatus = parseInteger(38, 41);
        final int rateOfTurn = parseSignedInteger(42, 49);
        final double speedOverGround = parseInteger(50, 59) / 10.0;
        final boolean positionAccurate = parseBoolean(60);
        final double longitude = parseSignedInteger(61, 88) / 600000.0;
        final double latitude = parseSignedInteger(89, 115) / 600000.0;
        final double courseOverGround = parseInteger(116, 127) / 10.0;
        final int trueHeading = parseInteger(128, 136);
        final int timestamp = parseInteger(137, 142);
        final int maneuverIndicator = parseInteger(143, 144);
        final boolean raimUsed = parseBoolean(148);
        final int radioStatus = parseInteger(149, 167);

        final Message01 message = new Message01();
        message.setType(type);
        message.setRepeatIndicator(repeatIndicator);
        message.setSourceMmsi(sourceMmsi);
        message.setNavigationStatus(navigationStatus);
        message.setRateOfTurn(rateOfTurn);
        message.setSpeedOverGround(speedOverGround);
        message.setPositionAccurate(positionAccurate);
        message.setLongitude(longitude);
        message.setLatitude(latitude);
        message.setCourseOverGround(courseOverGround);
        message.setTrueHeading(trueHeading);
        message.setTimestamp(timestamp);
        message.setManeuverIndicator(maneuverIndicator);
        message.setRaimUsed(raimUsed);
        message.setRadioStatus(radioStatus);

        return message;
    }

}
