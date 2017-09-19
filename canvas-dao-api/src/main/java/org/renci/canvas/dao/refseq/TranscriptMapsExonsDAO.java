package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExonsPK;

public interface TranscriptMapsExonsDAO extends BaseDAO<TranscriptMapsExons, TranscriptMapsExonsPK> {

    public List<TranscriptMapsExons> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion) throws CANVASDAOException;

    public List<TranscriptMapsExons> findByGenomeRefIdAndRefSeqVersionAndAccession(Integer genomeRefId, String refSeqVersion,
            String accession) throws CANVASDAOException;

    public List<TranscriptMapsExons> findByTranscriptMapsId(Integer id) throws CANVASDAOException;

    public List<TranscriptMapsExons> findByTranscriptVersionIdAndTranscriptMapsMapCount(String versionId, Integer mapCount)
            throws CANVASDAOException;

    public List<TranscriptMapsExons> findByGenomeRefSeqAccessionAndInExonRange(String refSeqAccession, Integer start)
            throws CANVASDAOException;

    public Integer findMaxContig(Integer genomeRefId, String refSeqVersion, String refSeqAccession, Integer transcriptMapId)
            throws CANVASDAOException;

    public List<TranscriptMapsExons> findByGeneIdAndGenomeRefId(Integer geneName, Integer genomeRefId) throws CANVASDAOException;

}
