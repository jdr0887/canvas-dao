package org.renci.canvas.dao;

import org.renci.canvas.dao.annotation.AnnotationGeneDAO;
import org.renci.canvas.dao.annotation.AnnotationGeneExternalIdDAO;
import org.renci.canvas.dao.annotation.AnnotationGeneSynonymDAO;
import org.renci.canvas.dao.clinbin.AnalysisClassDAO;
import org.renci.canvas.dao.clinbin.AnalysisClassIncidentalDAO;
import org.renci.canvas.dao.clinbin.AnalysisNoteDAO;
import org.renci.canvas.dao.clinbin.BinResultsFinalDiagnosticDAO;
import org.renci.canvas.dao.clinbin.BinResultsFinalIncidentalXDAO;
import org.renci.canvas.dao.clinbin.BinResultsFinalRiskXDAO;
import org.renci.canvas.dao.clinbin.CarrierStatusDAO;
import org.renci.canvas.dao.clinbin.ConfirmedTrackingDAO;
import org.renci.canvas.dao.clinbin.DXCoverageDAO;
import org.renci.canvas.dao.clinbin.DXDAO;
import org.renci.canvas.dao.clinbin.DXExonsDAO;
import org.renci.canvas.dao.clinbin.DiagnosticBinningJobDAO;
import org.renci.canvas.dao.clinbin.DiagnosticGeneDAO;
import org.renci.canvas.dao.clinbin.DiagnosticGeneGroupVersionDAO;
import org.renci.canvas.dao.clinbin.DiagnosticResultVersionDAO;
import org.renci.canvas.dao.clinbin.DiagnosticStatusTypeDAO;
import org.renci.canvas.dao.clinbin.DiseaseClassDAO;
import org.renci.canvas.dao.clinbin.IncidentalBinGeneXDAO;
import org.renci.canvas.dao.clinbin.IncidentalBinGroupVersionXDAO;
import org.renci.canvas.dao.clinbin.IncidentalBinHaplotypeXDAO;
import org.renci.canvas.dao.clinbin.IncidentalBinXDAO;
import org.renci.canvas.dao.clinbin.IncidentalBinningJobDAO;
import org.renci.canvas.dao.clinbin.IncidentalResultVersionXDAO;
import org.renci.canvas.dao.clinbin.IncidentalStatusTypeDAO;
import org.renci.canvas.dao.clinbin.MaxFrequencyDAO;
import org.renci.canvas.dao.clinbin.MaxFrequencySourceDAO;
import org.renci.canvas.dao.clinbin.MissingHaplotypeDAO;
import org.renci.canvas.dao.clinbin.NCGenesFrequenciesDAO;
import org.renci.canvas.dao.clinbin.PhenotypeXDAO;
import org.renci.canvas.dao.clinbin.ReportDAO;
import org.renci.canvas.dao.clinbin.UnimportantExonDAO;
import org.renci.canvas.dao.clinbin.ZygosityModeTypeDAO;
import org.renci.canvas.dao.clinvar.AssertionRankingDAO;
import org.renci.canvas.dao.clinvar.ClinVarVersionDAO;
import org.renci.canvas.dao.clinvar.ReferenceClinicalAssertionDAO;
import org.renci.canvas.dao.clinvar.SubmissionClinicalAssertionDAO;
import org.renci.canvas.dao.clinvar.TraitDAO;
import org.renci.canvas.dao.clinvar.TraitSetDAO;
import org.renci.canvas.dao.dbsnp.SNPAlleleDAO;
import org.renci.canvas.dao.dbsnp.SNPAlleleFrequencyDAO;
import org.renci.canvas.dao.dbsnp.SNPDAO;
import org.renci.canvas.dao.dbsnp.SNPGenotypeFrequencyDAO;
import org.renci.canvas.dao.dbsnp.SNPMappingAggDAO;
import org.renci.canvas.dao.dbsnp.SNPMappingDAO;
import org.renci.canvas.dao.esp.ESPSNPFrequencyPopulationDAO;
import org.renci.canvas.dao.exac.ExACMaxVariantFrequencyDAO;
import org.renci.canvas.dao.exac.ExACVariantFrequencyDAO;
import org.renci.canvas.dao.gnomad.GnomADMaxVariantFrequencyDAO;
import org.renci.canvas.dao.gnomad.GnomADVariantFrequencyDAO;
import org.renci.canvas.dao.hgmd.HGMDLocatedVariantDAO;
import org.renci.canvas.dao.hgnc.HGNCGeneDAO;
import org.renci.canvas.dao.hgnc.HGNCStatusTypeDAO;
import org.renci.canvas.dao.hgnc.LocusGroupDAO;
import org.renci.canvas.dao.hgnc.LocusTypeDAO;
import org.renci.canvas.dao.onekgen.OneKGenomesIndelFrequencyDAO;
import org.renci.canvas.dao.onekgen.OneKGenomesIndelMaxFrequencyDAO;
import org.renci.canvas.dao.onekgen.OneKGenomesSNPFrequencyPopulationDAO;
import org.renci.canvas.dao.onekgen.OneKGenomesSNPFrequencySubpopulationDAO;
import org.renci.canvas.dao.onekgen.OneKGenomesSNPPopulationMaxFrequencyDAO;
import org.renci.canvas.dao.ref.GenomeRefDAO;
import org.renci.canvas.dao.ref.GenomeRefSeqAlternateIdDAO;
import org.renci.canvas.dao.ref.GenomeRefSeqDAO;
import org.renci.canvas.dao.ref.GenomeRefSeqLocationDAO;
import org.renci.canvas.dao.ref.SequenceTypeDAO;
import org.renci.canvas.dao.refseq.CDSECNumberDAO;
import org.renci.canvas.dao.refseq.CDSTranslationExceptionDAO;
import org.renci.canvas.dao.refseq.FeatureDAO;
import org.renci.canvas.dao.refseq.FeatureTypeDAO;
import org.renci.canvas.dao.refseq.GroupingTypeDAO;
import org.renci.canvas.dao.refseq.LocationTypeDAO;
import org.renci.canvas.dao.refseq.RefSeqCodingSequenceDAO;
import org.renci.canvas.dao.refseq.RefSeqGeneDAO;
import org.renci.canvas.dao.refseq.RegionGroupDAO;
import org.renci.canvas.dao.refseq.RegionGroupRegionDAO;
import org.renci.canvas.dao.refseq.TranscriptDAO;
import org.renci.canvas.dao.refseq.TranscriptMapsDAO;
import org.renci.canvas.dao.refseq.TranscriptMapsExonsDAO;
import org.renci.canvas.dao.refseq.TranscriptRefSeqVersionDAO;
import org.renci.canvas.dao.refseq.VariantEffectDAO;
import org.renci.canvas.dao.refseq.Variants_48_2_DAO;
import org.renci.canvas.dao.refseq.Variants_61_2_DAO;
import org.renci.canvas.dao.refseq.Variants_80_4_DAO;
import org.renci.canvas.dao.var.AssemblyDAO;
import org.renci.canvas.dao.var.AssemblyLocatedVariantDAO;
import org.renci.canvas.dao.var.AssemblyLocatedVariantQCDAO;
import org.renci.canvas.dao.var.AssemblyLocationDAO;
import org.renci.canvas.dao.var.CanonicalAlleleDAO;
import org.renci.canvas.dao.var.LabDAO;
import org.renci.canvas.dao.var.LibraryDAO;
import org.renci.canvas.dao.var.LocatedVariantDAO;
import org.renci.canvas.dao.var.ProjectDAO;
import org.renci.canvas.dao.var.SampleDAO;
import org.renci.canvas.dao.var.VariantSetDAO;
import org.renci.canvas.dao.var.VariantSetLoadDAO;
import org.renci.canvas.dao.var.VariantSetLocationDAO;
import org.renci.canvas.dao.var.VariantTypeDAO;

