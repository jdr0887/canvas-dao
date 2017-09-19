package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalStatusType;

public interface IncidentalStatusTypeDAO extends BaseDAO<IncidentalStatusType, String> {

    public List<IncidentalStatusType> findAll() throws CANVASDAOException;

}
