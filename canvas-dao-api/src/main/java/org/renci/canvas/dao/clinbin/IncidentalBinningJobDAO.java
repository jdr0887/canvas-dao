package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinningJob;

public interface IncidentalBinningJobDAO extends BaseDAO<IncidentalBinningJob, Integer> {

    public List<IncidentalBinningJob> findAvailableJobs() throws CANVASDAOException;

    public List<IncidentalBinningJob> findCompletedJobs() throws CANVASDAOException;

    public List<IncidentalBinningJob> findCompletedJobsByStudy(String study) throws CANVASDAOException;

    public List<IncidentalBinningJob> findByExample(IncidentalBinningJob binningJob) throws CANVASDAOException;

}
