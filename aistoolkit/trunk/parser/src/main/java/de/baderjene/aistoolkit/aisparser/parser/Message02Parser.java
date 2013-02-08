package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.Message01;
import de.baderjene.aistoolkit.aisparser.message.Message02;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Parser for AIS messages of type 2.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Message02Parser extends Message01Parser {

    /**
     * Constructs a new parser for AIS messages of type 2.
     * 
     * @param message An unparsed AIS message
     */
    public Message02Parser(final RawMessage message) {
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

        final Message02 message02 = new Message02();
        message02.setType(type);
        message02.setRepeatIndicator(repeatIndicator);
        message02.setSourceMmsi(sourceMmsi);
        message02.setNavigationStatus(navigationStatus);
        message02.setRateOfTurn(rateOfTurn);
        message02.setSpeedOverGround(speedOverGround);
        message02.setPositionAccurate(positionAccurate);
        message02.setLongitude(longitude);
        message02.setLatitude(latitude);
        message02.setCourseOverGround(courseOverGround);
        message02.setTrueHeading(trueHeading);
        message02.setTimestamp(timestamp);
        message02.setManeuverIndicator(maneuverIndicator);
        message02.setRaimUsed(raimUsed);
        message02.setRadioStatus(radioStatus);
        return message02;

    }
}
