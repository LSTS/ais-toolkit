package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.Message01;
import de.baderjene.aistoolkit.aisparser.message.Message03;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Parser for AIS messages of type 3.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Message03Parser extends Message01Parser {

    /**
     * Constructs a new parser for AIS messages of type 3.
     * 
     * @param message An unparsed AIS message
     */
    public Message03Parser(final RawMessage message) {
        super(message);
    }

    @Override
    public Message parse() throws AISParserException {

        final Message01 message01 = (Message01) super.parse();

        final int type = message01.getType();
        final int repeatIndicator = message01.getRepeatIndicator();
        final int sourceMmsi = message01.getSourceMmsi();
        final int navigationStatus = message01.getNavigationStatus();
        final double rateOfTurn = message01.getRateOfTurn();
        final double speedOverGround = message01.getSpeedOverGround();
        final boolean positionAccurate = message01.isPositionAccurate();
        final double longitude = message01.getLongitude();
        final double latitude = message01.getLatitude();
        final double courseOverGround = message01.getCourseOverGround();
        final int trueHeading = message01.getTrueHeading();
        final int timestamp = message01.getTimestamp();
        final int maneuverIndicator = message01.getManeuverIndicator();
        final boolean raimUsed = message01.isRaimUsed();
        final int radioStatus = message01.getRadioStatus();

        final Message03 message03 = new Message03();
        message03.setType(type);
        message03.setRepeatIndicator(repeatIndicator);
        message03.setSourceMmsi(sourceMmsi);
        message03.setNavigationStatus(navigationStatus);
        message03.setRateOfTurn(rateOfTurn);
        message03.setSpeedOverGround(speedOverGround);
        message03.setPositionAccurate(positionAccurate);
        message03.setLongitude(longitude);
        message03.setLatitude(latitude);
        message03.setCourseOverGround(courseOverGround);
        message03.setTrueHeading(trueHeading);
        message03.setTimestamp(timestamp);
        message03.setManeuverIndicator(maneuverIndicator);
        message03.setRaimUsed(raimUsed);
        message03.setRadioStatus(radioStatus);
        return message03;

    }
}
