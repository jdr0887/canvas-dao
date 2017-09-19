package org.renci.canvas.dao.refseq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.RegionGroupRegionDAOImpl;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;

public class RegionGroupRegionTest {

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
    public void testFindByRefSeqVersionAndTranscriptId() throws CANVASDAOException {
        RegionGroupRegionDAOImpl regionGroupRegionDAO = new RegionGroupRegionDAOImpl();
        regionGroupRegionDAO.setEntityManager(em);

        long startTime = System.currentTimeMillis();

        List<RegionGroupRegion> rgrList = regionGroupRegionDAO.findByRefSeqVersionAndTranscriptId("61", "NM_001101330.1");
        rgrList.forEach(a -> System.out.println(a.toString()));
        
        long endTime = System.currentTimeMillis();
        System.out.printf("%s millis %n", endTime - startTime);

    }
}
