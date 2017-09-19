package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.LocatedVariant;

public interface LocatedVariantDAO extends BaseDAO<LocatedVariant, Long> {

    public List<LocatedVariant> findIncrementable(Integer genomeRefId) throws CANVASDAOException;

    public List<LocatedVariant> findByGeneSymbol(String symbol) throws CANVASDAOException;

    public List<LocatedVariant> findByExample(LocatedVariant locatedVariant) throws CANVASDAOException;

    public List<LocatedVariant> findByAssemblyId(Integer assemblyId) throws CANVASDAOException;

    public List<LocatedVariant> findByCanonicalAlleleId(Integer canonicalAlleleId) throws CANVASDAOException;

    public void delete(LocatedVariant entity) throws CANVASDAOException;

    public List<LocatedVariant> findByVersionAccessionAndRefId(String verAccession, Integer genomeRefId) throws CANVASDAOException;

}