public interface CANVASDAOBeanService {

    public AnalysisClassDAO getAnalysisClassDAO();

    public void setAnalysisClassDAO(AnalysisClassDAO analysisClassDAO);

    public AnalysisClassIncidentalDAO getAnalysisClassIncidentalDAO();

    public void setAnalysisClassIncidentalDAO(AnalysisClassIncidentalDAO analysisClassIncidentalDAO);

    public AnalysisNoteDAO getAnalysisNoteDAO();

    public void setAnalysisNoteDAO(AnalysisNoteDAO analysisNoteDAO);

    public AnnotationGeneDAO getAnnotationGeneDAO();

    public void setAnnotationGeneDAO(AnnotationGeneDAO annotationGeneDAO);

    public AnnotationGeneExternalIdDAO getAnnotationGeneExternalIdDAO();

    public void setAnnotationGeneExternalIdDAO(AnnotationGeneExternalIdDAO annotationGeneExternalIdDAO);

    public AnnotationGeneSynonymDAO getAnnotationGeneSynonymDAO();

    public void setAnnotationGeneSynonymDAO(AnnotationGeneSynonymDAO annotationGeneSynonymDAO);

    public BinResultsFinalDiagnosticDAO getBinResultsFinalDiagnosticDAO();

    public void setBinResultsFinalDiagnosticDAO(BinResultsFinalDiagnosticDAO binResultsFinalDiagnosticDAO);

