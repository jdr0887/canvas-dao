package org.renci.canvas.dao.jpa.refseq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Coalesce;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene_;
import org.renci.canvas.dao.clinbin.model.MaxFrequency;
import org.renci.canvas.dao.clinbin.model.MaxFrequency_;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency_;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariant;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariantPK_;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariant_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.Variants_80_4_DAO;
import org.renci.canvas.dao.refseq.model.LocationType;
import org.renci.canvas.dao.refseq.model.LocationType_;
import org.renci.canvas.dao.refseq.model.VariantEffect;
import org.renci.canvas.dao.refseq.model.VariantEffect_;
import org.renci.canvas.dao.refseq.model.Variants_80_4;
import org.renci.canvas.dao.refseq.model.Variants_80_4PK;
import org.renci.canvas.dao.refseq.model.Variants_80_4_;
import org.renci.canvas.dao.var.model.Assembly;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant_;
import org.renci.canvas.dao.var.model.Assembly_;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.renci.canvas.dao.var.model.VariantType_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { Variants_80_4_DAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class Variants_80_4_DAOImpl extends BaseDAOImpl<Variants_80_4, Variants_80_4PK> implements Variants_80_4_DAO {

    private static final Logger logger = LoggerFactory.getLogger(Variants_80_4_DAOImpl.class);

    public Variants_80_4_DAOImpl() {
        super();
    }

    @Override
    public Class<Variants_80_4> getPersistentClass() {
        return Variants_80_4.class;
    }

    @Override
    public List<Variants_80_4> findByGeneId(Integer geneId) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneId(Integer)");
        List<Variants_80_4> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            predicates.add(critBuilder.equal(variantsAnnotationGeneJoin.get(AnnotationGene_.id), geneId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findByLocatedVariantId(Long id) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<Variants_80_4> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).get(LocatedVariant_.id), id));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findKnownPathenogenic(Integer hgmdVersion, Integer dxId, Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findKnownPathenogenic(Integer, Integer, Integer)");
        List<Variants_80_4> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = root.join(Variants_80_4_.gene)
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx).get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            predicates.add(critBuilder.equal(variantsLocatedVariantJoin.join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = variantsLocatedVariantJoin
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.equal(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList(Integer hgmdVersion, Double threshold,
            Integer geneId, List<String> variantEffectList) throws CANVASDAOException {
        logger.debug(
                "ENTERING findByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList(Integer, Double, Integer, List<String>)");
        List<Variants_80_4> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, VariantEffect> variantsVariantEffectJoin = root.join(Variants_80_4_.variantEffect);
            predicates.add(variantsVariantEffectJoin.get(VariantEffect_.id).in(variantEffectList));

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            predicates.add(critBuilder.equal(variantsAnnotationGeneJoin.get(AnnotationGene_.id), geneId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), threshold));

            // Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = variantsLocatedVariantJoin
            // .join(LocatedVariant_.hgmdLocatedVariants);
            // predicates.add(critBuilder.notEqual(
            // critBuilder.function("hgmd_enum_to_varchar", String.class,
            // variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
            // "DM"));
            // predicates.add(critBuilder
            // .notEqual(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.key).get(HGMDLocatedVariantPK_.version),
            // hgmdVersion));

            Subquery<HGMDLocatedVariant> subquery = crit.subquery(HGMDLocatedVariant.class);
            Root<HGMDLocatedVariant> hgmdLocatedVariantRoot = subquery.from(HGMDLocatedVariant.class);
            subquery.select(hgmdLocatedVariantRoot);
            List<Predicate> subQueryPredicates = new ArrayList<Predicate>();
            subQueryPredicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, hgmdLocatedVariantRoot.get(HGMDLocatedVariant_.tag)), "DM"));
            subQueryPredicates.add(
                    critBuilder.equal(hgmdLocatedVariantRoot.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version), hgmdVersion));
            subQueryPredicates.add(critBuilder.equal(variantsLocatedVariantJoin.get(LocatedVariant_.id),
                    hgmdLocatedVariantRoot.get(HGMDLocatedVariant_.locatedVariant).get(LocatedVariant_.id)));
            subquery.where(subQueryPredicates.toArray(new Predicate[subQueryPredicates.size()]));

            predicates.add(critBuilder.not(critBuilder.exists(subquery)));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findByAssemblyIdAndSampleNameAndHGMDVersionAndMaxFrequencyThresholdAndGeneId(Integer assemblyId,
            String sampleName, Integer hgmdVersion, Double threshold, Integer geneId) throws CANVASDAOException {
        logger.debug(
                "ENTERING findByAssemblyIdAndSampleNameAndHGMDVersionAndMaxFrequencyThresholdAndGeneId(Integer, String, Integer, Double, Integer)");
        List<Variants_80_4> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            predicates.add(critBuilder.equal(variantsLocatedVariantJoin.join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            predicates.add(
                    critBuilder.lt(variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs).get(MaxFrequency_.maxAlleleFreq), threshold));

            predicates.add(critBuilder.equal(root.join(Variants_80_4_.gene).get(AnnotationGene_.id), geneId));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = variantsLocatedVariantJoin
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.equal(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findLikelyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findLikelyPathenogenic(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            List<String> variantEffectNames = new ArrayList<>();
            variantEffectNames.add("nonsense");
            variantEffectNames.add("splice-site");
            variantEffectNames.add("boundary-crossing indel");
            variantEffectNames.add("stoploss");
            variantEffectNames.add("nonsense indel");
            variantEffectNames.add("frameshifting indel");

            Join<Variants_80_4, VariantEffect> variantsVariantEffectJoin = root.join(Variants_80_4_.variantEffect);
            predicates.add(variantsVariantEffectJoin.get(VariantEffect_.id).in(variantEffectNames));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.01));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findPossiblyPathenogenic(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findLikelyPathenogenic(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            List<String> variantEffectNames = new ArrayList<>();
            variantEffectNames.add("missense");
            variantEffectNames.add("non-frameshifting indel");

            Join<Variants_80_4, VariantEffect> variantsVariantEffectJoin = root.join(Variants_80_4_.variantEffect);
            predicates.add(variantsVariantEffectJoin.get(VariantEffect_.id).in(variantEffectNames));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.01));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findUncertainSignificance(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findLikelyPathogenic(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.01));

            List<String> variantEffectNames = new ArrayList<>();
            variantEffectNames.add("synonymous");
            variantEffectNames.add("synonymous indel");
            variantEffectNames.add("intron");
            variantEffectNames.add("splice-site-UTR-3");
            variantEffectNames.add("splice-site-UTR-5");
            variantEffectNames.add("splice-site-UTR");
            variantEffectNames.add("potential RNA-editing site");
            variantEffectNames.add("noncoding boundary-crossing indel");

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            Join<Variants_80_4, VariantEffect> variantsVariantEffectJoin = root.join(Variants_80_4_.variantEffect);
            predicates.add(critBuilder.or(variantsVariantEffectJoin.get(VariantEffect_.id).in(variantEffectNames),
                    variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames)));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.05));

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");
            locationTypeNames.add("exon");
            locationTypeNames.add("intron/exon boundary");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            predicates.add(variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return ret;
    }

    @Override
    public List<Variants_80_4> findLikelyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findLikelyBenign(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();

        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.1));

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");
            locationTypeNames.add("exon");
            locationTypeNames.add("intron/exon boundary");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            predicates.add(variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.lt(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.05));

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");
            locationTypeNames.add("exon");
            locationTypeNames.add("intron/exon boundary");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            predicates.add(critBuilder.not(variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames)));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return ret;
    }

    @Override
    public List<Variants_80_4> findAlmostCertainlyBenign(Integer hgmdVersion, Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findLikelyPathogenic(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.greaterThanOrEqualTo(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.1));

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");
            locationTypeNames.add("exon");
            locationTypeNames.add("intron/exon boundary");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            predicates.add(variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<Variants_80_4, AnnotationGene> variantsAnnotationGeneJoin = root.join(Variants_80_4_.gene);
            Join<AnnotationGene, DiagnosticGene> annotationGeneDiagnosticGeneJoin = variantsAnnotationGeneJoin
                    .join(AnnotationGene_.diagnosticGenes);
            predicates.add(
                    critBuilder.equal(annotationGeneDiagnosticGeneJoin.get(DiagnosticGene_.diagnosticListVersion), diagnosticListVersion));

            Join<DiagnosticGene, DX> annotationGeneDiagnosticCodeJoin = annotationGeneDiagnosticGeneJoin.join(DiagnosticGene_.dx,
                    JoinType.LEFT);
            predicates.add(critBuilder.equal(annotationGeneDiagnosticCodeJoin.get(DX_.id), dxId));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);
            Join<LocatedVariant, MaxFrequency> locatedVariantMaxFrequencyJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs,
                    JoinType.LEFT);
            predicates.add(critBuilder.greaterThanOrEqualTo(locatedVariantMaxFrequencyJoin.get(MaxFrequency_.maxAlleleFreq), 0.1));

            List<String> locationTypeNames = new ArrayList<>();
            locationTypeNames.add("UTR-5");
            locationTypeNames.add("UTR-3");
            locationTypeNames.add("UTR");
            locationTypeNames.add("exon");
            locationTypeNames.add("intron/exon boundary");

            Join<Variants_80_4, LocationType> variantsLocationTypeJoin = root.join(Variants_80_4_.locationType);
            predicates.add(critBuilder.not(variantsLocationTypeJoin.get(LocationType_.id).in(locationTypeNames)));

            Join<LocatedVariant, HGMDLocatedVariant> variantsHGMDLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.hgmdLocatedVariants);
            predicates.add(critBuilder.notEqual(
                    critBuilder.function("hgmd_enum_to_varchar", String.class, variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.tag)),
                    "DM"));
            predicates.add(critBuilder.equal(variantsHGMDLocatedVariantJoin.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version),
                    hgmdVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return ret;

    }

    @Override
    public List<Variants_80_4> findByGeneName(String name) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        TypedQuery<Variants_80_4> query = getEntityManager().createNamedQuery("Variants_80_4.findByGeneName", Variants_80_4.class);
        query.setParameter("geneName", name);
        List<Variants_80_4> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Variants_80_4> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneNameAndMaxAlleleFrequency(String, Double)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(Variants_80_4_.hgncGene), name));

            Join<Variants_80_4, LocatedVariant> variantsLocatedVariantJoin = root.join(Variants_80_4_.locatedVariant);

            Join<LocatedVariant, ExACMaxVariantFrequency> LocatedVariantMaxVariantFrequencyJoin = variantsLocatedVariantJoin
                    .join(LocatedVariant_.exACMaxVariantFrequencies);

            Coalesce<Double> maxVariantFrequencyCoalesce = critBuilder.coalesce();
            maxVariantFrequencyCoalesce.value(LocatedVariantMaxVariantFrequencyJoin.get(ExACMaxVariantFrequency_.maxAlleleFrequency));
            maxVariantFrequencyCoalesce.value(0D);

            predicates.add(critBuilder.lessThanOrEqualTo(maxVariantFrequencyCoalesce, threshold));

            Join<LocatedVariant, MaxFrequency> LocatedVariantMaxFreqJoin = variantsLocatedVariantJoin.join(LocatedVariant_.maxFreqs);

            Coalesce<Double> maxFreqCoalesce = critBuilder.coalesce();
            maxFreqCoalesce.value(LocatedVariantMaxFreqJoin.get(MaxFrequency_.maxAlleleFreq));
            maxFreqCoalesce.value(0D);

            predicates.add(critBuilder.lessThanOrEqualTo(maxFreqCoalesce, threshold));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findByAssemblyId(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyId(Integer)");
        List<Variants_80_4> ret = new ArrayList<Variants_80_4>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Variants_80_4> crit = critBuilder.createQuery(getPersistentClass());
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<AssemblyLocatedVariant, Assembly> assemblyLocatedVariantAssemblyJoin = root.join(Variants_80_4_.locatedVariant)
                    .join(LocatedVariant_.assemblyLocatedVariants).join(AssemblyLocatedVariant_.assembly);
            predicates.add(critBuilder.equal(assemblyLocatedVariantAssemblyJoin.get(Assembly_.id), assemblyId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Variants_80_4> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Variants_80_4.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Variants_80_4> findByTranscriptAccession(String accession) throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptAccession(String)");
        TypedQuery<Variants_80_4> query = getEntityManager().createNamedQuery("Variants_80_4.findByTranscriptAccession",
                Variants_80_4.class);
        query.setParameter("transcr", accession);
        List<Variants_80_4> ret = query.getResultList();
        return ret;
    }

    @Override
    public Long findByAssemblyIdAndVariantEffect(Integer assemblyId, String variantEffect) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyIdAndVariantEffect(Integer, String)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.variantEffect).get(VariantEffect_.id), variantEffect));
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            crit.select(critBuilder.countDistinct(root.join(Variants_80_4_.locatedVariant).get(LocatedVariant_.id)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findByAssemblyIdAndVariantType(Integer assemblyId, String variantType) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyIdAndLocationType(Integer, String)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.variantType).get(VariantType_.id), variantType));
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            crit.select(critBuilder.countDistinct(root.join(Variants_80_4_.locatedVariant).get(LocatedVariant_.id)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findTranscriptDependentCount(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findTranscriptDependentCount(Integer)");
        Long sum = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(root.join(Variants_80_4_.locationType).get(LocationType_.id).in(Arrays.asList("exon", "intron/exon boundary")));
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.select(critBuilder.count(root.get(Variants_80_4_.locationType)));
            crit.groupBy(root.get(Variants_80_4_.locatedVariant));
            crit.having(critBuilder.gt(critBuilder.count(root.get(Variants_80_4_.locationType)), 1));

            List<Long> results = new ArrayList<>();
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            results.addAll(query.getResultList());
            sum = results.stream().mapToLong(Long::intValue).sum();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sum;
    }

    @Override
    public Long findCodingCount(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findCodingCount(Integer)");
        Long sum = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(root.join(Variants_80_4_.locationType).get(LocationType_.id).in(Arrays.asList("exon", "intron/exon boundary")));
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.select(critBuilder.count(root.get(Variants_80_4_.locationType)));
            crit.groupBy(root.get(Variants_80_4_.locatedVariant));
            crit.having(critBuilder.lessThanOrEqualTo(critBuilder.count(root.get(Variants_80_4_.locationType)), 1L));

            List<Long> results = new ArrayList<>();
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            results.addAll(query.getResultList());
            sum = results.stream().mapToLong(Long::intValue).sum();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sum;
    }

    @Override
    public Long findNonCodingCount(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findNonCodingCount(Integer)");
        Long sum = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<Variants_80_4> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(root.join(Variants_80_4_.locationType).get(LocationType_.id)
                    .in(Arrays.asList("UTR", "UTR-3", "UTR-5", "intron", "intergenic", "potential RNA-editing site")));
            predicates.add(critBuilder.equal(root.join(Variants_80_4_.locatedVariant).join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly).get(Assembly_.id), assemblyId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.select(critBuilder.count(root.get(Variants_80_4_.locationType)));
            crit.groupBy(root.get(Variants_80_4_.locatedVariant));

            List<Long> results = new ArrayList<>();
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            results.addAll(query.getResultList());
            sum = results.stream().mapToLong(Long::intValue).sum();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sum;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void deleteByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException {
        Query qDelete = getEntityManager()
                .createQuery("delete from " + getPersistentClass().getSimpleName() + " a where a.locatedVariant.id = :locatedVariantId");
        qDelete.setParameter("locatedVariantId", locatedVariantId);
        qDelete.executeUpdate();
    }

}
