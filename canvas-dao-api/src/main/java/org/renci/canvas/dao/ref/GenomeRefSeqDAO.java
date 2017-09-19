package org.renci.canvas.dao.ref;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;

public interface GenomeRefSeqDAO extends BaseDAO<GenomeRefSeq, String> {

    public List<GenomeRefSeq> findAll() throws CANVASDAOException;

    public List<GenomeRefSeq> findBySeqType(String seqType) throws CANVASDAOException;

    public List<GenomeRefSeq> findByNameAndSourceAndContig(String name, String source, String contig) throws CANVASDAOException;

    public List<GenomeRefSeq> findByGenomeRefId(Integer refId) throws CANVASDAOException;

    public List<GenomeRefSeq> findByGenomeRefIdAndSeqType(Integer refId, String seqType) throws CANVASDAOException;

    public List<GenomeRefSeq> findByGenomeRefIdAndContigAndSeqType(Integer refId, String contig, String seqType) throws CANVASDAOException;

    public List<GenomeRefSeq> findByGenomeRefIdAndContigAndSeqTypeAndAccessionPrefix(Integer refId, String contig, String seqType,
            String prefix) throws CANVASDAOException;

}
