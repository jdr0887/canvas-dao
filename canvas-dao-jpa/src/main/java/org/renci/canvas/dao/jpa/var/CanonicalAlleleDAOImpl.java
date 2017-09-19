package org.renci.canvas.dao.jpa.var;

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
import org.renci.canvas.dao.var.CanonicalAlleleDAO;
import org.renci.canvas.dao.var.LocatedVariantDAO;
import org.renci.canvas.dao.var.model.CanonicalAllele;
import org.renci.canvas.dao.var.model.CanonicalAllele_;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { LocatedVariantDAO.class })
@javax.transaction.Transactional
@Singleton
public class CanonicalAlleleDAOImpl extends BaseDAOImpl<CanonicalAllele, Integer> implements CanonicalAlleleDAO {

    private static final Logger logger = LoggerFactory.getLogger(CanonicalAlleleDAOImpl.class);

    public CanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAllele> getPersistentClass() {
        return CanonicalAllele.class;
    }

    @Override
    public List<CanonicalAllele> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<CanonicalAllele> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<CanonicalAllele> crit = critBuilder.createQuery(getPersistentClass());
            Root<CanonicalAllele> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(CanonicalAllele_.locatedVariants).get(LocatedVariant_.id), locatedVariantId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<CanonicalAllele> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
