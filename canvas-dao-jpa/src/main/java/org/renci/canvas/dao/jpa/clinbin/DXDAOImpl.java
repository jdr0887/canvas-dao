package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.DXDAO;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DXDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DXDAOImpl extends BaseDAOImpl<DX, Integer> implements DXDAO {

    private static final Logger logger = LoggerFactory.getLogger(DXDAOImpl.class);

    public DXDAOImpl() {
        super();
    }

    @Override
    public Class<DX> getPersistentClass() {
        return DX.class;
    }

    @Override
    public List<DX> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<DX> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DX> crit = critBuilder.createQuery(getPersistentClass());
            Root<DX> root = crit.from(getPersistentClass());
            crit.orderBy(critBuilder.asc(root.get(DX_.id)));
            TypedQuery<DX> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<DX> findByName(String name) throws CANVASDAOException {
        logger.debug("ENTERING findByName(String)");
        List<DX> ret = new ArrayList<>();
        try {
            if (StringUtils.isEmpty(name)) {
                return ret;
            }

            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DX> crit = critBuilder.createQuery(getPersistentClass());
            Root<DX> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (!name.endsWith("%")) {
                name += "%";
            }

            predicates.add(critBuilder.like(root.get(DX_.name), name));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(root.get(DX_.id)));
            TypedQuery<DX> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
