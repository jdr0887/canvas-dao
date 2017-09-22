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

        File f = new File(LocatedVariantFactoryTest.class.getClassLoader().getResource("complex.vcf").toURI());

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

        // assertTrue(locatedVariantList.size() == 10);
        //
        // // NC_000001.11 827209 . GGCC CGCG
        // logger.info(locatedVariantList.get(0).toString());
        // assertTrue(locatedVariantList.get(0).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(0).getRef().equals("GGCC"));
        // assertTrue(locatedVariantList.get(0).getSeq().equals("CGCG"));
        // assertTrue(locatedVariantList.get(0).getPosition().equals(827209));
        // assertTrue(locatedVariantList.get(0).getEndPosition().equals(827213));
        //
        // // NC_000001.11 964557 . GGCGCG TCCGCA
        // logger.info(locatedVariantList.get(1).toString());
        // assertTrue(locatedVariantList.get(1).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(1).getRef().equals("GGCGCG"));
        // assertTrue(locatedVariantList.get(1).getSeq().equals("TCCGCA"));
        // assertTrue(locatedVariantList.get(1).getPosition().equals(964557));
        // assertTrue(locatedVariantList.get(1).getEndPosition().equals(964563));
        //
        // // NC_000001.11 1714635 . ATGG GTGT
        // logger.info(locatedVariantList.get(2).toString());
        // assertTrue(locatedVariantList.get(2).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(2).getRef().equals("ATGG"));
        // assertTrue(locatedVariantList.get(2).getSeq().equals("GTGT"));
        // assertTrue(locatedVariantList.get(2).getPosition().equals(1714635));
        // assertTrue(locatedVariantList.get(2).getEndPosition().equals(1714639));
        //
        // // NC_000001.11 1722625 . AAGCGT TAT
        // logger.info(locatedVariantList.get(3).toString());
        // assertTrue(locatedVariantList.get(3).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(3).getRef().equals("AAGCG"));
        // assertTrue(locatedVariantList.get(3).getSeq().equals("TA"));
        // assertTrue(locatedVariantList.get(3).getPosition().equals(1722625));
        // assertTrue(locatedVariantList.get(3).getEndPosition().equals(1722630));
        //
        // // NC_000001.11 3830021 . TACTC GACTG
        // logger.info(locatedVariantList.get(4).toString());
        // assertTrue(locatedVariantList.get(4).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(4).getRef().equals("TACTC"));
        // assertTrue(locatedVariantList.get(4).getSeq().equals("GACTG"));
        // assertTrue(locatedVariantList.get(4).getPosition().equals(3830021));
        // assertTrue(locatedVariantList.get(4).getEndPosition().equals(3830026));
        //
        // // NC_000001.11 7829964 . TGA GGG
        // logger.info(locatedVariantList.get(5).toString());
        // assertTrue(locatedVariantList.get(5).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(5).getRef().equals("TGA"));
        // assertTrue(locatedVariantList.get(5).getSeq().equals("GGG"));
        // assertTrue(locatedVariantList.get(5).getPosition().equals(7829964));
        // assertTrue(locatedVariantList.get(5).getEndPosition().equals(7829967));
        //
        // // NC_000001.11 7969445 . GGGGGG TGGGAA
        // logger.info(locatedVariantList.get(6).toString());
        // assertTrue(locatedVariantList.get(6).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(6).getRef().equals("GGGGGG"));
        // assertTrue(locatedVariantList.get(6).getSeq().equals("TGGGAA"));
        // assertTrue(locatedVariantList.get(6).getPosition().equals(7969445));
        // assertTrue(locatedVariantList.get(6).getEndPosition().equals(7969451));
        //
        // // NC_000001.11 10411465 . GATAGAGTATGGG CATCGAATACGGC
        // logger.info(locatedVariantList.get(7).toString());
        // assertTrue(locatedVariantList.get(7).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(7).getRef().equals("GATAGAGTATGGG"));
        // assertTrue(locatedVariantList.get(7).getSeq().equals("CATCGAATACGGC"));
        // assertTrue(locatedVariantList.get(7).getPosition().equals(10411465));
        // assertTrue(locatedVariantList.get(7).getEndPosition().equals(10411478));
        //
        // // NC_000001.11 11764518 . TCTC CCTT
        // logger.info(locatedVariantList.get(8).toString());
        // assertTrue(locatedVariantList.get(8).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(8).getRef().equals("TCTC"));
        // assertTrue(locatedVariantList.get(8).getSeq().equals("CCTT"));
        // assertTrue(locatedVariantList.get(8).getPosition().equals(11764518));
        // assertTrue(locatedVariantList.get(8).getEndPosition().equals(11764522));
        //
        // // NC_000001.11 12838922 . CACT GACA
        // logger.info(locatedVariantList.get(9).toString());
        // assertTrue(locatedVariantList.get(9).getVariantType().getId().equals("sub"));
        // assertTrue(locatedVariantList.get(9).getRef().equals("CACT"));
        // assertTrue(locatedVariantList.get(9).getSeq().equals("GACA"));
        // assertTrue(locatedVariantList.get(9).getPosition().equals(12838922));
        // assertTrue(locatedVariantList.get(9).getEndPosition().equals(12838926));
        //
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

        // assertTrue(locatedVariantList.size() == 10);
        //
        // logger.info(locatedVariantList.get(0).toString());
        // assertTrue(locatedVariantList.get(0).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(0).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(0).getSeq().equals("C"));
        // assertTrue(locatedVariantList.get(0).getPosition().equals(13273));
        // assertTrue(locatedVariantList.get(0).getEndPosition().equals(13274));
        //
        // logger.info(locatedVariantList.get(1).toString());
        // assertTrue(locatedVariantList.get(1).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(1).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(1).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(1).getPosition().equals(137825));
        // assertTrue(locatedVariantList.get(1).getEndPosition().equals(137826));
        //
        // logger.info(locatedVariantList.get(2).toString());
        // assertTrue(locatedVariantList.get(2).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(2).getRef().equals("C"));
        // assertTrue(locatedVariantList.get(2).getSeq().equals("T"));
        // assertTrue(locatedVariantList.get(2).getPosition().equals(138080));
        // assertTrue(locatedVariantList.get(2).getEndPosition().equals(138081));
        //
        // logger.info(locatedVariantList.get(3).toString());
        // assertTrue(locatedVariantList.get(3).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(3).getRef().equals("A"));
        // assertTrue(locatedVariantList.get(3).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(3).getPosition().equals(139213));
        // assertTrue(locatedVariantList.get(3).getEndPosition().equals(139214));
        //
        // logger.info(locatedVariantList.get(4).toString());
        // assertTrue(locatedVariantList.get(4).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(4).getRef().equals("C"));
        // assertTrue(locatedVariantList.get(4).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(4).getPosition().equals(139233));
        // assertTrue(locatedVariantList.get(4).getEndPosition().equals(139234));
        //
        // logger.info(locatedVariantList.get(5).toString());
        // assertTrue(locatedVariantList.get(5).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(5).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(5).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(5).getPosition().equals(139781));
        // assertTrue(locatedVariantList.get(5).getEndPosition().equals(139782));
        //
        // logger.info(locatedVariantList.get(6).toString());
        // assertTrue(locatedVariantList.get(6).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(6).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(6).getSeq().equals("C"));
        // assertTrue(locatedVariantList.get(6).getPosition().equals(727717));
        // assertTrue(locatedVariantList.get(6).getEndPosition().equals(727718));
        //
        // logger.info(locatedVariantList.get(7).toString());
        // assertTrue(locatedVariantList.get(7).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(7).getRef().equals("T"));
        // assertTrue(locatedVariantList.get(7).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(7).getPosition().equals(729454));
        // assertTrue(locatedVariantList.get(7).getEndPosition().equals(729455));
        //
        // logger.info(locatedVariantList.get(8).toString());
        // assertTrue(locatedVariantList.get(8).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(8).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(8).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(8).getPosition().equals(826893));
        // assertTrue(locatedVariantList.get(8).getEndPosition().equals(826894));
        //
        // logger.info(locatedVariantList.get(9).toString());
        // assertTrue(locatedVariantList.get(9).getVariantType().getId().equals("snp"));
        // assertTrue(locatedVariantList.get(9).getRef().equals("T"));
        // assertTrue(locatedVariantList.get(9).getSeq().equals("C"));
        // assertTrue(locatedVariantList.get(9).getPosition().equals(827221));
        // assertTrue(locatedVariantList.get(9).getEndPosition().equals(827222));

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

        // assertTrue(locatedVariantList.size() == 9);
        //
        // // NC_000001.11 1387763 . CCTG CG
        // logger.info(locatedVariantList.get(0).toString());
        // assertTrue(locatedVariantList.get(0).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(0).getRef().equals("CT"));
        // assertTrue(locatedVariantList.get(0).getSeq().equals("CT"));
        // assertTrue(locatedVariantList.get(0).getPosition().equals(1387764));
        // assertTrue(locatedVariantList.get(0).getEndPosition().equals(1387766));
        //
        // // NC_000001.11 1629572 . TGGGGGGC TGGGGGC
        // logger.info(locatedVariantList.get(1).toString());
        // assertTrue(locatedVariantList.get(1).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(1).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(1).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(1).getPosition().equals(1629578));
        // assertTrue(locatedVariantList.get(1).getEndPosition().equals(1629579));
        //
        // // NC_000001.11 1714399 . CAA CA
        // logger.info(locatedVariantList.get(2).toString());
        // assertTrue(locatedVariantList.get(2).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(2).getRef().equals("A"));
        // assertTrue(locatedVariantList.get(2).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(2).getPosition().equals(1714401));
        // assertTrue(locatedVariantList.get(2).getEndPosition().equals(1714402));
        //
        // // NC_000001.11 1955652 . CGC CC
        // logger.info(locatedVariantList.get(3).toString());
        // assertTrue(locatedVariantList.get(3).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(3).getRef().equals("G"));
        // assertTrue(locatedVariantList.get(3).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(3).getPosition().equals(1955653));
        // assertTrue(locatedVariantList.get(3).getEndPosition().equals(1955654));
        //
        // // NC_000001.11 2505160 . GCC GC
        // logger.info(locatedVariantList.get(4).toString());
        // assertTrue(locatedVariantList.get(4).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(4).getRef().equals("C"));
        // assertTrue(locatedVariantList.get(4).getSeq().equals("C"));
        // assertTrue(locatedVariantList.get(4).getPosition().equals(2505162));
        // assertTrue(locatedVariantList.get(4).getEndPosition().equals(2505163));
        //
        // // NC_000001.11 3479409 . ACCCCCA ACCCA
        // logger.info(locatedVariantList.get(5).toString());
        // assertTrue(locatedVariantList.get(5).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(5).getRef().equals("CC"));
        // assertTrue(locatedVariantList.get(5).getSeq().equals("CC"));
        // assertTrue(locatedVariantList.get(5).getPosition().equals(3479413));
        // assertTrue(locatedVariantList.get(5).getEndPosition().equals(3479415));
        //
        // // NC_000001.11 3627671 . TAA TA
        // logger.info(locatedVariantList.get(6).toString());
        // assertTrue(locatedVariantList.get(6).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(6).getRef().equals("A"));
        // assertTrue(locatedVariantList.get(6).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(6).getPosition().equals(3627673));
        // assertTrue(locatedVariantList.get(6).getEndPosition().equals(3627674));
        //
        // // NC_000001.11 3628611 . GTTCTGGGAGCTCCTCCCCCT GT
        // logger.info(locatedVariantList.get(7).toString());
        // assertTrue(locatedVariantList.get(7).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(7).getRef().equals("TCTGGGAGCTCCTCCCCCT"));
        // assertTrue(locatedVariantList.get(7).getSeq().equals("TCTGGGAGCTCCTCCCCCT"));
        // assertTrue(locatedVariantList.get(7).getPosition().equals(3628613));
        // // assertTrue(locatedVariantList.get(7).getEndPosition().equals());
        //
        // // NC_000001.11 5969026 . CAAAAAAAAAAAAAAAG CAAAAAAAAAAAAAAG
        // logger.info(locatedVariantList.get(8).toString());
        // assertTrue(locatedVariantList.get(8).getVariantType().getId().equals("del"));
        // assertTrue(locatedVariantList.get(8).getRef().equals("A"));
        // assertTrue(locatedVariantList.get(8).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(8).getPosition().equals(5969041));
        // assertTrue(locatedVariantList.get(8).getEndPosition().equals(5969042));

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

        // assertTrue(locatedVariantList.size() == 10);
        //
        // // NC_000001.11 13417 . CGAGAT CGAGAGAGAT
        // logger.info(locatedVariantList.get(0).toString());
        // assertTrue(locatedVariantList.get(0).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(0).getRef().equals(""));
        // assertTrue(locatedVariantList.get(0).getSeq().equals("GAGA"));
        // assertTrue(locatedVariantList.get(0).getPosition().equals(13421));
        // assertTrue(locatedVariantList.get(0).getEndPosition().equals(13422));
        //
        // // NC_000001.11 826577 . AC ATC
        // logger.info(locatedVariantList.get(1).toString());
        // assertTrue(locatedVariantList.get(1).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(1).getRef().equals(""));
        // assertTrue(locatedVariantList.get(1).getSeq().equals("T"));
        // assertTrue(locatedVariantList.get(1).getPosition().equals(826577));
        // assertTrue(locatedVariantList.get(1).getEndPosition().equals(826578));
        //
        // // NC_000001.11 1013466 . TAT TAAT
        // logger.info(locatedVariantList.get(2).toString());
        // assertTrue(locatedVariantList.get(2).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(2).getRef().equals(""));
        // assertTrue(locatedVariantList.get(2).getSeq().equals("A"));
        // assertTrue(locatedVariantList.get(2).getPosition().equals(1013467));
        // assertTrue(locatedVariantList.get(2).getEndPosition().equals(1013468));
        //
        // // NC_000001.11 1341593 . GACACA GACACACACA
        // logger.info(locatedVariantList.get(3).toString());
        // assertTrue(locatedVariantList.get(3).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(3).getRef().equals(""));
        // assertTrue(locatedVariantList.get(3).getSeq().equals("CACA"));
        // assertTrue(locatedVariantList.get(3).getPosition().equals(1341598));
        // assertTrue(locatedVariantList.get(3).getEndPosition().equals(1341599));
        //
        // // NC_000001.11 1752908 . CCCTCCTCCTCCTCCTCCTCCTCCTCCTC CCCTCCTCCTCCTCCTCCTCCTCCTCCTCCTC
        // logger.info(locatedVariantList.get(4).toString());
        // assertTrue(locatedVariantList.get(4).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(4).getRef().equals(""));
        // assertTrue(locatedVariantList.get(4).getSeq().equals("CTC"));
        // assertTrue(locatedVariantList.get(4).getPosition().equals(1752936));
        // assertTrue(locatedVariantList.get(4).getEndPosition().equals(1752937));
        //
        // // NC_000001.11 1968667 . TC TCTCC
        // logger.info(locatedVariantList.get(5).toString());
        // assertTrue(locatedVariantList.get(5).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(5).getRef().equals(""));
        // assertTrue(locatedVariantList.get(5).getSeq().equals("TCC"));
        // assertTrue(locatedVariantList.get(5).getPosition().equals(1968668));
        // assertTrue(locatedVariantList.get(5).getEndPosition().equals(1968669));
        //
        // // NC_000001.11 2526686 . GCC GCCC
        // logger.info(locatedVariantList.get(6).toString());
        // assertTrue(locatedVariantList.get(6).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(6).getRef().equals(""));
        // assertTrue(locatedVariantList.get(6).getSeq().equals("C"));
        // assertTrue(locatedVariantList.get(6).getPosition().equals(2526688));
        // assertTrue(locatedVariantList.get(6).getEndPosition().equals(2526689));
        //
        // // NC_000001.11 2598902 . AGT AGGT
        // logger.info(locatedVariantList.get(7).toString());
        // assertTrue(locatedVariantList.get(7).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(7).getRef().equals(""));
        // assertTrue(locatedVariantList.get(7).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(7).getPosition().equals(2598903));
        // assertTrue(locatedVariantList.get(7).getEndPosition().equals(2598904));
        //
        // // NC_000001.11 3625542 . TGGGGGGA TGGGGGGGA
        // logger.info(locatedVariantList.get(8).toString());
        // assertTrue(locatedVariantList.get(8).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(8).getRef().equals(""));
        // assertTrue(locatedVariantList.get(8).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(8).getPosition().equals(3625548));
        // assertTrue(locatedVariantList.get(8).getEndPosition().equals(3625549));
        //
        // // NC_000001.11 3826869 . TGG TGGG
        // logger.info(locatedVariantList.get(9).toString());
        // assertTrue(locatedVariantList.get(9).getVariantType().getId().equals("ins"));
        // assertTrue(locatedVariantList.get(9).getRef().equals(""));
        // assertTrue(locatedVariantList.get(9).getSeq().equals("G"));
        // assertTrue(locatedVariantList.get(9).getPosition().equals(3826871));
        // assertTrue(locatedVariantList.get(9).getEndPosition().equals(3826872));

    }
}
