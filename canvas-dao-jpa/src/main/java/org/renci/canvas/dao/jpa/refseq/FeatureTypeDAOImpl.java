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
import org.renci.canvas.dao.refseq.FeatureTypeDAO;
import org.renci.canvas.dao.refseq.model.FeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { FeatureTypeDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class FeatureTypeDAOImpl extends BaseDAOImpl<FeatureType, String> implements FeatureTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(FeatureTypeDAOImpl.class);

    public FeatureTypeDAOImpl() {
        super();
    }

    @Override
    public Class<FeatureType> getPersistentClass() {
        return FeatureType.class;
    }

    @Override
    public List<FeatureType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<FeatureType> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<FeatureType> crit = critBuilder.createQuery(getPersistentClass());
            Root<FeatureType> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<FeatureType> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
