package de.baderjene.aistoolkit.aisreceiver.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Stores information about vessels.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
@Entity
@Table(name = "vessel_data")
public class VesselData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "mmsi_id")
    private MMSI mmsi;

    @Column(name = "ais_version")
    private int aisVersion;

    @Column(name = "imo")
    private int imoNumber;

    @Column(name = "callsign")
    private String callSign;

    @Column(name = "vesselname")
    private String vesselName;

    @Column(name = "shiptype")
    private int shipType;

    @Column(name = "dimension_to_bow")
    private int dimensionToBow;

    @Column(name = "dimension_to_stern")
    private int dimensionToStern;

    @Column(name = "dimension_to_port")
    private int dimensionToPort;

    @Column(name = "dimension_to_starboard")
    private int dimensionToStarboard;

    @Column(name = "position_fix_type")
    private int positionFixType;

    @Column(name = "eta_month")
    private int etaMonth;

    @Column(name = "eta_day")
    private int etaDay;

    @Column(name = "eta_hour")
    private int etaHour;

    @Column(name = "eta_minute")
    private int etaMinute;

    @Column(name = "draught")
    private double draught;

    @Column(name = "destination")
    private String destination;

    @Column(name = "terminal_ready")
    private boolean terminalReady;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public MMSI getMmsi() {
        return mmsi;
    }

    public void setMmsi(final MMSI mmsi) {
        this.mmsi = mmsi;
    }

    public int getAisVersion() {
        return aisVersion;
    }

    public void setAisVersion(final int aisVersion) {
        this.aisVersion = aisVersion;
    }

    public int getImoNumber() {
        return imoNumber;
    }

    public void setImoNumber(final int imoNumber) {
        this.imoNumber = imoNumber;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(final String callSign) {
        this.callSign = callSign;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(final String vesselName) {
        this.vesselName = vesselName;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(final int shipType) {
        this.shipType = shipType;
    }

    public int getDimensionToBow() {
        return dimensionToBow;
    }

    public void setDimensionToBow(final int dimensionToBow) {
        this.dimensionToBow = dimensionToBow;
    }

    public int getDimensionToStern() {
        return dimensionToStern;
    }

    public void setDimensionToStern(final int dimensionToStern) {
        this.dimensionToStern = dimensionToStern;
    }

    public int getDimensionToPort() {
        return dimensionToPort;
    }

    public void setDimensionToPort(final int dimensionToPort) {
        this.dimensionToPort = dimensionToPort;
    }

    public int getDimensionToStarboard() {
        return dimensionToStarboard;
    }

    public void setDimensionToStarboard(final int dimensionToStarboard) {
        this.dimensionToStarboard = dimensionToStarboard;
    }

    public int getPositionFixType() {
        return positionFixType;
    }

    public void setPositionFixType(final int positionFixType) {
        this.positionFixType = positionFixType;
    }

    public int getEtaMonth() {
        return etaMonth;
    }

    public void setEtaMonth(final int etaMonth) {
        this.etaMonth = etaMonth;
    }

    public int getEtaDay() {
        return etaDay;
    }

    public void setEtaDay(final int etaDay) {
        this.etaDay = etaDay;
    }

    public int getEtaHour() {
        return etaHour;
    }

    public void setEtaHour(final int etaHour) {
        this.etaHour = etaHour;
    }

    public int getEtaMinute() {
        return etaMinute;
    }

    public void setEtaMinute(final int etaMinute) {
        this.etaMinute = etaMinute;
    }

    public double getDraught() {
        return draught;
    }

    public void setDraught(final double draught) {
        this.draught = draught;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public boolean isTerminalReady() {
        return terminalReady;
    }

    public void setTerminalReady(final boolean terminalReady) {
        this.terminalReady = terminalReady;
    }

}
