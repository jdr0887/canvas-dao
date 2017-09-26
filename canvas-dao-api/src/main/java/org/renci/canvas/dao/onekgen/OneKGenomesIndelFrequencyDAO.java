package org.renci.canvas.dao.onekgen;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequencyPK;

public interface OneKGenomesIndelFrequencyDAO extends BaseDAO<OneKGenomesIndelFrequency, OneKGenomesIndelFrequencyPK> {

    public List<OneKGenomesIndelFrequency> findByLocatedVariantIdAndVersion(Long locVarId, Integer version) throws CANVASDAOException;

    public List<OneKGenomesIndelFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

}
