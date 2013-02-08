package de.baderjene.aistoolkit.aisparser.parser;

import de.baderjene.aistoolkit.aisparser.AISParserException;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.Message05;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;

/**
 * Parser for AIS messages of type 5.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Message05Parser extends AbstractMessageParser {

    /**
     * Constructs a new parser for AIS messages of type 5.
     * 
     * @param message An unparsed AIS message
     */
    public Message05Parser(final RawMessage message) {
        super(message);
    }

    @Override
    public Message parse() throws AISParserException {

        final RawMessage baseMessage = getRawMessage();

        final int type = baseMessage.getType();
        final int repeatIndicator = baseMessage.getRepeatIndicator();
        final int sourceMmsi = baseMessage.getSourceMmsi();

        final int aisVersion = parseInteger(38, 39);
        final int imoNumber = parseInteger(40, 69);
        final String callSign = parseText(70, 111);
        final String vesselName = parseText(112, 231);
        final int shipType = parseInteger(232, 239);
        final int dimensionToBow = parseInteger(240, 248);
        final int dimensionToStern = parseInteger(249, 257);
        final int dimensionToPort = parseInteger(258, 263);
        final int dimensionToStarboard = parseInteger(264, 269);
        final int positionFixType = parseInteger(270, 273);
        final int etaMonth = parseInteger(274, 277);
        final int etaDay = parseInteger(278, 282);
        final int etaHour = parseInteger(283, 287);
        final int etaMinute = parseInteger(288, 293);
        final double draught = parseInteger(294, 301) / 10.0;
        final String destination = parseText(302, 421);
        final boolean terminalReady = parseBoolean(422);

        final Message05 message = new Message05();
        message.setType(type);
        message.setRepeatIndicator(repeatIndicator);
        message.setSourceMmsi(sourceMmsi);
        message.setAisVersion(aisVersion);
        message.setImoNumber(imoNumber);
        message.setCallSign(callSign);
        message.setVesselName(vesselName);
        message.setShipType(shipType);
        message.setDimensionToBow(dimensionToBow);
        message.setDimensionToStern(dimensionToStern);
        message.setDimensionToPort(dimensionToPort);
        message.setDimensionToStarboard(dimensionToStarboard);
        message.setPositionFixType(positionFixType);
        message.setEtaMonth(etaMonth);
        message.setEtaDay(etaDay);
        message.setEtaHour(etaHour);
        message.setEtaMinute(etaMinute);
        message.setDraught(draught);
        message.setDestination(destination);
        message.setTerminalReady(terminalReady);
        return message;

    }

}
