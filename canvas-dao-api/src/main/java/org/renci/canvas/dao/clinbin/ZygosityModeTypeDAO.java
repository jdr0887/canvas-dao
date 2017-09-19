package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.ZygosityModeType;

public interface ZygosityModeTypeDAO extends BaseDAO<ZygosityModeType, String> {

    public List<ZygosityModeType> findAll() throws CANVASDAOException;

}
