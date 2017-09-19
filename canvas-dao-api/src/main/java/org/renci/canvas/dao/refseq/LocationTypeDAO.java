package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.LocationType;

public interface LocationTypeDAO extends BaseDAO<LocationType, String> {

    public List<LocationType> findAll() throws CANVASDAOException;

}
