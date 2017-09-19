package org.renci.canvas.dao.clinvar;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;

public interface ClinVarVersionDAO extends BaseDAO<ClinVarVersion, Long> {

    public ClinVarVersion findLatestVersion() throws CANVASDAOException;

}
