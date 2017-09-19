package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskX;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskXPK;

public interface BinResultsFinalRiskXDAO extends BaseDAO<BinResultsFinalRiskX, BinResultsFinalRiskXPK> {

    public List<BinResultsFinalRiskX> findByParticipantAndIndicentalBinIdAndResultVersion(String participant, Integer incidentalBinId,
            Integer resultVersion) throws CANVASDAOException;

}
