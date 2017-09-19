package org.renci.canvas.dao.jpa.annotation;

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
import org.renci.canvas.dao.annotation.AnnotationGeneDAO;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { AnnotationGeneDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AnnotationGeneDAOImpl extends BaseDAOImpl<AnnotationGene, Integer> implements AnnotationGeneDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationGeneDAOImpl.class);

    public AnnotationGeneDAOImpl() {
        super();
    }

    @Override
    public Class<AnnotationGene> getPersistentClass() {
        return AnnotationGene.class;
    }

    @Override
    public List<AnnotationGene> findByName(String name) throws CANVASDAOException {
        logger.debug("ENTERING findByName(String)");
        List<AnnotationGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGene> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(AnnotationGene_.preferredName), name));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGene> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
