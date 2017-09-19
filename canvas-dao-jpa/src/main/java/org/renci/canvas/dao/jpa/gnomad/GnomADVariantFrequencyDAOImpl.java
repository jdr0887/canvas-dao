package org.renci.canvas.dao.jpa.gnomad;

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
import org.renci.canvas.dao.gnomad.GnomADVariantFrequencyDAO;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequency;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequencyPK;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequencyPK_;
import org.renci.canvas.dao.gnomad.model.GnomADVariantFrequency_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GnomADVariantFrequencyDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GnomADVariantFrequencyDAOImpl extends BaseDAOImpl<GnomADVariantFrequency, GnomADVariantFrequencyPK>
        implements GnomADVariantFrequencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(GnomADVariantFrequencyDAOImpl.class);

    public GnomADVariantFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<GnomADVariantFrequency> getPersistentClass() {
        return GnomADVariantFrequency.class;
    }

    @Override
    public List<GnomADVariantFrequency> findByLocatedVariantIdAndVersion(Long locVarId, String version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, String)");
        List<GnomADVariantFrequency> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GnomADVariantFrequency> crit = critBuilder.createQuery(getPersistentClass());
            Root<GnomADVariantFrequency> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(GnomADVariantFrequency_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.equal(root.get(GnomADVariantFrequency_.id).get(GnomADVariantFrequencyPK_.version), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GnomADVariantFrequency> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
