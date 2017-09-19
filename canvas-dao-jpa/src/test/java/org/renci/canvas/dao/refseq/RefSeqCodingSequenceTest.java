package org.renci.canvas.dao.refseq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.RefSeqCodingSequenceDAOImpl;
import org.renci.canvas.dao.jpa.refseq.RegionGroupRegionDAOImpl;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;

public class RefSeqCodingSequenceTest {

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

        RefSeqCodingSequenceDAOImpl refSeqCodingSequenceDAO = new RefSeqCodingSequenceDAOImpl();
        refSeqCodingSequenceDAO.setEntityManager(em);

        RegionGroupRegionDAOImpl regionGroupRegionDAO = new RegionGroupRegionDAOImpl();
        regionGroupRegionDAO.setEntityManager(em);

        long startTime = System.currentTimeMillis();
        List<RefSeqCodingSequence> refSeqCodingSequenceList = refSeqCodingSequenceDAO.findByRefSeqVersionAndTranscriptId("61",
                "NM_001101330.1");
        refSeqCodingSequenceList.forEach(a -> System.out.println(a.toString()));

        RefSeqCodingSequence refSeqCDS = null;
        if (CollectionUtils.isNotEmpty(refSeqCodingSequenceList)) {
            refSeqCDS = refSeqCodingSequenceList.get(0);
            List<RegionGroupRegion> rgrList = regionGroupRegionDAO.findByRefSeqCodingSequenceId(refSeqCDS.getId());
            if (CollectionUtils.isNotEmpty(rgrList)) {
                System.out.println(rgrList.get(0).toString());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("%s millis %n", endTime - startTime);
    }

}
