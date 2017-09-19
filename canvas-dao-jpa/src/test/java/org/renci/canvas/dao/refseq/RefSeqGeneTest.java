package org.renci.canvas.dao.refseq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.RefSeqGeneDAOImpl;
import org.renci.canvas.dao.refseq.model.RefSeqGene;

public class RefSeqGeneTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("canvas_test", null);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testFindByRefSeqVersion() throws CANVASDAOException {
        RefSeqGeneDAOImpl refSeqGeneDAO = new RefSeqGeneDAOImpl();
        refSeqGeneDAO.setEntityManager(em);

        List<RefSeqGene> refSeqGeneList = refSeqGeneDAO.findByRefSeqVersion("61");
        refSeqGeneList.forEach(a -> System.out.println(a.toString()));
    }

    @Test
    public void testFindByRefSeqVersionAndTranscriptId() throws CANVASDAOException {
        RefSeqGeneDAOImpl refSeqGeneDAO = new RefSeqGeneDAOImpl();
        refSeqGeneDAO.setEntityManager(em);
        // List<RefSeqGene> refSeqGeneList = refSeqGeneDAO.findByRefSeqVersionAndTranscriptId("61", "NM_001101330.1");
        // List<RefSeqGene> refSeqGeneList = refSeqGeneDAO.findByRefSeqVersionAndTranscriptId("61", "XR_108279.1");

        List<RefSeqGene> refSeqGeneList = refSeqGeneDAO.findByRefSeqVersionAndTranscriptId("80", "NM_000203.4");
        refSeqGeneList.forEach(a -> System.out.println(a.toString()));
    }

}
