package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;

public interface DiagnosticResultVersionDAO extends BaseDAO<DiagnosticResultVersion, Integer> {

    public Integer findMaxResultVersion() throws CANVASDAOException;

    public List<DiagnosticResultVersion> findAll() throws CANVASDAOException;

}