    public BinResultsFinalIncidentalXDAO getBinResultsFinalIncidentalXDAO();

    public void setBinResultsFinalIncidentalXDAO(BinResultsFinalIncidentalXDAO binResultsFinalIncidentalXDAO);

    public BinResultsFinalRiskXDAO getBinResultsFinalRiskXDAO();

    public void setBinResultsFinalRiskXDAO(BinResultsFinalRiskXDAO binResultsFinalRiskXDAO);

    public CarrierStatusDAO getCarrierStatusDAO();

    public void setCarrierStatusDAO(CarrierStatusDAO carrierStatusDAO);

    public ConfirmedTrackingDAO getConfirmedTrackingDAO();

    public void setConfirmedTrackingDAO(ConfirmedTrackingDAO confirmedTrackingDAO);

    public DiagnosticBinningJobDAO getDiagnosticBinningJobDAO();

    public void setDiagnosticBinningJobDAO(DiagnosticBinningJobDAO diagnosticBinningJobDAO);

    public DiagnosticGeneDAO getDiagnosticGeneDAO();

    public void setDiagnosticGeneDAO(DiagnosticGeneDAO diagnosticGeneDAO);

    public DiagnosticResultVersionDAO getDiagnosticResultVersionDAO();

    public void setDiagnosticResultVersionDAO(DiagnosticResultVersionDAO diagnosticResultVersionDAO);

    public DiagnosticGeneGroupVersionDAO getDiagnosticGeneGroupVersionDAO();

    public void setDiagnosticGeneGroupVersionDAO(DiagnosticGeneGroupVersionDAO diagnosticGeneGroupVersionDAO);

    public DiagnosticStatusTypeDAO getDiagnosticStatusTypeDAO();

    public void setDiagnosticStatusTypeDAO(DiagnosticStatusTypeDAO diagnosticStatusTypeDAO);

    public DiseaseClassDAO getDiseaseClassDAO();

    public void setDiseaseClassDAO(DiseaseClassDAO diseaseClassDAO);

    public DXCoverageDAO getDXCoverageDAO();

    public void setDXCoverageDAO(DXCoverageDAO dXCoverageDAO);

    public DXDAO getDXDAO();

    public void setDXDAO(DXDAO dXDAO);

    public DXExonsDAO getDXExonsDAO();

    public void setDXExonsDAO(DXExonsDAO dXExonsDAO);

    public IncidentalBinGeneXDAO getIncidentalBinGeneXDAO();

    public void setIncidentalBinGeneXDAO(IncidentalBinGeneXDAO incidentalBinGeneXDAO);

    public IncidentalBinGroupVersionXDAO getIncidentalBinGroupVersionXDAO();

    public void setIncidentalBinGroupVersionXDAO(IncidentalBinGroupVersionXDAO incidentalBinGroupVersionXDAO);

    public IncidentalBinHaplotypeXDAO getIncidentalBinHaplotypeXDAO();

    public void setIncidentalBinHaplotypeXDAO(IncidentalBinHaplotypeXDAO incidentalBinHaplotypeXDAO);

    public IncidentalBinningJobDAO getIncidentalBinningJobDAO();

    public void setIncidentalBinningJobDAO(IncidentalBinningJobDAO incidentalBinningJobDAO);

    public IncidentalBinXDAO getIncidentalBinXDAO();

    public void setIncidentalBinXDAO(IncidentalBinXDAO incidentalBinXDAO);

    public IncidentalResultVersionXDAO getIncidentalResultVersionXDAO();

    public void setIncidentalResultVersionXDAO(IncidentalResultVersionXDAO incidentalResultVersionXDAO);

