package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGroupVersionX;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGroupVersionXPK;

public interface IncidentalBinGroupVersionXDAO extends BaseDAO<IncidentalBinGroupVersionX, IncidentalBinGroupVersionXPK> {

    public List<IncidentalBinGroupVersionX> findByIncidentalBinIdAndGroupVersion(Integer id, Integer groupVersion)
            throws CANVASDAOException;

}
