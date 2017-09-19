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
import org.renci.canvas.dao.onekgen.OneKGenomesSNPPopulationMaxFrequencyDAO;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequencyPK;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequencyPK_;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequency_;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { OneKGenomesSNPPopulationMaxFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class OneKGenomesSNPPopulationMaxFrequencyDAOImpl
        extends BaseDAOImpl<OneKGenomesSNPPopulationMaxFrequency, OneKGenomesSNPPopulationMaxFrequencyPK>
        implements OneKGenomesSNPPopulationMaxFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(OneKGenomesSNPPopulationMaxFrequencyDAOImpl.class);

    public OneKGenomesSNPPopulationMaxFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<OneKGenomesSNPPopulationMaxFrequency> getPersistentClass() {
        return OneKGenomesSNPPopulationMaxFrequency.class;
    }

    @Override
    public Integer findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            Root<OneKGenomesSNPPopulationMaxFrequency> root = crit.from(getPersistentClass());
            crit.select(root.get(OneKGenomesSNPPopulationMaxFrequency_.id).get(OneKGenomesSNPPopulationMaxFrequencyPK_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder
                    .desc(root.get(OneKGenomesSNPPopulationMaxFrequency_.id).get(OneKGenomesSNPPopulationMaxFrequencyPK_.version)));
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
    public List<OneKGenomesSNPPopulationMaxFrequency> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<OneKGenomesSNPPopulationMaxFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OneKGenomesSNPPopulationMaxFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<OneKGenomesSNPPopulationMaxFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<OneKGenomesSNPPopulationMaxFrequency, LocatedVariant> snpPopulationMaxFrequencyLocatedVariantJoin = root
                    .join(OneKGenomesSNPPopulationMaxFrequency_.locatedVariant);
            predicates.add(critBuilder.equal(snpPopulationMaxFrequencyLocatedVariantJoin.get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<OneKGenomesSNPPopulationMaxFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
