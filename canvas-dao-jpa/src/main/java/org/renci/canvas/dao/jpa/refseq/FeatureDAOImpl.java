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
import org.renci.canvas.dao.refseq.FeatureDAO;
import org.renci.canvas.dao.refseq.model.Feature;
import org.renci.canvas.dao.refseq.model.Feature_;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;
import org.renci.canvas.dao.refseq.model.RegionGroupRegionPK;
import org.renci.canvas.dao.refseq.model.RegionGroupRegionPK_;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion_;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { FeatureDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class FeatureDAOImpl extends BaseDAOImpl<Feature, Integer> implements FeatureDAO {

    private static final Logger logger = LoggerFactory.getLogger(FeatureDAOImpl.class);

    public FeatureDAOImpl() {
        super();
    }

    @Override
    public Class<Feature> getPersistentClass() {
        return Feature.class;
    }

    @Override
    public List<Feature> findByRefSeqCodingSequenceId(Integer refSeqCodingSequenceId) throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqCodingSequenceId(Integer)");
        List<Feature> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Feature> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<Feature> fromFeature = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(
                    fromFeature.join(Feature_.regionGroup).join(RegionGroup_.refSeqCodingSequence).get(RefSeqCodingSequence_.id),
                    refSeqCodingSequenceId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Feature> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Feature.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Feature> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String versionId) throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersionAndTranscriptId(String, String)");
        List<Feature> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Feature> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<Feature> fromFeature = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(fromFeature.get(Feature_.refseqVersion), refSeqVersion));
            Join<Feature, RegionGroup> featureRegionGroupJoin = fromFeature.join(Feature_.regionGroup);
            predicates.add(critBuilder.equal(featureRegionGroupJoin.join(RegionGroup_.transcript).get(Transcript_.id), versionId));

            featureRegionGroupJoin.join(RegionGroup_.regionGroupRegions).get(RegionGroupRegion_.id);
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Feature> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Feature.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Feature> findByRefSeqVersionAndTranscriptIdAndTranscriptPosition(String refSeqVersion, String versionId,
            Integer transcriptPosition) throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersionAndTranscriptIdAndTranscriptPosition(String, String, Integer)");
        List<Feature> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Feature> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<Feature> fromFeature = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(fromFeature.get(Feature_.refseqVersion), refSeqVersion));
            Join<Feature, RegionGroup> featureRegionGroupJoin = fromFeature.join(Feature_.regionGroup);
            predicates.add(critBuilder.equal(featureRegionGroupJoin.join(RegionGroup_.transcript).get(Transcript_.id), versionId));
            Join<RegionGroupRegion, RegionGroupRegionPK> rgr2rgrPKJoin = featureRegionGroupJoin.join(RegionGroup_.regionGroupRegions)
                    .join(RegionGroupRegion_.id);
            predicates.add(critBuilder.between(critBuilder.literal(transcriptPosition), rgr2rgrPKJoin.get(RegionGroupRegionPK_.regionStart),
                    rgr2rgrPKJoin.get(RegionGroupRegionPK_.regionEnd)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Feature> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.Feature.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
