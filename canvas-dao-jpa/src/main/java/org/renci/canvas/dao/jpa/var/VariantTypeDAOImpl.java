package org.renci.canvas.dao.jpa.var;

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
import org.renci.canvas.dao.var.VariantTypeDAO;
import org.renci.canvas.dao.var.model.VariantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { VariantTypeDAO.class })
@javax.transaction.Transactional
@Singleton
public class VariantTypeDAOImpl extends BaseDAOImpl<VariantType, String> implements VariantTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(VariantTypeDAOImpl.class);

    public VariantTypeDAOImpl() {
        super();
    }

    @Override
    public Class<VariantType> getPersistentClass() {
        return VariantType.class;
    }

    @Override
    public List<VariantType> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<VariantType> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<VariantType> crit = critBuilder.createQuery(getPersistentClass());
            Root<VariantType> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<VariantType> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
