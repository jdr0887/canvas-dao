package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.MaxFrequencySource;

public interface MaxFrequencySourceDAO extends BaseDAO<MaxFrequencySource, String> {

    public List<MaxFrequencySource> findAll() throws CANVASDAOException;

}
