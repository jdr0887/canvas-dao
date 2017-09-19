package org.renci.canvas.dao.jpa.clinbin;

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
import org.renci.canvas.dao.clinbin.BinResultsFinalDiagnosticDAO;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnosticPK;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnosticPK_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic_;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticBinningJob;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion_;
import org.renci.canvas.dao.clinbin.model.DiseaseClass_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@OsgiServiceProvider(classes = { BinResultsFinalDiagnosticDAO.class })
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class BinResultsFinalDiagnosticDAOImpl extends BaseDAOImpl<BinResultsFinalDiagnostic, BinResultsFinalDiagnosticPK>
        implements BinResultsFinalDiagnosticDAO {

    private static final Logger logger = LoggerFactory.getLogger(BinResultsFinalDiagnosticDAOImpl.class);

    public BinResultsFinalDiagnosticDAOImpl() {
        super();
    }

    @Override
    public Class<BinResultsFinalDiagnostic> getPersistentClass() {
        return BinResultsFinalDiagnostic.class;
    }

    @Override
    public List<BinResultsFinalDiagnostic> findByKeyAndHGMDDiseaseClassId(BinResultsFinalDiagnosticPK id, Integer diseaseClassId)
            throws CANVASDAOException {
        logger.debug("ENTERING findByKeyAndHGMDDiseaseClassId(BinResultsFinalDiagnosticPK, Integer)");
        List<BinResultsFinalDiagnostic> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<BinResultsFinalDiagnostic> crit = critBuilder.createQuery(getPersistentClass());
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id), id));
            predicates.add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.hgmdDiseaseClass).get(DiseaseClass_.id), diseaseClassId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<BinResultsFinalDiagnostic> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<BinResultsFinalDiagnostic> findByKeyAndClinVarDiseaseClassId(BinResultsFinalDiagnosticPK id, Integer diseaseClassId)
            throws CANVASDAOException {
        logger.debug("ENTERING findByKeyAndHGMDDiseaseClassId(BinResultsFinalDiagnosticPK, Integer)");
        List<BinResultsFinalDiagnostic> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<BinResultsFinalDiagnostic> crit = critBuilder.createQuery(getPersistentClass());
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id), id));
            predicates
                    .add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.clinvarDiseaseClass).get(DiseaseClass_.id), diseaseClassId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<BinResultsFinalDiagnostic> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<BinResultsFinalDiagnostic> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantId(Long)");
        List<BinResultsFinalDiagnostic> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<BinResultsFinalDiagnostic> crit = critBuilder.createQuery(getPersistentClass());
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.locatedVariant),
                    locatedVariantId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<BinResultsFinalDiagnostic> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<BinResultsFinalDiagnostic> findByDXIdAndParticipantAndVersion(Long dxId, String participant, Integer version)
            throws CANVASDAOException {
        logger.debug("ENTERING findByDXIdAndParticipantAndListVersion(Long, String, Integer)");

        List<BinResultsFinalDiagnostic> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<BinResultsFinalDiagnostic> crit = critBuilder.createQuery(getPersistentClass());
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<BinResultsFinalDiagnostic, BinResultsFinalDiagnosticPK> binResultsFinalDiagnosticBinResultsFinalDiagnosticPKJoin = root
                    .join(BinResultsFinalDiagnostic_.id);
            predicates.add(critBuilder.equal(
                    binResultsFinalDiagnosticBinResultsFinalDiagnosticPKJoin.get(BinResultsFinalDiagnosticPK_.participant), participant));

            Join<BinResultsFinalDiagnostic, DX> binResultsFinalDiagnosticDXJoin = root.join(BinResultsFinalDiagnostic_.dx);
            predicates.add(critBuilder.equal(binResultsFinalDiagnosticDXJoin.get(DX_.id), dxId));

            Join<BinResultsFinalDiagnostic, DiagnosticResultVersion> coverageExonJoin = root
                    .join(BinResultsFinalDiagnostic_.diagnosticResultVersion);
            predicates.add(critBuilder.equal(coverageExonJoin.get(DiagnosticResultVersion_.id), version));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<BinResultsFinalDiagnostic> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findDXIdCount(String participant) throws CANVASDAOException {
        logger.debug("ENTERING findDXIdCount(String)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(
                    critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.participant), participant));
            crit.select(critBuilder.countDistinct(root.get(BinResultsFinalDiagnostic_.dx).get(DX_.id)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findByAssemblyIdAndHGMDDiseaseClassId(Integer assemblyId, Integer diseaseClassId) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyIdAndDiseaseClassId(Integer, Integer)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates
                    .add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.assembly), assemblyId));
            predicates.add(critBuilder.equal(root.join(BinResultsFinalDiagnostic_.hgmdDiseaseClass).get(DiseaseClass_.id), diseaseClassId));
            crit.select(
                    critBuilder.countDistinct(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.locatedVariant)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findByAssemblyIdAndClinVarDiseaseClassId(Integer assemblyId, Integer diseaseClassId) throws CANVASDAOException {
        logger.debug("ENTERING findByAssemblyIdAndDiseaseClassId(Integer, Integer)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates
                    .add(critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.assembly), assemblyId));
            predicates.add(
                    critBuilder.equal(root.join(BinResultsFinalDiagnostic_.clinvarDiseaseClass).get(DiseaseClass_.id), diseaseClassId));
            crit.select(
                    critBuilder.countDistinct(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.locatedVariant)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Long findAnalyzedVariantsCount(String participant) throws CANVASDAOException {
        logger.debug("ENTERING findAnalyzedVariantsCount(String)");
        Long ret = 0L;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> crit = critBuilder.createQuery(Long.class);
            Root<BinResultsFinalDiagnostic> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(
                    critBuilder.equal(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.participant), participant));
            crit.select(
                    critBuilder.countDistinct(root.get(BinResultsFinalDiagnostic_.id).get(BinResultsFinalDiagnosticPK_.locatedVariant)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Long> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
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

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void deleteByDiagnosticBinningJobAndHGMDDiseaseClassId(DiagnosticBinningJob diagnosticBinningJob, Integer hgmdDiseaseClassId)
            throws CANVASDAOException {
        Query qDelete = getEntityManager().createQuery(String.format(
                "delete from %s a where a.assembly.id = :assemblyId and a.dx.id = :dxId and a.diagnosticResultVersion.id = :diagnosticResultVersion and a.hgmdDiseaseClass.id = :hgmdDiseaseClassId",
                getPersistentClass().getSimpleName()));
        qDelete.setParameter("assemblyId", diagnosticBinningJob.getAssembly().getId());
        qDelete.setParameter("dxId", diagnosticBinningJob.getDx().getId());
        qDelete.setParameter("diagnosticResultVersion", diagnosticBinningJob.getDiagnosticResultVersion().getId());
        qDelete.setParameter("hgmdDiseaseClassId", hgmdDiseaseClassId);
        qDelete.executeUpdate();

    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void deleteByDiagnosticBinningJobAndClinVarDiseaseClassId(DiagnosticBinningJob diagnosticBinningJob,
            Integer clinvarDiseaseClassId) throws CANVASDAOException {
        Query qDelete = getEntityManager().createQuery(String.format(
                "delete from %s a where a.assembly.id = :assemblyId and a.dx.id = :dxId and a.diagnosticResultVersion.id = :diagnosticResultVersion and a.clinvarDiseaseClass.id = :clinvarDiseaseClassId",
                getPersistentClass().getSimpleName()));
        qDelete.setParameter("assemblyId", diagnosticBinningJob.getAssembly().getId());
        qDelete.setParameter("dxId", diagnosticBinningJob.getDx().getId());
        qDelete.setParameter("diagnosticResultVersion", diagnosticBinningJob.getDiagnosticResultVersion().getId());
        qDelete.setParameter("clinvarDiseaseClassId", clinvarDiseaseClassId);
        qDelete.executeUpdate();
    }

}
