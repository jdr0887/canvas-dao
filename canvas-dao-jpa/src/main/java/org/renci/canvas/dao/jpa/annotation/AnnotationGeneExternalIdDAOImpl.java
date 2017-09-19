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
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK_;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId_;
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
public class AnnotationGeneExternalIdDAOImpl extends BaseDAOImpl<AnnotationGeneExternalId, AnnotationGeneExternalIdPK>
        implements AnnotationGeneExternalIdDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationGeneExternalIdDAOImpl.class);

    public AnnotationGeneExternalIdDAOImpl() {
        super();
    }

    @Override
    public Class<AnnotationGeneExternalId> getPersistentClass() {
        return AnnotationGeneExternalId.class;
    }

    @Override
    public List<AnnotationGeneExternalId> findByAnnotationGeneId(Integer geneId) throws CANVASDAOException {
        logger.debug("ENTERING findByAnnotationGeneId(Integer)");
        List<AnnotationGeneExternalId> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneExternalId> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneExternalId> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(AnnotationGeneExternalId_.gene).get(AnnotationGene_.id), geneId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneExternalId> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("annot.AnnotationGeneExternalId.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<AnnotationGeneExternalId> findByExternalId(Integer externalId) throws CANVASDAOException {
        logger.debug("ENTERING findByNamespace(String)");
        List<AnnotationGeneExternalId> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneExternalId> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneExternalId> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates
                    .add(critBuilder.equal(root.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.externalId), externalId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneExternalId> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("annot.AnnotationGeneExternalId.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<AnnotationGeneExternalId> findByNamespace(String namespace) throws CANVASDAOException {
        logger.debug("ENTERING findByNamespace(String)");
        List<AnnotationGeneExternalId> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneExternalId> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneExternalId> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespace), namespace));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneExternalId> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("annot.AnnotationGeneExternalId.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<AnnotationGeneExternalId> findByNamespaceAndNamespaceVersion(String namespace, String version) throws CANVASDAOException {
        logger.debug("ENTERING findByNamespaceAndNamespaceVersion(String, String)");
        List<AnnotationGeneExternalId> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneExternalId> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneExternalId> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespace), namespace));
            predicates
                    .add(critBuilder.equal(root.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespaceVer), version));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneExternalId> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("annot.AnnotationGeneExternalId.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<AnnotationGeneExternalId> findByExternalIdAndNamespace(Integer externalId, String namespace) throws CANVASDAOException {
        logger.debug("ENTERING findByExternalIdAndNamespace(Integer, String)");
        List<AnnotationGeneExternalId> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AnnotationGeneExternalId> crit = critBuilder.createQuery(getPersistentClass());
            Root<AnnotationGeneExternalId> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespace), namespace));
            predicates.add(critBuilder.equal(root.get(AnnotationGeneExternalId_.gene).get(AnnotationGene_.id), externalId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<AnnotationGeneExternalId> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("annot.AnnotationGeneExternalId.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
