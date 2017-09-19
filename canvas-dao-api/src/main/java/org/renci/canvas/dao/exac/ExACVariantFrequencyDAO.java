package org.renci.canvas.dao.exac;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.exac.model.ExACVariantFrequency;
import org.renci.canvas.dao.exac.model.ExACVariantFrequencyPK;

public interface ExACVariantFrequencyDAO extends BaseDAO<ExACVariantFrequency, ExACVariantFrequencyPK> {

    public List<ExACVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException;

}
