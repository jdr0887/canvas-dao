package org.renci.canvas.dao.hgnc;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.model.HGNCGene;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class HGNCGeneTest {

    @Test
    public void testFindByGeneIdAndNamespace() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        HGNCGeneDAO hgncGeneDAO = daoMgr.getDAOBean().getHGNCGeneDAO();
        List<HGNCGene> foundHGNCGenes = hgncGeneDAO.findByAnnotationGeneExternalIdsGeneIdsAndNamespace(20051, "HGNC");
        foundHGNCGenes.forEach(a -> System.out.println(a.toString()));
    }

}
