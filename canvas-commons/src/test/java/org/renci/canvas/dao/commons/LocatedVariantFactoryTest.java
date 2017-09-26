package org.renci.canvas.dao.commons;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.VariantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import htsjdk.variant.variantcontext.Allele;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;

public class LocatedVariantFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(LocatedVariantFactoryTest.class);

    private static final List<VariantType> allVariantTypes = Arrays.asList(new VariantType("snp"), new VariantType("ins"),
            new VariantType("del"), new VariantType("sub"));

    @Test
    public void testCreateSNP() {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;
        LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 13273, "G", "C",
                Arrays.asList(new VariantType("snp")));
        logger.info(locVar.toString());
        assertTrue(locVar.getEndPosition().equals(13274));
    }

    @Test
    public void testComplex() throws Exception {

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("complex.vcf").toURI());
        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAlleleList = variantContext.getAlternateAlleles();

                for (Allele altAllele : altAlleleList) {
                    LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext.getStart(),
                            variantContext.getReference().getDisplayString(), altAllele.getDisplayString(), allVariantTypes);
                    locatedVariantList.add(locVar);
                }

            }
        }

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("complex-expected.txt").toURI());
        List<String> lines = Files.readAllLines(expectedFile.toPath());

        assertTrue(lines.size() == locatedVariantList.size());

        for (int i = 0; i < locatedVariantList.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\t");
            LocatedVariant locatedVariant = locatedVariantList.get(i);
            assertTrue(locatedVariant.getPosition().equals(Integer.valueOf(split[0])));
            assertTrue(locatedVariant.getEndPosition().equals(Integer.valueOf(split[1])));
            assertTrue(locatedVariant.getRef().equals(split[2]));
            assertTrue(locatedVariant.getSeq().equals(split[3]));
            assertTrue(locatedVariant.getVariantType().getId().equals(split[4]));
        }

    }

    @Test
    public void testMNP() throws Exception {

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("mnp.vcf").toURI());
        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAlleleList = variantContext.getAlternateAlleles();

                for (Allele altAllele : altAlleleList) {
                    LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext.getStart(),
                            variantContext.getReference().getDisplayString(), altAllele.getDisplayString(), allVariantTypes);
                    locatedVariantList.add(locVar);
                }

            }
        }

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("mnp-expected.txt").toURI());
        List<String> lines = Files.readAllLines(expectedFile.toPath());

        assertTrue(lines.size() == locatedVariantList.size());

        for (int i = 0; i < locatedVariantList.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\t");
            LocatedVariant locatedVariant = locatedVariantList.get(i);
            assertTrue(locatedVariant.getPosition().equals(Integer.valueOf(split[0])));
            assertTrue(locatedVariant.getEndPosition().equals(Integer.valueOf(split[1])));
            assertTrue(locatedVariant.getRef().equals(split[2]));
            assertTrue(locatedVariant.getSeq().equals(split[3]));
            assertTrue(locatedVariant.getVariantType().getId().equals(split[4]));
        }

    }

    @Test
    public void testSNP() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("snp.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAlleleList = variantContext.getAlternateAlleles();

                for (Allele altAllele : altAlleleList) {
                    LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext.getStart(),
                            variantContext.getReference().getDisplayString(), altAllele.getDisplayString(), allVariantTypes);
                    locatedVariantList.add(locVar);
                }

            }
        }

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("snp-expected.txt").toURI());
        List<String> lines = Files.readAllLines(expectedFile.toPath());

        assertTrue(lines.size() == locatedVariantList.size());

        for (int i = 0; i < locatedVariantList.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\t");
            LocatedVariant locatedVariant = locatedVariantList.get(i);
            assertTrue(locatedVariant.getPosition().equals(Integer.valueOf(split[0])));
            assertTrue(locatedVariant.getEndPosition().equals(Integer.valueOf(split[1])));
            assertTrue(locatedVariant.getRef().equals(split[2]));
            assertTrue(locatedVariant.getSeq().equals(split[3]));
            assertTrue(locatedVariant.getVariantType().getId().equals(split[4]));
        }

    }

    @Test
    public void testDeletion() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("del.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAlleleList = variantContext.getAlternateAlleles();

                for (Allele altAllele : altAlleleList) {
                    LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext.getStart(),
                            variantContext.getReference().getDisplayString(), altAllele.getDisplayString(), allVariantTypes);
                    locatedVariantList.add(locVar);
                }

            }
        }

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("del-expected.txt").toURI());
        List<String> lines = Files.readAllLines(expectedFile.toPath());

        assertTrue(lines.size() == locatedVariantList.size());

        for (int i = 0; i < locatedVariantList.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\t");
            LocatedVariant locatedVariant = locatedVariantList.get(i);
            assertTrue(locatedVariant.getPosition().equals(Integer.valueOf(split[0])));
            assertTrue(locatedVariant.getEndPosition().equals(Integer.valueOf(split[1])));
            assertTrue(locatedVariant.getRef().equals(split[2]));
            assertTrue(locatedVariant.getSeq().equals(split[3]));
            assertTrue(locatedVariant.getVariantType().getId().equals(split[4]));
        }

    }

    @Test
    public void testInsertion() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("ins.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAlleleList = variantContext.getAlternateAlleles();

                for (Allele altAllele : altAlleleList) {
                    LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext.getStart(),
                            variantContext.getReference().getDisplayString(), altAllele.getDisplayString(), allVariantTypes);
                    locatedVariantList.add(locVar);
                }

            }
        }

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("ins-expected.txt").toURI());
        List<String> lines = Files.readAllLines(expectedFile.toPath());

        assertTrue(lines.size() == locatedVariantList.size());

        for (int i = 0; i < locatedVariantList.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\t");
            LocatedVariant locatedVariant = locatedVariantList.get(i);
            assertTrue(locatedVariant.getPosition().equals(Integer.valueOf(split[0])));
            assertTrue(locatedVariant.getEndPosition().equals(Integer.valueOf(split[1])));
            assertTrue(locatedVariant.getRef().equals(split[2]));
            assertTrue(locatedVariant.getSeq().equals(split[3]));
            assertTrue(locatedVariant.getVariantType().getId().equals(split[4]));
        }

    }

    @Test
    public void testCreateInsertion() throws Exception {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;
        LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 1891654, "CAGAGA", "CAGAGAGAGA",
                Arrays.asList(new VariantType("ins")));
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("ins"));
    }

    @Test
    public void testCreateDeletion() throws Exception {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;
        LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 3021843, "CAGAAGAA", "CAGAA",
                Arrays.asList(new VariantType("del")));
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("del"));
    }

}
