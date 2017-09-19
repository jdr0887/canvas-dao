package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DXCoverage;
import org.renci.canvas.dao.clinbin.model.DXCoveragePK;

public interface DXCoverageDAO extends BaseDAO<DXCoverage, DXCoveragePK> {

    public List<DXCoverage> findByDXIdAndParticipantAndListVersion(Long dxId, String participant, Integer listVersion)
            throws CANVASDAOException;

    public List<DXCoverage> findByParticipantAndListVersion(String participant, Integer listVersion) throws CANVASDAOException;

}
