package org.renci.canvas.dao.jpa.refseq;

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
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.RegionGroupDAO;
import org.renci.canvas.dao.refseq.model.Feature_;
import org.renci.canvas.dao.refseq.model.GroupingType_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { RegionGroupDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class RegionGroupDAOImpl extends BaseDAOImpl<RegionGroup, Integer> implements RegionGroupDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegionGroupDAOImpl.class);

    public RegionGroupDAOImpl() {
        super();
    }

    @Override
    public Class<RegionGroup> getPersistentClass() {
        return RegionGroup.class;
    }

    @Override
    public List<RegionGroup> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId) throws CANVASDAOException {
        logger.debug("ENTERING findByRefSeqVersionAndTranscriptId(String, String)");
        List<RegionGroup> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RegionGroup> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<RegionGroup> fromRegionGroup = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(fromRegionGroup.join(RegionGroup_.transcript).get(Transcript_.id), transcriptId));
            predicates.add(critBuilder.equal(fromRegionGroup.join(RegionGroup_.features).get(Feature_.refseqVersion), refSeqVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RegionGroup> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RegionGroup.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<RegionGroup> findByTranscriptIdAndGroupingType(String transcriptVersionId, String groupingType) throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptIdAndGroupingType(String, String)");
        List<RegionGroup> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<RegionGroup> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<RegionGroup> fromRegionGroup = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(fromRegionGroup.join(RegionGroup_.transcript).get(Transcript_.id), transcriptVersionId));
            predicates.add(critBuilder.equal(fromRegionGroup.join(RegionGroup_.groupingType).get(GroupingType_.id), groupingType));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<RegionGroup> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.RegionGroup.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
