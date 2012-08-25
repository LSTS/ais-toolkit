package de.baderjene.aistoolkit.aisreceiver.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class holds the information of a position report.
 * 
 * @author Patrick Gotthard
 * 
 */
@Entity
@Table(name = "position_report")
public class PositionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "mmsi_id")
    private MMSI mmsi;

    @Column(name = "repeat_indicator")
    private int repeatIndicator;

    @Column(name = "navigation_status")
    private int navigationStatus;

    @Column(name = "rate_of_turn")
    private double rateOfTurn;

    @Column(name = "speed_over_ground")
    private double speedOverGround;

    @Column(name = "position_accurate")
    private boolean positionAccurate;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "course_over_ground")
    private double courseOverGround;

    @Column(name = "true_heading")
    private int trueHeading;

    @Column(name = "timestamp")
    private int timestamp;

    @Column(name = "maneuver_indicator")
    private int maneuverIndicator;

    @Column(name = "raim_used")
    private boolean raimUsed;

    @Column(name = "radio_status")
    private int radioStatus;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public MMSI getMmsi() {
        return mmsi;
    }

    public void setMmsi(final MMSI mmsi) {
        this.mmsi = mmsi;
    }

    public int getRepeatIndicator() {
        return repeatIndicator;
    }

    public void setRepeatIndicator(final int repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }

    public int getNavigationStatus() {
        return navigationStatus;
    }

    public void setNavigationStatus(final int navigationStatus) {
        this.navigationStatus = navigationStatus;
    }

    public double getRateOfTurn() {
        return rateOfTurn;
    }

    public void setRateOfTurn(final double rateOfTurn) {
        this.rateOfTurn = rateOfTurn;
    }

    public double getSpeedOverGround() {
        return speedOverGround;
    }

    public void setSpeedOverGround(final double speedOverGround) {
        this.speedOverGround = speedOverGround;
    }

    public boolean isPositionAccurate() {
        return positionAccurate;
    }

    public void setPositionAccurate(final boolean positionAccurate) {
        this.positionAccurate = positionAccurate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getCourseOverGround() {
        return courseOverGround;
    }

    public void setCourseOverGround(final double courseOverGround) {
        this.courseOverGround = courseOverGround;
    }

    public int getTrueHeading() {
        return trueHeading;
    }

    public void setTrueHeading(final int trueHeading) {
        this.trueHeading = trueHeading;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    public int getManeuverIndicator() {
        return maneuverIndicator;
    }

    public void setManeuverIndicator(final int maneuverIndicator) {
        this.maneuverIndicator = maneuverIndicator;
    }

    public boolean isRaimUsed() {
        return raimUsed;
    }

    public void setRaimUsed(final boolean raimUsed) {
        this.raimUsed = raimUsed;
    }

    public int getRadioStatus() {
        return radioStatus;
    }

    public void setRadioStatus(final int radioStatus) {
        this.radioStatus = radioStatus;
    }

}
