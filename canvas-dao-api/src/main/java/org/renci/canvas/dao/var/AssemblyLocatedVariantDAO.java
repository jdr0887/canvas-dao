package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariantPK;

public interface AssemblyLocatedVariantDAO extends BaseDAO<AssemblyLocatedVariant, AssemblyLocatedVariantPK> {

    public void delete(AssemblyLocatedVariant entity) throws CANVASDAOException;

    public List<AssemblyLocatedVariant> findByAssemblyId(Integer assemblyId) throws CANVASDAOException;

    public List<AssemblyLocatedVariant> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException;

    public void deleteByAssemblyId(Integer assemblyId) throws CANVASDAOException;

}
