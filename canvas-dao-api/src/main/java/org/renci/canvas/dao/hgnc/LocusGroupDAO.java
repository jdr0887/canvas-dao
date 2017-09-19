package org.renci.canvas.dao.hgnc;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.model.LocusGroup;

public interface LocusGroupDAO extends BaseDAO<LocusGroup, String> {

    public List<LocusGroup> findAll() throws CANVASDAOException;

}
