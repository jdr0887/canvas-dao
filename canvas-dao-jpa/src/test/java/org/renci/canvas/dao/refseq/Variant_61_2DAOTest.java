package org.renci.canvas.dao.refseq;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.MaxFrequency;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency;
import org.renci.canvas.dao.jpa.ref.GenomeRefDAOImpl;
import org.renci.canvas.dao.jpa.ref.GenomeRefSeqDAOImpl;
import org.renci.canvas.dao.jpa.refseq.VariantEffectDAOImpl;
import org.renci.canvas.dao.jpa.refseq.Variants_61_2_DAOImpl;
import org.renci.canvas.dao.jpa.var.LocatedVariantDAOImpl;
import org.renci.canvas.dao.jpa.var.VariantTypeDAOImpl;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.refseq.model.VariantEffect;
import org.renci.canvas.dao.refseq.model.Variants_61_2;
import org.renci.canvas.dao.refseq.model.Variants_61_2PK;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.VariantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Variant_61_2DAOTest {

    private static final Logger logger = LoggerFactory.getLogger(Variant_61_2DAOTest.class);

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
    public void testFindByAssemblyId() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        List<Variants_61_2> variants = variantsDAO.findByAssemblyId(36299);
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
    }

    @Test
    public void testFindTranscriptDependentCount() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        Long count = variantsDAO.findTranscriptDependentCount(36299);
        assertTrue(count != null);
        System.out.println(count);
    }

    @Test
    public void testFindCodingCount() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        Long count = variantsDAO.findCodingCount(36299);
        assertTrue(count != null);
        System.out.println(count);
    }

    @Test
    public void testFindNonCodingCount() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        Long count = variantsDAO.findNonCodingCount(36299);
        assertTrue(count != null);
        System.out.println(count);
    }

    @Test
    public void testFindByLocatedVariantId() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        List<Variants_61_2> variants = variantsDAO.findByLocatedVariantId(491812292L);
        assertTrue(CollectionUtils.isNotEmpty(variants));
        variants.forEach(a -> {
            System.out.println(a.toString());
            System.out.println(a.getLocatedVariant().toString());
        });
    }

    @Test
    public void testFindByName() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        List<Variants_61_2> variants = variantsDAO.findByGeneName("BRCA1");
        assertTrue(CollectionUtils.isNotEmpty(variants));
        variants.forEach(a -> {
            System.out.println(a.getLocatedVariant().toString());
        });
    }

    @Test
    public void testFindByAssemblyIdAndHGMDVersionAndMaxFrequencyThresholdAndGeneId() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findByAssemblyIdAndSampleNameAndHGMDVersionAndMaxFrequencyThresholdAndGeneId(33269,
                "NCG_01089", 1, 0.1, 21084);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        List<String> variantEffectList = Arrays.asList("nonsense", "splice-site", "frameshifting indel", "nonsense indel",
                "boundary-crossing indel", "potential RNA-editing site");
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findByHGMDVersionAndMaxFrequencyThresholdAndGeneIdAndVariantEffectList(2, 0.05, 21084,
                variantEffectList);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindByGeneNameAndMaxAlleleFrequency() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        List<Variants_61_2> variants = variantsDAO.findByGeneNameAndMaxAlleleFrequency("BRCA1", 0.05);
        assertTrue(CollectionUtils.isNotEmpty(variants));
        int count = 0;
        for (Variants_61_2 variant : variants) {
            LocatedVariant locatedVariant = variant.getLocatedVariant();
            List<MaxFrequency> clinbinMaxFrequencies = locatedVariant.getMaxFreqs();
            count += clinbinMaxFrequencies.size();
            List<ExACMaxVariantFrequency> exacMaxFrequencies = locatedVariant.getExACMaxVariantFrequencies();
            count += exacMaxFrequencies.size();
        }
        System.out.println(count);
    }

    @Test
    public void testFindKnownPathogenic() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findKnownPathenogenic(1, 8, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindLikelyPathogenic() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findLikelyPathenogenic(1, 29, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindPossiblyPathogenic() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findPossiblyPathenogenic(1, 6, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindUncertainSignificance() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findUncertainSignificance(1, 29, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindLikelyBenign() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findLikelyBenign(1, 6, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testFindAlmostCertainlyBenign() throws CANVASDAOException {
        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);
        long startTime = System.currentTimeMillis();
        List<Variants_61_2> variants = variantsDAO.findAlmostCertainlyBenign(1, 6, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

    @Test
    public void testSave() throws CANVASDAOException {

        // GenomeRef genomeRef = new GenomeRef("NCBI", "BUILD.37.1", "37.1",
        // "file:///volume1/annotation/abeast/reference_fasta/BUILD.37.1/BUILD.37.1.fasta",
        // "file:///volume1/annotation/abeast/reference_fasta/BUILD.37.1/BUILD.37.1_extras.fasta");
        // genomeRef.setId(1);
        // daoMgr.getDAOBean().getGenomeRefDAO().save(genomeRef);
        //
        // GenomeRefSeq genomeRefSeq = new GenomeRefSeq("NC_000001.9", "1", "Chromosome");
        // daoMgr.getDAOBean().getGenomeRefSeqDAO().save(genomeRefSeq);
        //
        // LocatedVariant locatedVariant = new LocatedVariant(genomeRef, genomeRefSeq, 154614895, 154614896, vType, "G",
        // "A");
        // locatedVariant.setId(daoMgr.getDAOBean().getLocatedVariantDAO().save(locatedVariant));

        Variants_61_2_DAOImpl variantsDAO = new Variants_61_2_DAOImpl();
        variantsDAO.setEntityManager(em);

        GenomeRefDAOImpl genomeRefDAO = new GenomeRefDAOImpl();
        genomeRefDAO.setEntityManager(em);

        GenomeRefSeqDAOImpl genomeRefSeqDAO = new GenomeRefSeqDAOImpl();
        genomeRefSeqDAO.setEntityManager(em);

        VariantTypeDAOImpl variantTypeDAO = new VariantTypeDAOImpl();
        variantTypeDAO.setEntityManager(em);

        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        VariantEffectDAOImpl variantEffectDAO = new VariantEffectDAOImpl();
        variantEffectDAO.setEntityManager(em);

        GenomeRef genomeRef = genomeRefDAO.findById(1);
        GenomeRefSeq genomeRefSeq = genomeRefSeqDAO.findById("NC_000001.9");
        VariantType variantType = variantTypeDAO.findById("snp");
        LocatedVariant locatedVariant = locatedVariantDAO.findById(2L);
        VariantEffect variantEffect = variantEffectDAO.findById("missense");

        Variants_61_2PK key = new Variants_61_2PK(locatedVariant.getId(), "NC_000001.10", 47904478, variantType.getId(), "NM_004474.3",
                "exon", variantEffect.getId(), 1);

        Variants_61_2 variant = new Variants_61_2(key);
        variant.setAminoAcidEnd(224);
        variant.setAminoAcidStart(225);
        variant.setCodingSequencePosition(671);
        variant.setTranscriptPosition(2790);
        variant.setFeatureId(0);
        variant.setFinalAminoAcid("M");
        variant.setFrameshift(Boolean.FALSE);
        // variant.setGene(gene);
        variant.setGenomeRefSeq(genomeRefSeq);
        variant.setHgncGene("FOXD2");
        variant.setInframe(Boolean.FALSE);
        variant.setRefSeqGene("FOXD2");
        variant.setStrand("+");

        variantsDAO.save(variant);

    }

    @Test
    public void findByAssemblyId() throws CANVASDAOException {
        Variants_61_2_DAOImpl variants_61_2_DAO = new Variants_61_2_DAOImpl();
        variants_61_2_DAO.setEntityManager(em);

        List<Variants_61_2> variants = variants_61_2_DAO.findByAssemblyId(36299);

        Map<Long, Set<String>> asdf = new HashMap<>();
        for (Variants_61_2 variant : variants) {
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
