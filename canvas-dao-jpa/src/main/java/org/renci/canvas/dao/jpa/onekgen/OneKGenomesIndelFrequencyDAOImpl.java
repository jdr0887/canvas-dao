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
import org.renci.canvas.dao.onekgen.OneKGenomesIndelFrequencyDAO;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequencyPK;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequencyPK_;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelFrequency_;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { OneKGenomesIndelFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class OneKGenomesIndelFrequencyDAOImpl extends BaseDAOImpl<OneKGenomesIndelFrequency, OneKGenomesIndelFrequencyPK>
        implements OneKGenomesIndelFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(OneKGenomesIndelFrequencyDAOImpl.class);

    public OneKGenomesIndelFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<OneKGenomesIndelFrequency> getPersistentClass() {
        return OneKGenomesIndelFrequency.class;
    }

    @Override
    public List<OneKGenomesIndelFrequency> findByLocatedVariantIdAndVersion(Long locVarId, Integer version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, Integer)");
        List<OneKGenomesIndelFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OneKGenomesIndelFrequency> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<OneKGenomesIndelFrequency> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.join(OneKGenomesIndelFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.equal(root.get(OneKGenomesIndelFrequency_.id).get(OneKGenomesIndelFrequencyPK_.version), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<OneKGenomesIndelFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
