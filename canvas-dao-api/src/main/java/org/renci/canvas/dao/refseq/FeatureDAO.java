package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.Feature;

public interface FeatureDAO extends BaseDAO<Feature, Integer> {

    public List<Feature> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId) throws CANVASDAOException;

    public List<Feature> findByRefSeqCodingSequenceId(Integer refSeqCodingSequenceId) throws CANVASDAOException;

    public List<Feature> findByRefSeqVersionAndTranscriptIdAndTranscriptPosition(String refSeqVersion, String versionId,
            Integer transcriptPosition) throws CANVASDAOException;

}
