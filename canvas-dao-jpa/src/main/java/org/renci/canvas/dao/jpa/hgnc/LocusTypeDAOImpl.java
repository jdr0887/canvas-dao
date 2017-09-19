package org.renci.canvas.dao.jpa.hgnc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.LocusTypeDAO;
import org.renci.canvas.dao.hgnc.model.LocusType;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { LocusTypeDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class LocusTypeDAOImpl extends BaseDAOImpl<LocusType, String> implements LocusTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(LocusTypeDAOImpl.class);

    public LocusTypeDAOImpl() {
        super();
    }

    @Override
    public Class<LocusType> getPersistentClass() {
        return LocusType.class;
    }

    @Override
    public List<LocusType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<LocusType> ret = new ArrayList<>();
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LocusType> crit = critBuilder.createQuery(getPersistentClass());
        Root<LocusType> root = crit.from(getPersistentClass());
        crit.distinct(true);
        TypedQuery<LocusType> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());
        return ret;
    }

}
