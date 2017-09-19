package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DX;

public interface DXDAO extends BaseDAO<DX, Integer> {

    public List<DX> findAll() throws CANVASDAOException;

    public List<DX> findByName(String name) throws CANVASDAOException;

}
