package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiagnosticStatusType;

public interface DiagnosticStatusTypeDAO extends BaseDAO<DiagnosticStatusType, String> {

    public List<DiagnosticStatusType> findAll() throws CANVASDAOException;

}
