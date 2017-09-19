package org.renci.canvas.dao.onekgen;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequencyPK;

public interface OneKGenomesIndelMaxFrequencyDAO extends BaseDAO<OneKGenomesIndelMaxFrequency, OneKGenomesIndelMaxFrequencyPK> {

    public List<OneKGenomesIndelMaxFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

    public Integer findLatestVersion() throws CANVASDAOException;

}
