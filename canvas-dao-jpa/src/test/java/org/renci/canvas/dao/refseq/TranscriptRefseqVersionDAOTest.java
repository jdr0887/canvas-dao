package org.renci.canvas.dao.refseq;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.TranscriptRefSeqVersionDAOImpl;

public class TranscriptRefseqVersionDAOTest {

    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    @Before
    public void before() {
        this.emf = Persistence.createEntityManagerFactory("canvas_test", null);
        this.em = emf.createEntityManager();
    }

    @After
    public void after() {
        this.em.close();
        this.emf.close();
    }

    @Test
    public void testFindLatestVersion() throws CANVASDAOException {
        TranscriptRefSeqVersionDAOImpl transcriptRefSeqVersionDAO = new TranscriptRefSeqVersionDAOImpl();
        transcriptRefSeqVersionDAO.setEntityManager(em);
        System.out.println(transcriptRefSeqVersionDAO.findLatestVersion());
    }

}
