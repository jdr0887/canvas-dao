package org.renci.canvas.dao.commons;

import static org.junit.Assert.assertTrue;

import java.io.File;
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
        LocatedVariant locVar = LocatedVariantFactory.createSNP(null, null, new VariantType("snp"), "G", "C", 13273);
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

                List<Allele> altAllele = variantContext.getAlternateAlleles();
                LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext, altAllele.get(0),
                        allVariantTypes);
                locatedVariantList.add(locVar);

            }
        }

        f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("mnp.vcf").toURI());
        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAllele = variantContext.getAlternateAlleles();
                LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext, altAllele.get(0),
                        allVariantTypes);
                locatedVariantList.add(locVar);

            }
        }

        locatedVariantList.forEach(a -> logger.info(a.toString()));
        locatedVariantList.forEach(a -> assertTrue(a.getVariantType().getId().equals("sub")));

    }

    @Test
    public void testSNP() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("snp.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAllele = variantContext.getAlternateAlleles();

                LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext, altAllele.get(0),
                        allVariantTypes);
                locatedVariantList.add(locVar);

            }
        }
        locatedVariantList.forEach(a -> logger.info(a.toString()));
        locatedVariantList.forEach(a -> assertTrue(a.getVariantType().getId().equals("snp")));

    }

    @Test
    public void testDeletion() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("del.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAllele = variantContext.getAlternateAlleles();

                LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext, altAllele.get(0),
                        allVariantTypes);
                locatedVariantList.add(locVar);

            }
        }
        locatedVariantList.forEach(a -> logger.info(a.toString()));
        locatedVariantList.forEach(a -> assertTrue(a.getVariantType().getId().equals("del")));

    }

    @Test
    public void testInsertion() throws Exception {

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("ins.vcf").toURI());

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        try (VCFFileReader vcfFileReader = new VCFFileReader(f, false)) {
            for (VariantContext variantContext : vcfFileReader) {

                List<Allele> altAllele = variantContext.getAlternateAlleles();

                LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, variantContext, altAllele.get(0),
                        allVariantTypes);
                locatedVariantList.add(locVar);

            }
        }
        locatedVariantList.forEach(a -> logger.info(a.toString()));
        locatedVariantList.forEach(a -> assertTrue(a.getVariantType().getId().equals("ins")));

    }

    @Test
    public void testCreateInsertion() throws Exception {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;
        LocatedVariant locVar = LocatedVariantFactory.createInsertion(genomeRef, genomeRefSeq, new VariantType("ins"), "CAGAGA",
                "CAGAGAGAGA", 1891654);
        logger.info(locVar.toString());
    }

    @Test
    public void testCreateDeletion() throws Exception {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;
        LocatedVariant locVar = LocatedVariantFactory.createDeletion(genomeRef, genomeRefSeq, new VariantType("del"), "CAGAAGAA", "CAGAA",
                3021843);
        logger.info(locVar.toString());
    }

}
