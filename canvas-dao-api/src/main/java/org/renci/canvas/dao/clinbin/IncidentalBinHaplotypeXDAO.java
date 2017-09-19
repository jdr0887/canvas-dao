package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinHaplotypeX;
import org.renci.canvas.dao.clinbin.model.IncidentalBinHaplotypeXPK;

public interface IncidentalBinHaplotypeXDAO extends BaseDAO<IncidentalBinHaplotypeX, IncidentalBinHaplotypeXPK> {

    public List<IncidentalBinHaplotypeX> findAll() throws CANVASDAOException;

    public List<IncidentalBinHaplotypeX> findByIncidentalBinIdAndVersionAndAssemblyIdAndHGMDVersionAndZygosityMode(Integer id,
            Integer version, Integer assemblyId, Integer hgmdVersion, List<String> zygosityModes) throws CANVASDAOException;

    public List<IncidentalBinHaplotypeX> findByIncidentalBinIdAndVersion(Integer id, Integer version) throws CANVASDAOException;

}
