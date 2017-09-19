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
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.ref.model.GenomeRefSeq_;
import org.renci.canvas.dao.ref.model.GenomeRef_;
import org.renci.canvas.dao.refseq.TranscriptMapsExonsDAO;
import org.renci.canvas.dao.refseq.model.RefSeqGene;
import org.renci.canvas.dao.refseq.model.RefSeqGene_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript;
import org.renci.canvas.dao.refseq.model.TranscriptMaps;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExonsPK;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons_;
import org.renci.canvas.dao.refseq.model.TranscriptMaps_;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersion;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersionPK_;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersion_;
import org.renci.canvas.dao.refseq.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { TranscriptMapsExonsDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TranscriptMapsExonsDAOImpl extends BaseDAOImpl<TranscriptMapsExons, TranscriptMapsExonsPK> implements TranscriptMapsExonsDAO {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptMapsExonsDAOImpl.class);

    public TranscriptMapsExonsDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptMapsExons> getPersistentClass() {
        return TranscriptMapsExons.class;
    }

    @Override
    public List<TranscriptMapsExons> findByTranscriptMapsId(Integer id) throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptMapsId(Integer)");
        List<TranscriptMapsExons> ret = new ArrayList<TranscriptMapsExons>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());
            Join<TranscriptMapsExons, TranscriptMaps> join = root.join(TranscriptMapsExons_.transcriptMaps);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(join.get(TranscriptMaps_.id), id));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMapsExons> findByTranscriptVersionIdAndTranscriptMapsMapCount(String versionId, Integer mapCount)
            throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptVersionIdAndTranscriptMapsMapCount(String, Integer)");
        List<TranscriptMapsExons> ret = new ArrayList<TranscriptMapsExons>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.mapCount), mapCount));

            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptjoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.transcript);
            predicates.add(critBuilder.equal(transcriptMapsTranscriptjoin.get(Transcript_.id), versionId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMapsExons> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersion(Integer, String)");
        List<TranscriptMapsExons> ret = new ArrayList<TranscriptMapsExons>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id),
                    genomeRefId));

            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.transcript);
            Join<Transcript, TranscriptRefSeqVersion> TranscriptTranscriptRefseqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    TranscriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMapsExons> findByGenomeRefSeqAccessionAndInExonRange(String refSeqAccession, Integer start)
            throws CANVASDAOException {
        List<TranscriptMapsExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            Join<TranscriptMaps, GenomeRefSeq> transcriptMapsGenomeRefSeqJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.genomeRefSeq);
            predicates.add(critBuilder.equal(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.id), refSeqAccession));

            predicates.add(critBuilder.between(critBuilder.literal(start), root.get(TranscriptMapsExons_.contigStart),
                    root.get(TranscriptMapsExons_.contigEnd)));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<TranscriptMapsExons> findByGenomeRefIdAndRefSeqVersionAndAccession(Integer genomeRefId, String refSeqVersion,
            String accession) throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersionAndAccession(Integer, String, String)");
        List<TranscriptMapsExons> ret = new ArrayList<TranscriptMapsExons>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());
            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id),
                    genomeRefId));
            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.transcript);
            predicates.add(critBuilder.equal(transcriptMapsTranscriptJoin.get(Transcript_.id), accession));
            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefseqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMapsExons> findByGeneIdAndGenomeRefId(Integer geneId, Integer genomeRefId) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        List<TranscriptMapsExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMapsExons> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());

            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id),
                    genomeRefId));

            Join<RegionGroup, RefSeqGene> regionGroup2RefSeqGeneJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.transcript).join(Transcript_.regionGroups).join(RegionGroup_.refSeqGenes);
            predicates.add(critBuilder.equal(regionGroup2RefSeqGeneJoin.get(RefSeqGene_.id), geneId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMapsExons> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.TranscriptMapsExons.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Integer findMaxContig(Integer genomeRefId, String refSeqVersion, String refSeqAccession, Integer transcriptMapsId)
            throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersionAndAccession(Integer, String, String)");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMapsExons> root = crit.from(getPersistentClass());

            Join<TranscriptMapsExons, TranscriptMaps> transcriptMapsExonsTranscriptMapsJoin = root
                    .join(TranscriptMapsExons_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id),
                    genomeRefId));
            predicates.add(critBuilder.equal(transcriptMapsExonsTranscriptMapsJoin.get(TranscriptMaps_.id), transcriptMapsId));

            Join<TranscriptMaps, GenomeRefSeq> transcriptMapsGenomeRefSeqJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.genomeRefSeq);
            predicates.add(critBuilder.equal(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.id), refSeqAccession));

            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = transcriptMapsExonsTranscriptMapsJoin
                    .join(TranscriptMaps_.transcript);
            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefseqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));

            // crit.select(critBuilder.max(root.get(TranscriptMapsExons_.contigStart)));
            crit.select(critBuilder.min(root.get(TranscriptMapsExons_.contigEnd)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            // crit.distinct(true);
            TypedQuery<Integer> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
