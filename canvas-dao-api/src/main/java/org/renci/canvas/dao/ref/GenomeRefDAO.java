package org.renci.canvas.dao.ref;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.ref.model.GenomeRef;

public interface GenomeRefDAO extends BaseDAO<GenomeRef, Integer> {

    public List<GenomeRef> findAll() throws CANVASDAOException;

    public List<GenomeRef> findByName(String name) throws CANVASDAOException;

    public List<GenomeRef> findByNameAndSource(String name, String source) throws CANVASDAOException;

    public List<GenomeRef> findBySeqTypeAndContig(String seqType, String contig) throws CANVASDAOException;

    public List<GenomeRef> findByGenomeRefSeqVersionAccession(String versionAccession) throws CANVASDAOException;

}
