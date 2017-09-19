package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.Sample;

public interface SampleDAO extends BaseDAO<Sample, Integer> {

    public void delete(Sample entity) throws CANVASDAOException;

    public List<Sample> findByNameAndProjectName(String name, String projectName) throws CANVASDAOException;

    public List<Sample> findByName(String name) throws CANVASDAOException;

}
