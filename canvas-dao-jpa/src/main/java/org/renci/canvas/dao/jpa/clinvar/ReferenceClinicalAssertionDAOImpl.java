package org.renci.canvas.dao.jpa.clinvar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnosticPK;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnosticPK_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalX;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalXPK;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalXPK_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalIncidentalX_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskX;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskXPK;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskXPK_;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalRiskX_;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion_;
import org.renci.canvas.dao.clinbin.model.IncidentalBinX;
import org.renci.canvas.dao.clinbin.model.IncidentalBinX_;
import org.renci.canvas.dao.clinbin.model.IncidentalResultVersionX;
import org.renci.canvas.dao.clinbin.model.IncidentalResultVersionX_;
import org.renci.canvas.dao.clinvar.ReferenceClinicalAssertionDAO;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion_;
import org.renci.canvas.dao.clinvar.model.ReferenceClinicalAssertion;
import org.renci.canvas.dao.clinvar.model.ReferenceClinicalAssertion_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.model.LocatedVariant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ReferenceClinicalAssertionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ReferenceClinicalAssertionDAOImpl extends BaseDAOImpl<ReferenceClinicalAssertion, Long>
        implements ReferenceClinicalAssertionDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReferenceClinicalAssertionDAOImpl.class);

    public ReferenceClinicalAssertionDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceClinicalAssertion> getPersistentClass() {
        return ReferenceClinicalAssertion.class;
    }

    @Override
    public List<ReferenceClinicalAssertion> findDiagnostic(Long dxId, String participant, Integer resultVersion) throws CANVASDAOException {
        logger.debug("ENTERING findDiagnostic(Long, String, Integer)");

        List<ReferenceClinicalAssertion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceClinicalAssertion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceClinicalAssertion> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            SetJoin<ClinVarVersion, DiagnosticResultVersion> versionsDiagnosticResultVersionJoin = root
                    .join(ReferenceClinicalAssertion_.versions).join(ClinVarVersion_.diagnosticResultVersions);

            predicates.add(critBuilder.equal(versionsDiagnosticResultVersionJoin.get(DiagnosticResultVersion_.id), resultVersion));

            Join<DiagnosticResultVersion, BinResultsFinalDiagnostic> diagnosticResultVersionBinResultsFinalDiagnosticJoin = versionsDiagnosticResultVersionJoin
                    .join(DiagnosticResultVersion_.binResultsFinalDiagnostics);

            Join<BinResultsFinalDiagnostic, BinResultsFinalDiagnosticPK> binResultsFinalDiagnosticBinResultsFinalDiagnosticPKJoin = diagnosticResultVersionBinResultsFinalDiagnosticJoin
                    .join(BinResultsFinalDiagnostic_.id);

            predicates.add(critBuilder.equal(
                    binResultsFinalDiagnosticBinResultsFinalDiagnosticPKJoin.get(BinResultsFinalDiagnosticPK_.participant), participant));

            Join<BinResultsFinalDiagnostic, DX> binResultsFinalDiagnosticDXJoin = diagnosticResultVersionBinResultsFinalDiagnosticJoin
                    .join(BinResultsFinalDiagnostic_.dx);

            predicates.add(critBuilder.equal(binResultsFinalDiagnosticDXJoin.get(DX_.id), dxId));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<ReferenceClinicalAssertion> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceClinicalAssertion> findIncidental(Long incidentalBinId, String participant, Integer resultVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByIncidental(Long, String, Integer)");

        List<ReferenceClinicalAssertion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceClinicalAssertion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceClinicalAssertion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            SetJoin<ClinVarVersion, IncidentalResultVersionX> versionsIncidentalResultVersionXJoin = root
                    .join(ReferenceClinicalAssertion_.versions).join(ClinVarVersion_.incidentalResultVersions);

            predicates.add(critBuilder.equal(versionsIncidentalResultVersionXJoin.get(IncidentalResultVersionX_.id), resultVersion));

            Join<IncidentalResultVersionX, BinResultsFinalIncidentalX> incidentalResultVersionXBinResultsFinalIncidentalXJoin = versionsIncidentalResultVersionXJoin
                    .join(IncidentalResultVersionX_.binResultsFinalIncidentals);
            Join<BinResultsFinalIncidentalX, BinResultsFinalIncidentalXPK> binResultsFinalIncidentalXBinResultsFinalIncidentalXPKJoin = incidentalResultVersionXBinResultsFinalIncidentalXJoin
                    .join(BinResultsFinalIncidentalX_.id);
            predicates.add(critBuilder.equal(
                    binResultsFinalIncidentalXBinResultsFinalIncidentalXPKJoin.get(BinResultsFinalIncidentalXPK_.participant),
                    participant));

            Join<BinResultsFinalIncidentalX, IncidentalBinX> binResultsFinalIncidentalXIncidentalBinXJoin = incidentalResultVersionXBinResultsFinalIncidentalXJoin
                    .join(BinResultsFinalIncidentalX_.incidentalBin);

            predicates.add(critBuilder.equal(binResultsFinalIncidentalXIncidentalBinXJoin.get(IncidentalBinX_.id), incidentalBinId));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<ReferenceClinicalAssertion> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceClinicalAssertion> findRisk(Long incidentalBinId, String participant, Integer resultVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findRisk(Long, String, Integer)");

        List<ReferenceClinicalAssertion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceClinicalAssertion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceClinicalAssertion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            SetJoin<ClinVarVersion, IncidentalResultVersionX> versionsIncidentalResultVersionXJoin = root
                    .join(ReferenceClinicalAssertion_.versions).join(ClinVarVersion_.incidentalResultVersions);
            predicates.add(critBuilder.equal(versionsIncidentalResultVersionXJoin.get(IncidentalResultVersionX_.id), resultVersion));

            Join<IncidentalResultVersionX, BinResultsFinalRiskX> incidentalResultVersionXBinResultsFinalRiskXJoin = versionsIncidentalResultVersionXJoin
                    .join(IncidentalResultVersionX_.binResultsFinalRisks);
            Join<BinResultsFinalRiskX, BinResultsFinalRiskXPK> binResultsFinalRiskXBinResultsFinalRiskXPKJoin = incidentalResultVersionXBinResultsFinalRiskXJoin
                    .join(BinResultsFinalRiskX_.id);
            predicates.add(critBuilder.equal(binResultsFinalRiskXBinResultsFinalRiskXPKJoin.get(BinResultsFinalRiskXPK_.participant),
                    participant));

            Join<BinResultsFinalRiskX, IncidentalBinX> binResultsFinalIncidentalXIncidentalBinXJoin = incidentalResultVersionXBinResultsFinalRiskXJoin
                    .join(BinResultsFinalRiskX_.incidentalBin);

            predicates.add(critBuilder.equal(binResultsFinalIncidentalXIncidentalBinXJoin.get(IncidentalBinX_.id), incidentalBinId));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<ReferenceClinicalAssertion> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceClinicalAssertion> findByLocatedVariantIdAndVersion(Long locVarId, Long version) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersion(Long, Integer)");
        List<ReferenceClinicalAssertion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceClinicalAssertion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceClinicalAssertion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.join(ReferenceClinicalAssertion_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.equal(root.join(ReferenceClinicalAssertion_.versions).get(ClinVarVersion_.id), version));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<ReferenceClinicalAssertion> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinvar.ReferenceClinicalAssertion.includeAll"));

            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceClinicalAssertion> findByLocatedVariantIdAndVersionAndAssertionStatusExclusionList(Long locVarId, Long version,
            List<String> assertionStatusExcludes) throws CANVASDAOException {
        logger.debug("ENTERING findByLocatedVariantIdAndVersionAndAssertionStatusExclusionList(Long, Integer, List<String>)");
        List<ReferenceClinicalAssertion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceClinicalAssertion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceClinicalAssertion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.join(ReferenceClinicalAssertion_.locatedVariant).get(LocatedVariant_.id), locVarId));
            predicates.add(critBuilder.not(root.get(ReferenceClinicalAssertion_.assertionStatus).in(assertionStatusExcludes)));
            predicates.add(critBuilder.equal(root.join(ReferenceClinicalAssertion_.versions).get(ClinVarVersion_.id), version));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<ReferenceClinicalAssertion> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinvar.ReferenceClinicalAssertion.includeAll"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
