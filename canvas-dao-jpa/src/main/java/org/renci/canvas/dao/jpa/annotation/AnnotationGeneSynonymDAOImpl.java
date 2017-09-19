package org.renci.canvas.dao.jpa.annotation;

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
import org.renci.canvas.dao.annotation.AnnotationGeneExternalIdDAO;
import org.renci.canvas.dao.annotation.AnnotationGeneSynonymDAO;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonym;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonymPK;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonym_;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { AnnotationGeneExternalIdDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AnnotationGeneSynonymDAOImpl extends BaseDAOImpl<AnnotationGeneSynonym, AnnotationGeneSynonymPK>
        implements AnnotationGeneSynonymDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationGeneSynonymDAOImpl.class);

    public AnnotationGeneSynonymDAOImpl() {
        super();
    }

    @Override
    public Class<AnnotationGeneSynonym> getPersistentClass() {
        return AnnotationGeneSynonym.class;
    }

    @Override
    public List<AnnotationGeneSynonym> findByGeneId(Integer annotationGeneId) throws CANVASDAOException {
        logger.debug("ENTERING findByNamespace(Integer)");
        List<AnnotationGeneSynonym> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneSynonym> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneSynonym> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(AnnotationGeneSynonym_.gene).get(AnnotationGene_.id), annotationGeneId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneSynonym> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
