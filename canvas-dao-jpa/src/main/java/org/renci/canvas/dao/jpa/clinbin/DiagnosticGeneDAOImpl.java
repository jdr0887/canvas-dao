package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK_;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId_;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.clinbin.DiagnosticGeneDAO;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersionPK_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DiagnosticGeneDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DiagnosticGeneDAOImpl extends BaseDAOImpl<DiagnosticGene, Integer> implements DiagnosticGeneDAO {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosticGeneDAOImpl.class);

    public DiagnosticGeneDAOImpl() {
        super();
    }

    @Override
    public Class<DiagnosticGene> getPersistentClass() {
        return DiagnosticGene.class;
    }

    @Override
    public Integer findMaxDiagnosticListVersionByDxId(Integer dxId) throws CANVASDAOException {
        logger.debug("ENTERING findMaxDiagnosticListVersionByDxId(Integer)");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            Root<DiagnosticGene> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(DiagnosticGene_.dx).get(DX_.id), dxId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.select(critBuilder.max(root.get(DiagnosticGene_.diagnosticListVersion)));
            TypedQuery<Integer> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticGene> findByExample(DiagnosticGene diagnosticGene) throws CANVASDAOException {
        logger.debug("ENTERING findByExample(DiagnosticGene)");
        List<DiagnosticGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticGene> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotEmpty(diagnosticGene.getTier())) {
                predicates.add(critBuilder.equal(root.get(DiagnosticGene_.tier), diagnosticGene.getTier()));
            }

            if (StringUtils.isNotEmpty(diagnosticGene.getInheritance())) {
                predicates.add(critBuilder.equal(root.get(DiagnosticGene_.inheritance), diagnosticGene.getInheritance()));
            }

            if (diagnosticGene.getDiagnosticListVersion() != null) {
                predicates
                        .add(critBuilder.equal(root.get(DiagnosticGene_.diagnosticListVersion), diagnosticGene.getDiagnosticListVersion()));
            }

            if (diagnosticGene.getGene() != null) {
                predicates
                        .add(critBuilder.equal(root.join(DiagnosticGene_.gene).get(AnnotationGene_.id), diagnosticGene.getGene().getId()));
            }

            if (diagnosticGene.getDx() != null) {
                predicates.add(critBuilder.equal(root.join(DiagnosticGene_.dx).get(DX_.id), diagnosticGene.getDx().getId()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            crit.distinct(true);
            TypedQuery<DiagnosticGene> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("clinbin.DiagnosticGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<DiagnosticGene> findByGeneIdAndDXId(Integer geneId, Integer dxId) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneIdAndListVersionAndDXId(Integer, Integer)");
        List<DiagnosticGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticGene> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(DiagnosticGene_.gene).get(AnnotationGene_.id), geneId));
            predicates.add(critBuilder.equal(root.join(DiagnosticGene_.dx).get(DX_.id), dxId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<DiagnosticGene> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("clinbin.DiagnosticGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<DiagnosticGene> findByGroupVersionAndExternalNamespaceAndVersion(Integer groupVersion, String namespace, String version)
            throws CANVASDAOException {
        logger.debug("ENTERING findByGroupVersionAndExternalNamespaceAndVersion(Integer, String, String)");
        List<DiagnosticGene> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticGene> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticGene> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<AnnotationGene, AnnotationGeneExternalId> annotationGene2AnnotationGeneExternalIdJoin = root.join(DiagnosticGene_.gene)
                    .join(AnnotationGene_.externals);

            predicates.add(critBuilder.equal(annotationGene2AnnotationGeneExternalIdJoin.join(AnnotationGeneExternalId_.id)
                    .get(AnnotationGeneExternalIdPK_.namespace), namespace));
            predicates.add(critBuilder.equal(annotationGene2AnnotationGeneExternalIdJoin.join(AnnotationGeneExternalId_.namespaceVersion),
                    version));

            Join<DX, DiagnosticGeneGroupVersion> dxDiagnosticGeneGroupVersionJoin = root.join(DiagnosticGene_.dx)
                    .join(DX_.diagnosticGeneGroupVersions);
            dxDiagnosticGeneGroupVersionJoin
                    .on(critBuilder.equal(root.get(DiagnosticGene_.diagnosticListVersion), dxDiagnosticGeneGroupVersionJoin
                            .get(DiagnosticGeneGroupVersion_.id).get(DiagnosticGeneGroupVersionPK_.diagnosticListVersion)));

            predicates.add(critBuilder.equal(dxDiagnosticGeneGroupVersionJoin.join(DiagnosticGeneGroupVersion_.id)
                    .get(DiagnosticGeneGroupVersionPK_.diagnosticBinGroupVersion), groupVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<DiagnosticGene> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("clinbin.DiagnosticGene.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
