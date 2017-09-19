package org.renci.canvas.dao.refseq;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersion;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersionPK;

public interface TranscriptRefSeqVersionDAO extends BaseDAO<TranscriptRefSeqVersion, TranscriptRefSeqVersionPK> {

    public String findLatestVersion() throws CANVASDAOException;

}
