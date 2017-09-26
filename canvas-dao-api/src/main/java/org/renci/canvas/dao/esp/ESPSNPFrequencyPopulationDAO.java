package org.renci.canvas.dao.esp;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulation;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulationPK;

public interface ESPSNPFrequencyPopulationDAO extends BaseDAO<ESPSNPFrequencyPopulation, ESPSNPFrequencyPopulationPK> {

    public List<ESPSNPFrequencyPopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version) throws CANVASDAOException;

    public List<ESPSNPFrequencyPopulation> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

}
