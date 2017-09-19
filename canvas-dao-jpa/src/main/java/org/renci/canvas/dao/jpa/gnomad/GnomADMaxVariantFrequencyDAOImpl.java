package org.renci.canvas.dao.jpa.gnomad;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Coalesce;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.gnomad.GnomADMaxVariantFrequencyDAO;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequency;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequencyPK;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequencyPK_;
import org.renci.canvas.dao.gnomad.model.GnomADMaxVariantFrequency_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.model.Variants_61_2;
import org.renci.canvas.dao.refseq.model.Variants_61_2_;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GnomADMaxVariantFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GnomADMaxVariantFrequencyDAOImpl extends BaseDAOImpl<GnomADMaxVariantFrequency, GnomADMaxVariantFrequencyPK>
        implements GnomADMaxVariantFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(GnomADMaxVariantFrequencyDAOImpl.class);

    public GnomADMaxVariantFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<GnomADMaxVariantFrequency> getPersistentClass() {
        return GnomADMaxVariantFrequency.class;
    }

    @Override
    public String findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        String ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<String> crit = critBuilder.createQuery(String.class);
            Root<GnomADMaxVariantFrequency> root = crit.from(getPersistentClass());
            crit.select(root.get(GnomADMaxVariantFrequency_.id).get(GnomADMaxVariantFrequencyPK_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder.desc(root.get(GnomADMaxVariantFrequency_.id).get(GnomADMaxVariantFrequencyPK_.version)));
            TypedQuery<String> query = getEntityManager().createQuery(crit);
            List<String> results = query.getResultList();
            if (CollectionUtils.isNotEmpty(results)) {
                ret = results.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<GnomADMaxVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, String)");
        List<GnomADMaxVariantFrequency> ret = new ArrayList<GnomADMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GnomADMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<GnomADMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GnomADMaxVariantFrequency_.id).get(GnomADMaxVariantFrequencyPK_.version), version));
            predicates.add(critBuilder.equal(root.join(GnomADMaxVariantFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GnomADMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("gnomad.GnomADMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<GnomADMaxVariantFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<GnomADMaxVariantFrequency> ret = new ArrayList<GnomADMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GnomADMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<GnomADMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<GnomADMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(GnomADMaxVariantFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(maxVariantFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GnomADMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("gnomad.GnomADMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<GnomADMaxVariantFrequency> findByLocatedVariantIdAndFrequencyThreshold(Long locVarId, Double threshold)
            throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<GnomADMaxVariantFrequency> ret = new ArrayList<GnomADMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GnomADMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<GnomADMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<GnomADMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(GnomADMaxVariantFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(maxVariantFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            Coalesce<Double> maxVariantFrequencyCoalesce = critBuilder.coalesce();
            maxVariantFrequencyCoalesce.value(root.get(GnomADMaxVariantFrequency_.maxAlleleFrequency));
            maxVariantFrequencyCoalesce.value(0D);
            predicates.add(critBuilder.lessThanOrEqualTo(maxVariantFrequencyCoalesce, threshold));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GnomADMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("gnomad.GnomADMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<GnomADMaxVariantFrequency> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneNameAndMaxAlleleFrequency(String, Double)");
        List<GnomADMaxVariantFrequency> ret = new ArrayList<GnomADMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GnomADMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<GnomADMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<GnomADMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(GnomADMaxVariantFrequency_.locatedVariant);
            Join<LocatedVariant, Variants_61_2> LocatedVariantVariantsJoin = maxVariantFrequencyLocatedVariantJoin
                    .join(LocatedVariant_.variants_61_2);
            predicates.add(critBuilder.equal(LocatedVariantVariantsJoin.get(Variants_61_2_.hgncGene), name));
            Coalesce<Double> maxVariantFrequencyCoalesce = critBuilder.coalesce();
            maxVariantFrequencyCoalesce.value(root.get(GnomADMaxVariantFrequency_.maxAlleleFrequency));
            maxVariantFrequencyCoalesce.value(0D);
            predicates.add(critBuilder.lessThanOrEqualTo(maxVariantFrequencyCoalesce, threshold));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GnomADMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("gnomad.GnomADMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
