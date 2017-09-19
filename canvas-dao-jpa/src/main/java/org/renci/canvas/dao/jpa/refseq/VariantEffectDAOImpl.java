package org.renci.canvas.dao.jpa.refseq;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.VariantEffectDAO;
import org.renci.canvas.dao.refseq.model.VariantEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { VariantEffectDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class VariantEffectDAOImpl extends BaseDAOImpl<VariantEffect, String> implements VariantEffectDAO {

    private static final Logger logger = LoggerFactory.getLogger(VariantEffectDAOImpl.class);

    public VariantEffectDAOImpl() {
        super();
    }

    @Override
    public Class<VariantEffect> getPersistentClass() {
        return VariantEffect.class;
    }

    @Override
    public List<VariantEffect> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<VariantEffect> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<VariantEffect> crit = critBuilder.createQuery(getPersistentClass());
            Root<VariantEffect> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<VariantEffect> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
