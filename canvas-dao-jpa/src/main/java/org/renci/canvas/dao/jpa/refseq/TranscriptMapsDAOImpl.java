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
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.ref.model.GenomeRefSeq_;
import org.renci.canvas.dao.ref.model.GenomeRef_;
import org.renci.canvas.dao.ref.model.SequenceType_;
import org.renci.canvas.dao.refseq.TranscriptMapsDAO;
import org.renci.canvas.dao.refseq.model.RefSeqGene;
import org.renci.canvas.dao.refseq.model.RefSeqGene_;
import org.renci.canvas.dao.refseq.model.RegionGroup;
import org.renci.canvas.dao.refseq.model.RegionGroup_;
import org.renci.canvas.dao.refseq.model.Transcript;
import org.renci.canvas.dao.refseq.model.TranscriptMaps;
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
@OsgiServiceProvider(classes = { TranscriptMapsDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TranscriptMapsDAOImpl extends BaseDAOImpl<TranscriptMaps, Integer> implements TranscriptMapsDAO {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptMapsDAOImpl.class);

    public TranscriptMapsDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptMaps> getPersistentClass() {
        return TranscriptMaps.class;
    }

    @Override
    public List<TranscriptMaps> findByTranscriptId(String versionId) throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptId(String)");
        return findByTranscriptId("refseq.TranscriptMaps.includeManyToOnes", versionId);
    }

    @Override
    public List<TranscriptMaps> findByExample(TranscriptMaps transcriptMaps) throws CANVASDAOException {
        List<TranscriptMaps> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());

            if (transcriptMaps.getStrand() != null) {
                predicates.add(critBuilder.equal(root.get(TranscriptMaps_.strand), transcriptMaps.getStrand()));
            }

            if (transcriptMaps.getExonCount() != null) {
                predicates.add(critBuilder.equal(root.get(TranscriptMaps_.exonCount), transcriptMaps.getExonCount()));
            }

            if (transcriptMaps.getTranscript() != null) {
                predicates.add(critBuilder.equal(root.join(TranscriptMaps_.transcript).get(Transcript_.id),
                        transcriptMaps.getTranscript().getId()));
            }

            if (transcriptMaps.getGenomeRef() != null) {
                predicates.add(
                        critBuilder.equal(root.join(TranscriptMaps_.genomeRef).get(GenomeRef_.id), transcriptMaps.getGenomeRef().getId()));
            }

            if (transcriptMaps.getGenomeRefSeq() != null) {
                predicates.add(critBuilder.equal(root.join(TranscriptMaps_.genomeRefSeq).get(GenomeRefSeq_.id),
                        transcriptMaps.getGenomeRefSeq().getId()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("refseq.TranscriptMaps.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGeneIdAndGenomeRefId(Integer geneId, Integer genomeRefId) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneIdAndGenomeRefId(String)");
        List<TranscriptMaps> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());

            predicates.add(critBuilder.equal(root.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));

            Join<RegionGroup, RefSeqGene> regionGroup2RefSeqGeneJoin = root.join(TranscriptMaps_.transcript).join(Transcript_.regionGroups)
                    .join(RegionGroup_.refSeqGenes);
            predicates.add(critBuilder.equal(regionGroup2RefSeqGeneJoin.get(RefSeqGene_.id), geneId));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("refseq.TranscriptMaps.includeAll"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByTranscriptId(String fetchGroup, String versionId) throws CANVASDAOException {
        logger.debug("ENTERING findByTranscriptId(String, String)");
        List<TranscriptMaps> ret = new ArrayList<TranscriptMaps>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = root.join(TranscriptMaps_.transcript);
            predicates.add(critBuilder.equal(transcriptMapsTranscriptJoin.get(Transcript_.id), versionId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit);
            if (StringUtils.isNotEmpty(fetchGroup)) {
                query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph(fetchGroup));
            }
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGeneName(String geneName) throws CANVASDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        List<TranscriptMaps> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());

            Join<RegionGroup, RefSeqGene> regionGroup2RefSeqGeneJoin = root.join(TranscriptMaps_.transcript).join(Transcript_.regionGroups)
                    .join(RegionGroup_.refSeqGenes);
            predicates.add(critBuilder.equal(regionGroup2RefSeqGeneJoin.get(RefSeqGene_.name), geneName));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph("refseq.TranscriptMaps.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange(Integer genomeRefId,
            String refseqVersion, String refSeqAccession, Integer position) throws CANVASDAOException {
        List<TranscriptMaps> ret = new ArrayList<TranscriptMaps>();
        try {

            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));

            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = root.join(TranscriptMaps_.transcript);

            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefSeqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);

            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefSeqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refseqVersion));

            Join<TranscriptMaps, GenomeRefSeq> transcriptMapsGenomeRefSeqJoin = root.join(TranscriptMaps_.genomeRefSeq);
            predicates.add(critBuilder.equal(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.id), refSeqAccession));

            predicates.add(critBuilder.between(critBuilder.literal(position), root.get(TranscriptMaps_.minContig),
                    root.get(TranscriptMaps_.maxContig)));

            crit.orderBy(critBuilder.asc(root.get(TranscriptMaps_.mapCount)));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.TranscriptMaps.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndTranscriptId(Integer genomeRefId, String refSeqVersion,
            String versionId) throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersionAndTranscriptId(Integer, String, String)");
        return findByGenomeRefIdAndRefSeqVersionAndTranscriptId("refseq.TranscriptMaps.includeManyToOnes", genomeRefId, refSeqVersion,
                versionId);
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndTranscriptId(String fetchGroup, Integer genomeRefId,
            String refSeqVersion, String versionId) throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersionAndTranscriptId(String, Integer, String, String)");
        List<TranscriptMaps> ret = new ArrayList<TranscriptMaps>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));
            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = root.join(TranscriptMaps_.transcript);
            predicates.add(critBuilder.equal(transcriptMapsTranscriptJoin.get(Transcript_.id), versionId));
            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefseqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(root.get(TranscriptMaps_.mapCount)));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit);
            if (StringUtils.isNotEmpty(fetchGroup)) {
                query.setHint("javax.persistence.fetchgraph", getEntityManager().getEntityGraph(fetchGroup));
            }
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion) throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersion(Integer, String)");
        return findByGenomeRefIdAndRefSeqVersion("refseq.TranscriptMaps.includeManyToOnes", genomeRefId, refSeqVersion);
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndStrand(Integer genomeRefId, String refSeqVersion, String strand)
            throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersionAndStrand(Integer, String, String)");

        List<TranscriptMaps> ret = new ArrayList<TranscriptMaps>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.get(TranscriptMaps_.strand), strand));
            predicates.add(critBuilder.equal(root.join(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));
            predicates.add(critBuilder.equal(root.join(TranscriptMaps_.transcript).join(Transcript_.refseqVersions)
                    .get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version), refSeqVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("refseq.TranscriptMaps.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;

    }

    @Override
    public TranscriptMaps findById(String fetchGroup, Integer id) throws CANVASDAOException {
        logger.debug("ENTERING findById(String, Integer)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
        Root<TranscriptMaps> root = crit.from(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(root.get(TranscriptMaps_.id), id));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                getEntityManager().getEntityGraph(fetchGroup));
        TranscriptMaps ret = query.getSingleResult();
        return ret;
    }

    @Override
    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersion(String fetchGroup, Integer genomeRefId, String refSeqVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefIdAndRefSeqVersion(String, Integer, String)");
        List<TranscriptMaps> ret = new ArrayList<TranscriptMaps>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TranscriptMaps> crit = critBuilder.createQuery(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            predicates.add(critBuilder.equal(root.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));
            Join<TranscriptMaps, GenomeRefSeq> transcriptMapsGenomeRefSeqJoin = root.join(TranscriptMaps_.genomeRefSeq);
            predicates.add(
                    critBuilder.equal(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), "Chromosome"));
            predicates.add(critBuilder.equal(critBuilder.substring(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.id), 1, 3), "NC_"));
            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = root.join(TranscriptMaps_.transcript);
            predicates.add(
                    critBuilder.or(critBuilder.equal(critBuilder.substring(transcriptMapsTranscriptJoin.get(Transcript_.id), 1, 3), "NM_"),
                            critBuilder.equal(critBuilder.substring(transcriptMapsTranscriptJoin.get(Transcript_.id), 1, 3), "NR_")));
            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefseqVersJoin = transcriptMapsTranscriptJoin
                    .join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<TranscriptMaps> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph(fetchGroup));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public Integer findNextMapCount() throws CANVASDAOException {
        logger.debug("ENTERING findNextMapCount()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Root<TranscriptMaps> root = crit.from(getPersistentClass());
            crit.select(critBuilder.max(root.get(TranscriptMaps_.mapCount)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Integer> query = getEntityManager().createQuery(crit);
            ret = query.getSingleResult();
            if (ret == null) {
                ret = 0;
            }
            ret += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
