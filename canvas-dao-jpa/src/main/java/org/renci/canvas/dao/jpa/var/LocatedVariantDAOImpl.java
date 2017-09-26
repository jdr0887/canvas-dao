package org.renci.canvas.dao.jpa.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.ref.model.GenomeRefSeq_;
import org.renci.canvas.dao.ref.model.GenomeRef_;
import org.renci.canvas.dao.refseq.model.Variants_61_2;
import org.renci.canvas.dao.refseq.model.Variants_61_2_;
import org.renci.canvas.dao.var.LocatedVariantDAO;
import org.renci.canvas.dao.var.model.Assembly;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant_;
import org.renci.canvas.dao.var.model.Assembly_;
import org.renci.canvas.dao.var.model.CanonicalAllele_;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.renci.canvas.dao.var.model.VariantType;
import org.renci.canvas.dao.var.model.VariantType_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { LocatedVariantDAO.class })
@javax.transaction.Transactional
@Singleton
public class LocatedVariantDAOImpl extends BaseDAOImpl<LocatedVariant, Long> implements LocatedVariantDAO {

    private static final Logger logger = LoggerFactory.getLogger(LocatedVariantDAOImpl.class);

    public LocatedVariantDAOImpl() {
        super();
    }

    @Override
    public Class<LocatedVariant> getPersistentClass() {
        return LocatedVariant.class;
    }

    @Override
    public List<LocatedVariant> findByAssemblyId(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyId(Integer)");
        List<LocatedVariant> ret = new ArrayList<LocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<AssemblyLocatedVariant, Assembly> assemblyLocatedVariantAssemblyJoin = root.join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly);
            predicates.add(critBuilder.equal(assemblyLocatedVariantAssemblyJoin.get(Assembly_.id), assemblyId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findByVersionAccessionAndRefId(String verAccession, Integer genomeRefId) throws CANVASDAOException {
        logger.debug("ENTERING findByVersionAccessionAndRefId(String, Integer)");
        List<LocatedVariant> ret = new ArrayList<LocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<LocatedVariant, GenomeRefSeq> locatedVariantGenomeRefSeqJoin = root.join(LocatedVariant_.genomeRefSeq);
            predicates.add(critBuilder.equal(locatedVariantGenomeRefSeqJoin.get(GenomeRefSeq_.id), verAccession));

            Join<LocatedVariant, GenomeRef> locatedVariantGenomeRefJoin = root.join(LocatedVariant_.genomeRef);
            predicates.add(critBuilder.equal(locatedVariantGenomeRefJoin.get(GenomeRef_.id), genomeRefId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findIncrementable(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findIncrementable(Integer)");
        List<LocatedVariant> ret = new ArrayList<LocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.lt(critBuilder.length(root.get(LocatedVariant_.ref)), 10000));
            Join<AssemblyLocatedVariant, Assembly> assemblyLocatedVariantAssemblyJoin = root.join(LocatedVariant_.assemblyLocatedVariants)
                    .join(AssemblyLocatedVariant_.assembly);
            predicates.add(critBuilder.equal(assemblyLocatedVariantAssemblyJoin.get(Assembly_.id), assemblyId));

            predicates.add(critBuilder.isEmpty(root.get(LocatedVariant_.variants_61_2)));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findByGeneSymbol(String symbol) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneSymbol(String)");
        List<LocatedVariant> ret = new ArrayList<LocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<LocatedVariant, Variants_61_2> locatedVariantVariants_61_2Join = root.join(LocatedVariant_.variants_61_2);
            predicates.add(critBuilder.equal(locatedVariantVariants_61_2Join.get(Variants_61_2_.hgncGene), symbol));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findByExample(LocatedVariant locatedVariant) throws CANVASDAOException {
        logger.debug("ENTERING findByExample(LocatedVariant)");
        List<LocatedVariant> ret = new ArrayList<LocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (locatedVariant.getPosition() != null) {
                predicates.add(critBuilder.equal(root.get(LocatedVariant_.position), locatedVariant.getPosition()));
            }

            if (locatedVariant.getEndPosition() != null) {
                predicates.add(critBuilder.equal(root.get(LocatedVariant_.endPosition), locatedVariant.getEndPosition()));
            }

            if (StringUtils.isNotEmpty(locatedVariant.getSeq())) {
                predicates.add(critBuilder.equal(root.get(LocatedVariant_.seq), locatedVariant.getSeq()));
            }

            if (StringUtils.isNotEmpty(locatedVariant.getRef())) {
                predicates.add(critBuilder.equal(root.get(LocatedVariant_.ref), locatedVariant.getRef()));
            }

            if (locatedVariant.getVariantType() != null) {
                Join<LocatedVariant, VariantType> locatedVariantVariantTypeJoin = root.join(LocatedVariant_.variantType);
                predicates.add(
                        critBuilder.equal(locatedVariantVariantTypeJoin.get(VariantType_.id), locatedVariant.getVariantType().getId()));
            }

            if (locatedVariant.getGenomeRef() != null) {
                Join<LocatedVariant, GenomeRef> locatedVariantGenomeRefJoin = root.join(LocatedVariant_.genomeRef);
                predicates.add(critBuilder.equal(locatedVariantGenomeRefJoin.get(GenomeRef_.id), locatedVariant.getGenomeRef().getId()));
            }

            if (locatedVariant.getGenomeRefSeq() != null) {
                Join<LocatedVariant, GenomeRefSeq> locatedVariantGenomeRefSeqJoin = root.join(LocatedVariant_.genomeRefSeq);
                predicates.add(
                        critBuilder.equal(locatedVariantGenomeRefSeqJoin.get(GenomeRefSeq_.id), locatedVariant.getGenomeRefSeq().getId()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findByCanonicalAlleleId(Integer canonicalAlleleId) throws CANVASDAOException {
        logger.debug("ENTERING findByCanonicalAlleleId(Integer)");
        List<LocatedVariant> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(LocatedVariant_.canonicalAlleles).get(CanonicalAllele_.id), canonicalAlleleId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.LocatedVariant.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<LocatedVariant> findBad(Integer genomeRefId) throws CANVASDAOException {
        logger.debug("ENTERING findByCanonicalAlleleId(Integer)");
        List<LocatedVariant> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<LocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(LocatedVariant_.genomeRef).get(GenomeRef_.id), genomeRefId));
            predicates.add(critBuilder.equal(root.get(LocatedVariant_.seq), root.get(LocatedVariant_.ref)));
            predicates.add(root.join(LocatedVariant_.variantType).get(VariantType_.id).in(Arrays.asList("snp", "sub")));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<LocatedVariant> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<Long> findIdByGenomeRefIdAndVariantType(Integer genomeRefId, String variantType) throws CANVASDAOException {
        logger.debug("ENTERING findIdByGenomeRefIdAndVariantType(Integer, String)");
        List<Long> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<LocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(LocatedVariant_.genomeRef).get(GenomeRef_.id), genomeRefId));
            predicates.add(critBuilder.equal(root.join(LocatedVariant_.variantType).get(VariantType_.id), variantType));
            crit.select(critBuilder.countDistinct(root.get(LocatedVariant_.id)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(LocatedVariant entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(LocatedVariant)");
        LocatedVariant foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

}
