package de.baderjene.aistoolkit.aisreceiver.persistence;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.baderjene.aistoolkit.aisparser.message.Message01;
import de.baderjene.aistoolkit.aisparser.message.Message05;

/**
 * The persistence class is used to store AIS messages to the database and to read from the database.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class Persistence {

    @Autowired
    private SessionFactory factory;

    private MMSI getMMSI(final int mmsiNumber) {
        final Session session = factory.getCurrentSession();
        final Criteria criteria = session.createCriteria(MMSI.class);
        criteria.add(Restrictions.eq("number", mmsiNumber));
        MMSI mmsi = (MMSI) criteria.uniqueResult();
        if (mmsi == null) {
            mmsi = new MMSI(mmsiNumber);
            session.save(mmsi);
        }
        return mmsi;
    }

    /**
     * Saves position reports to the database.
     * 
     * @param dto the position report
     */
    public void save(final Message01 dto) {
        final Session session = factory.getCurrentSession();
        final PositionReport report = new PositionReport();
        report.setCreationDate(new Date());
        report.setMmsi(getMMSI(dto.getSourceMmsi()));
        report.setRepeatIndicator(dto.getRepeatIndicator());
        report.setNavigationStatus(dto.getNavigationStatus());
        report.setRateOfTurn(dto.getRateOfTurn());
        report.setSpeedOverGround(dto.getSpeedOverGround());
        report.setPositionAccurate(dto.isPositionAccurate());
        report.setLongitude(dto.getLongitude());
        report.setLatitude(dto.getLatitude());
        report.setCourseOverGround(dto.getCourseOverGround());
        report.setTrueHeading(dto.getTrueHeading());
        report.setTimestamp(dto.getTimestamp());
        report.setManeuverIndicator(dto.getManeuverIndicator());
        report.setRaimUsed(dto.isRaimUsed());
        report.setRadioStatus(dto.getRadioStatus());
        session.save(report);
    }

    /**
     * Saves vessel data to the database.
     * 
     * @param dto the vessel data
     */
    public void save(final Message05 dto) {

        final MMSI mmsi = getMMSI(dto.getSourceMmsi());

        final Session session = factory.getCurrentSession();
        final Criteria criteria = session.createCriteria(VesselData.class);
        criteria.add(Restrictions.eq("mmsi", mmsi));
        VesselData vesselData = (VesselData) criteria.uniqueResult();
        if (vesselData == null) {
            vesselData = new VesselData();
        }
        vesselData.setMmsi(mmsi);
        vesselData.setAisVersion(dto.getAisVersion());
        vesselData.setImoNumber(dto.getImoNumber());
        vesselData.setCallSign(dto.getCallSign().trim());
        vesselData.setVesselName(dto.getVesselName().trim());
        vesselData.setShipType(dto.getShipType());
        vesselData.setDimensionToBow(dto.getDimensionToBow());
        vesselData.setDimensionToStern(dto.getDimensionToStern());
        vesselData.setDimensionToPort(dto.getDimensionToPort());
        vesselData.setDimensionToStarboard(dto.getDimensionToStarboard());
        vesselData.setPositionFixType(dto.getPositionFixType());
        vesselData.setEtaMonth(dto.getEtaMonth());
        vesselData.setEtaDay(dto.getEtaDay());
        vesselData.setEtaHour(dto.getEtaHour());
        vesselData.setEtaMinute(dto.getEtaMinute());
        vesselData.setDraught(dto.getDraught());
        vesselData.setDestination(dto.getDestination().trim());
        vesselData.setTerminalReady(dto.isTerminalReady());
        session.saveOrUpdate(vesselData);
    }

    /**
     * Get the latest position report which occured within the last five minutes from each vessel.
     * 
     * @return all position reports from the last five minutes
     */
    @SuppressWarnings("unchecked")
    public List<PositionReport> getLatestPositionData() {
        final Session session = factory.getCurrentSession();
        final String hql = "from PositionReport report where creationDate > :period and creationDate = (select max(creationDate) from PositionReport where mmsi = report.mmsi)";
        final Query query = session.createQuery(hql);
        query.setTimestamp("period", DateUtils.addMinutes(new Date(), -5));
        return query.list();
    }

    /**
     * Deletes all position reports that are older than 24 hours. It is triggered by a cron scheduler every three hours.
     */
    public void cleanup() {
        final Session session = factory.getCurrentSession();
        final String hql = "delete from PositionReport where creationDate < :expireDate";
        final Query query = session.createQuery(hql);
        query.setTimestamp("expireDate", DateUtils.addHours(new Date(), -24));
        query.executeUpdate();
    }
}
