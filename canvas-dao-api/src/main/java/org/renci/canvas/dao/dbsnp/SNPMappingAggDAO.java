package org.renci.canvas.dao.dbsnp;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.dbsnp.model.SNPMappingAgg;
import org.renci.canvas.dao.dbsnp.model.SNPMappingAggPK;

public interface SNPMappingAggDAO extends BaseDAO<SNPMappingAgg, SNPMappingAggPK> {

    public List<SNPMappingAgg> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException;

}
