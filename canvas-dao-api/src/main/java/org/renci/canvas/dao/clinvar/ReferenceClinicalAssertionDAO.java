package org.renci.canvas.dao.clinvar;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.model.ReferenceClinicalAssertion;

public interface ReferenceClinicalAssertionDAO extends BaseDAO<ReferenceClinicalAssertion, Long> {

    public List<ReferenceClinicalAssertion> findDiagnostic(Long dxId, String participant, Integer resultVersion) throws CANVASDAOException;

    public List<ReferenceClinicalAssertion> findIncidental(Long incidentalBinId, String participant, Integer resultVersion)
            throws CANVASDAOException;

    public List<ReferenceClinicalAssertion> findRisk(Long incidentalBinId, String participant, Integer resultVersion)
            throws CANVASDAOException;

    public List<ReferenceClinicalAssertion> findByLocatedVariantId(Long locVarId) throws CANVASDAOException;

    public List<ReferenceClinicalAssertion> findByLocatedVariantIdAndVersion(Long locVarId, Long version) throws CANVASDAOException;

    public List<ReferenceClinicalAssertion> findByLocatedVariantIdAndVersionAndAssertionStatusExclusionList(Long locVarId, Long version,
            List<String> assertionStatusExcludes) throws CANVASDAOException;

}