    public IncidentalStatusTypeDAO getIncidentalStatusTypeDAO();

    public void setIncidentalStatusTypeDAO(IncidentalStatusTypeDAO incidentalStatusTypeDAO);

    public MaxFrequencyDAO getMaxFrequencyDAO();

    public void setMaxFrequencyDAO(MaxFrequencyDAO maxFrequencyDAO);

    public MaxFrequencySourceDAO getMaxFrequencySourceDAO();

    public void setMaxFrequencySourceDAO(MaxFrequencySourceDAO maxFrequencySourceDAO);

    public MissingHaplotypeDAO getMissingHaplotypeDAO();

    public void setMissingHaplotypeDAO(MissingHaplotypeDAO missingHaplotypeDAO);

    public NCGenesFrequenciesDAO getNCGenesFrequenciesDAO();

    public void setNCGenesFrequenciesDAO(NCGenesFrequenciesDAO nCGenesFrequenciesDAO);

    public PhenotypeXDAO getPhenotypeXDAO();

    public void setPhenotypeXDAO(PhenotypeXDAO phenotypeXDAO);

    public ReportDAO getReportDAO();

    public void setReportDAO(ReportDAO reportDAO);

    public UnimportantExonDAO getUnimportantExonDAO();

    public void setUnimportantExonDAO(UnimportantExonDAO unimportantExonDAO);

    public AssertionRankingDAO getAssertionRankingDAO();

    public void setAssertionRankingDAO(AssertionRankingDAO assertionRankingDAO);

    public ReferenceClinicalAssertionDAO getReferenceClinicalAssertionDAO();

    public void setReferenceClinicalAssertionDAO(ReferenceClinicalAssertionDAO referenceClinicalAssertionDAO);

    public SubmissionClinicalAssertionDAO getSubmissionClinicalAssertionDAO();

    public void setSubmissionClinicalAssertionDAO(SubmissionClinicalAssertionDAO submissionClinicalAssertionDAO);

    public TraitDAO getTraitDAO();

    public void setTraitDAO(TraitDAO traitDAO);

    public TraitSetDAO getTraitSetDAO();

    public void setTraitSetDAO(TraitSetDAO traitSetDAO);

    public ClinVarVersionDAO getClinVarVersionDAO();

    public void setClinVarVersionDAO(ClinVarVersionDAO versionDAO);

    public SNPAlleleDAO getSNPAlleleDAO();

    public void setSNPAlleleDAO(SNPAlleleDAO sNPAlleleDAO);

    public SNPAlleleFrequencyDAO getSNPAlleleFrequencyDAO();

    public void setSNPAlleleFrequencyDAO(SNPAlleleFrequencyDAO sNPAlleleFrequencyDAO);

    public SNPDAO getSNPDAO();

    public void setSNPDAO(SNPDAO sNPDAO);

    public SNPGenotypeFrequencyDAO getSNPGenotypeFrequencyDAO();

    public void setSNPGenotypeFrequencyDAO(SNPGenotypeFrequencyDAO sNPGenotypeFrequencyDAO);

    public SNPMappingAggDAO getSNPMappingAggDAO();

    public void setSNPMappingAggDAO(SNPMappingAggDAO sNPMappingAggDAO);

    public SNPMappingDAO getSNPMappingDAO();

    public void setSNPMappingDAO(SNPMappingDAO sNPMappingDAO);

    public ESPSNPFrequencyPopulationDAO getESPSNPFrequencyPopulationDAO();

    public void setESPSNPFrequencyPopulationDAO(ESPSNPFrequencyPopulationDAO eSPSNPFrequencyPopulationDAO);

    public ExACMaxVariantFrequencyDAO getExACMaxVariantFrequencyDAO();

    public void setExACMaxVariantFrequencyDAO(ExACMaxVariantFrequencyDAO exacMaxVariantFrequencyDAO);

    public ExACVariantFrequencyDAO getExACVariantFrequencyDAO();

    public void setExACVariantFrequencyDAO(ExACVariantFrequencyDAO exacVariantFrequencyDAO);

    public GnomADMaxVariantFrequencyDAO getGnomADMaxVariantFrequencyDAO();

    public void setGnomADMaxVariantFrequencyDAO(GnomADMaxVariantFrequencyDAO gnomadMaxVariantFrequencyDAO);

