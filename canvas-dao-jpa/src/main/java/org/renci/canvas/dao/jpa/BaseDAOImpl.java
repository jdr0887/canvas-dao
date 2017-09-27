package org.renci.canvas.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.transaction.annotation.Transactional
@javax.transaction.Transactional
public abstract class BaseDAOImpl<T extends Persistable<ID>, ID extends Serializable> implements BaseDAO<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);

    @PersistenceContext(name = "canvas", unitName = "canvas")
    private EntityManager entityManager;

    public BaseDAOImpl() {
        super();
    }

    public abstract Class<T> getPersistentClass();

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
    @Override
    public T findById(ID id) throws CANVASDAOException {
        logger.debug("ENTERING findById(T)");
        T ret = entityManager.find(getPersistentClass(), id);
        return ret;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
    @Override
    public List<T> findByIdList(List<ID> idList) throws CANVASDAOException {
        logger.debug("ENTERING findById(T)");
        List<T> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> crit = critBuilder.createQuery(getPersistentClass());
            Root<T> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(root.<ID> get("id").in(idList));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<T> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public synchronized ID save(T entity) throws CANVASDAOException {
        logger.debug("ENTERING save(T)");
        if (entity == null) {
            logger.error("entity is null");
            return null;
        }
        if (!getEntityManager().contains(entity) && entity.getId() != null) {
            entity = getEntityManager().merge(entity);
        } else {
            getEntityManager().persist(entity);
        }
        getEntityManager().flush();
        getEntityManager().clear();
        return entity.getId();
    }

    @Override
    public synchronized List<ID> save(List<T> entities) throws CANVASDAOException {
        logger.debug("ENTERING save(List<T>)");
        List<ID> ret = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            T entity = entities.get(i);
            if (entity == null) {
                logger.error("entity is null");
                return null;
            }
            if (!getEntityManager().contains(entity) && entity.getId() != null) {
                entity = getEntityManager().merge(entity);
            } else {
                getEntityManager().persist(entity);
            }

            if (i % 100 == 0) {
                logger.debug("flushing batch: {}", i);
                getEntityManager().flush();
                getEntityManager().clear();
            }

            ret.add(entity.getId());
        }
        getEntityManager().flush();
        getEntityManager().clear();
        return ret;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
