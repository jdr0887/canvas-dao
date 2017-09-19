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
import org.renci.canvas.dao.onekgen.OneKGenomesSNPFrequencySubpopulationDAO;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulation;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulationPK;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulationPK_;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPFrequencySubpopulation_;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { OneKGenomesSNPFrequencySubpopulationDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class OneKGenomesSNPFrequencySubpopulationDAOImpl
        extends BaseDAOImpl<OneKGenomesSNPFrequencySubpopulation, OneKGenomesSNPFrequencySubpopulationPK>
        implements OneKGenomesSNPFrequencySubpopulationDAO {

    private static final Logger logger = LoggerFactory.getLogger(OneKGenomesSNPFrequencySubpopulationDAOImpl.class);

    public OneKGenomesSNPFrequencySubpopulationDAOImpl() {
        super();
    }

    @Override
    public Class<OneKGenomesSNPFrequencySubpopulation> getPersistentClass() {
        return OneKGenomesSNPFrequencySubpopulation.class;
    }

    @Override
    public List<OneKGenomesSNPFrequencySubpopulation> findByLocatedVariantIdAndVersion(Long locVarId, Integer version)
            throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, Integer)");
        List<OneKGenomesSNPFrequencySubpopulation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OneKGenomesSNPFrequencySubpopulation> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<OneKGenomesSNPFrequencySubpopulation> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.join(OneKGenomesSNPFrequencySubpopulation_.locatedVariant).get(LocatedVariant_.id),
                    locVarId));
            predicates.add(critBuilder.equal(
                    root.get(OneKGenomesSNPFrequencySubpopulation_.id).get(OneKGenomesSNPFrequencySubpopulationPK_.version),
                    version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<OneKGenomesSNPFrequencySubpopulation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
