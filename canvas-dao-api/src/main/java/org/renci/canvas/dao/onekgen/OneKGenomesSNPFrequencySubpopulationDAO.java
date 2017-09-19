package org.renci.canvas.dao.onekgen;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulation;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulationPK;

public interface OneKGenomesSNPFrequencySubpopulationDAO
        extends BaseDAO<OneKGenomesSNPFrequencySubpopulation, OneKGenomesSNPFrequencySubpopulationPK> {

    public List<OneKGenomesSNPFrequencySubpopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version)
            throws CANVASDAOException;

}
