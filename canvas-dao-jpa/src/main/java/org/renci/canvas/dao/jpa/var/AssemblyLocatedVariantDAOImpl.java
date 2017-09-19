package org.renci.canvas.dao.jpa.var;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.AssemblyDAO;
import org.renci.canvas.dao.var.AssemblyLocatedVariantDAO;
import org.renci.canvas.dao.var.model.Assembly;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariantPK;
import org.renci.canvas.dao.var.model.AssemblyLocatedVariant_;
import org.renci.canvas.dao.var.model.Assembly_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { AssemblyDAO.class })
@javax.transaction.Transactional
@Singleton
public class AssemblyLocatedVariantDAOImpl extends BaseDAOImpl<AssemblyLocatedVariant, AssemblyLocatedVariantPK>
        implements AssemblyLocatedVariantDAO {

    private static final Logger logger = LoggerFactory.getLogger(AssemblyLocatedVariantDAOImpl.class);

    public AssemblyLocatedVariantDAOImpl() {
        super();
    }

    @Override
    public Class<AssemblyLocatedVariant> getPersistentClass() {
        return AssemblyLocatedVariant.class;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(AssemblyLocatedVariant entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(AssemblyLocatedVariant)");
        AssemblyLocatedVariant foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void deleteByAssemblyId(Integer assemblyId) throws CANVASDAOException {
        Query qDelete = getEntityManager()
                .createQuery("delete from " + getPersistentClass().getSimpleName() + " a where a.assembly.id = :assemblyId");
        qDelete.setParameter("assemblyId", assemblyId);
        qDelete.executeUpdate();
    }

    @Override
    public List<AssemblyLocatedVariant> findByAssemblyId(Integer assemblyId) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyId(Integer)");
        List<AssemblyLocatedVariant> ret = new ArrayList<AssemblyLocatedVariant>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AssemblyLocatedVariant> crit = critBuilder.createQuery(getPersistentClass());
            Root<AssemblyLocatedVariant> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<AssemblyLocatedVariant, Assembly> assemblyLocatedVariantAssemblyJoin = root.join(AssemblyLocatedVariant_.assembly);
            predicates.add(critBuilder.equal(assemblyLocatedVariantAssemblyJoin.get(Assembly_.id), assemblyId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<AssemblyLocatedVariant> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
