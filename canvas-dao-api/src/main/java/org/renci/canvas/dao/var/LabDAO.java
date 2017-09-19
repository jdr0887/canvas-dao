package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.Lab;

public interface LabDAO extends BaseDAO<Lab, String> {

    public void delete(Lab entity) throws CANVASDAOException;

    public List<Lab> findByName(String name) throws CANVASDAOException;

}
