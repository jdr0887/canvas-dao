package org.renci.canvas.dao.gnomad;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequency;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequencyPK;

public interface GnomADVariantFrequencyDAO extends BaseDAO<GnomADVariantFrequency, GnomADVariantFrequencyPK> {

    public List<GnomADVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException;

}
