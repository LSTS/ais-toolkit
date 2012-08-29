package de.baderjene.aistoolkit.aisreceiver.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The MMSI is the identifier of an AIS sender and occurs in every AIS message.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
@Entity
@Table(name = "mmsi")
public class MMSI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @OneToOne(mappedBy = "mmsi")
    private VesselData vesselData;

    @OneToMany(mappedBy = "mmsi")
    private List<PositionReport> positionReports;

    public MMSI() {
    }

    public MMSI(final int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public VesselData getVesselData() {
        return vesselData;
    }

    public void setVesselData(final VesselData data) {
        vesselData = data;
    }

    public List<PositionReport> getPositionReports() {
        return positionReports;
    }

    public void setPositionReports(final List<PositionReport> reports) {
        positionReports = reports;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

}
