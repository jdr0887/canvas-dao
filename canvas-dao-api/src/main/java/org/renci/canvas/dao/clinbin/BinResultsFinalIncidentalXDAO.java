package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalX;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalXPK;

public interface BinResultsFinalIncidentalXDAO extends BaseDAO<BinResultsFinalIncidentalX, BinResultsFinalIncidentalXPK> {

    public List<BinResultsFinalIncidentalX> findByParticipantAndIncidentalBinIdAndResultVersion(String participant, Integer incidentalBinId,
            Integer version) throws CANVASDAOException;

    public List<BinResultsFinalIncidentalX> findByParticipantAndIncidentalBinIdAndResultVersionAndCarrierStatusId(String participant,
            Integer incidentalBinId, Integer version, Integer carrierStatusId) throws CANVASDAOException;

}
