package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Coalesce;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.MaxFrequencyDAO;
import org.renci.canvas.dao.clinbin.model.MaxFrequency;
import org.renci.canvas.dao.clinbin.model.MaxFrequencyPK;
import org.renci.canvas.dao.clinbin.model.MaxFrequency_;
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
@OsgiServiceProvider(classes = { MaxFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class MaxFrequencyDAOImpl extends BaseDAOImpl<MaxFrequency, MaxFrequencyPK> implements MaxFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(MaxFrequencyDAOImpl.class);

    public MaxFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<MaxFrequency> getPersistentClass() {
        return MaxFrequency.class;
    }

    @Override
    public List<MaxFrequency> findByGeneNameAndMaxAlleleFrequency(String name, Double threshold) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneNameAndMaxAlleleFrequency(String, Double)");
        List<MaxFrequency> ret = new ArrayList<MaxFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<MaxFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<MaxFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<MaxFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root.join(MaxFrequency_.locatedVariant,
                    JoinType.LEFT);
            Join<LocatedVariant, Variants_61_2> locatedVariantVariantsJoin = maxVariantFrequencyLocatedVariantJoin
                    .join(LocatedVariant_.variants_61_2, JoinType.LEFT);
            predicates.add(critBuilder.equal(locatedVariantVariantsJoin.get(Variants_61_2_.hgncGene), name));
            Coalesce<Double> maxFreqCoalesce = critBuilder.coalesce();
            maxFreqCoalesce.value(root.get(MaxFrequency_.maxAlleleFreq));
            maxFreqCoalesce.value(0D);
            predicates.add(critBuilder.lessThanOrEqualTo(maxFreqCoalesce, threshold));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<MaxFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.MaxFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<MaxFrequency> findByLocatedVariantId(Long LocatedVariantId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<MaxFrequency> ret = new ArrayList<MaxFrequency>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<MaxFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<MaxFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<MaxFrequency, LocatedVariant> maxVariantFrequencyLocatedVariantJoin = root.join(MaxFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(maxVariantFrequencyLocatedVariantJoin.get(LocatedVariant_.id), LocatedVariantId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<MaxFrequency> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.MaxFrequency.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
