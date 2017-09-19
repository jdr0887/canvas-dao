package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.BinResultsFinalDiagnostic;
import org.renci.canvas.dao.jpa.clinbin.BinResultsFinalDiagnosticDAOImpl;

public class BinResultsFinalDiagnosticDAOTest {

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
        try {
            BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
            binResultsFinalDiagnosticDAO.setEntityManager(em);

            List<BinResultsFinalDiagnostic> results = binResultsFinalDiagnosticDAO.findByDXIdAndParticipantAndVersion(22L, "NCG_00064", 16);
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindDXIdCount() {
        try {
            BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
            binResultsFinalDiagnosticDAO.setEntityManager(em);

            Long ret = binResultsFinalDiagnosticDAO.findDXIdCount("NCG_00064");
            assertTrue(ret != null);
            System.out.println(ret.toString());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAnalyzedVariantsCount() {
        try {
            BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
            binResultsFinalDiagnosticDAO.setEntityManager(em);

            // Long ret = binResultsFinalDiagnosticDAO.findAnalyzedVariantsCount("NCG_00064");
            Long ret = binResultsFinalDiagnosticDAO.findAnalyzedVariantsCount("NCX_00004");
            assertTrue(ret != null);
            System.out.println(ret.toString());
            
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByAssemblyIdAndHGMDDiseaseClassId() {
        try {
            BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
            binResultsFinalDiagnosticDAO.setEntityManager(em);
            
            Long class1 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 1);
            assertTrue(class1 != null);
            System.out.println(class1.toString());

            Long class2 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 2);
            assertTrue(class2 != null);
            System.out.println(class2.toString());

            Long class3 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 3);
            assertTrue(class3 != null);
            System.out.println(class3.toString());

            Long class4 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 4);
            assertTrue(class4 != null);
            System.out.println(class4.toString());

            Long class5 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 5);
            assertTrue(class5 != null);
            System.out.println(class5.toString());

            Long class6 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndHGMDDiseaseClassId(36355, 6);
            assertTrue(class6 != null);
            System.out.println(class6.toString());
            
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByAssemblyIdAndClinVarDiseaseClassId() {
        try {
            BinResultsFinalDiagnosticDAOImpl binResultsFinalDiagnosticDAO = new BinResultsFinalDiagnosticDAOImpl();
            binResultsFinalDiagnosticDAO.setEntityManager(em);

            Long class1 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 1);
            assertTrue(class1 != null);
            System.out.println(class1.toString());

            Long class2 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 2);
            assertTrue(class2 != null);
            System.out.println(class2.toString());

            Long class3 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 3);
            assertTrue(class3 != null);
            System.out.println(class3.toString());

            Long class4 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 4);
            assertTrue(class4 != null);
            System.out.println(class4.toString());

            Long class5 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 5);
            assertTrue(class5 != null);
            System.out.println(class5.toString());

            Long class6 = binResultsFinalDiagnosticDAO.findByAssemblyIdAndClinVarDiseaseClassId(36355, 6);
            assertTrue(class6 != null);
            System.out.println(class6.toString());
            
            
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
