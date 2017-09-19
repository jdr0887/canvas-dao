package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersionPK;

public interface DiagnosticGeneGroupVersionDAO extends BaseDAO<DiagnosticGeneGroupVersion, DiagnosticGeneGroupVersionPK> {

    public Integer findMaxDiagnosticBinGroupVersion() throws CANVASDAOException;

    public List<DiagnosticGeneGroupVersion> findByDXIdAndDiagnosticListVersion(Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<DiagnosticGeneGroupVersion> findByDBinGroupVersion(Integer dbinGroupVersion) throws CANVASDAOException;

}
