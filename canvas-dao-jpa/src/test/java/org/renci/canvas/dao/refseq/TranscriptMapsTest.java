package org.renci.canvas.dao.refseq;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.TranscriptMapsDAOImpl;
import org.renci.canvas.dao.jpa.refseq.TranscriptMapsExonsDAOImpl;
import org.renci.canvas.dao.jpa.var.LocatedVariantDAOImpl;
import org.renci.canvas.dao.refseq.model.TranscriptMaps;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptMapsTest {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptMapsTest.class);

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
    public void findByGenomeRefIdAndRefSeqVersionAndTranscriptId() throws CANVASDAOException {

        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);

        List<TranscriptMaps> transcriptMapsList = new ArrayList<>();
        // transcriptMapsList.addAll(transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndTranscriptId(2, "61",
        // "NM_000821.5"));
        transcriptMapsList.addAll(transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndTranscriptId(2, "61", "XR_253608.1"));
        // transcriptMapsList.addAll(transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndTranscriptId(2, "61",
        // "XR_250120.1"));
        transcriptMapsList.forEach(a -> {
            System.out.println("----------------");
            System.out.println(a.toString());
            // System.out.println(a.getGenomeRefSeq().toString());
            // System.out.println(a.getTranscript().toString());
            // System.out.println(a.getExons().size());
        });
    }

    @Test
    public void testFindByGenomeRefIdAndRefSeqVersion() throws CANVASDAOException {

        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);

        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersion(2, "61");
        System.out.println(transcriptMapsList.size());
        // Set<Transcript> transcriptSet = new HashSet<Transcript>();
        // transcriptMapsList.forEach(a -> {
        // if (!transcriptSet.contains(a.getTranscript())) {
        // transcriptSet.add(a.getTranscript());
        // }
        // });
        // System.out.println(transcriptSet.size());

        TranscriptMaps transcriptMaps = transcriptMapsList.get(0);
        System.out.println(transcriptMaps.toString());

        List<LocatedVariant> LocatedVariants = locatedVariantDAO.findByVersionAccessionAndRefId(transcriptMaps.getGenomeRefSeq().getId(),
                transcriptMaps.getGenomeRef().getId());
        System.out.println(LocatedVariants.size());

    }

    @Test
    public void testFindByVersionId() throws CANVASDAOException {
        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);
        // List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByTranscriptId("NM_002105.2");
        // List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByTranscriptId("NM_000821.5");
        List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByTranscriptId("NM_016521.2");
        // List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByTranscriptId("XM_005274819.1");
        transcriptMapsList.forEach(a -> {
            System.out.println(a.getTranscript().toString());
            System.out.println(a.getGenomeRefSeq().toString());
            System.out.println(a.toString());
            System.out.println("----------------");
        });
    }

    @Test
    public void findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange() throws CANVASDAOException {
        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);
        List<TranscriptMaps> transcriptMapsExonsList = transcriptMapsDAO
                .findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange(2, "61", "NC_000001.10", 65898);
        assertTrue(transcriptMapsExonsList.size() == 1);
        transcriptMapsExonsList.forEach(a -> {
            System.out.printf("%s%n", a.getTranscript().toString());
            System.out.printf("%s%n", a.getGenomeRefSeq().toString());
            System.out.printf("%s%n", a.toString());
        });

        transcriptMapsExonsList = transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange(2, "61",
                "NC_000001.10", 134774);
        assertTrue(transcriptMapsExonsList.size() == 5);
        transcriptMapsExonsList.forEach(a -> {
            System.out.printf("%s%n", a.getTranscript().toString());
            System.out.printf("%s%n", a.getGenomeRefSeq().toString());
            System.out.printf("%s%n", a.toString());
        });

        transcriptMapsExonsList = transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndGenomeRefSeqAccessionAndInExonRange(2, "61",
                "NC_000001.10", 721323);
        assertTrue(transcriptMapsExonsList.size() == 3);
        transcriptMapsExonsList.forEach(a -> {
            System.out.printf("%s%n", a.getTranscript().toString());
            System.out.printf("%s%n", a.getGenomeRefSeq().toString());
            System.out.printf("%s%n", a.toString());
        });

    }

    @Test
    public void testUpdateMinMaxContigFields() throws CANVASDAOException {

        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);

        TranscriptMapsExonsDAOImpl transcriptMapsExonsDAO = new TranscriptMapsExonsDAOImpl();
        transcriptMapsExonsDAO.setEntityManager(em);

        TranscriptMaps tmap = transcriptMapsDAO.findById(390484);

        List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByTranscriptMapsId(tmap.getId());
        logger.info("transcriptMapsExonsList.size(): {}", transcriptMapsExonsList.size());

        transcriptMapsExonsList.sort((a, b) -> a.getId().getExonNum().compareTo(b.getId().getExonNum()));

        tmap.setMinContig(transcriptMapsExonsList.get(transcriptMapsExonsList.size() - 1).getContigEnd());
        tmap.setMaxContig(transcriptMapsExonsList.get(0).getContigStart());
        transcriptMapsDAO.save(tmap);

        // List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndStrand(2, "61", "+");
        // logger.info("transcriptMapsList.size(): {}", transcriptMapsList.size());
        //
        //
        // for (TranscriptMaps transcriptMaps : transcriptMapsList) {
        //
        // List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByTranscriptMapsId(transcriptMaps.getId());
        // logger.info("transcriptMapsExonsList.size(): {}", transcriptMapsExonsList.size());
        //
        // transcriptMapsExonsList.sort((a, b) -> a.getId().getExonNum().compareTo(b.getId().getExonNum()));
        //
        // transcriptMaps.setMinContig(transcriptMapsExonsList.get(0).getContigStart());
        // transcriptMaps.setMaxContig(transcriptMapsExonsList.get(transcriptMapsExonsList.size() - 1).getContigEnd());
        // transcriptMapsDAO.save(transcriptMaps);
        // }
        //
        // transcriptMapsList = transcriptMapsDAO.findByGenomeRefIdAndRefSeqVersionAndStrand(2, "61", "-");
        // logger.info("transcriptMapsList.size(): {}", transcriptMapsList.size());
        //
        // for (TranscriptMaps transcriptMaps : transcriptMapsList) {
        //
        // List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByTranscriptMapsId(transcriptMaps.getId());
        // logger.info("transcriptMapsExonsList.size(): {}", transcriptMapsExonsList.size());
        //
        // transcriptMapsExonsList.sort((a, b) -> a.getId().getExonNum().compareTo(b.getId().getExonNum()));
        //
        // transcriptMaps.setMinContig(transcriptMapsExonsList.get(transcriptMapsExonsList.size() - 1).getContigEnd());
        // transcriptMaps.setMaxContig(transcriptMapsExonsList.get(0).getContigStart());
        // transcriptMapsDAO.save(transcriptMaps);
        // }

    }

}
