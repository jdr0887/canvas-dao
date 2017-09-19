package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.IncidentalStatusTypeDAO;
import org.renci.canvas.dao.clinbin.model.IncidentalStatusType;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { IncidentalStatusTypeDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class IncidentalStatusTypeDAOImpl extends BaseDAOImpl<IncidentalStatusType, String> implements IncidentalStatusTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(IncidentalStatusTypeDAOImpl.class);

    public IncidentalStatusTypeDAOImpl() {
        super();
    }

    @Override
    public Class<IncidentalStatusType> getPersistentClass() {
        return IncidentalStatusType.class;
    }

    @Override
    public List<IncidentalStatusType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<IncidentalStatusType> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<IncidentalStatusType> crit = critBuilder.createQuery(getPersistentClass());
            Root<IncidentalStatusType> root = crit.from(getPersistentClass());
            TypedQuery<IncidentalStatusType> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
