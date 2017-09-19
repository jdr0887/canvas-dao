package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinX;

public interface IncidentalBinXDAO extends BaseDAO<IncidentalBinX, Integer> {

    public List<IncidentalBinX> findByHGMDVersion(Integer hgmdVersion) throws CANVASDAOException;

}
