package org.renci.canvas.dao.jpa.ref;

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
import org.renci.canvas.dao.ref.GenomeRefDAO;
import org.renci.canvas.dao.ref.SequenceTypeDAO;
import org.renci.canvas.dao.ref.model.SequenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GenomeRefDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class SequenceTypeDAOImpl extends BaseDAOImpl<SequenceType, String> implements SequenceTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(SequenceTypeDAOImpl.class);

    public SequenceTypeDAOImpl() {
        super();
    }

    @Override
    public Class<SequenceType> getPersistentClass() {
        return SequenceType.class;
    }

    @Override
    public List<SequenceType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<SequenceType> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SequenceType> crit = critBuilder.createQuery(getPersistentClass());
            Root<SequenceType> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<SequenceType> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
