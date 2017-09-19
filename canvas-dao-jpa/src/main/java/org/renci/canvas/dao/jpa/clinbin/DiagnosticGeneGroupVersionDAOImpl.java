package org.renci.canvas.dao.jpa.clinbin;

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
import org.renci.canvas.dao.clinbin.DiagnosticGeneDAO;
import org.renci.canvas.dao.clinbin.DiagnosticGeneGroupVersionDAO;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersionPK;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersionPK_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DiagnosticGeneDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DiagnosticGeneGroupVersionDAOImpl extends BaseDAOImpl<DiagnosticGeneGroupVersion, DiagnosticGeneGroupVersionPK>
        implements DiagnosticGeneGroupVersionDAO {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosticGeneGroupVersionDAOImpl.class);

    public DiagnosticGeneGroupVersionDAOImpl() {
        super();
    }

    @Override
    public Class<DiagnosticGeneGroupVersion> getPersistentClass() {
        return DiagnosticGeneGroupVersion.class;
    }

    @Override
    public Integer findMaxDiagnosticBinGroupVersion() throws CANVASDAOException {
        logger.debug("ENTERING findMaxDiagnosticBinGroupVersion()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            Root<DiagnosticGeneGroupVersion> root = crit.from(getPersistentClass());
            crit.select(
                    critBuilder.max(root.get(DiagnosticGeneGroupVersion_.id).get(DiagnosticGeneGroupVersionPK_.diagnosticBinGroupVersion)));
            TypedQuery<Integer> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticGeneGroupVersion> findByDXIdAndDiagnosticListVersion(Integer dxId, Integer diagnosticListVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByDXIdAndDiagnosticListVersion(Integer, Integer)");
        List<DiagnosticGeneGroupVersion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticGeneGroupVersion> crit = critBuilder.createQuery(DiagnosticGeneGroupVersion.class);
            Root<DiagnosticGeneGroupVersion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(DiagnosticGeneGroupVersion_.id).get(DiagnosticGeneGroupVersionPK_.dx), dxId));

            predicates.add(
                    critBuilder.equal(root.get(DiagnosticGeneGroupVersion_.id).get(DiagnosticGeneGroupVersionPK_.diagnosticListVersion),
                            diagnosticListVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<DiagnosticGeneGroupVersion> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticGeneGroupVersion> findByDBinGroupVersion(Integer dbinGroupVersion) throws CANVASDAOException {
        logger.debug("ENTERING findByDBinGroupVersion(Integer)");
        List<DiagnosticGeneGroupVersion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticGeneGroupVersion> crit = critBuilder.createQuery(DiagnosticGeneGroupVersion.class);
            Root<DiagnosticGeneGroupVersion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(
                    critBuilder.equal(root.get(DiagnosticGeneGroupVersion_.id).get(DiagnosticGeneGroupVersionPK_.diagnosticBinGroupVersion),
                            dbinGroupVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<DiagnosticGeneGroupVersion> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
