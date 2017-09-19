package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.RefSeqGene;

public interface RefSeqGeneDAO extends BaseDAO<RefSeqGene, Integer> {

    public List<RefSeqGene> findByExample(RefSeqGene refseqGene) throws CANVASDAOException;

    public List<RefSeqGene> findByRefSeqVersion(String refSeqVersion) throws CANVASDAOException;

    public List<RefSeqGene> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId) throws CANVASDAOException;

    public List<RefSeqGene> findByTranscriptId(String transcriptId) throws CANVASDAOException;

}
