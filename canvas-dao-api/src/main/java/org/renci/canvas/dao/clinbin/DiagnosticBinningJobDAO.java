package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiagnosticBinningJob;

public interface DiagnosticBinningJobDAO extends BaseDAO<DiagnosticBinningJob, Integer> {

    public List<DiagnosticBinningJob> findAvailableJobs() throws CANVASDAOException;

    public List<DiagnosticBinningJob> findCompletedJobs() throws CANVASDAOException;

    public List<DiagnosticBinningJob> findCompletedJobsByStudy(String study) throws CANVASDAOException;

    public List<DiagnosticBinningJob> findByExample(DiagnosticBinningJob binningJob) throws CANVASDAOException;

}
