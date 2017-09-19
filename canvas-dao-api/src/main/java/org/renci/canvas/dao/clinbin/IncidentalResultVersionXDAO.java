package org.renci.canvas.dao.clinbin;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalResultVersionX;

public interface IncidentalResultVersionXDAO extends BaseDAO<IncidentalResultVersionX, Integer> {

    public Integer findMaxResultVersion() throws CANVASDAOException;

}
