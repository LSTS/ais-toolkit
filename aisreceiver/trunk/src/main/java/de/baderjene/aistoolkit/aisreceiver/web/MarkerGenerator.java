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

public class MarkerGenerator implements HttpRequestHandler {

    @Autowired
    private Persistence persistence;

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        final ServletOutputStream out = response.getOutputStream();
        out.println("function showMap() {");
        out.println("   initMap();");
        for (final PositionReport report : persistence.getLatestPositionData()) {
            final double longitude = report.getLongitude();
            final double latitude = report.getLatitude();
            final int trueHeading = report.getTrueHeading();
            if (report.getMmsi().getVesselData() == null) {
                out.println("   addMarker(" + longitude + ", " + latitude + ", " + trueHeading + ", \"UNKNOWN\");");
            } else {
                final String vesselName = report.getMmsi().getVesselData().getVesselName();
                out.println("   addMarker(" + longitude + ", " + latitude + ", " + trueHeading + ", \"" + vesselName + "\");");
            }
        }
        out.println("}");
        out.flush();
        out.close();
    }
}
