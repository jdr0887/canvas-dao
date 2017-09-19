package org.renci.canvas.dao.refseq;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.Variants_80_4_DAOImpl;
import org.renci.canvas.dao.jpa.var.LocatedVariantDAOImpl;
import org.renci.canvas.dao.refseq.model.Variants_80_4;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Variant_80_4_DAOTest {

    private static final Logger logger = LoggerFactory.getLogger(Variant_80_4_DAOTest.class);

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
    public void testFindTranscriptDependentCount() throws CANVASDAOException {

        Variants_80_4_DAOImpl variantsDAO = new Variants_80_4_DAOImpl();
        variantsDAO.setEntityManager(em);

        Long count = variantsDAO.findTranscriptDependentCount(36355);
        assertTrue(count != null);
        logger.info("count = {}", count);

    }

    @Test
    public void testFindMissingLocatedVariants() throws CANVASDAOException {

        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        Variants_80_4_DAOImpl variantsDAO = new Variants_80_4_DAOImpl();
        variantsDAO.setEntityManager(em);

        List<LocatedVariant> locatedVariants = locatedVariantDAO.findByAssemblyId(36355);

        List<LocatedVariant> missingLocatedVariants = locatedVariantDAO.findByAssemblyId(36355);
        for (LocatedVariant locatedVariant : locatedVariants) {
            List<Variants_80_4> variants = variantsDAO.findByLocatedVariantId(locatedVariant.getId());
            if (CollectionUtils.isEmpty(variants)) {
                missingLocatedVariants.add(locatedVariant);
            }
        }

        missingLocatedVariants.forEach(a -> logger.info(a.toString()));
    }

    @Test
    public void testFindCodingCount() throws CANVASDAOException {

        Variants_80_4_DAOImpl variantsDAO = new Variants_80_4_DAOImpl();
        variantsDAO.setEntityManager(em);

        Long count = variantsDAO.findCodingCount(36355);
        assertTrue(count != null);
        logger.info("count = {}", count);

    }

    @Test
    public void testFindNonCodingCount() throws CANVASDAOException {

        Variants_80_4_DAOImpl variantsDAO = new Variants_80_4_DAOImpl();
        variantsDAO.setEntityManager(em);

        Long count = variantsDAO.findNonCodingCount(36355);
        assertTrue(count != null);
        logger.info("count = {}", count);

    }

    @Test
    public void findByAssemblyId() throws CANVASDAOException {
        Variants_80_4_DAOImpl variants_80_4_DAO = new Variants_80_4_DAOImpl();
        variants_80_4_DAO.setEntityManager(em);

        List<Variants_80_4> variants = variants_80_4_DAO.findByAssemblyId(36355);

        Map<Long, Set<String>> asdf = new HashMap<>();
        for (Variants_80_4 variant : variants) {
            if (!asdf.containsKey(variant.getLocatedVariant().getId())) {
                asdf.put(variant.getLocatedVariant().getId(), new HashSet<>());
            }
            asdf.get(variant.getLocatedVariant().getId()).add(variant.getLocationType().getId());
        }

        Map<Set<String>, Integer> qwer = new HashMap<>();
        for (Long locatedVariantId : asdf.keySet()) {
            if (!qwer.containsKey(asdf.get(locatedVariantId))) {
                qwer.put(asdf.get(locatedVariantId), 0);
            }
            qwer.put(asdf.get(locatedVariantId), qwer.get(asdf.get(locatedVariantId)) + 1);
        }

        Map<String, Integer> zxcv = new HashMap<>();
        for (Set<String> locationTypeSet : qwer.keySet()) {

            String zxcvKey = null;
            if (locationTypeSet.contains("exon") || locationTypeSet.contains("intron/exon boundary")) {
                if (locationTypeSet.size() > 1) {
                    zxcvKey = "transcript-dependent";
                } else {
                    zxcvKey = "coding";
                }
            } else {
                zxcvKey = "noncoding";
            }

            if (!zxcv.containsKey(zxcvKey)) {
                zxcv.put(zxcvKey, 0);
            }

            zxcv.put(zxcvKey, zxcv.get(zxcvKey) + qwer.get(locationTypeSet));

        }

        logger.info("transcript-dependent: {}", zxcv.get("transcript-dependent"));
        logger.info("coding: {}", zxcv.get("coding"));
        logger.info("noncoding: {}", zxcv.get("noncoding"));

    }

}
