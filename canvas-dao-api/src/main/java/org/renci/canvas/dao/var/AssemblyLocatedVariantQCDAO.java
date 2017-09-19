package org.renci.canvas.dao.var;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariantQC;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariantQCPK;

public interface AssemblyLocatedVariantQCDAO extends BaseDAO<AssemblyLocatedVariantQC, AssemblyLocatedVariantQCPK> {

    public void delete(AssemblyLocatedVariantQC entity) throws CANVASDAOException;

    public void deleteByAssemblyId(Integer assemblyId) throws CANVASDAOException;

}
