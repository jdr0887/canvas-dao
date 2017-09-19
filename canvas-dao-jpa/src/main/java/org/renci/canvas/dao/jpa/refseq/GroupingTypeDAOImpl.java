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
import org.renci.canvas.dao.refseq.GroupingTypeDAO;
import org.renci.canvas.dao.refseq.LocationTypeDAO;
import org.renci.canvas.dao.refseq.model.GroupingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { LocationTypeDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GroupingTypeDAOImpl extends BaseDAOImpl<GroupingType, String> implements GroupingTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(GroupingTypeDAOImpl.class);

    public GroupingTypeDAOImpl() {
        super();
    }

    @Override
    public Class<GroupingType> getPersistentClass() {
        return GroupingType.class;
    }

    @Override
    public List<GroupingType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<GroupingType> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GroupingType> crit = critBuilder.createQuery(getPersistentClass());
            Root<GroupingType> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<GroupingType> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
