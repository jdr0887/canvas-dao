package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiseaseClass;

public interface DiseaseClassDAO extends BaseDAO<DiseaseClass, Integer> {

    public List<DiseaseClass> findAll() throws CANVASDAOException;

}
