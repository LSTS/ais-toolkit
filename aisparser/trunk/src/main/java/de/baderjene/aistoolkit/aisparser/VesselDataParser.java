package de.baderjene.aistoolkit.aisparser;

/**
 * Parses vessel data information of ais message type 5.<br />
 * Refer to http://gpsd.berlios.de/AIVDM.html for more information.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class VesselDataParser {

    /**
     * Parses vessel data from an ais message of type 5
     * 
     * @param message ais message of type 5
     * @return the parsed vessel data
     */
    public VesselDataDTO parse(final Message message) {
        final BinaryParser parser = new BinaryParser(message.getBinary());
        final VesselDataDTO data = new VesselDataDTO();
        data.setAisMessageType(message.getAisMessageType());
        data.setRepeatIndicator(message.getRepeatIndicator());
        data.setMmsi(message.getMmsi());
        data.setAisVersion(parser.parseInteger(38, 39));
        data.setImoNumber(parser.parseInteger(40, 69));
        data.setCallSign(parser.parseText(70, 111));
        data.setVesselName(parser.parseText(112, 231));
        data.setShipType(parser.parseInteger(232, 239));
        data.setDimensionToBow(parser.parseInteger(240, 248));
        data.setDimensionToStern(parser.parseInteger(249, 257));
        data.setDimensionToPort(parser.parseInteger(258, 263));
        data.setDimensionToStarboard(parser.parseInteger(264, 269));
        data.setPositionFixType(parser.parseInteger(270, 273));
        data.setEtaMonth(parser.parseInteger(274, 277));
        data.setEtaDay(parser.parseInteger(278, 282));
        data.setEtaHour(parser.parseInteger(283, 287));
        data.setEtaMinute(parser.parseInteger(288, 293));
        data.setDraught(parser.parseInteger(294, 301) / 10.0);
        data.setDestination(parser.parseText(302, 421));
        data.setTerminalReady(parser.parseBoolean(422));
        return data;
    }
}
