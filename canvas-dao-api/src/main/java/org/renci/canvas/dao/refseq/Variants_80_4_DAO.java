package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.Variants_80_4;
import org.renci.canvas.dao.refseq.model.Variants_80_4PK;

public interface Variants_80_4_DAO extends BaseDAO<Variants_80_4, Variants_80_4PK> {

    public List<Variants_80_4> findByLocatedVariantId(Long id) throws CANVASDAOException;

    public List<Variants_80_4> findByGeneName(String name) throws CANVASDAOException;

    public List<Variants_80_4> findByGeneId(Integer geneId) throws CANVASDAOException;

    public List<Variants_80_4> findByTranscriptAccession(String accession) throws CANVASDAOException;

    public List<Variants_80_4> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException;

    public List<Variants_80_4> findByAssemblyId(Integer id) throws CANVASDAOException;

    public List<Variants_80_4> findByAssemblyIdAndSampleNameAndHGMDVersionAndMaxFrequencyThresholdAndGeneId(Integer assemblyId,
            String sampleName, Integer hgmdVersion, Double threshold, Integer geneId) throws CANVASDAOException;

    public List<Variants_80_4> findByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList(Integer hgmdVersion, Double threshold,
            Integer geneId, List<String> variantEffectList) throws CANVASDAOException;

    public List<Variants_80_4> findKnownPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_80_4> findLikelyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_80_4> findPossiblyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_80_4> findUncertainSignificance(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public List<Variants_80_4> findLikelyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion) throws CANVASDAOException;

    public List<Variants_80_4> findAlmostCertainlyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException;

    public Long findByAssemblyIdAndVariantEffect(Integer assemblyId, String variantEffect) throws CANVASDAOException;

    public Long findByAssemblyIdAndVariantType(Integer assemblyId, String variantType) throws CANVASDAOException;

    public Long findTranscriptDependentCount(Integer assemblyId) throws CANVASDAOException;

    public Long findCodingCount(Integer assemblyId) throws CANVASDAOException;

    public Long findNonCodingCount(Integer assemblyId) throws CANVASDAOException;

    public void deleteByLocatedVariantId(Long id) throws CANVASDAOException;

    public void deleteByLocatedVariantIdList(List<Long> id) throws CANVASDAOException;

}
