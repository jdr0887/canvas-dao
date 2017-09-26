package org.renci.canvas.dao.jpa.esp;

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
import org.renci.canvas.dao.esp.ESPSNPFrequencyPopulationDAO;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulation;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulationPK;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulationPK_;
import org.renci.canvas.dao.esp.model.ESPSNPFrequencyPopulation_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ESPSNPFrequencyPopulationDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ESPSNPFrequencyPopulationDAOImpl extends BaseDAOImpl<ESPSNPFrequencyPopulation, ESPSNPFrequencyPopulationPK>
        implements ESPSNPFrequencyPopulationDAO {

    private static final Logger logger = LoggerFactory.getLogger(ESPSNPFrequencyPopulationDAOImpl.class);

    public ESPSNPFrequencyPopulationDAOImpl() {
        super();
    }

    @Override
    public Class<ESPSNPFrequencyPopulation> getPersistentClass() {
        return ESPSNPFrequencyPopulation.class;
    }

    @Override
    public List<ESPSNPFrequencyPopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, Integer)");
        List<ESPSNPFrequencyPopulation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ESPSNPFrequencyPopulation> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<ESPSNPFrequencyPopulation> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.join(ESPSNPFrequencyPopulation_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.equal(root.get(ESPSNPFrequencyPopulation_.id).get(ESPSNPFrequencyPopulationPK_.version), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<ESPSNPFrequencyPopulation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<ESPSNPFrequencyPopulation> findByLocatedVariantId(Long locVarId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<ESPSNPFrequencyPopulation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ESPSNPFrequencyPopulation> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<ESPSNPFrequencyPopulation> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.join(ESPSNPFrequencyPopulation_.locatedVariant).get(LocatedVariant_.id), locVarId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<ESPSNPFrequencyPopulation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
