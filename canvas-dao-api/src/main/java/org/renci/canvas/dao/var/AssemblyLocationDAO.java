package org.renci.canvas.dao.var;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.AssemblyLocation;
import org.renci.canvas.dao.var.model.AssemblyLocationPK;

public interface AssemblyLocationDAO extends BaseDAO<AssemblyLocation, AssemblyLocationPK> {

    public void delete(AssemblyLocation entity) throws CANVASDAOException;

}
