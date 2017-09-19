package org.renci.canvas.dao.ref;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;

public class GenomeRefSeqTest {

    @Test
    public void testFindAll() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        GenomeRefSeqDAO genomeRefSeqDAO = daoMgr.getDAOBean().getGenomeRefSeqDAO();
        List<GenomeRefSeq> genomeRefSeqList = genomeRefSeqDAO.findAll();
        genomeRefSeqList.forEach(a -> System.out.println(a.toString()));
    }

    @Test
    public void testFindBySeqType() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        GenomeRefSeqDAO genomeRefSeqDAO = daoMgr.getDAOBean().getGenomeRefSeqDAO();
        List<GenomeRefSeq> genomeRefSeqList = genomeRefSeqDAO.findBySeqType("Chromosome");
        genomeRefSeqList.forEach(a -> System.out.println(a.toString()));
    }

    @Test
    public void testFindByRefIdAndContigAndSeqType() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        GenomeRefSeqDAO genomeRefSeqDAO = daoMgr.getDAOBean().getGenomeRefSeqDAO();
        List<GenomeRefSeq> genomeRefSeqList = genomeRefSeqDAO.findByGenomeRefIdAndContigAndSeqTypeAndAccessionPrefix(4, "1", "Chromosome", "NC_");
        genomeRefSeqList.forEach(a -> System.out.println(a.toString()));
    }

}
