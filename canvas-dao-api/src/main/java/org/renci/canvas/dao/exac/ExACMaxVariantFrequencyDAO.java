package org.renci.canvas.dao.exac;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequencyPK;

public interface ExACMaxVariantFrequencyDAO extends BaseDAO<ExACMaxVariantFrequency, ExACMaxVariantFrequencyPK> {

    public List<ExACMaxVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException;

    public List<ExACMaxVariantFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

    public List<ExACMaxVariantFrequency> findByLocatedVariantIdAndFrequencyThreshold(Long locVarId, Double threshold) throws CANVASDAOException;

    public List<ExACMaxVariantFrequency> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException;

}
