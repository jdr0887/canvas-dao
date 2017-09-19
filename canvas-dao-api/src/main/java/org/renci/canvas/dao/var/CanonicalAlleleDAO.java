package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.CanonicalAllele;

public interface CanonicalAlleleDAO extends BaseDAO<CanonicalAllele, Integer> {

    public List<CanonicalAllele> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException;

}
