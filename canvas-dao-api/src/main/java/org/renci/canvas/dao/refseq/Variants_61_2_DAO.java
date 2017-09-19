package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.Variants_61_2;
import org.renci.canvas.dao.refseq.model.Variants_61_2PK;

public interface Variants_61_2_DAO extends BaseDAO<Variants_61_2, Variants_61_2PK> {

    public List<Variants_61_2> findByLocatedVariantId(Long id) throws CANVASDAOException;

    public List<Variants_61_2> findByGeneName(String name) throws CANVASDAOException;

    public List<Variants_61_2> findByGeneId(Integer geneId) throws CANVASDAOException;

    public List<Variants_61_2> findByTranscriptAccession(String accession) throws CANVASDAOException;

    public List<Variants_61_2> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException;

    public List<Variants_61_2> findByAssemblyId(Integer id) throws CANVASDAOException;

    public List<Variants_61_2> findByAssemblyIdAndSampleNameAndHGMDVersionAndMaxFrequencyThresholdAndGeneId(Integer assemblyId,
            String sampleName, Integer hgmdVersion, Double threshold, Integer geneId) throws CANVASDAOException;

    public List<Variants_61_2> findByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList(Integer hgmdVersion, Double threshold,
            Integer geneId, List<String> variantEffectList) throws CANVASDAOException;

    public List<Variants_61_2> findKnownPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_61_2> findLikelyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_61_2> findPossiblyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_61_2> findUncertainSignificance(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_61_2> findLikelyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion) throws CANVASDAOException;

    public List<Variants_61_2> findAlmostCertainlyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public Long findByAssemblyIdAndVariantEffect(Integer assemblyId, String variantEffect) throws CANVASDAOException;

    public Long findByAssemblyIdAndVariantType(Integer assemblyId, String variantType) throws CANVASDAOException;

    public Long findTranscriptDependentCount(Integer assemblyId) throws CANVASDAOException;

    public Long findCodingCount(Integer assemblyId) throws CANVASDAOException;

    public Long findNonCodingCount(Integer assemblyId) throws CANVASDAOException;

}
