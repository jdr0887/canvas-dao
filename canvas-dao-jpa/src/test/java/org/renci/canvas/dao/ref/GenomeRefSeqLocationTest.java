package org.renci.canvas.dao.ref;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocation;

public class GenomeRefSeqLocationTest {

    @Test
    public void testFindBySeqType() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        GenomeRefSeqLocationDAO genomeRefSeqLocationDAO = daoMgr.getDAOBean().getGenomeRefSeqLocationDAO();
        List<GenomeRefSeqLocation> genomeRefSeqLocationList = genomeRefSeqLocationDAO.findByRefIdAndVersionedAccesionAndPosition(2,
                "NC_000024.9", 21477867);
        genomeRefSeqLocationList.forEach(a -> System.out.println(a.toString()));
    }

}
