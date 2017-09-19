package org.renci.canvas.dao.jpa.refseq;

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
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.RefSeqCodingSequenceDAO;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript;
import org.renci.canvas.dao.refseq.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { RefSeqCodingSequenceDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class RefSeqCodingSequenceDAOImpl extends BaseDAOImpl<RefSeqCodingSequence, Integer> implements RefSeqCodingSequenceDAO {

    private static final Logger logger = LoggerFactory.getLogger(RefSeqCodingSequenceDAOImpl.class);

    public RefSeqCodingSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<RefSeqCodingSequence> getPersistentClass() {
        return RefSeqCodingSequence.class;
    }

    @Override
    public List<RefSeqCodingSequence> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId)
            throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersionAndTranscriptId(String, String)");
        return findByRefSeqVersionAndTranscriptId(null, refSeqVersion, transcriptId);
    }

    @Override
    public List<RefSeqCodingSequence> findByRefSeqVersionAndTranscriptId(String fetchPlan, String refSeqVersion, String transcriptId)
            throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersionAndTranscriptId(String, String)");
        List<RefSeqCodingSequence> ret = new ArrayList<RefSeqCodingSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RefSeqCodingSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<RefSeqCodingSequence> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(RefSeqCodingSequence_.version), refSeqVersion));
            Join<RefSeqCodingSequence, RegionGroup> locationJoin = root.join(RefSeqCodingSequence_.locations);
            Join<RegionGroup, Transcript> transcriptJoin = locationJoin.join(RegionGroup_.transcript);
            predicates.add(critBuilder.equal(transcriptJoin.get(Transcript_.id), transcriptId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RefSeqCodingSequence> query = getEntityManager().createQuery(crit);
            if (StringUtils.isNotEmpty(fetchPlan)) {
                query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph(fetchPlan));
            }
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<RefSeqCodingSequence> findByVersion(String version) throws CANVASDAOException {
        logger.debug("ENTERING findByVersion(String)");
        List<RefSeqCodingSequence> ret = new ArrayList<RefSeqCodingSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RefSeqCodingSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<RefSeqCodingSequence> root = crit.from(getPersistentClass());
            Predicate condition1 = critBuilder.equal(root.get(RefSeqCodingSequence_.version), version);
            crit.where(condition1);
            TypedQuery<RefSeqCodingSequence> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RefSeqCodingSequence.includeAll"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<RefSeqCodingSequence> findByExample(RefSeqCodingSequence refseqCodingSequence) throws CANVASDAOException {
        logger.debug("ENTERING findByExample(RefSeqCodingSequence)");
        List<RefSeqCodingSequence> ret = new ArrayList<RefSeqCodingSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RefSeqCodingSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<RefSeqCodingSequence> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (refseqCodingSequence.getCodonStart() != null) {
                predicates.add(critBuilder.equal(root.get(RefSeqCodingSequence_.codonStart), refseqCodingSequence.getCodonStart()));
            }

            if (StringUtils.isNotEmpty(refseqCodingSequence.getProduct())) {
                predicates.add(critBuilder.equal(root.get(RefSeqCodingSequence_.product), refseqCodingSequence.getProduct()));
            }

            if (StringUtils.isNotEmpty(refseqCodingSequence.getProteinId())) {
                predicates.add(critBuilder.equal(root.get(RefSeqCodingSequence_.proteinId), refseqCodingSequence.getProteinId()));
            }
            if (StringUtils.isNotEmpty(refseqCodingSequence.getVersion())) {
                predicates.add(critBuilder.equal(root.get(RefSeqCodingSequence_.version), refseqCodingSequence.getVersion()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RefSeqCodingSequence> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RefSeqCodingSequence.includeAll"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
