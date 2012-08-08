package de.baderjene.ais.parser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for the AIS Parser.
 * 
 * @author Patrick Gotthard
 * 
 */
public class AISParserTest implements AISObserver {

    private AISParser parser;
    private PositionReportDTO report;
    private VesselDataDTO data;

    /**
     * Setting up the test class.
     * 
     * @throws InvalidPacketException when a packet contains no valid ais data
     * @throws InvalidChecksumException when the checksum of an ais packet is incorrect
     */
    @Before
    public void setup() throws InvalidPacketException, InvalidChecksumException {
        parser = new AISParser();
        parser.register(this);
        // Position Report
        parser.process("!AIVDM,1,1,,A,13:<SD0018PfV>bO7INHNFfn04q0,0*72");
        // Vessel Data
        parser.process("!AIVDM,2,1,4,A,53aqG402??J50984000Dm0U8D000000000000017CP?==4EmNFQ1@TR00000,0*14");
        parser.process("!AIVDM,2,2,4,A,00000000000,2*20");
    }

    /**
     * Tests whether the position report notifications from the parser work correctly.
     */
    @Test
    public void positionReportNotificationTest() {
        Assert.assertNotNull(report);
        Assert.assertEquals(1, report.getAisMessageType());
        Assert.assertEquals(216.9, report.getCourseOverGround());
        Assert.assertEquals(54.37844166666667, report.getLatitude());
        Assert.assertEquals(10.179341666666666, report.getLongitude());
        Assert.assertEquals(0, report.getManeuverIndicator());
        Assert.assertEquals(212018000, report.getMmsi());
        Assert.assertEquals(20032, report.getRadioStatus());
        Assert.assertEquals(0.0, report.getRateOfTurn());
        Assert.assertEquals(0, report.getRepeatIndicator());
        Assert.assertEquals(7.2, report.getSpeedOverGround());
        Assert.assertEquals(27, report.getTimestamp());
        Assert.assertEquals(215, report.getTrueHeading());
        Assert.assertEquals(0, report.getNavigationStatus());
    }

    /**
     * Tests whether the vessel data notifications from the parser work correctly.
     */
    @Test
    public void vesselDataNotificationTest() {
        Assert.assertNotNull(data);
        Assert.assertEquals(5, data.getAisMessageType());
        Assert.assertEquals(0, data.getAisVersion());
        Assert.assertEquals("PBRA", data.getCallSign());
        Assert.assertEquals("DEBRH", data.getDestination());
        Assert.assertEquals(156, data.getDimensionToBow());
        Assert.assertEquals(15, data.getDimensionToStern());
        Assert.assertEquals(13, data.getDimensionToPort());
        Assert.assertEquals(13, data.getDimensionToStarboard());
        Assert.assertEquals(9.0, data.getDraught());
        Assert.assertEquals(11, data.getEtaDay());
        Assert.assertEquals(21, data.getEtaHour());
        Assert.assertEquals(30, data.getEtaMinute());
        Assert.assertEquals(1, data.getEtaMonth());
        Assert.assertEquals(9387425, data.getImoNumber());
        Assert.assertEquals(245258000, data.getMmsi());
        Assert.assertEquals(1, data.getPositionFixType());
        Assert.assertEquals(0, data.getRepeatIndicator());
        Assert.assertEquals(71, data.getShipType());
        Assert.assertEquals("EMPIRE", data.getVesselName());
    }

    @Override
    public void update(final PositionReportDTO report) {
        this.report = report;
    }

    @Override
    public void update(final VesselDataDTO data) {
        this.data = data;
    }

}
