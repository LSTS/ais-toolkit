package de.baderjene.aistoolkit.aisreceiver.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import de.baderjene.aistoolkit.aisreceiver.persistence.Persistence;
import de.baderjene.aistoolkit.aisreceiver.persistence.PositionReport;

/**
 * This servlet creates the JavaScript for showing markers on the OpenSeaMap.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public class MarkerGenerator implements HttpRequestHandler {

    @Autowired
    private Persistence persistence;

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final StringBuilder builder = new StringBuilder();
        builder.append(" function showMap() { ");
        builder.append(" initMap(); ");
        for (final PositionReport report : persistence.getLatestPositionData()) {
            final double longitude = report.getLongitude();
            final double latitude = report.getLatitude();
            final int trueHeading = report.getTrueHeading();
            String vesselName = "UNKNOWN";
            if (report.getMmsi().getVesselData() != null) {
                vesselName = report.getMmsi().getVesselData().getVesselName();
            }
            builder.append(" addMarker(" + longitude + ", " + latitude + ", " + trueHeading + ", \"" + vesselName + "\"); ");

        }
        builder.append(" } ");

        response.setContentType("text/plain; charset=UTF-8");
        final ServletOutputStream out = response.getOutputStream();
        out.println(builder.toString());
        out.flush();
        out.close();
    }
}
