package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.VariantSet;

public interface VariantSetDAO extends BaseDAO<VariantSet, Integer> {

    public void delete(VariantSet entity) throws CANVASDAOException;

    public List<VariantSet> findByAssemblyId(Integer assemblyId) throws CANVASDAOException;

}
