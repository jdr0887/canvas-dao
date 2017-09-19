package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.TranscriptMaps;

public interface TranscriptMapsDAO extends BaseDAO<TranscriptMaps, Integer> {

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndTranscriptId(Integer genomeRefId, String refSeqVersion,
            String versionId) throws CANVASDAOException;

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndTranscriptId(String fetchGroup, Integer genomeRefId,
            String refSeqVersion, String versionId) throws CANVASDAOException;

    public List<TranscriptMaps> findByTranscriptId(String versionId) throws CANVASDAOException;

    public List<TranscriptMaps> findByTranscriptId(String fetchGroup, String versionId) throws CANVASDAOException;

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion) throws CANVASDAOException;

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersion(String fetchGroup, Integer genomeRefId, String refSeqVersion)
            throws CANVASDAOException;

    public TranscriptMaps findById(String fetchGroup, Integer id) throws CANVASDAOException;

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange(Integer genomeRefId,
            String refseqVersion, String refSeqAccession, Integer position) throws CANVASDAOException;

    public List<TranscriptMaps> findByExample(TranscriptMaps transcriptMaps) throws CANVASDAOException;

    public Integer findNextMapCount() throws CANVASDAOException;

    public List<TranscriptMaps> findByGeneName(String geneName) throws CANVASDAOException;

    public List<TranscriptMaps> findByGeneIdAndGenomeRefId(Integer geneId, Integer genomeRefId) throws CANVASDAOException;

    public List<TranscriptMaps> findByGenomeRefIdAndRefSeqVersionAndStrand(Integer genomeRefId, String refSeqVersion, String strand)
            throws CANVASDAOException;

}
