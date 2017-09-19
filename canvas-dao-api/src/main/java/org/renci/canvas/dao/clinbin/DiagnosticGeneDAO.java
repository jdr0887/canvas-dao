package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene;

public interface DiagnosticGeneDAO extends BaseDAO<DiagnosticGene, Integer> {

    public Integer findMaxDiagnosticListVersionByDxId(Integer dxId) throws CANVASDAOException;

    public List<DiagnosticGene> findByGeneIdAndDXId(Integer geneId, Integer dxId) throws CANVASDAOException;

    public List<DiagnosticGene> findByGroupVersionAndExternalNamespaceAndVersion(Integer groupVersion, String namespace, String version)
            throws CANVASDAOException;

    public List<DiagnosticGene> findByExample(DiagnosticGene diagnosticGene) throws CANVASDAOException;

}
