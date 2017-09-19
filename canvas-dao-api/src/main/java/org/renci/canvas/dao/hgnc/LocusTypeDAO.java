package org.renci.canvas.dao.hgnc;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.model.LocusType;

public interface LocusTypeDAO extends BaseDAO<LocusType, String> {

    public List<LocusType> findAll() throws CANVASDAOException;

}
