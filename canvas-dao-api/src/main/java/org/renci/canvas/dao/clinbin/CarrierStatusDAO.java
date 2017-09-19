package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.CarrierStatus;

public interface CarrierStatusDAO extends BaseDAO<CarrierStatus, Integer> {

    public List<CarrierStatus> findAll() throws CANVASDAOException;

}
