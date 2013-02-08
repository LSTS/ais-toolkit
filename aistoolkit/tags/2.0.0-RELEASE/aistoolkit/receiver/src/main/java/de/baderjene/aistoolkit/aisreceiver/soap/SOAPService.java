package de.baderjene.aistoolkit.aisreceiver.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import de.baderjene.aistoolkit.aisreceiver.persistence.Persistence;
import de.baderjene.aistoolkit.aisreceiver.persistence.PositionReport;
import de.baderjene.aistoolkit.aisreceiver.soap.VesselStatus;
import de.baderjene.aistoolkit.aisreceiver.soap.VesselStatus.UnderWay;

/**
 * Implementation of the ais webservice.
 * 
 * @author Patrick Gotthard
 * 
 */
@WebService
public class SOAPService {

    @Autowired
    private Persistence persistence;

    /**
     * Get a vessel status by imo number.
     * 
     * @param imo the vessels imo number
     * @return a list of vessel statuses<br />
     *         an empty list if the vessel could not be found
     */
    public List<VesselStatus> getVesselStatusByImo(final int imo) {
        final List<PositionReport> positionReports = persistence.getShipStatusByImo(imo);
        return processVesselStatus(positionReports);
    }

    /**
     * Get a vessel status by call sign.
     * 
     * @param callSign a callsign of a vessel
     * @return a list of vessel statuses<br />
     *         an empty list if the vessel could not be found
     */
    public List<VesselStatus> getVesselStatusByCallSign(final String callSign) {
        final List<PositionReport> positionReports = persistence.getShipStatusByCallSign(callSign);
        return processVesselStatus(positionReports);
    }

    /**
     * Get a vessel status by vesselname.
     * 
     * @param vesselName a vessel name
     * @return a list of vessel statuses<br />
     *         an empty list if the vessel could not be found
     */
    public List<VesselStatus> getVesselStatusByVesselName(final String vesselName) {
        final List<PositionReport> positionReports = persistence.getShipStatusByVesselName(vesselName);
        return processVesselStatus(positionReports);
    }

    /**
     * Processes the vessels status by the last given position report.
     * 
     * @param report
     * @return the generated status from the vessels position report<br />
     *         null if no position report was commited.
     */
    private List<VesselStatus> processVesselStatus(final List<PositionReport> reports) {

        final List<VesselStatus> statusList = new ArrayList<VesselStatus>();

        for (final PositionReport report : reports) {

            final VesselStatus status = new VesselStatus();
            status.setCreationDate(report.getCreationDate());
            status.setLongitude(report.getLongitude());
            status.setLatitude(report.getLatitude());

            if (report.getSpeedOverGround() > 0 && report.getSpeedOverGround() < 102.3) {
                status.setUnderWay(UnderWay.YES);
            } else {
                final int navStatus = report.getNavigationStatus();
                if (navStatus == 0 || navStatus == 3 || navStatus == 4 || navStatus == 7 || navStatus == 8) {
                    status.setUnderWay(UnderWay.YES);
                } else if (navStatus == 1 || navStatus == 2 || navStatus == 5 || navStatus == 6) {
                    status.setUnderWay(UnderWay.NO);
                } else {
                    status.setUnderWay(UnderWay.UNKNOWN);
                }
            }

            statusList.add(status);
        }

        return statusList;
    }
}
