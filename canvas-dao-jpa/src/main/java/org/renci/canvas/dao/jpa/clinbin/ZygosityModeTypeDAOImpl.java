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
import org.renci.canvas.dao.clinbin.UnimportantExonDAO;
import org.renci.canvas.dao.clinbin.ZygosityModeTypeDAO;
import org.renci.canvas.dao.clinbin.model.ZygosityModeType;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { UnimportantExonDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ZygosityModeTypeDAOImpl extends BaseDAOImpl<ZygosityModeType, String> implements ZygosityModeTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(ZygosityModeTypeDAOImpl.class);

    public ZygosityModeTypeDAOImpl() {
        super();
    }

    @Override
    public Class<ZygosityModeType> getPersistentClass() {
        return ZygosityModeType.class;
    }

    @Override
    public List<ZygosityModeType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<ZygosityModeType> ret = new ArrayList<>();
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ZygosityModeType> crit = critBuilder.createQuery(getPersistentClass());
        Root<ZygosityModeType> root = crit.from(getPersistentClass());
        crit.distinct(true);
        TypedQuery<ZygosityModeType> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());
        return ret;
    }

}
