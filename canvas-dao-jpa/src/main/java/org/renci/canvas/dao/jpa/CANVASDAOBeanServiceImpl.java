package org.renci.canvas.dao.jpa;

import org.renci.canvas.dao.CANVASDAOBeanService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CANVASDAOBeanServiceImpl implements CANVASDAOBeanService {

    @Autowired
    private AnalysisClassDAO analysisClassDAO;

    @Autowired
    private AnalysisClassIncidentalDAO analysisClassIncidentalDAO;

    @Autowired
    private AnalysisNoteDAO analysisNoteDAO;

    @Autowired
    private ConfirmedTrackingDAO confirmedTrackingDAO;

    @Autowired
    private AnnotationGeneDAO annotationGeneDAO;

    @Autowired
    private AnnotationGeneExternalIdDAO annotationGeneExternalIdDAO;

    @Autowired
    private AnnotationGeneSynonymDAO annotationGeneSynonymDAO;

    @Autowired
    private BinResultsFinalDiagnosticDAO binResultsFinalDiagnosticDAO;

    @Autowired
    private BinResultsFinalIncidentalXDAO binResultsFinalIncidentalXDAO;

    @Autowired
    private BinResultsFinalRiskXDAO binResultsFinalRiskXDAO;

    @Autowired
    private CarrierStatusDAO carrierStatusDAO;

    @Autowired
    private DiagnosticBinningJobDAO diagnosticBinningJobDAO;

    @Autowired
    private DiagnosticStatusTypeDAO diagnosticStatusTypeDAO;

    @Autowired
    private DiagnosticGeneDAO diagnosticGeneDAO;

    @Autowired
    private DiagnosticResultVersionDAO diagnosticResultVersionDAO;

    @Autowired
    private DiagnosticGeneGroupVersionDAO diagnosticGeneGroupVersionDAO;

    @Autowired
    private DiseaseClassDAO diseaseClassDAO;

    @Autowired
    private IncidentalResultVersionXDAO incidentalResultVersionXDAO;

    @Autowired
    private IncidentalStatusTypeDAO incidentalStatusTypeDAO;

    @Autowired
    private IncidentalBinHaplotypeXDAO incidentalBinHaplotypeXDAO;

    @Autowired
    private IncidentalBinGeneXDAO incidentalBinGeneXDAO;

    @Autowired
    private IncidentalBinGroupVersionXDAO incidentalBinGroupVersionXDAO;

    @Autowired
    private IncidentalBinXDAO incidentalBinXDAO;

    @Autowired
    private DXDAO DXDAO;

    @Autowired
    private DXExonsDAO DXExonsDAO;

    @Autowired
    private DXCoverageDAO DXCoverageDAO;

    @Autowired
    private FeatureDAO featureDAO;

    @Autowired
    private GenomeRefDAO genomeRefDAO;

    @Autowired
    private GenomeRefSeqDAO genomeRefSeqDAO;

    @Autowired
    private GenomeRefSeqAlternateIdDAO genomeRefSeqAlternateIdDAO;

    @Autowired
    private GenomeRefSeqLocationDAO genomeRefSeqLocationDAO;

    @Autowired
    private HGNCGeneDAO HGNCGeneDAO;

    @Autowired
    private HGNCStatusTypeDAO HGNCStatusTypeDAO;

    @Autowired
    private LocusGroupDAO locusGroupDAO;

    @Autowired
    private LocusTypeDAO locusTypeDAO;

    @Autowired
    private HGMDLocatedVariantDAO HGMDLocatedVariantDAO;

    @Autowired
    private IncidentalBinningJobDAO incidentalBinningJobDAO;

    @Autowired
    private LocationTypeDAO locationTypeDAO;

    @Autowired
    private ExACMaxVariantFrequencyDAO exACMaxVariantFrequencyDAO;

    @Autowired
    private ExACVariantFrequencyDAO exACVariantFrequencyDAO;

    @Autowired
    private GnomADMaxVariantFrequencyDAO gnomADMaxVariantFrequencyDAO;

    @Autowired
    private GnomADVariantFrequencyDAO gnomADVariantFrequencyDAO;

    @Autowired
    private MaxFrequencyDAO maxFrequencyDAO;

    @Autowired
    private MaxFrequencySourceDAO maxFrequencySourceDAO;

    @Autowired
    private MissingHaplotypeDAO missingHaplotypeDAO;

    @Autowired
    private NCGenesFrequenciesDAO NCGenesFrequenciesDAO;

    @Autowired
    private OneKGenomesIndelFrequencyDAO oneKGenomesIndelFrequencyDAO;

    @Autowired
    private OneKGenomesSNPFrequencyPopulationDAO oneKGenomesSNPFrequencyPopulationDAO;

    @Autowired
    private OneKGenomesSNPFrequencySubpopulationDAO oneKGenomesSNPFrequencySubpopulationDAO;

    @Autowired
    private OneKGenomesIndelMaxFrequencyDAO oneKGenomesIndelMaxFrequencyDAO;

    @Autowired
    private OneKGenomesSNPPopulationMaxFrequencyDAO oneKGenomesSNPPopulationMaxFrequencyDAO;

    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    private ESPSNPFrequencyPopulationDAO ESPSNPFrequencyPopulationDAO;

    @Autowired
    private SNPAlleleDAO SNPAlleleDAO;

    @Autowired
    private SNPAlleleFrequencyDAO SNPAlleleFrequencyDAO;

    @Autowired
    private SNPDAO SNPDAO;

    @Autowired
    private SNPGenotypeFrequencyDAO SNPGenotypeFrequencyDAO;

    @Autowired
    private SNPMappingAggDAO SNPMappingAggDAO;

    @Autowired
    private SNPMappingDAO SNPMappingDAO;

    @Autowired
    private ReferenceClinicalAssertionDAO referenceClinicalAssertionDAO;

    @Autowired
    private SubmissionClinicalAssertionDAO submissionClinicalAssertionDAO;

    @Autowired
    private AssertionRankingDAO assertionRankingDAO;

    @Autowired
    private TraitSetDAO traitSetDAO;

    @Autowired
    private TraitDAO traitDAO;

    @Autowired
    private ClinVarVersionDAO clinVarVersionDAO;

    @Autowired
    private CDSECNumberDAO CDSECNumberDAO;

    @Autowired
    private CDSTranslationExceptionDAO CDSTranslationExceptionDAO;

    @Autowired
    private RefSeqCodingSequenceDAO refSeqCodingSequenceDAO;

    @Autowired
    private RefSeqGeneDAO refSeqGeneDAO;

    @Autowired
    private RegionGroupDAO regionGroupDAO;

    @Autowired
    private RegionGroupRegionDAO regionGroupRegionDAO;

    @Autowired
    private TranscriptDAO transcriptDAO;

    @Autowired
    private TranscriptMapsDAO transcriptMapsDAO;

    @Autowired
    private TranscriptMapsExonsDAO transcriptMapsExonsDAO;

    @Autowired
    private TranscriptRefSeqVersionDAO transcriptRefSeqVersionDAO;

    @Autowired
    private GroupingTypeDAO groupingTypeDAO;

    @Autowired
    private FeatureTypeDAO featureTypeDAO;

    @Autowired
    private VariantEffectDAO variantEffectDAO;

    @Autowired
    private Variants_61_2_DAO variants_61_2_DAO;

    @Autowired
    private Variants_48_2_DAO variants_48_2_DAO;

    @Autowired
    private Variants_80_4_DAO variants_80_4_DAO;

    @Autowired
    private AssemblyDAO assemblyDAO;

    @Autowired
    private AssemblyLocationDAO assemblyLocationDAO;

    @Autowired
    private AssemblyLocatedVariantDAO assemblyLocatedVariantDAO;

    @Autowired
    private AssemblyLocatedVariantQCDAO assemblyLocatedVariantQCDAO;

    @Autowired
    private CanonicalAlleleDAO canonicalAlleleDAO;

    @Autowired
    private LabDAO labDAO;

    @Autowired
    private LibraryDAO libraryDAO;

    @Autowired
    private LocatedVariantDAO LocatedVariantDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private SampleDAO sampleDAO;

    @Autowired
    private VariantSetDAO variantSetDAO;

    @Autowired
    private VariantSetLoadDAO variantSetLoadDAO;

    @Autowired
    private VariantSetLocationDAO variantSetLocationDAO;

    @Autowired
    private VariantTypeDAO variantTypeDAO;

    @Autowired
    private UnimportantExonDAO unimportantExonDAO;

    @Autowired
    private PhenotypeXDAO phenotypeXDAO;

    @Autowired
    private ZygosityModeTypeDAO zygosityModeTypeDAO;

    @Autowired
    private SequenceTypeDAO sequenceTypeDAO;

    public CANVASDAOBeanServiceImpl() {
        super();
    }

    public ClinVarVersionDAO getClinVarVersionDAO() {
        return clinVarVersionDAO;
    }

    public void setClinVarVersionDAO(ClinVarVersionDAO clinVarVersionDAO) {
        this.clinVarVersionDAO = clinVarVersionDAO;
    }

    public AnalysisClassDAO getAnalysisClassDAO() {
        return analysisClassDAO;
    }

    public void setAnalysisClassDAO(AnalysisClassDAO analysisClassDAO) {
        this.analysisClassDAO = analysisClassDAO;
    }

    public AnalysisClassIncidentalDAO getAnalysisClassIncidentalDAO() {
        return analysisClassIncidentalDAO;
    }

    public void setAnalysisClassIncidentalDAO(AnalysisClassIncidentalDAO analysisClassIncidentalDAO) {
        this.analysisClassIncidentalDAO = analysisClassIncidentalDAO;
    }

    public AnalysisNoteDAO getAnalysisNoteDAO() {
        return analysisNoteDAO;
    }

    public void setAnalysisNoteDAO(AnalysisNoteDAO analysisNoteDAO) {
        this.analysisNoteDAO = analysisNoteDAO;
    }

    public ConfirmedTrackingDAO getConfirmedTrackingDAO() {
        return confirmedTrackingDAO;
    }

    public void setConfirmedTrackingDAO(ConfirmedTrackingDAO confirmedTrackingDAO) {
        this.confirmedTrackingDAO = confirmedTrackingDAO;
    }

    public AnnotationGeneDAO getAnnotationGeneDAO() {
        return annotationGeneDAO;
    }

    public void setAnnotationGeneDAO(AnnotationGeneDAO annotationGeneDAO) {
        this.annotationGeneDAO = annotationGeneDAO;
    }

    public AnnotationGeneExternalIdDAO getAnnotationGeneExternalIdDAO() {
        return annotationGeneExternalIdDAO;
    }

    public void setAnnotationGeneExternalIdDAO(AnnotationGeneExternalIdDAO annotationGeneExternalIdDAO) {
        this.annotationGeneExternalIdDAO = annotationGeneExternalIdDAO;
    }

    public AnnotationGeneSynonymDAO getAnnotationGeneSynonymDAO() {
        return annotationGeneSynonymDAO;
    }

    public void setAnnotationGeneSynonymDAO(AnnotationGeneSynonymDAO annotationGeneSynonymDAO) {
        this.annotationGeneSynonymDAO = annotationGeneSynonymDAO;
    }

    public BinResultsFinalDiagnosticDAO getBinResultsFinalDiagnosticDAO() {
        return binResultsFinalDiagnosticDAO;
    }

    public void setBinResultsFinalDiagnosticDAO(BinResultsFinalDiagnosticDAO binResultsFinalDiagnosticDAO) {
        this.binResultsFinalDiagnosticDAO = binResultsFinalDiagnosticDAO;
    }

    public BinResultsFinalIncidentalXDAO getBinResultsFinalIncidentalXDAO() {
        return binResultsFinalIncidentalXDAO;
    }

    public void setBinResultsFinalIncidentalXDAO(BinResultsFinalIncidentalXDAO binResultsFinalIncidentalXDAO) {
        this.binResultsFinalIncidentalXDAO = binResultsFinalIncidentalXDAO;
    }

    public BinResultsFinalRiskXDAO getBinResultsFinalRiskXDAO() {
        return binResultsFinalRiskXDAO;
    }

    public void setBinResultsFinalRiskXDAO(BinResultsFinalRiskXDAO binResultsFinalRiskXDAO) {
        this.binResultsFinalRiskXDAO = binResultsFinalRiskXDAO;
    }

    public CarrierStatusDAO getCarrierStatusDAO() {
        return carrierStatusDAO;
    }

    public void setCarrierStatusDAO(CarrierStatusDAO carrierStatusDAO) {
        this.carrierStatusDAO = carrierStatusDAO;
    }

    public DiagnosticBinningJobDAO getDiagnosticBinningJobDAO() {
        return diagnosticBinningJobDAO;
    }

    public void setDiagnosticBinningJobDAO(DiagnosticBinningJobDAO diagnosticBinningJobDAO) {
        this.diagnosticBinningJobDAO = diagnosticBinningJobDAO;
    }

    public DiagnosticStatusTypeDAO getDiagnosticStatusTypeDAO() {
        return diagnosticStatusTypeDAO;
    }

    public void setDiagnosticStatusTypeDAO(DiagnosticStatusTypeDAO diagnosticStatusTypeDAO) {
        this.diagnosticStatusTypeDAO = diagnosticStatusTypeDAO;
    }

    public DiagnosticGeneDAO getDiagnosticGeneDAO() {
        return diagnosticGeneDAO;
    }

    public void setDiagnosticGeneDAO(DiagnosticGeneDAO diagnosticGeneDAO) {
        this.diagnosticGeneDAO = diagnosticGeneDAO;
    }

    public DiagnosticGeneGroupVersionDAO getDiagnosticGeneGroupVersionDAO() {
        return diagnosticGeneGroupVersionDAO;
    }

    public void setDiagnosticGeneGroupVersionDAO(DiagnosticGeneGroupVersionDAO diagnosticGeneGroupVersionDAO) {
        this.diagnosticGeneGroupVersionDAO = diagnosticGeneGroupVersionDAO;
    }

    public DiagnosticResultVersionDAO getDiagnosticResultVersionDAO() {
        return diagnosticResultVersionDAO;
    }

    public void setDiagnosticResultVersionDAO(DiagnosticResultVersionDAO diagnosticResultVersionDAO) {
        this.diagnosticResultVersionDAO = diagnosticResultVersionDAO;
    }

    public DiseaseClassDAO getDiseaseClassDAO() {
        return diseaseClassDAO;
    }

    public void setDiseaseClassDAO(DiseaseClassDAO diseaseClassDAO) {
        this.diseaseClassDAO = diseaseClassDAO;
    }

    public IncidentalResultVersionXDAO getIncidentalResultVersionXDAO() {
        return incidentalResultVersionXDAO;
    }

    public void setIncidentalResultVersionXDAO(IncidentalResultVersionXDAO incidentalResultVersionXDAO) {
        this.incidentalResultVersionXDAO = incidentalResultVersionXDAO;
    }

    public IncidentalStatusTypeDAO getIncidentalStatusTypeDAO() {
        return incidentalStatusTypeDAO;
    }

    public void setIncidentalStatusTypeDAO(IncidentalStatusTypeDAO incidentalStatusTypeDAO) {
        this.incidentalStatusTypeDAO = incidentalStatusTypeDAO;
    }

    public TraitSetDAO getTraitSetDAO() {
        return traitSetDAO;
    }

    public void setTraitSetDAO(TraitSetDAO traitSetDAO) {
        this.traitSetDAO = traitSetDAO;
    }

    public TraitDAO getTraitDAO() {
        return traitDAO;
    }

    public void setTraitDAO(TraitDAO traitDAO) {
        this.traitDAO = traitDAO;
    }

    public IncidentalBinHaplotypeXDAO getIncidentalBinHaplotypeXDAO() {
        return incidentalBinHaplotypeXDAO;
    }

    public void setIncidentalBinHaplotypeXDAO(IncidentalBinHaplotypeXDAO incidentalBinHaplotypeXDAO) {
        this.incidentalBinHaplotypeXDAO = incidentalBinHaplotypeXDAO;
    }

    public IncidentalBinGeneXDAO getIncidentalBinGeneXDAO() {
        return incidentalBinGeneXDAO;
    }

    public void setIncidentalBinGeneXDAO(IncidentalBinGeneXDAO incidentalBinGeneXDAO) {
        this.incidentalBinGeneXDAO = incidentalBinGeneXDAO;
    }

    public IncidentalBinGroupVersionXDAO getIncidentalBinGroupVersionXDAO() {
        return incidentalBinGroupVersionXDAO;
    }

    public void setIncidentalBinGroupVersionXDAO(IncidentalBinGroupVersionXDAO incidentalBinGroupVersionXDAO) {
        this.incidentalBinGroupVersionXDAO = incidentalBinGroupVersionXDAO;
    }

    public IncidentalBinXDAO getIncidentalBinXDAO() {
        return incidentalBinXDAO;
    }

    public void setIncidentalBinXDAO(IncidentalBinXDAO incidentalBinXDAO) {
        this.incidentalBinXDAO = incidentalBinXDAO;
    }

    public DXDAO getDXDAO() {
        return DXDAO;
    }

    public void setDXDAO(DXDAO dXDAO) {
        DXDAO = dXDAO;
    }

    public DXExonsDAO getDXExonsDAO() {
        return DXExonsDAO;
    }

    public void setDXExonsDAO(DXExonsDAO dXExonsDAO) {
        DXExonsDAO = dXExonsDAO;
    }

    public DXCoverageDAO getDXCoverageDAO() {
        return DXCoverageDAO;
    }

    public void setDXCoverageDAO(DXCoverageDAO dXCoverageDAO) {
        DXCoverageDAO = dXCoverageDAO;
    }

    public FeatureDAO getFeatureDAO() {
        return featureDAO;
    }

    public void setFeatureDAO(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

    public GenomeRefDAO getGenomeRefDAO() {
        return genomeRefDAO;
    }

    public void setGenomeRefDAO(GenomeRefDAO genomeRefDAO) {
        this.genomeRefDAO = genomeRefDAO;
    }

    public GenomeRefSeqDAO getGenomeRefSeqDAO() {
        return genomeRefSeqDAO;
    }

    public void setGenomeRefSeqDAO(GenomeRefSeqDAO genomeRefSeqDAO) {
        this.genomeRefSeqDAO = genomeRefSeqDAO;
    }

    public GenomeRefSeqAlternateIdDAO getGenomeRefSeqAlternateIdDAO() {
        return genomeRefSeqAlternateIdDAO;
    }

    public void setGenomeRefSeqAlternateIdDAO(GenomeRefSeqAlternateIdDAO genomeRefSeqAlternateIdDAO) {
        this.genomeRefSeqAlternateIdDAO = genomeRefSeqAlternateIdDAO;
    }

    public GenomeRefSeqLocationDAO getGenomeRefSeqLocationDAO() {
        return genomeRefSeqLocationDAO;
    }

    public void setGenomeRefSeqLocationDAO(GenomeRefSeqLocationDAO genomeRefSeqLocationDAO) {
        this.genomeRefSeqLocationDAO = genomeRefSeqLocationDAO;
    }

    public HGNCGeneDAO getHGNCGeneDAO() {
        return HGNCGeneDAO;
    }

    public void setHGNCGeneDAO(HGNCGeneDAO hGNCGeneDAO) {
        HGNCGeneDAO = hGNCGeneDAO;
    }

    public HGNCStatusTypeDAO getHGNCStatusTypeDAO() {
        return HGNCStatusTypeDAO;
    }

    public void setHGNCStatusTypeDAO(HGNCStatusTypeDAO hGNCStatusTypeDAO) {
        HGNCStatusTypeDAO = hGNCStatusTypeDAO;
    }

    public LocusGroupDAO getLocusGroupDAO() {
        return locusGroupDAO;
    }

    public void setLocusGroupDAO(LocusGroupDAO locusGroupDAO) {
        this.locusGroupDAO = locusGroupDAO;
    }

    public LocusTypeDAO getLocusTypeDAO() {
        return locusTypeDAO;
    }

    public void setLocusTypeDAO(LocusTypeDAO locusTypeDAO) {
        this.locusTypeDAO = locusTypeDAO;
    }

    public HGMDLocatedVariantDAO getHGMDLocatedVariantDAO() {
        return HGMDLocatedVariantDAO;
    }

    public void setHGMDLocatedVariantDAO(HGMDLocatedVariantDAO hGMDLocatedVariantDAO) {
        HGMDLocatedVariantDAO = hGMDLocatedVariantDAO;
    }

    public IncidentalBinningJobDAO getIncidentalBinningJobDAO() {
        return incidentalBinningJobDAO;
    }

    public void setIncidentalBinningJobDAO(IncidentalBinningJobDAO incidentalBinningJobDAO) {
        this.incidentalBinningJobDAO = incidentalBinningJobDAO;
    }

    public LocationTypeDAO getLocationTypeDAO() {
        return locationTypeDAO;
    }

    public void setLocationTypeDAO(LocationTypeDAO locationTypeDAO) {
        this.locationTypeDAO = locationTypeDAO;
    }

    public ExACMaxVariantFrequencyDAO getExACMaxVariantFrequencyDAO() {
        return exACMaxVariantFrequencyDAO;
    }

    public void setExACMaxVariantFrequencyDAO(ExACMaxVariantFrequencyDAO exACMaxVariantFrequencyDAO) {
        this.exACMaxVariantFrequencyDAO = exACMaxVariantFrequencyDAO;
    }

    public ExACVariantFrequencyDAO getExACVariantFrequencyDAO() {
        return exACVariantFrequencyDAO;
    }

    public void setExACVariantFrequencyDAO(ExACVariantFrequencyDAO exACVariantFrequencyDAO) {
        this.exACVariantFrequencyDAO = exACVariantFrequencyDAO;
    }

    public GnomADMaxVariantFrequencyDAO getGnomADMaxVariantFrequencyDAO() {
        return gnomADMaxVariantFrequencyDAO;
    }

    public void setGnomADMaxVariantFrequencyDAO(GnomADMaxVariantFrequencyDAO gnomADMaxVariantFrequencyDAO) {
        this.gnomADMaxVariantFrequencyDAO = gnomADMaxVariantFrequencyDAO;
    }

    public GnomADVariantFrequencyDAO getGnomADVariantFrequencyDAO() {
        return gnomADVariantFrequencyDAO;
    }

    public void setGnomADVariantFrequencyDAO(GnomADVariantFrequencyDAO gnomADVariantFrequencyDAO) {
        this.gnomADVariantFrequencyDAO = gnomADVariantFrequencyDAO;
    }

    public MaxFrequencyDAO getMaxFrequencyDAO() {
        return maxFrequencyDAO;
    }

    public void setMaxFrequencyDAO(MaxFrequencyDAO maxFrequencyDAO) {
        this.maxFrequencyDAO = maxFrequencyDAO;
    }

    public MaxFrequencySourceDAO getMaxFrequencySourceDAO() {
        return maxFrequencySourceDAO;
    }

    public void setMaxFrequencySourceDAO(MaxFrequencySourceDAO maxFrequencySourceDAO) {
        this.maxFrequencySourceDAO = maxFrequencySourceDAO;
    }

    public MissingHaplotypeDAO getMissingHaplotypeDAO() {
        return missingHaplotypeDAO;
    }

    public void setMissingHaplotypeDAO(MissingHaplotypeDAO missingHaplotypeDAO) {
        this.missingHaplotypeDAO = missingHaplotypeDAO;
    }

    public NCGenesFrequenciesDAO getNCGenesFrequenciesDAO() {
        return NCGenesFrequenciesDAO;
    }

    public void setNCGenesFrequenciesDAO(NCGenesFrequenciesDAO nCGenesFrequenciesDAO) {
        NCGenesFrequenciesDAO = nCGenesFrequenciesDAO;
    }

    public ReportDAO getReportDAO() {
        return reportDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public CDSECNumberDAO getCDSECNumberDAO() {
        return CDSECNumberDAO;
    }

    public void setCDSECNumberDAO(CDSECNumberDAO cDSECNumberDAO) {
        CDSECNumberDAO = cDSECNumberDAO;
    }

    public CDSTranslationExceptionDAO getCDSTranslationExceptionDAO() {
        return CDSTranslationExceptionDAO;
    }

    public void setCDSTranslationExceptionDAO(CDSTranslationExceptionDAO cDSTranslationExceptionDAO) {
        CDSTranslationExceptionDAO = cDSTranslationExceptionDAO;
    }

    public ESPSNPFrequencyPopulationDAO getESPSNPFrequencyPopulationDAO() {
        return ESPSNPFrequencyPopulationDAO;
    }

    public void setESPSNPFrequencyPopulationDAO(ESPSNPFrequencyPopulationDAO eSPSNPFrequencyPopulationDAO) {
        ESPSNPFrequencyPopulationDAO = eSPSNPFrequencyPopulationDAO;
    }

    public OneKGenomesIndelFrequencyDAO getOneKGenomesIndelFrequencyDAO() {
        return oneKGenomesIndelFrequencyDAO;
    }

    public void setOneKGenomesIndelFrequencyDAO(OneKGenomesIndelFrequencyDAO oneKGenomesIndelFrequencyDAO) {
        this.oneKGenomesIndelFrequencyDAO = oneKGenomesIndelFrequencyDAO;
    }

    public OneKGenomesSNPFrequencyPopulationDAO getOneKGenomesSNPFrequencyPopulationDAO() {
        return oneKGenomesSNPFrequencyPopulationDAO;
    }

    public void setOneKGenomesSNPFrequencyPopulationDAO(OneKGenomesSNPFrequencyPopulationDAO oneKGenomesSNPFrequencyPopulationDAO) {
        this.oneKGenomesSNPFrequencyPopulationDAO = oneKGenomesSNPFrequencyPopulationDAO;
    }

    public OneKGenomesSNPFrequencySubpopulationDAO getOneKGenomesSNPFrequencySubpopulationDAO() {
        return oneKGenomesSNPFrequencySubpopulationDAO;
    }

    public void setOneKGenomesSNPFrequencySubpopulationDAO(
            OneKGenomesSNPFrequencySubpopulationDAO oneKGenomesSNPFrequencySubpopulationDAO) {
        this.oneKGenomesSNPFrequencySubpopulationDAO = oneKGenomesSNPFrequencySubpopulationDAO;
    }

    public OneKGenomesIndelMaxFrequencyDAO getOneKGenomesIndelMaxFrequencyDAO() {
        return oneKGenomesIndelMaxFrequencyDAO;
    }

    public void setOneKGenomesIndelMaxFrequencyDAO(OneKGenomesIndelMaxFrequencyDAO oneKGenomesIndelMaxFrequencyDAO) {
        this.oneKGenomesIndelMaxFrequencyDAO = oneKGenomesIndelMaxFrequencyDAO;
    }

    public OneKGenomesSNPPopulationMaxFrequencyDAO getOneKGenomesSNPPopulationMaxFrequencyDAO() {
        return oneKGenomesSNPPopulationMaxFrequencyDAO;
    }

    public void setOneKGenomesSNPPopulationMaxFrequencyDAO(
            OneKGenomesSNPPopulationMaxFrequencyDAO oneKGenomesSNPPopulationMaxFrequencyDAO) {
        this.oneKGenomesSNPPopulationMaxFrequencyDAO = oneKGenomesSNPPopulationMaxFrequencyDAO;
    }

    public SNPAlleleDAO getSNPAlleleDAO() {
        return SNPAlleleDAO;
    }

    public void setSNPAlleleDAO(SNPAlleleDAO sNPAlleleDAO) {
        SNPAlleleDAO = sNPAlleleDAO;
    }

    public SNPAlleleFrequencyDAO getSNPAlleleFrequencyDAO() {
        return SNPAlleleFrequencyDAO;
    }

    public void setSNPAlleleFrequencyDAO(SNPAlleleFrequencyDAO sNPAlleleFrequencyDAO) {
        SNPAlleleFrequencyDAO = sNPAlleleFrequencyDAO;
    }

    public SNPDAO getSNPDAO() {
        return SNPDAO;
    }

    public void setSNPDAO(SNPDAO sNPDAO) {
        SNPDAO = sNPDAO;
    }

    public SNPGenotypeFrequencyDAO getSNPGenotypeFrequencyDAO() {
        return SNPGenotypeFrequencyDAO;
    }

    public void setSNPGenotypeFrequencyDAO(SNPGenotypeFrequencyDAO sNPGenotypeFrequencyDAO) {
        SNPGenotypeFrequencyDAO = sNPGenotypeFrequencyDAO;
    }

    public SNPMappingAggDAO getSNPMappingAggDAO() {
        return SNPMappingAggDAO;
    }

    public void setSNPMappingAggDAO(SNPMappingAggDAO sNPMappingAggDAO) {
        SNPMappingAggDAO = sNPMappingAggDAO;
    }

    public SNPMappingDAO getSNPMappingDAO() {
        return SNPMappingDAO;
    }

    public void setSNPMappingDAO(SNPMappingDAO sNPMappingDAO) {
        SNPMappingDAO = sNPMappingDAO;
    }

    public ReferenceClinicalAssertionDAO getReferenceClinicalAssertionDAO() {
        return referenceClinicalAssertionDAO;
    }

    public void setReferenceClinicalAssertionDAO(ReferenceClinicalAssertionDAO referenceClinicalAssertionDAO) {
        this.referenceClinicalAssertionDAO = referenceClinicalAssertionDAO;
    }

    public SubmissionClinicalAssertionDAO getSubmissionClinicalAssertionDAO() {
        return submissionClinicalAssertionDAO;
    }

    public void setSubmissionClinicalAssertionDAO(SubmissionClinicalAssertionDAO submissionClinicalAssertionDAO) {
        this.submissionClinicalAssertionDAO = submissionClinicalAssertionDAO;
    }

    public AssertionRankingDAO getAssertionRankingDAO() {
        return assertionRankingDAO;
    }

    public void setAssertionRankingDAO(AssertionRankingDAO assertionRankingDAO) {
        this.assertionRankingDAO = assertionRankingDAO;
    }

    public RefSeqCodingSequenceDAO getRefSeqCodingSequenceDAO() {
        return refSeqCodingSequenceDAO;
    }

    public void setRefSeqCodingSequenceDAO(RefSeqCodingSequenceDAO refSeqCodingSequenceDAO) {
        this.refSeqCodingSequenceDAO = refSeqCodingSequenceDAO;
    }

    public RefSeqGeneDAO getRefSeqGeneDAO() {
        return refSeqGeneDAO;
    }

    public void setRefSeqGeneDAO(RefSeqGeneDAO refSeqGeneDAO) {
        this.refSeqGeneDAO = refSeqGeneDAO;
    }

    public RegionGroupDAO getRegionGroupDAO() {
        return regionGroupDAO;
    }

    public void setRegionGroupDAO(RegionGroupDAO regionGroupDAO) {
        this.regionGroupDAO = regionGroupDAO;
    }

    public RegionGroupRegionDAO getRegionGroupRegionDAO() {
        return regionGroupRegionDAO;
    }

    public void setRegionGroupRegionDAO(RegionGroupRegionDAO regionGroupRegionDAO) {
        this.regionGroupRegionDAO = regionGroupRegionDAO;
    }

    public TranscriptDAO getTranscriptDAO() {
        return transcriptDAO;
    }

    public void setTranscriptDAO(TranscriptDAO transcriptDAO) {
        this.transcriptDAO = transcriptDAO;
    }

    public TranscriptMapsDAO getTranscriptMapsDAO() {
        return transcriptMapsDAO;
    }

    public void setTranscriptMapsDAO(TranscriptMapsDAO transcriptMapsDAO) {
        this.transcriptMapsDAO = transcriptMapsDAO;
    }

    public TranscriptMapsExonsDAO getTranscriptMapsExonsDAO() {
        return transcriptMapsExonsDAO;
    }

    public void setTranscriptMapsExonsDAO(TranscriptMapsExonsDAO transcriptMapsExonsDAO) {
        this.transcriptMapsExonsDAO = transcriptMapsExonsDAO;
    }

    public TranscriptRefSeqVersionDAO getTranscriptRefSeqVersionDAO() {
        return transcriptRefSeqVersionDAO;
    }

    public void setTranscriptRefSeqVersionDAO(TranscriptRefSeqVersionDAO transcriptRefSeqVersionDAO) {
        this.transcriptRefSeqVersionDAO = transcriptRefSeqVersionDAO;
    }

    public FeatureTypeDAO getFeatureTypeDAO() {
        return featureTypeDAO;
    }

    public void setFeatureTypeDAO(FeatureTypeDAO featureTypeDAO) {
        this.featureTypeDAO = featureTypeDAO;
    }

    public GroupingTypeDAO getGroupingTypeDAO() {
        return groupingTypeDAO;
    }

    public void setGroupingTypeDAO(GroupingTypeDAO groupingTypeDAO) {
        this.groupingTypeDAO = groupingTypeDAO;
    }

    public VariantEffectDAO getVariantEffectDAO() {
        return variantEffectDAO;
    }

    public void setVariantEffectDAO(VariantEffectDAO variantEffectDAO) {
        this.variantEffectDAO = variantEffectDAO;
    }

    public Variants_61_2_DAO getVariants_61_2_DAO() {
        return variants_61_2_DAO;
    }

    public void setVariants_61_2_DAO(Variants_61_2_DAO variants_61_2_DAO) {
        this.variants_61_2_DAO = variants_61_2_DAO;
    }

    public Variants_48_2_DAO getVariants_48_2_DAO() {
        return variants_48_2_DAO;
    }

    public void setVariants_48_2_DAO(Variants_48_2_DAO variants_48_2_DAO) {
        this.variants_48_2_DAO = variants_48_2_DAO;
    }

    public Variants_80_4_DAO getVariants_80_4_DAO() {
        return variants_80_4_DAO;
    }

    public void setVariants_80_4_DAO(Variants_80_4_DAO variants_80_4_DAO) {
        this.variants_80_4_DAO = variants_80_4_DAO;
    }

    public AssemblyDAO getAssemblyDAO() {
        return assemblyDAO;
    }

    public void setAssemblyDAO(AssemblyDAO assemblyDAO) {
        this.assemblyDAO = assemblyDAO;
    }

    public AssemblyLocationDAO getAssemblyLocationDAO() {
        return assemblyLocationDAO;
    }

    public void setAssemblyLocationDAO(AssemblyLocationDAO assemblyLocationDAO) {
        this.assemblyLocationDAO = assemblyLocationDAO;
    }

    public AssemblyLocatedVariantDAO getAssemblyLocatedVariantDAO() {
        return assemblyLocatedVariantDAO;
    }

    public void setAssemblyLocatedVariantDAO(AssemblyLocatedVariantDAO assemblyLocatedVariantDAO) {
        this.assemblyLocatedVariantDAO = assemblyLocatedVariantDAO;
    }

    public AssemblyLocatedVariantQCDAO getAssemblyLocatedVariantQCDAO() {
        return assemblyLocatedVariantQCDAO;
    }

    public void setAssemblyLocatedVariantQCDAO(AssemblyLocatedVariantQCDAO assemblyLocatedVariantQCDAO) {
        this.assemblyLocatedVariantQCDAO = assemblyLocatedVariantQCDAO;
    }

    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
    }

    public LabDAO getLabDAO() {
        return labDAO;
    }

    public void setLabDAO(LabDAO labDAO) {
        this.labDAO = labDAO;
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }

    public void setLibraryDAO(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    public LocatedVariantDAO getLocatedVariantDAO() {
        return LocatedVariantDAO;
    }

    public void setLocatedVariantDAO(LocatedVariantDAO locatedVariantDAO) {
        LocatedVariantDAO = locatedVariantDAO;
    }

    public ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public SampleDAO getSampleDAO() {
        return sampleDAO;
    }

    public void setSampleDAO(SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }

    public VariantSetDAO getVariantSetDAO() {
        return variantSetDAO;
    }

    public void setVariantSetDAO(VariantSetDAO variantSetDAO) {
        this.variantSetDAO = variantSetDAO;
    }

    public VariantSetLoadDAO getVariantSetLoadDAO() {
        return variantSetLoadDAO;
    }

    public void setVariantSetLoadDAO(VariantSetLoadDAO variantSetLoadDAO) {
        this.variantSetLoadDAO = variantSetLoadDAO;
    }

    public VariantSetLocationDAO getVariantSetLocationDAO() {
        return variantSetLocationDAO;
    }

    public void setVariantSetLocationDAO(VariantSetLocationDAO variantSetLocationDAO) {
        this.variantSetLocationDAO = variantSetLocationDAO;
    }

    public VariantTypeDAO getVariantTypeDAO() {
        return variantTypeDAO;
    }

    public void setVariantTypeDAO(VariantTypeDAO variantTypeDAO) {
        this.variantTypeDAO = variantTypeDAO;
    }

    public UnimportantExonDAO getUnimportantExonDAO() {
        return unimportantExonDAO;
    }

    public void setUnimportantExonDAO(UnimportantExonDAO unimportantExonDAO) {
        this.unimportantExonDAO = unimportantExonDAO;
    }

    public PhenotypeXDAO getPhenotypeXDAO() {
        return phenotypeXDAO;
    }

    public void setPhenotypeXDAO(PhenotypeXDAO phenotypeXDAO) {
        this.phenotypeXDAO = phenotypeXDAO;
    }

    public ZygosityModeTypeDAO getZygosityModeTypeDAO() {
        return zygosityModeTypeDAO;
    }

    public void setZygosityModeTypeDAO(ZygosityModeTypeDAO zygosityModeTypeDAO) {
        this.zygosityModeTypeDAO = zygosityModeTypeDAO;
    }

    public SequenceTypeDAO getSequenceTypeDAO() {
        return sequenceTypeDAO;
    }

    public void setSequenceTypeDAO(SequenceTypeDAO sequenceTypeDAO) {
        this.sequenceTypeDAO = sequenceTypeDAO;
    }

}
