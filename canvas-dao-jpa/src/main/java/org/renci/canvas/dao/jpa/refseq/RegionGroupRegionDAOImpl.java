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

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.RegionGroupRegionDAO;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;
import org.renci.canvas.dao.refseq.model.RegionGroupRegionPK;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion_;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript;
import org.renci.canvas.dao.refseq.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { RegionGroupRegionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class RegionGroupRegionDAOImpl extends BaseDAOImpl<RegionGroupRegion, RegionGroupRegionPK> implements RegionGroupRegionDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegionGroupRegionDAOImpl.class);

    public RegionGroupRegionDAOImpl() {
        super();
    }

    @Override
    public Class<RegionGroupRegion> getPersistentClass() {
        return RegionGroupRegion.class;
    }

    @Override
    public List<RegionGroupRegion> findByRegionGroupId(Integer regionGroupId) throws CANVASDAOException {
        logger.debug("ENTERING findByRegionGroupId(Long)");
        List<RegionGroupRegion> ret = new ArrayList<RegionGroupRegion>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RegionGroupRegion> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<RegionGroupRegion> fromRegionGroupRegion = crit.from(getPersistentClass());
            Join<RegionGroupRegion, RegionGroup> regionGroupRegionGroupRegionJoin = fromRegionGroupRegion
                    .join(RegionGroupRegion_.regionGroup);
            predicates.add(critBuilder.equal(regionGroupRegionGroupRegionJoin.get(RegionGroup_.id), regionGroupId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RegionGroupRegion> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RegionGroupRegion.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<RegionGroupRegion> findByRefSeqCodingSequenceId(Integer refSeqCodingSequenceId) throws CANVASDAOException {
        logger.debug("ENTERING findByRegionGroupId(Long)");
        List<RegionGroupRegion> ret = new ArrayList<RegionGroupRegion>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RegionGroupRegion> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<RegionGroupRegion> fromRegionGroupRegion = crit.from(getPersistentClass());

            Join<RegionGroupRegion, RegionGroup> regionGroupRegionGroupRegionJoin = fromRegionGroupRegion
                    .join(RegionGroupRegion_.regionGroup);
            Join<RegionGroup, RefSeqCodingSequence> regionGroupRefSeqCodingSequenceJoin = regionGroupRegionGroupRegionJoin
                    .join(RegionGroup_.refSeqCodingSequence);
            predicates.add(critBuilder.equal(regionGroupRefSeqCodingSequenceJoin.get(RefSeqCodingSequence_.id), refSeqCodingSequenceId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RegionGroupRegion> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RegionGroupRegion.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<RegionGroupRegion> findByRefSeqVersionAndTranscriptId(String version, String transcriptId) throws CANVASDAOException {
        logger.debug("ENTERING findByRegionGroupId(Long)");
        List<RegionGroupRegion> ret = new ArrayList<RegionGroupRegion>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RegionGroupRegion> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<RegionGroupRegion> fromRegionGroupRegion = crit.from(getPersistentClass());
            
            Join<RegionGroup, RefSeqCodingSequence> regionGroupRefSeqCodingSequenceJoin = fromRegionGroupRegion
                    .join(RegionGroupRegion_.regionGroup).join(RegionGroup_.refSeqCodingSequence);

            predicates.add(critBuilder.equal(regionGroupRefSeqCodingSequenceJoin.get(RefSeqCodingSequence_.version), version));

            Join<RefSeqCodingSequence, RegionGroup> refSeqCodingSequenceRegionGroupJoin = regionGroupRefSeqCodingSequenceJoin
                    .join(RefSeqCodingSequence_.locations);

            Join<RegionGroup, Transcript> transcriptJoin = refSeqCodingSequenceRegionGroupJoin.join(RegionGroup_.transcript);
            predicates.add(critBuilder.equal(transcriptJoin.get(Transcript_.id), transcriptId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RegionGroupRegion> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RegionGroupRegion.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
