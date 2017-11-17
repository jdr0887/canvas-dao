package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnosticPK;
import org.renci.canvas.dao.clinbin.model.DiagnosticBinningJob;

public interface BinResultsFinalDiagnosticDAO extends BaseDAO<BinResultsFinalDiagnostic, BinResultsFinalDiagnosticPK> {

    public List<BinResultsFinalDiagnostic> findByDXIdAndParticipantAndVersion(Long dxId, String participant, Integer version)
            throws CANVASDAOException;

    public Long findDXIdCount(String participant) throws CANVASDAOException;

    public void deleteByAssemblyId(Integer assemblyId) throws CANVASDAOException;

    public void deleteByDiagnosticBinningJobAndHGMDDiseaseClassId(DiagnosticBinningJob diagnosticBinningJob, Integer hgmdDiseaseClassId)
            throws CANVASDAOException;

    public void deleteByDiagnosticBinningJobAndClinVarDiseaseClassId(DiagnosticBinningJob diagnosticBinningJob,
            Integer clinvarDiseaseClassId) throws CANVASDAOException;

    public Long findAnalyzedVariantsCount(String participant) throws CANVASDAOException;

    public List<BinResultsFinalDiagnostic> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException;

    public Long findByAssemblyIdAndHGMDDiseaseClassId(Integer assemblyId, Integer diseaseClassId) throws CANVASDAOException;

    public Long findByAssemblyIdAndClinVarDiseaseClassId(Integer assemblyId, Integer diseaseClassId) throws CANVASDAOException;

    public List<BinResultsFinalDiagnostic> findByKeyAndHGMDDiseaseClassId(BinResultsFinalDiagnosticPK key, Integer diseaseClassId)
            throws CANVASDAOException;

    public List<BinResultsFinalDiagnostic> findByKeyAndClinVarDiseaseClassId(BinResultsFinalDiagnosticPK key, Integer diseaseClassId)
            throws CANVASDAOException;

    public Long findNullClinVarDiseaseClassCount(DiagnosticBinningJob diagnosticBinningJob) throws CANVASDAOException;

    public Long findNullHGMDDiseaseClassCount(DiagnosticBinningJob diagnosticBinningJob) throws CANVASDAOException;

}
