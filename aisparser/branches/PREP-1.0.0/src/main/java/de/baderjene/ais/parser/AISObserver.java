package de.baderjene.ais.parser;

/**
 * This is the interface for ais observers.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public interface AISObserver {

    /**
     * Receive notifications for new position reports.
     * 
     * @param report Position report
     */
    void update(PositionReportDTO report);

    /**
     * Receive notifications for new shipdata.
     * 
     * @param data Shipdata
     */
    void update(VesselDataDTO data);

}
