package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence;

public interface RefSeqCodingSequenceDAO extends BaseDAO<RefSeqCodingSequence, Integer> {

    public List<RefSeqCodingSequence> findByVersion(String version) throws CANVASDAOException;

    public List<RefSeqCodingSequence> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId)
            throws CANVASDAOException;

    public List<RefSeqCodingSequence> findByRefSeqVersionAndTranscriptId(String fetchPlan, String refSeqVersion, String transcriptId)
            throws CANVASDAOException;

    public List<RefSeqCodingSequence> findByExample(RefSeqCodingSequence refseqCodingSequence) throws CANVASDAOException;

}
