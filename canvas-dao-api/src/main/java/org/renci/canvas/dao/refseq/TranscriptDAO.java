package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.Transcript;

public interface TranscriptDAO extends BaseDAO<Transcript, String> {

    public List<Transcript> findByGenomeRefIdAndRefSeqVersion(Integer genomeRefId, String refSeqVersion) throws CANVASDAOException;

}
