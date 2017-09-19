package org.renci.canvas.dao.dbsnp;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.dbsnp.model.SNP;

public interface SNPDAO extends BaseDAO<SNP, Integer> {

    public String findLatestVersion() throws CANVASDAOException;

}
