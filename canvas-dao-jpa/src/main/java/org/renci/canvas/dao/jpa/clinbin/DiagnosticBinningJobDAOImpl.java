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
import org.renci.canvas.dao.clinbin.DiagnosticBinningJobDAO;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticBinningJob;
import org.renci.canvas.dao.clinbin.model.DiagnosticBinningJob_;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion_;
import org.renci.canvas.dao.clinbin.model.DiagnosticStatusType;
import org.renci.canvas.dao.clinbin.model.DiagnosticStatusType_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DiagnosticBinningJobDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DiagnosticBinningJobDAOImpl extends BaseDAOImpl<DiagnosticBinningJob, Integer> implements DiagnosticBinningJobDAO {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosticBinningJobDAOImpl.class);

    public DiagnosticBinningJobDAOImpl() {
        super();
    }

    @Override
    public Class<DiagnosticBinningJob> getPersistentClass() {
        return DiagnosticBinningJob.class;
    }

    @Override
    public List<DiagnosticBinningJob> findAvailableJobs() throws CANVASDAOException {
        logger.debug("ENTERING findProcessingJobs()");
        List<DiagnosticBinningJob> ret = new ArrayList<DiagnosticBinningJob>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticBinningJob> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticBinningJob> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<DiagnosticBinningJob, DiagnosticStatusType> diagnosticBinningJobDiagnosticStatusTypeJoin = root
                    .join(DiagnosticBinningJob_.status);
            predicates.add(critBuilder
                    .not(diagnosticBinningJobDiagnosticStatusTypeJoin.get(DiagnosticStatusType_.id).in("Complete", "Failed", "Paused")));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DiagnosticBinningJob> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticBinningJob> findCompletedJobs() throws CANVASDAOException {
        logger.debug("ENTERING findCompletedJobs()");
        List<DiagnosticBinningJob> ret = new ArrayList<DiagnosticBinningJob>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticBinningJob> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticBinningJob> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<DiagnosticBinningJob, DiagnosticStatusType> diagnosticBinningJobDiagnosticStatusTypeJoin = root
                    .join(DiagnosticBinningJob_.status);
            predicates.add(critBuilder.equal(diagnosticBinningJobDiagnosticStatusTypeJoin.get(DiagnosticStatusType_.id), "Complete"));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DiagnosticBinningJob> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticBinningJob> findCompletedJobsByStudy(String study) throws CANVASDAOException {
        logger.debug("ENTERING findCompletedJobsByStudy(String)");
        List<DiagnosticBinningJob> ret = new ArrayList<DiagnosticBinningJob>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticBinningJob> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticBinningJob> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(DiagnosticBinningJob_.study), study));
            Join<DiagnosticBinningJob, DiagnosticStatusType> diagnosticBinningJobDiagnosticStatusTypeJoin = root
                    .join(DiagnosticBinningJob_.status);
            predicates.add(critBuilder.equal(diagnosticBinningJobDiagnosticStatusTypeJoin.get(DiagnosticStatusType_.id), "Complete"));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DiagnosticBinningJob> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DiagnosticBinningJob> findByExample(DiagnosticBinningJob binningJob) throws CANVASDAOException {
        logger.debug("ENTERING findByExample(DiagnosticBinningJob)");
        List<DiagnosticBinningJob> ret = new ArrayList<DiagnosticBinningJob>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticBinningJob> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticBinningJob> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotEmpty(binningJob.getStudy())) {
                predicates.add(critBuilder.equal(root.get(DiagnosticBinningJob_.study), binningJob.getStudy()));
            }

            if (StringUtils.isNotEmpty(binningJob.getParticipant())) {
                predicates.add(critBuilder.equal(root.get(DiagnosticBinningJob_.participant), binningJob.getParticipant()));
            }

            if (StringUtils.isNotEmpty(binningJob.getGender())) {
                predicates.add(critBuilder.equal(root.get(DiagnosticBinningJob_.gender), binningJob.getGender()));
            }

            if (binningJob.getDiagnosticResultVersion() != null) {
                predicates.add(critBuilder.equal(root.join(DiagnosticBinningJob_.diagnosticResultVersion).get(DiagnosticResultVersion_.id),
                        binningJob.getDiagnosticResultVersion().getId()));
            }

            if (binningJob.getDx() != null) {
                predicates.add(critBuilder.equal(root.join(DiagnosticBinningJob_.dx).get(DX_.id), binningJob.getDx().getId()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DiagnosticBinningJob> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