    public GnomADVariantFrequencyDAO getGnomADVariantFrequencyDAO();

    public void setGnomADVariantFrequencyDAO(GnomADVariantFrequencyDAO gnomadVariantFrequencyDAO);

    public OneKGenomesIndelMaxFrequencyDAO getOneKGenomesIndelMaxFrequencyDAO();

    public void setOneKGenomesIndelMaxFrequencyDAO(OneKGenomesIndelMaxFrequencyDAO indelMaxFrequencyDAO);

    public OneKGenomesIndelFrequencyDAO getOneKGenomesIndelFrequencyDAO();

    public void setOneKGenomesIndelFrequencyDAO(OneKGenomesIndelFrequencyDAO oneKGenomesIndelFrequencyDAO);

    public OneKGenomesSNPFrequencyPopulationDAO getOneKGenomesSNPFrequencyPopulationDAO();

    public void setOneKGenomesSNPFrequencyPopulationDAO(OneKGenomesSNPFrequencyPopulationDAO oneKGenomesSNPFrequencyPopulationDAO);

    public OneKGenomesSNPFrequencySubpopulationDAO getOneKGenomesSNPFrequencySubpopulationDAO();

    void setOneKGenomesSNPFrequencySubpopulationDAO(OneKGenomesSNPFrequencySubpopulationDAO oneKGenomesSNPFrequencySubpopulationDAO);

    public OneKGenomesSNPPopulationMaxFrequencyDAO getOneKGenomesSNPPopulationMaxFrequencyDAO();

    public void setOneKGenomesSNPPopulationMaxFrequencyDAO(OneKGenomesSNPPopulationMaxFrequencyDAO oneKGenomesSNPPopulationMaxFrequencyDAO);

    public HGMDLocatedVariantDAO getHGMDLocatedVariantDAO();

    public void setHGMDLocatedVariantDAO(HGMDLocatedVariantDAO hGMDLocatedVariantDAO);

    public HGNCGeneDAO getHGNCGeneDAO();

    public void setHGNCGeneDAO(HGNCGeneDAO hGNCGeneDAO);

    public HGNCStatusTypeDAO getHGNCStatusTypeDAO();

    public void setHGNCStatusTypeDAO(HGNCStatusTypeDAO statusTypeDAO);

    public LocusGroupDAO getLocusGroupDAO();

    public void setLocusGroupDAO(LocusGroupDAO locusGroupDAO);

    public LocusTypeDAO getLocusTypeDAO();

    public void setLocusTypeDAO(LocusTypeDAO locusTypeDAO);

    public GenomeRefDAO getGenomeRefDAO();

    public void setGenomeRefDAO(GenomeRefDAO genomeRefDAO);

    public GenomeRefSeqDAO getGenomeRefSeqDAO();

    public void setGenomeRefSeqDAO(GenomeRefSeqDAO genomeRefSeqDAO);

    public GenomeRefSeqAlternateIdDAO getGenomeRefSeqAlternateIdDAO();

    public void setGenomeRefSeqAlternateIdDAO(GenomeRefSeqAlternateIdDAO genomeRefSeqAlternateIdDAO);

    public GenomeRefSeqLocationDAO getGenomeRefSeqLocationDAO();

    public void setGenomeRefSeqLocationDAO(GenomeRefSeqLocationDAO genomeRefSeqLocationDAO);

    public FeatureDAO getFeatureDAO();

    public void setFeatureDAO(FeatureDAO featureDAO);

    public LocationTypeDAO getLocationTypeDAO();

    public void setLocationTypeDAO(LocationTypeDAO locationTypeDAO);

    public GroupingTypeDAO getGroupingTypeDAO();

    public void setGroupingTypeDAO(GroupingTypeDAO groupingTypeDAO);

    public FeatureTypeDAO getFeatureTypeDAO();

    public void setFeatureTypeDAO(FeatureTypeDAO featureTypeDAO);

    public CDSECNumberDAO getCDSECNumberDAO();

    public void setCDSECNumberDAO(CDSECNumberDAO CDSECNumberDAO);

    public CDSTranslationExceptionDAO getCDSTranslationExceptionDAO();

    public void setCDSTranslationExceptionDAO(CDSTranslationExceptionDAO CDSTranslationExceptionDAO);

    public RefSeqCodingSequenceDAO getRefSeqCodingSequenceDAO();

