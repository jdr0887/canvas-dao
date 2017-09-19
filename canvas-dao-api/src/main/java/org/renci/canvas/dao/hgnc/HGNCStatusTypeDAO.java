package org.renci.canvas.dao.hgnc;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.model.HGNCStatusType;

public interface HGNCStatusTypeDAO extends BaseDAO<HGNCStatusType, String> {

    public List<HGNCStatusType> findAll() throws CANVASDAOException;

}
