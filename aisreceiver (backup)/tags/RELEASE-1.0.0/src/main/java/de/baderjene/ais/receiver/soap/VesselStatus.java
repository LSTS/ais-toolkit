package de.baderjene.ais.receiver.soap;

import java.util.Date;

/**
 * Stores the status of a vessel.
 * 
 * @author Patrick Gotthard
 * 
 */
public class VesselStatus {

    public enum UnderWay {
        YES,
        NO,
        UNKNOWN
    }

    private Date creationDate;
    private double longitude;
    private double latitude;
    private UnderWay underWay;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
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

    public UnderWay isUnderWay() {
        return underWay;
    }

    public void setUnderWay(final UnderWay underWay) {
        this.underWay = underWay;
    }

}