    public void setRefSeqCodingSequenceDAO(RefSeqCodingSequenceDAO refSeqCodingSequenceDAO);

    public RefSeqGeneDAO getRefSeqGeneDAO();

    public void setRefSeqGeneDAO(RefSeqGeneDAO refSeqGeneDAO);

    public RegionGroupDAO getRegionGroupDAO();

    public void setRegionGroupDAO(RegionGroupDAO regionGroupDAO);

    public RegionGroupRegionDAO getRegionGroupRegionDAO();

    public void setRegionGroupRegionDAO(RegionGroupRegionDAO regionGroupRegionDAO);

    public TranscriptDAO getTranscriptDAO();

    public void setTranscriptDAO(TranscriptDAO transcriptDAO);

    public TranscriptRefSeqVersionDAO getTranscriptRefSeqVersionDAO();

    public void setTranscriptRefSeqVersionDAO(TranscriptRefSeqVersionDAO transcriptRefSeqVersionDAO);

    public TranscriptMapsDAO getTranscriptMapsDAO();

    public void setTranscriptMapsDAO(TranscriptMapsDAO transcriptMapsDAO);

    public TranscriptMapsExonsDAO getTranscriptMapsExonsDAO();

    public void setTranscriptMapsExonsDAO(TranscriptMapsExonsDAO transcriptMapsExonsDAO);

    public VariantEffectDAO getVariantEffectDAO();

    public void setVariantEffectDAO(VariantEffectDAO variantEffectDAO);

    public Variants_48_2_DAO getVariants_48_2_DAO();

    public void setVariants_48_2_DAO(Variants_48_2_DAO variants_48_2_DAO);

    public Variants_61_2_DAO getVariants_61_2_DAO();

    public void setVariants_61_2_DAO(Variants_61_2_DAO variants_61_2_DAO);

    public Variants_80_4_DAO getVariants_80_4_DAO();

    public void setVariants_80_4_DAO(Variants_80_4_DAO variants_80_4_DAO);

    public AssemblyDAO getAssemblyDAO();

    public void setAssemblyDAO(AssemblyDAO assemblyDAO);

    public AssemblyLocatedVariantDAO getAssemblyLocatedVariantDAO();

    public void setAssemblyLocatedVariantDAO(AssemblyLocatedVariantDAO assemblyLocatedVariantDAO);

    public AssemblyLocatedVariantQCDAO getAssemblyLocatedVariantQCDAO();

    public void setAssemblyLocatedVariantQCDAO(AssemblyLocatedVariantQCDAO assemblyLocatedVariantQCDAO);

    public AssemblyLocationDAO getAssemblyLocationDAO();

    public void setAssemblyLocationDAO(AssemblyLocationDAO assemblyLocationDAO);

    public CanonicalAlleleDAO getCanonicalAlleleDAO();

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO);

    public LabDAO getLabDAO();

    public void setLabDAO(LabDAO labDAO);

    public LibraryDAO getLibraryDAO();

    public void setLibraryDAO(LibraryDAO libraryDAO);

    public LocatedVariantDAO getLocatedVariantDAO();

    public void setLocatedVariantDAO(LocatedVariantDAO locatedVariantDAO);

    public ProjectDAO getProjectDAO();

    public void setProjectDAO(ProjectDAO projectDAO);

    public SampleDAO getSampleDAO();

    public void setSampleDAO(SampleDAO sampleDAO);

    public VariantSetDAO getVariantSetDAO();

    public void setVariantSetDAO(VariantSetDAO variantSetDAO);

    public VariantSetLoadDAO getVariantSetLoadDAO();

    public void setVariantSetLoadDAO(VariantSetLoadDAO variantSetLoadDAO);

    public VariantSetLocationDAO getVariantSetLocationDAO();

    public void setVariantSetLocationDAO(VariantSetLocationDAO variantSetLocationDAO);

    public VariantTypeDAO getVariantTypeDAO();

    public void setVariantTypeDAO(VariantTypeDAO variantTypeDAO);

    public ZygosityModeTypeDAO getZygosityModeTypeDAO();

    public void setZygosityModeTypeDAO(ZygosityModeTypeDAO zygosityModeTypeDAO);

    public SequenceTypeDAO getSequenceTypeDAO();

    public void setSequenceTypeDAO(SequenceTypeDAO sequenceTypeDAO);

}