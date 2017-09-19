package org.renci.canvas.dao.onekgen;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulation;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulationPK;

public interface OneKGenomesSNPFrequencyPopulationDAO extends BaseDAO<OneKGenomesSNPFrequencyPopulation, OneKGenomesSNPFrequencyPopulationPK> {

    public List<OneKGenomesSNPFrequencyPopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version)
            throws CANVASDAOException;

}
