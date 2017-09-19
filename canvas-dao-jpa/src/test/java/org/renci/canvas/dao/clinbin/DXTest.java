package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.clinbin.model.DX;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticGeneGroupVersionPK;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.jpa.annotation.AnnotationGeneDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DXDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticGeneDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticGeneGroupVersionDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticResultVersionDAOImpl;

public class DXTest {

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
    public void testFindByParticipantAndListVersion() {
        DXDAOImpl dxDAO = new DXDAOImpl();
        dxDAO.setEntityManager(em);
        try {
            List<DX> results = dxDAO.findAll();
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByName() {
        DXDAOImpl dxDAO = new DXDAOImpl();
        dxDAO.setEntityManager(em);
        try {
            List<DX> results = dxDAO.findByName("Choice Childhood-onset Nonactionable");
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadDiagnosticGene() throws CANVASDAOException {

        DXDAOImpl dxDAO = new DXDAOImpl();
        dxDAO.setEntityManager(em);

        DiagnosticGeneDAOImpl diagnosticGeneDAO = new DiagnosticGeneDAOImpl();
        diagnosticGeneDAO.setEntityManager(em);

        DiagnosticResultVersionDAOImpl diagnosticResultVersionDAO = new DiagnosticResultVersionDAOImpl();
        diagnosticResultVersionDAO.setEntityManager(em);

        AnnotationGeneDAOImpl annotationGeneDAO = new AnnotationGeneDAOImpl();
        annotationGeneDAO.setEntityManager(em);

        DiagnosticGeneGroupVersionDAOImpl diagnosticGeneGroupVersionDAO = new DiagnosticGeneGroupVersionDAOImpl();
        diagnosticGeneGroupVersionDAO.setEntityManager(em);

        // Arrays.asList("Choice Adult-onset Actionable", "Choice Childhood-onset Nonactionable", "Hearing Loss",
        // "Inborn Errors of Metabolism", "NGS-NBS", "Pulmonary").forEach(a -> {
        // try {
        // em.getTransaction().begin();
        // dxDAO.save(new DX(a));
        // em.getTransaction().commit();
        // } catch (CANVASDAOException e) {
        // e.printStackTrace();
        // }
        // });

        Integer maxDiagnosticBinGroupVersion = diagnosticGeneGroupVersionDAO.findMaxDiagnosticBinGroupVersion();
        if (maxDiagnosticBinGroupVersion == null) {
            maxDiagnosticBinGroupVersion = 0;
        }

        // TODO: how is this determined?
        Integer diagnosticListVersion = 1;

        List<DX> allDXs = dxDAO.findAll();

        DX dx = allDXs.stream().filter(a -> a.getName().equals("Choice Adult-onset Actionable")).findAny().get();

        List<DiagnosticGeneGroupVersion> diagnosticGeneGroupVersions = diagnosticGeneGroupVersionDAO
                .findByDBinGroupVersion(maxDiagnosticBinGroupVersion);

        ++maxDiagnosticBinGroupVersion;

        // List<String> data = Arrays.asList("BRCA1,AD,1", "BRCA2,AD,1", "BRIP1,AD,1", "CDH1,AD,1", "COCH,AD,1", "CP,AR,1", "EPCAM,CX,1",
        // "EYA4,AD,1", "FH,AD,1", "GJA5,AD,1", "HFE,AR,1", "KRIT1,AD,1", "MLH1,AD,1", "MSH2,AD,1", "MSH6,AD,1", "MUTYH,AR,1",
        // "NF2,AD,1", "PALB2,AD,1", "PMS2,AD,1", "POLD1,AD,1", "POLE,AD,1", "SERPINA1,AR,1", "SERPINB6,AR,1", "SLC40A1,AD,1");
        //
        // for (String a : data) {
        //
        // System.out.println(a);
        //
        // String[] dataSplit = a.split(",");
        // String gene = dataSplit[0].trim();
        // List<AnnotationGene> genes = annotationGeneDAO.findByName(gene);
        // if (CollectionUtils.isEmpty(genes)) {
        // System.out.printf("Gene not found: %s\n", gene);
        // return;
        // }
        //
        // AnnotationGene annotationGene = genes.get(0);
        // System.out.println(annotationGene.toString());
        //
        // String inheritance = dataSplit[1];
        // String tier = dataSplit[2];
        //
        // DiagnosticGene diagnosticGene = new DiagnosticGene(annotationGene, diagnosticListVersion, dx, tier, inheritance);
        //
        // List<DiagnosticGene> foundDiagnosticGenes = diagnosticGeneDAO.findByExample(diagnosticGene);
        // if (CollectionUtils.isEmpty(foundDiagnosticGenes)) {
        // em.getTransaction().begin();
        // diagnosticGene.setId(diagnosticGeneDAO.save(diagnosticGene));
        // em.getTransaction().commit();
        // } else {
        // diagnosticGene = foundDiagnosticGenes.get(0);
        // }
        //
        // }

        for (DiagnosticGeneGroupVersion diagnosticGeneGroupVersion : diagnosticGeneGroupVersions) {

            DiagnosticGeneGroupVersionPK diagnosticGeneGroupVersionPK = new DiagnosticGeneGroupVersionPK(maxDiagnosticBinGroupVersion,
                    diagnosticGeneGroupVersion.getDx().getId(), diagnosticGeneGroupVersion.getId().getDiagnosticListVersion());
            DiagnosticGeneGroupVersion newDiagnosticGeneGroupVersion = new DiagnosticGeneGroupVersion(diagnosticGeneGroupVersionPK);
            newDiagnosticGeneGroupVersion.setDx(diagnosticGeneGroupVersion.getDx());
            em.getTransaction().begin();
            newDiagnosticGeneGroupVersion.setId(diagnosticGeneGroupVersionDAO.save(newDiagnosticGeneGroupVersion));
            em.getTransaction().commit();
        }

        DiagnosticGeneGroupVersionPK diagnosticGeneGroupVersionPK = new DiagnosticGeneGroupVersionPK(maxDiagnosticBinGroupVersion,
                dx.getId(), diagnosticListVersion);
        DiagnosticGeneGroupVersion newDiagnosticGeneGroupVersion = new DiagnosticGeneGroupVersion(diagnosticGeneGroupVersionPK);
        newDiagnosticGeneGroupVersion.setDx(dx);
        em.getTransaction().begin();
        newDiagnosticGeneGroupVersion.setId(diagnosticGeneGroupVersionDAO.save(newDiagnosticGeneGroupVersion));
        em.getTransaction().commit();

    }

}
