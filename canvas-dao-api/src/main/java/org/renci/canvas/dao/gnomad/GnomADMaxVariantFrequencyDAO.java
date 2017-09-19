package org.renci.canvas.dao.gnomad;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequency;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequencyPK;

public interface GnomADMaxVariantFrequencyDAO extends BaseDAO<GnomADMaxVariantFrequency, GnomADMaxVariantFrequencyPK> {

    public List<GnomADMaxVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException;

    public List<GnomADMaxVariantFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

    public List<GnomADMaxVariantFrequency> findByLocatedVariantIdAndFrequencyThreshold(Long locVarId, Double threshold)
            throws CANVASDAOException;

    public List<GnomADMaxVariantFrequency> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException;

    public String findLatestVersion() throws CANVASDAOException;

}
