package org.renci.canvas.dao.var;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic;
import org.renci.canvas.dao.jpa.clinbin.BinResultsFinalDiagnosticDAOImpl;
import org.renci.canvas.dao.jpa.ref.GenomeRefDAOImpl;
import org.renci.canvas.dao.jpa.ref.GenomeRefSeqDAOImpl;
import org.renci.canvas.dao.jpa.refseq.Variants_80_4_DAOImpl;
import org.renci.canvas.dao.jpa.var.CanonicalAlleleDAOImpl;
import org.renci.canvas.dao.jpa.var.LocatedVariantDAOImpl;
import org.renci.canvas.dao.jpa.var.VariantTypeDAOImpl;
import org.renci.canvas.dao.refseq.model.Variants_80_4;
import org.renci.canvas.dao.var.model.CanonicalAllele;
import org.renci.canvas.dao.var.model.LocatedVariant;

public class LocatedVariantTest {

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
    public void testFindByGeneSymbol() throws CANVASDAOException {
        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);
        List<LocatedVariant> LocatedVariantList = locatedVariantDAO.findByGeneSymbol("BRCA1");
        LocatedVariantList.forEach(a -> System.out.println(a.toString()));
    }

    @Test
    public void testFindByExample() throws CANVASDAOException {
        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        GenomeRefDAOImpl genomeRefDAO = new GenomeRefDAOImpl();
        genomeRefDAO.setEntityManager(em);

        GenomeRefSeqDAOImpl genomeRefSeqDAO = new GenomeRefSeqDAOImpl();
        genomeRefSeqDAO.setEntityManager(em);

        VariantTypeDAOImpl variantTypeDAO = new VariantTypeDAOImpl();
        variantTypeDAO.setEntityManager(em);

        
        LocatedVariant locatedVariant = new LocatedVariant(genomeRefDAO.findById(4), genomeRefSeqDAO.findById("NC_000005.10"));
        locatedVariant.setVariantType(variantTypeDAO.findById("snp"));
        locatedVariant.setPosition(13841077);
        locatedVariant.setEndPosition(13841078);
        locatedVariant.setRef("A");
        locatedVariant.setSeq("C");

        List<LocatedVariant> locatedVariantList = locatedVariantDAO.findByExample(locatedVariant);
        assertTrue(CollectionUtils.isNotEmpty(locatedVariantList));
    }

    @Test
    public void testConstraint() throws CANVASDAOException {
        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        GenomeRefDAOImpl genomeRefDAO = new GenomeRefDAOImpl();
        genomeRefDAO.setEntityManager(em);

        GenomeRefSeqDAOImpl genomeRefSeqDAO = new GenomeRefSeqDAOImpl();
        genomeRefSeqDAO.setEntityManager(em);

        VariantTypeDAOImpl variantTypeDAO = new VariantTypeDAOImpl();
        variantTypeDAO.setEntityManager(em);

        LocatedVariant locatedVariant = new LocatedVariant(genomeRefDAO.findById(2), genomeRefSeqDAO.findById("NC_000001.11"));
        locatedVariant.setVariantType(variantTypeDAO.findById("snp"));
        locatedVariant.setPosition(123);
        locatedVariant.setEndPosition(126);
        em.getTransaction().begin();
        locatedVariantDAO.save(locatedVariant);
        em.getTransaction().commit();

    }

    @Test
    public void testIncrementable() throws CANVASDAOException {
        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        long startTime = System.currentTimeMillis();
        List<LocatedVariant> locatedVariantList = locatedVariantDAO.findIncrementable(35619);
        long endTime = System.currentTimeMillis();
        System.out.printf("duration = %d", (endTime - startTime) / 1000);
        File out = new File("/tmp", "out.txt");

        locatedVariantList.sort((a, b) -> {
            int ret = a.getGenomeRefSeq().getId().compareTo(b.getGenomeRefSeq().getId());
            if (ret == 0) {
                ret = a.getPosition().compareTo(b.getPosition());
            }
            return ret;
        });

        try (FileWriter outFW = new FileWriter(out); BufferedWriter outBW = new BufferedWriter(outFW)) {
            for (LocatedVariant LocatedVariant : locatedVariantList) {
                outBW.write(String.format("%d\t%s\t%d\t%s\t%s\t%d\t%s%n", LocatedVariant.getId(), LocatedVariant.getGenomeRefSeq().getId(),
                        LocatedVariant.getPosition(), LocatedVariant.getVariantType().getId(), LocatedVariant.getSeq(),
                        LocatedVariant.getEndPosition(), LocatedVariant.getRef()));
                outBW.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void scratch() throws CANVASDAOException {

        LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        locatedVariantDAO.setEntityManager(em);

        CanonicalAlleleDAOImpl canonicalAlleleDAO = new CanonicalAlleleDAOImpl();
        canonicalAlleleDAO.setEntityManager(em);

        BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
        binResultsFinalDiagnosticDAO.setEntityManager(em);

        Variants_80_4_DAOImpl variants_80_4_DAO = new Variants_80_4_DAOImpl();
        variants_80_4_DAO.setEntityManager(em);

        List<Long> locatedVariantIds = Arrays.asList(27584997L, 384486715L, 29318810L, 53635936L, 54268065L, 36461447L, 20027549L,
                16668437L, 42892652L, 27585134L, 31206141L, 29780809L, 24626632L, 45052253L, 41364947L, 44934597L, 41367349L, 40369226L,
                46960999L, 16668832L, 384463447L, 46860871L, 25153149L, 43420147L, 29779165L, 18665190L, 492029457L, 27585080L, 42890194L,
                25153012L, 26484736L, 49389282L, 31206211L, 45055434L, 25152947L, 33664118L, 19487849L, 18665274L, 31206241L, 29516269L,
                31205895L, 35077221L, 29165807L, 49389256L, 27585209L, 42891525L, 29806534L, 27515396L, 29165680L, 20128553L, 20128626L,
                33756371L, 27585047L, 26211388L, 439939526L, 26211441L, 29318551L, 49389264L, 45051765L, 49389263L, 49389279L, 17876559L,
                29165811L, 26211485L, 31206282L, 383928716L, 38392818L, 41125460L, 20027390L, 29318436L, 29318433L, 398628315L, 54273090L,
                46961040L, 488387557L, 24626599L, 18665218L, 384624446L, 27515397L, 33661593L, 492029224L, 21759663L, 40369432L, 52544466L,
                27585083L, 488381841L, 384593861L, 41365497L, 16668435L, 44933744L, 18262324L, 42891641L, 29806632L, 25152820L, 46860899L,
                24626634L, 424345902L, 38780289L, 21759665L, 44934333L, 42888928L, 53635929L, 24626680L, 38096255L, 20303927L, 17784672L,
                29779977L, 27585222L, 46961027L, 17787540L, 24626610L, 492028138L, 484313296L, 42872302L, 31205161L, 44457805L, 45051874L,
                42873507L, 33756370L, 38095493L, 33756354L, 36460627L, 46961196L, 52544815L, 377944044L, 44458465L, 27585006L, 43420225L,
                46961025L, 27585057L, 49834122L, 42891288L, 29318549L, 46961147L, 38780097L, 492028788L, 52544518L, 29779114L, 17876474L,
                29165676L, 43419942L, 27585232L, 41368694L, 19488114L, 27585004L, 401595375L, 21758044L, 24626602L, 17876473L, 43420151L,
                41365778L, 54267941L, 35077238L, 18262432L, 44934627L, 53635135L, 488384857L, 45053285L, 43420152L, 17876554L, 17876617L,
                45050685L, 35077140L, 384291564L, 488380797L, 38398864L, 21757373L, 41367316L, 31796482L, 29165802L, 16668648L, 36460141L,
                492029738L, 31206213L, 21758993L, 40369426L, 29779329L, 488384043L, 33756395L, 20027556L, 41367343L, 44457987L, 44934377L,
                20128472L, 27585008L, 384607459L, 390090961L, 17791048L, 16668201L, 52544817L, 383953851L, 29779976L, 42871940L, 25100399L,
                31693248L, 44457905L, 27585001L, 384305971L, 41368994L, 43420167L, 44934667L, 21759844L, 33756367L, 36460214L, 27477117L,
                385164965L, 35077222L, 18665267L, 32126486L, 383935752L, 42891179L, 379662357L, 10250960L, 40937584L, 43420168L, 27585051L,
                24626563L, 44934596L, 41368961L, 49389310L, 31206197L, 20027294L, 44934262L, 29318550L, 46860900L, 383902513L, 20128655L,
                53635505L, 31206051L, 38096400L, 24626702L, 20304101L, 44458467L, 27585062L, 46961004L, 20027346L, 24626720L, 27585126L,
                39478933L, 52544464L, 36460171L, 18321867L, 36460175L, 25153104L, 20027939L, 27476175L, 42890493L, 492028320L, 488381893L,
                35077233L, 42892480L, 488381022L, 29779258L, 25152863L, 19488127L, 44458113L, 488406215L, 16668439L, 20128551L, 27585138L,
                42876162L, 17876680L, 27584988L, 32775904L, 53635926L, 10084134L, 17737656L, 385164964L, 488384565L, 38779454L, 29779495L,
                42893585L, 21759396L, 384197554L, 20304100L, 44934327L, 52544812L, 27585003L, 40369151L, 44934395L, 31206030L, 384274303L,
                49389121L, 25152791L, 33756211L, 25152823L, 41368604L, 45050978L, 18262167L, 488380671L, 35077374L, 54268109L, 384182982L,
                29318440L, 33756398L, 21758021L, 17876478L, 492028846L, 27585002L, 29780249L, 24626741L, 35077371L, 27585096L, 21758994L,
                49834975L, 45056024L, 29318443L, 52544525L, 29806525L, 40369296L, 38779906L, 38779182L, 44458468L, 21760533L, 23692082L,
                38095804L, 20303872L, 18262166L, 27585123L, 42890430L, 31205336L, 18262414L, 384040989L, 29318441L, 46960928L, 17876591L,
                29165830L, 393164083L, 31273040L, 45052118L, 41368689L, 20128555L, 36460153L, 384035736L, 24626607L, 44934397L, 45052115L,
                43420074L, 42891314L, 377901558L, 49834355L, 29780037L, 38779181L, 21758020L, 54267974L, 29165809L, 33664036L, 377975896L,
                17876557L, 38779479L, 17876449L, 49834356L, 385385815L, 47421094L, 26211407L, 17876498L, 38096434L, 29231462L, 52544519L,
                492030168L, 45051615L, 25152834L, 46860886L, 40937633L, 31205658L, 40369431L, 44934394L, 29165812L, 24626700L, 29780014L);

        // List<Long> locatedVariantIds = Arrays.asList(27584997L, 384486715L, 29318810L, 53635936L, 54268065L, 36461447L, 20027549L,
        // 16668437L, 42892652L, 27585134L, 31206141L, 29780809L, 24626632L, 45052253L, 41364947L, 44934597L, 41367349L, 40369226L,
        // 46960999L, 16668832L, 384463447L, 46860871L, 25153149L, 43420147L, 29779165L, 18665190L, 492029457L, 27585080L, 42890194L,
        // 25153012L, 26484736L, 49389282L, 31206211L, 45055434L, 25152947L, 33664118L, 19487849L, 18665274L, 31206241L, 29516269L,
        // 31205895L, 35077221L, 29165807L, 49389256L, 27585209L, 42891525L, 29806534L, 27515396L, 29165680L, 20128553L, 20128626L,
        // 33756371L, 27585047L, 26211388L, 439939526L, 26211441L, 29318551L, 49389264L, 45051765L, 49389263L, 49389279L, 17876559L,
        // 29165811L, 26211485L, 31206282L, 383928716L, 38392818L, 41125460L, 20027390L, 29318436L, 29318433L, 398628315L, 54273090L,
        // 46961040L, 488387557L, 24626599L, 18665218L, 384624446L, 27515397L, 33661593L, 492029224L, 21759663L, 40369432L, 52544466L,
        // 27585083L, 488381841L, 384593861L, 41365497L, 16668435L, 44933744L, 18262324L, 42891641L, 29806632L, 25152820L, 46860899L,
        // 24626634L, 424345902L, 38780289L, 21759665L, 44934333L, 42888928L, 53635929L, 24626680L, 38096255L, 20303927L, 17784672L,
        // 29779977L, 27585222L, 46961027L, 17787540L, 24626610L, 492028138L, 484313296L, 42872302L, 31205161L, 44457805L, 45051874L,
        // 42873507L, 33756370L, 38095493L, 33756354L, 36460627L, 46961196L, 52544815L, 377944044L, 44458465L, 27585006L, 43420225L,
        // 46961025L, 27585057L, 49834122L, 42891288L, 29318549L, 46961147L, 38780097L, 492028788L, 52544518L, 29779114L, 17876474L,
        // 29165676L, 43419942L, 27585232L, 41368694L, 19488114L, 27585004L, 401595375L, 21758044L, 24626602L, 17876473L, 43420151L,
        // 41365778L, 54267941L, 35077238L, 18262432L, 44934627L, 53635135L, 488384857L, 45053285L, 43420152L, 17876554L, 17876617L,
        // 45050685L, 35077140L, 384291564L, 488380797L, 38398864L, 21757373L, 41367316L, 31796482L, 29165802L, 16668648L, 36460141L,
        // 492029738L, 31206213L, 21758993L, 40369426L, 29779329L, 488384043L, 33756395L, 20027556L, 41367343L, 44457987L, 44934377L,
        // 20128472L, 27585008L, 384607459L, 390090961L, 17791048L, 16668201L, 52544817L, 383953851L, 29779976L, 42871940L, 25100399L,
        // 31693248L, 44457905L, 27585001L, 384305971L, 41368994L, 43420167L, 44934667L, 21759844L, 33756367L, 36460214L, 27477117L,
        // 385164965L, 35077222L, 18665267L, 32126486L, 383935752L, 42891179L, 379662357L, 10250960L, 40937584L, 43420168L, 27585051L,
        // 24626563L, 44934596L, 41368961L, 49389310L, 31206197L, 20027294L, 44934262L, 29318550L, 46860900L, 383902513L, 20128655L,
        // 53635505L, 31206051L, 38096400L, 24626702L, 20304101L, 44458467L, 27585062L, 46961004L, 20027346L, 24626720L, 27585126L,
        // 39478933L, 52544464L, 36460171L, 18321867L, 36460175L, 25153104L, 20027939L, 27476175L, 42890493L, 492028320L, 488381893L,
        // 35077233L, 42892480L, 488381022L, 29779258L, 25152863L, 19488127L, 44458113L, 488406215L, 16668439L, 20128551L, 27585138L,
        // 42876162L, 17876680L, 27584988L, 32775904L, 53635926L, 10084134L, 17737656L, 385164964L, 488384565L, 38779454L, 29779495L,
        // 42893585L, 21759396L, 384197554L, 20304100L, 44934327L, 52544812L, 27585003L, 40369151L, 44934395L, 31206030L, 384274303L,
        // 49389121L, 25152791L, 33756211L, 25152823L, 41368604L, 45050978L, 18262167L, 488380671L, 35077374L, 54268109L, 384182982L,
        // 29318440L, 33756398L, 21758021L, 17876478L, 492028846L, 27585002L, 29780249L, 24626741L, 35077371L, 27585096L, 21758994L,
        // 49834975L, 45056024L, 29318443L, 52544525L, 29806525L, 40369296L, 38779906L, 38779182L, 44458468L, 21760533L, 23692082L,
        // 38095804L, 20303872L, 18262166L, 27585123L, 42890430L, 31205336L, 18262414L, 384040989L, 29318441L, 46960928L, 17876591L,
        // 29165830L, 393164083L, 31273040L, 45052118L, 41368689L, 20128555L, 36460153L, 384035736L, 24626607L, 44934397L, 45052115L,
        // 43420074L, 42891314L, 377901558L, 49834355L, 29780037L, 38779181L, 21758020L, 54267974L, 29165809L, 33664036L, 377975896L,
        // 17876557L, 38779479L, 17876449L, 49834356L, 385385815L, 47421094L, 26211407L, 17876498L, 38096434L, 29231462L, 52544519L,
        // 492030168L, 45051615L, 25152834L, 46860886L, 40937633L, 31205658L, 40369431L, 44934394L, 29165812L, 24626700L, 29780014L,
        // 44934581L, 17876552L, 23691670L, 27585140L, 17876464L, 52544484L, 29779677L, 45051111L, 27585056L, 38780200L, 21760526L,
        // 24626788L, 396743607L, 42892370L, 29230678L);

        List<Long> missingFrom38 = new ArrayList<>();

        List<Long> missingFromResults = new ArrayList<>();

        for (Long locatedVariantId : locatedVariantIds) {

            List<CanonicalAllele> caList = canonicalAlleleDAO.findByLocatedVariantId(locatedVariantId);
            if (CollectionUtils.isNotEmpty(caList)) {
                CanonicalAllele ca = caList.get(0);
                Optional<LocatedVariant> optionalLocatedVariant38 = ca.getLocatedVariants().stream()
                        .filter(a -> a.getGenomeRef().getId().equals(4)).findFirst();
                if (!optionalLocatedVariant38.isPresent()) {
                    missingFrom38.add(locatedVariantId);
                    continue;
                }

                boolean isIntergenic = false;
                boolean isIntron = false;
                List<Variants_80_4> variants = variants_80_4_DAO.findByLocatedVariantId(optionalLocatedVariant38.get().getId());
                if (CollectionUtils.isNotEmpty(variants)) {
                    for (Variants_80_4 variant : variants) {
                        if (variant.getLocationType().getId().equals("intergenic")) {
                            isIntergenic = true;
                        }
                        if (variant.getLocationType().getId().equals("intron")) {
                            isIntron = true;
                        }
                    }
                }

                List<BinResultsFinalDiagnostic> results = binResultsFinalDiagnosticDAO
                        .findByLocatedVariantId(optionalLocatedVariant38.get().getId());
                if (CollectionUtils.isEmpty(results) && !isIntergenic && !isIntron) {
                    missingFromResults.add(locatedVariantId);
                }
            }

        }

        missingFrom38.forEach(a -> System.out.println(a));
        System.out.println("--------------");
        missingFromResults.forEach(a -> System.out.println(a));

    }

}
