package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene_;
import org.renci.canvas.dao.clinbin.DXCoverageDAO;
import org.renci.canvas.dao.clinbin.model.DXCoverage;
import org.renci.canvas.dao.clinbin.model.DXCoveragePK;
import org.renci.canvas.dao.clinbin.model.DXCoveragePK_;
import org.renci.canvas.dao.clinbin.model.DXCoverage_;
import org.renci.canvas.dao.clinbin.model.DXExons;
import org.renci.canvas.dao.clinbin.model.DXExons_;
import org.renci.canvas.dao.clinbin.model.DX_;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DXCoverageDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DXCoverageDAOImpl extends BaseDAOImpl<DXCoverage, DXCoveragePK> implements DXCoverageDAO {

    private static final Logger logger = LoggerFactory.getLogger(DXCoverageDAOImpl.class);

    public DXCoverageDAOImpl() {
        super();
    }

    @Override
    public Class<DXCoverage> getPersistentClass() {
        return DXCoverage.class;
    }

    @Override
    public List<DXCoverage> findByDXIdAndParticipantAndListVersion(Long dxId, String participant, Integer listVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByDXIdAndParticipantAndListVersion(Long, String, Integer)");
        List<DXCoverage> ret = new ArrayList<>();
        try {

            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXCoverage> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXCoverage> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(DXCoverage_.id).get(DXCoveragePK_.participant), participant));

            Join<DXCoverage, DXExons> coverageExonJoin = root.join(DXCoverage_.exon);
            predicates.add(critBuilder.equal(coverageExonJoin.get(DXExons_.listVersion), listVersion));

            predicates.add(critBuilder.equal(
                    coverageExonJoin.join(DXExons_.gene).join(AnnotationGene_.diagnosticGenes).join(DiagnosticGene_.dx).get(DX_.id), dxId));

            crit.distinct(true);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<DXCoverage> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<DXCoverage> findByParticipantAndListVersion(String participant, Integer listVersion) throws CANVASDAOException {
        logger.debug("ENTERING findByParticipantAndListVersion(String, Integer)");
        List<DXCoverage> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXCoverage> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXCoverage> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(DXCoverage_.id).get(DXCoveragePK_.participant), participant));
            predicates.add(critBuilder.equal(root.join(DXCoverage_.exon, JoinType.LEFT).get(DXExons_.listVersion), listVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<DXCoverage> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
