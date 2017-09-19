package org.renci.canvas.dao.jpa.hgnc;

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
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK_;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId_;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.hgnc.HGNCGeneDAO;
import org.renci.canvas.dao.hgnc.model.HGNCGene;
import org.renci.canvas.dao.hgnc.model.HGNCGene_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { HGNCGeneDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class HGNCGeneDAOImpl extends BaseDAOImpl<HGNCGene, Integer> implements HGNCGeneDAO {

    private static final Logger logger = LoggerFactory.getLogger(HGNCGeneDAOImpl.class);

    public HGNCGeneDAOImpl() {
        super();
    }

    @Override
    public Class<HGNCGene> getPersistentClass() {
        return HGNCGene.class;
    }

    @Override
    public List<HGNCGene> findByName(String name) throws CANVASDAOException {
        logger.debug("ENTERING findByName(String)");
        List<HGNCGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<HGNCGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<HGNCGene> fromHGNCGene = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(fromHGNCGene.get(HGNCGene_.name), name));
            crit.select(fromHGNCGene);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(fromHGNCGene.get(HGNCGene_.id)));
            TypedQuery<HGNCGene> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("hgnc.HGNCGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<HGNCGene> findBySymbol(String symbol) throws CANVASDAOException {
        logger.debug("ENTERING findBySymbol(String)");
        List<HGNCGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<HGNCGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<HGNCGene> fromHGNCGene = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(fromHGNCGene.get(HGNCGene_.symbol), symbol));

            crit.select(fromHGNCGene);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(fromHGNCGene.get(HGNCGene_.id)));
            TypedQuery<HGNCGene> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("hgnc.HGNCGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<HGNCGene> findByAnnotationGeneExternalIdsNamespace(String namespace) throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersion(String)");
        List<HGNCGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<HGNCGene> crit = critBuilder.createQuery(getPersistentClass());

            Root<HGNCGene> fromHGNCGene = crit.from(getPersistentClass());
            Root<AnnotationGeneExternalId> fromAnnotationGeneExternalIds = crit.from(AnnotationGeneExternalId.class);

            Predicate condition1 = critBuilder.equal(fromHGNCGene.get(HGNCGene_.id),
                    fromAnnotationGeneExternalIds.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.externalId));
            Predicate condition2 = critBuilder.equal(
                    fromAnnotationGeneExternalIds.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespace), namespace);

            crit.select(fromHGNCGene);
            crit.where(condition1, condition2);
            crit.orderBy(critBuilder.asc(fromHGNCGene.get(HGNCGene_.id)));
            TypedQuery<HGNCGene> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("hgnc.HGNCGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<HGNCGene> findByAnnotationGeneExternalIdsGeneIdsAndNamespace(Integer geneId, String namespace) throws CANVASDAOException {
        logger.debug("ENTERING findByAnnotationGeneExternalIdsGeneIdsAndNamespace(Integer, String)");
        List<HGNCGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<HGNCGene> crit = critBuilder.createQuery(getPersistentClass());

            Root<HGNCGene> fromHGNCGene = crit.from(getPersistentClass());
            Root<AnnotationGeneExternalId> fromAnnotationGeneExternalIds = crit.from(AnnotationGeneExternalId.class);

            Predicate condition1 = critBuilder.equal(fromHGNCGene.get(HGNCGene_.id),
                    fromAnnotationGeneExternalIds.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.externalId));
            Predicate condition2 = critBuilder.equal(
                    fromAnnotationGeneExternalIds.get(AnnotationGeneExternalId_.id).get(AnnotationGeneExternalIdPK_.namespace), namespace);
            Predicate condition3 = critBuilder
                    .equal(fromAnnotationGeneExternalIds.join(AnnotationGeneExternalId_.gene).get(AnnotationGene_.id), geneId);

            crit.select(fromHGNCGene);
            crit.where(condition1, condition2, condition3);
            crit.orderBy(critBuilder.asc(fromHGNCGene.get(HGNCGene_.id)));
            TypedQuery<HGNCGene> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("hgnc.HGNCGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
