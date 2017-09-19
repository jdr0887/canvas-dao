package org.renci.canvas.dao.onekgen;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequencyPK;

public interface OneKGenomesSNPPopulationMaxFrequencyDAO
        extends BaseDAO<OneKGenomesSNPPopulationMaxFrequency, OneKGenomesSNPPopulationMaxFrequencyPK> {

    public List<OneKGenomesSNPPopulationMaxFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

    public Integer findLatestVersion() throws CANVASDAOException;

}
