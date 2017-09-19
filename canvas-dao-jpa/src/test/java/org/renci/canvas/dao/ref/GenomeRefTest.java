package org.renci.canvas.dao.ref;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.ref.model.GenomeRef;

public class GenomeRefTest {

    @Test
    public void testFindAll() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        GenomeRefDAO genomeRefDAO = daoMgr.getDAOBean().getGenomeRefDAO();
        List<GenomeRef> genomeRefList = genomeRefDAO.findAll();
        genomeRefList.forEach(a -> System.out.println(a.toString()));
    }

}
