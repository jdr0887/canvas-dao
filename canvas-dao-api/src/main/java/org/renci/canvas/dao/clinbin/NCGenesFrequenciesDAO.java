package org.renci.canvas.dao.clinbin;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequencies;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequenciesPK;

public interface NCGenesFrequenciesDAO extends BaseDAO<NCGenesFrequencies, NCGenesFrequenciesPK> {

    public Integer findMaxVersion() throws CANVASDAOException;

}
