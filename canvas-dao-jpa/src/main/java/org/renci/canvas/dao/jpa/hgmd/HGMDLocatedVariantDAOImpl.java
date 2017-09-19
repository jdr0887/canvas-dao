package org.renci.canvas.dao.jpa.hgmd;

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
import org.renci.canvas.dao.hgmd.HGMDLocatedVariantDAO;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariant;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariantPK;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariantPK_;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariant_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { HGMDLocatedVariantDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class HGMDLocatedVariantDAOImpl extends BaseDAOImpl<HGMDLocatedVariant, HGMDLocatedVariantPK> implements HGMDLocatedVariantDAO {

    private static final Logger logger = LoggerFactory.getLogger(HGMDLocatedVariantDAOImpl.class);

    public HGMDLocatedVariantDAOImpl() {
        super();
    }

    @Override
    public Class<HGMDLocatedVariant> getPersistentClass() {
        return HGMDLocatedVariant.class;
    }

    @Override
    public Integer findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            Root<HGMDLocatedVariant> root = crit.from(getPersistentClass());
            crit.select(root.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder.desc(root.get(HGMDLocatedVariant_.id).get(HGMDLocatedVariantPK_.version)));
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
    public List<HGMDLocatedVariant> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<HGMDLocatedVariant> ret = new ArrayList<HGMDLocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<HGMDLocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<HGMDLocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<HGMDLocatedVariant, LocatedVariant> hgmdLocatedVariantLocatedVariantJoin = root.join(HGMDLocatedVariant_.locatedVariant);
            predicates.add(critBuilder.equal(hgmdLocatedVariantLocatedVariantJoin.get(LocatedVariant_.id), locatedVariantId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<HGMDLocatedVariant> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
