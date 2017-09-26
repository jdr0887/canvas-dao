package org.renci.canvas.dao.jpa.exac;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.exac.ExACVariantFrequencyDAO;
import org.renci.canvas.dao.exac.model.ExACVariantFrequency;
import org.renci.canvas.dao.exac.model.ExACVariantFrequencyPK;
import org.renci.canvas.dao.exac.model.ExACVariantFrequencyPK_;
import org.renci.canvas.dao.exac.model.ExACVariantFrequency_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ExACVariantFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ExACVariantFrequencyDAOImpl extends BaseDAOImpl<ExACVariantFrequency, ExACVariantFrequencyPK>
        implements ExACVariantFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(ExACVariantFrequencyDAOImpl.class);

    public ExACVariantFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<ExACVariantFrequency> getPersistentClass() {
        return ExACVariantFrequency.class;
    }

    @Override
    public List<ExACVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, String)");
        List<ExACVariantFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(ExACVariantFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.equal(root.get(ExACVariantFrequency_.id).get(ExACVariantFrequencyPK_.version), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACVariantFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ExACVariantFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<ExACVariantFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ExACVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<ExACVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(ExACVariantFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ExACVariantFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
