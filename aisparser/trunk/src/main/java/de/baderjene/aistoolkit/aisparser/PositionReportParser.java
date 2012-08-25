package de.baderjene.aistoolkit.aisparser;

/**
 * Parse position report data from ais messages of types 1, 2 and 3.<br />
 * Refer to http://gpsd.berlios.de/AIVDM.html for more information.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class PositionReportParser {

    /**
     * Parses a position report.
     * 
     * @param message an ais message of type 1, 2 or 3
     * @return the parsed position report
     */
    public PositionReportDTO parse(final Message message) {
        final BinaryParser parser = new BinaryParser(message.getBinary());
        final PositionReportDTO report = new PositionReportDTO();
        report.setAisMessageType(message.getAisMessageType());
        report.setRepeatIndicator(message.getRepeatIndicator());
        report.setMmsi(message.getMmsi());
        report.setNavigationStatus(parser.parseInteger(38, 41));
        report.setRateOfTurn(parser.parseSignedInteger(42, 49));
        report.setSpeedOverGround(parser.parseInteger(50, 59) / 10.0);
        report.setPositionAccurate(parser.parseBoolean(60));
        report.setLongitude(parser.parseInteger(61, 88) / 600000.0);
        report.setLatitude(parser.parseInteger(89, 115) / 600000.0);
        report.setCourseOverGround(parser.parseInteger(116, 127) / 10.0);
        report.setTrueHeading(parser.parseInteger(128, 136));
        report.setTimestamp(parser.parseInteger(137, 142));
        report.setManeuverIndicator(parser.parseInteger(143, 144));
        report.setRaimUsed(parser.parseBoolean(148));
        report.setRadioStatus(parser.parseInteger(149, 167));
        return report;
    }

}
