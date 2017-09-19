package org.renci.canvas.dao.jpa.onekgen;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.onekgen.OneKGenomesIndelMaxFrequencyDAO;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequencyPK;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequencyPK_;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequency_;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { OneKGenomesIndelMaxFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class OneKGenomesIndelMaxFrequencyDAOImpl extends BaseDAOImpl<OneKGenomesIndelMaxFrequency, OneKGenomesIndelMaxFrequencyPK>
        implements OneKGenomesIndelMaxFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(OneKGenomesIndelMaxFrequencyDAOImpl.class);

    public OneKGenomesIndelMaxFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<OneKGenomesIndelMaxFrequency> getPersistentClass() {
        return OneKGenomesIndelMaxFrequency.class;
    }

    @Override
    public Integer findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            Root<OneKGenomesIndelMaxFrequency> root = crit.from(getPersistentClass());
            crit.select(root.get(OneKGenomesIndelMaxFrequency_.id).get(OneKGenomesIndelMaxFrequencyPK_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder.desc(root.get(OneKGenomesIndelMaxFrequency_.id).get(OneKGenomesIndelMaxFrequencyPK_.version)));
            TypedQuery<Integer> query = getEntityManager().createQuery(crit);
            List<Integer> results = query.getResultList();
            if (CollectionUtils.isNotEmpty(results)) {
                ret = results.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<OneKGenomesIndelMaxFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<OneKGenomesIndelMaxFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OneKGenomesIndelMaxFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<OneKGenomesIndelMaxFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<OneKGenomesIndelMaxFrequency, LocatedVariant> indelMaxFrequencyLocatedVariantJoin = root
                    .join(OneKGenomesIndelMaxFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(indelMaxFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<OneKGenomesIndelMaxFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
