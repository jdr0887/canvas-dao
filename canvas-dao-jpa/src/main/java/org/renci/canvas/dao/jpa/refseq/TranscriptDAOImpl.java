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
import org.renci.canvas.dao.ref.model.SequenceType_;
import org.renci.canvas.dao.refseq.TranscriptDAO;
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
@OsgiServiceProvider(classes = { TranscriptDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TranscriptDAOImpl extends BaseDAOImpl<Transcript, String> implements TranscriptDAO {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptDAOImpl.class);

    public TranscriptDAOImpl() {
        super();
    }

    @Override
    public Class<Transcript> getPersistentClass() {
        return Transcript.class;
    }

    @Override
    public List<Transcript> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion) throws CANVASDAOException {
        logger.debug("ENTERING findByGenomeRefAndRefSeqVersion(Integer, String)");
        List<Transcript> ret = new ArrayList<Transcript>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
            Root<Transcript> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Transcript, TranscriptMaps> transcriptTranscriptMapsJoin = root.join(Transcript_.transcriptMaps);
            predicates.add(critBuilder.equal(transcriptTranscriptMapsJoin.get(TranscriptMaps_.genomeRef).get(GenomeRef_.id), genomeRefId));
            Join<Transcript, TranscriptRefSeqVersion> transcriptTranscriptRefseqVersJoin = root.join(Transcript_.refseqVersions);
            predicates.add(critBuilder.equal(
                    transcriptTranscriptRefseqVersJoin.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version),
                    refSeqVersion));
            Join<TranscriptMaps, GenomeRefSeq> transcriptMapsGenomeRefSeqJoin = transcriptTranscriptMapsJoin
                    .join(TranscriptMaps_.genomeRefSeq);
            predicates.add(
                    critBuilder.equal(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), "Chromosome"));
            predicates.add(critBuilder.equal(critBuilder.substring(transcriptMapsGenomeRefSeqJoin.get(GenomeRefSeq_.id), 1, 3), "NC_"));
            Join<TranscriptMaps, Transcript> transcriptMapsTranscriptJoin = transcriptTranscriptMapsJoin.join(TranscriptMaps_.transcript);
            predicates.add(
                    critBuilder.or(critBuilder.equal(critBuilder.substring(transcriptMapsTranscriptJoin.get(Transcript_.id), 1, 3), "NM_"),
                            critBuilder.equal(critBuilder.substring(transcriptMapsTranscriptJoin.get(Transcript_.id), 1, 3), "NR_")));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }
}
