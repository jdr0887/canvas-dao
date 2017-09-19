package org.renci.canvas.dao.jpa.exac;

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

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.exac.ExACMaxVariantFrequencyDAO;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequencyPK;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequencyPK_;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency_;
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
@OsgiServiceProvider(classes = { ExACMaxVariantFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ExACMaxVariantFrequencyDAOImpl extends BaseDAOImpl<ExACMaxVariantFrequency, ExACMaxVariantFrequencyPK>
        implements ExACMaxVariantFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(ExACMaxVariantFrequencyDAOImpl.class);

    public ExACMaxVariantFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<ExACMaxVariantFrequency> getPersistentClass() {
        return ExACMaxVariantFrequency.class;
    }

    @Override
    public List<ExACMaxVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, String)");
        List<ExACMaxVariantFrequency> ret = new ArrayList<ExACMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(ExACMaxVariantFrequency_.id).get(ExACMaxVariantFrequencyPK_.version), version));
            predicates.add(critBuilder.equal(root.join(ExACMaxVariantFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("exac.ExACMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ExACMaxVariantFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<ExACMaxVariantFrequency> ret = new ArrayList<ExACMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ExACMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(ExACMaxVariantFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(maxVariantFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("exac.ExACMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ExACMaxVariantFrequency> findByLocatedVariantIdAndFrequencyThreshold(Long locVarId, Double threshold)
            throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<ExACMaxVariantFrequency> ret = new ArrayList<ExACMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ExACMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(ExACMaxVariantFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(maxVariantFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            Coalesce<Double> maxVariantFrequencyCoalesce = critBuilder.coalesce();
            maxVariantFrequencyCoalesce.value(root.get(ExACMaxVariantFrequency_.maxAlleleFrequency));
            maxVariantFrequencyCoalesce.value(0D);
            predicates.add(critBuilder.lessThanOrEqualTo(maxVariantFrequencyCoalesce, threshold));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("exac.ExACMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ExACMaxVariantFrequency> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneNameAndMaxAlleleFrequency(String, Double)");
        List<ExACMaxVariantFrequency> ret = new ArrayList<ExACMaxVariantFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACMaxVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACMaxVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ExACMaxVariantFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root
                    .join(ExACMaxVariantFrequency_.locatedVariant);
            Join<LocatedVariant, Variants_61_2> LocatedVariantVariantsJoin = maxVariantFrequencyLocatedVariantJoin
                    .join(LocatedVariant_.variants_61_2);
            predicates.add(critBuilder.equal(LocatedVariantVariantsJoin.get(Variants_61_2_.hgncGene), name));
            Coalesce<Double> maxVariantFrequencyCoalesce = critBuilder.coalesce();
            maxVariantFrequencyCoalesce.value(root.get(ExACMaxVariantFrequency_.maxAlleleFrequency));
            maxVariantFrequencyCoalesce.value(0D);
            predicates.add(critBuilder.lessThanOrEqualTo(maxVariantFrequencyCoalesce, threshold));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACMaxVariantFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("exac.ExACMaxVariantFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
