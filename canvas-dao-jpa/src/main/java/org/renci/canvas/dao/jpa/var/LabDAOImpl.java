package org.renci.canvas.dao.jpa.var;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.LabDAO;
import org.renci.canvas.dao.var.model.Lab;
import org.renci.canvas.dao.var.model.Lab_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { LabDAO.class })
@javax.transaction.Transactional
@Singleton
public class LabDAOImpl extends BaseDAOImpl<Lab, String> implements LabDAO {

    private static final Logger logger = LoggerFactory.getLogger(LabDAOImpl.class);

    public LabDAOImpl() {
        super();
    }

    @Override
    public Class<Lab> getPersistentClass() {
        return Lab.class;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(Lab entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(T)");
        Lab foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

    @Override
    public List<Lab> findByName(String name) throws CANVASDAOException {
        logger.debug("ENTERING findByName(String)");
        List<Lab> ret = new ArrayList<Lab>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Lab> crit = critBuilder.createQuery(getPersistentClass());
            Root<Lab> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (!name.endsWith("%")) {
                name += name + "%";
            }
            predicates.add(critBuilder.like(root.get(Lab_.id), name));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Lab> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
