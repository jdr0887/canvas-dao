package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.Assembly;

public interface AssemblyDAO extends BaseDAO<Assembly, Integer> {

    public void delete(Assembly entity) throws CANVASDAOException;

    public List<Assembly> findByVariantSetId(Integer variantSetId) throws CANVASDAOException;

    public List<Assembly> findBySampleName(String name) throws CANVASDAOException;

    public List<Assembly> findByLibraryId(Integer id) throws CANVASDAOException;

}
