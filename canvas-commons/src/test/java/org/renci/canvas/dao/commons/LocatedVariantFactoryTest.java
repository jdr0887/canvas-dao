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

    private void testIndividualVartype(String vartype) throws Exception {

        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        List<LocatedVariant> locatedVariantList = new ArrayList<>();

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource(vartype + ".vcf").toURI());
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

        File expectedFile = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource(vartype + "-expected.txt").toURI());
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
    public void testComplex() throws Exception {
        testIndividualVartype("complex");
    }

    @Test
    public void testMNP() throws Exception {
        testIndividualVartype("mnp");
    }

    @Test
    public void testSNP() throws Exception {
        testIndividualVartype("snp");
    }

    @Test
    public void testDeletion() throws Exception {
        testIndividualVartype("del");
    }

    @Test
    public void testInsertion() throws Exception {
        testIndividualVartype("ins");
    }

    @Test
    public void testCreateInsertion() throws Exception {
        GenomeRef genomeRef = null;
        GenomeRefSeq genomeRefSeq = null;

        LocatedVariant locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 1891654, "CAGAGA", "CAGAGAGAGA", allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("ins"));

        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 20763686, "C", "CC", allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("ins"));

        // <SequenceLocation Assembly="GRCh38" AssemblyAccessionVersion="GCF_000001405.33" AssemblyStatus="current" Chr="1"
        // Accession="NC_000001.11" start="3835081" stop="3835082" display_start="3835081" display_stop="3835082" variantLength="1"
        // referenceAllele="-" alternateAllele="A" positionVCF="3835081" referenceAlleleVCF="G" alternateAlleleVCF="GA"/>

        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 3835081, "G", "GA", allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("ins"));

        // <SequenceLocation Assembly="GRCh38" AssemblyAccessionVersion="GCF_000001405.33" AssemblyStatus="current" Chr="1"
        // Accession="NC_000001.11" start="7663429" stop="7663430" display_start="7663429" display_stop="7663430" variantLength="2"
        // referenceAllele="-" alternateAllele="CG" positionVCF="7663429" referenceAlleleVCF="A" alternateAlleleVCF="ACG"/>

        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 7663429, "", "CG", allVariantTypes);
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

        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 48422024, "AT", "", allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("del"));

        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 48422024, "AT", null, allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("del"));

        // <SequenceLocation Assembly="GRCh38" AssemblyAccessionVersion="GCF_000001405.33" AssemblyStatus="current" Chr="1"
        // Accession="NC_000001.11" start="1043224" stop="1043225" display_start="1043224" display_stop="1043225" variantLength="2"
        // referenceAllele="CT" alternateAllele="-" positionVCF="1043223" referenceAlleleVCF="CCT" alternateAlleleVCF="C"/>
        locVar = LocatedVariantFactory.create(genomeRef, genomeRefSeq, 1043224, "CT", null, allVariantTypes);
        logger.info(locVar.toString());
        assertTrue(locVar.getVariantType().getId().equals("del"));

    }

}
