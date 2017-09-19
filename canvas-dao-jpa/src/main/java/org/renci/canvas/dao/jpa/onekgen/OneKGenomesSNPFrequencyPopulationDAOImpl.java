package org.renci.canvas.dao.jpa.onekgen;

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
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.onekgen.OneKGenomesSNPFrequencyPopulationDAO;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulation;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulationPK;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulationPK_;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencyPopulation_;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { OneKGenomesSNPFrequencyPopulationDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class OneKGenomesSNPFrequencyPopulationDAOImpl extends
        BaseDAOImpl<OneKGenomesSNPFrequencyPopulation, OneKGenomesSNPFrequencyPopulationPK> implements OneKGenomesSNPFrequencyPopulationDAO {

    private static final Logger logger = LoggerFactory.getLogger(OneKGenomesSNPFrequencyPopulationDAOImpl.class);

    public OneKGenomesSNPFrequencyPopulationDAOImpl() {
        super();
    }

    @Override
    public Class<OneKGenomesSNPFrequencyPopulation> getPersistentClass() {
        return OneKGenomesSNPFrequencyPopulation.class;
    }

    @Override
    public List<OneKGenomesSNPFrequencyPopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version)
            throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, Integer)");
        List<OneKGenomesSNPFrequencyPopulation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OneKGenomesSNPFrequencyPopulation> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<OneKGenomesSNPFrequencyPopulation> root = crit.from(getPersistentClass());
            predicates
                    .add(critBuilder.equal(root.join(OneKGenomesSNPFrequencyPopulation_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder
                    .equal(root.get(OneKGenomesSNPFrequencyPopulation_.id).get(OneKGenomesSNPFrequencyPopulationPK_.version), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<OneKGenomesSNPFrequencyPopulation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
