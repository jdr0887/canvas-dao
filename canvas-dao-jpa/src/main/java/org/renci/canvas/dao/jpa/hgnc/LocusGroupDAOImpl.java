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
import org.renci.canvas.dao.hgnc.LocusGroupDAO;
import org.renci.canvas.dao.hgnc.model.LocusGroup;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { LocusGroupDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class LocusGroupDAOImpl extends BaseDAOImpl<LocusGroup, String> implements LocusGroupDAO {

    private static final Logger logger = LoggerFactory.getLogger(LocusGroupDAOImpl.class);

    public LocusGroupDAOImpl() {
        super();
    }

    @Override
    public Class<LocusGroup> getPersistentClass() {
        return LocusGroup.class;
    }

    @Override
    public List<LocusGroup> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<LocusGroup> ret = new ArrayList<>();
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LocusGroup> crit = critBuilder.createQuery(getPersistentClass());
        Root<LocusGroup> root = crit.from(getPersistentClass());
        crit.distinct(true);
        TypedQuery<LocusGroup> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());
        return ret;
    }

}
