package org.renci.canvas.dao.clinvar;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;
import org.renci.canvas.dao.clinvar.model.ReferenceClinicalAssertion;
import org.renci.canvas.dao.jpa.clinvar.ClinVarVersionDAOImpl;
import org.renci.canvas.dao.jpa.clinvar.ReferenceClinicalAssertionDAOImpl;

public class ReferenceClinicalAssertionsDAOTest {

    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    @Before
    public void before() {
        this.emf = Persistence.createEntityManagerFactory("canvas_test", null);
        this.em = emf.createEntityManager();
    }

    @After
    public void after() {
        this.em.close();
        this.emf.close();
    }

    @Test
    public void testClinVarVersionMap() {
        try {

            ClinVarVersionDAOImpl clinVarVersionDAO = new ClinVarVersionDAOImpl();
            clinVarVersionDAO.setEntityManager(em);

            ReferenceClinicalAssertionDAOImpl referenceClinicalAssertionsDAO = new ReferenceClinicalAssertionDAOImpl();
            referenceClinicalAssertionsDAO.setEntityManager(em);

            em.getTransaction().begin();

            ClinVarVersion version = new ClinVarVersion("test-release");
            version.setId(clinVarVersionDAO.save(version));

            ReferenceClinicalAssertion rca = new ReferenceClinicalAssertion();
            rca.getVersions().add(version);
            rca.setAccession("test");
            rca.setAssertionStatus("test");
            rca.setVersion(1);
            rca.setCreated(new java.sql.Date(System.currentTimeMillis()));
            rca.setUpdated(new java.sql.Date(System.currentTimeMillis()));

            rca.setId(referenceClinicalAssertionsDAO.save(rca));

//            version.getReferenceClinicalAssertions().add(rca);
//            clinVarVersionDAO.save(version);

            rca = new ReferenceClinicalAssertion();
            rca.getVersions().add(version);
            rca.setAccession("test");
            rca.setAssertionStatus("test");
            rca.setVersion(1);
            rca.setCreated(new java.sql.Date(System.currentTimeMillis()));
            rca.setUpdated(new java.sql.Date(System.currentTimeMillis()));

            rca.setId(referenceClinicalAssertionsDAO.save(rca));
//            version.getReferenceClinicalAssertions().add(rca);
//            clinVarVersionDAO.save(version);

            em.getTransaction().commit();

        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindDiagnostic() {
        try {
            ReferenceClinicalAssertionDAOImpl referenceClinicalAssertionsDAO = new ReferenceClinicalAssertionDAOImpl();
            referenceClinicalAssertionsDAO.setEntityManager(em);

            List<ReferenceClinicalAssertion> results = referenceClinicalAssertionsDAO.findDiagnostic(22L, "NCG_00064", 16);
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByLocVarIdAndVersion() {
        try {
            ReferenceClinicalAssertionDAOImpl referenceClinicalAssertionsDAO = new ReferenceClinicalAssertionDAOImpl();
            referenceClinicalAssertionsDAO.setEntityManager(em);
            List<ReferenceClinicalAssertion> results = referenceClinicalAssertionsDAO.findByLocatedVariantIdAndVersion(404269787L, 4L);
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByLocVarIdAndVersionAndAssertionStatusExclusionList() {
        try {
            ReferenceClinicalAssertionDAOImpl referenceClinicalAssertionsDAO = new ReferenceClinicalAssertionDAOImpl();
            referenceClinicalAssertionsDAO.setEntityManager(em);
            List<ReferenceClinicalAssertion> results = referenceClinicalAssertionsDAO
                    .findByLocatedVariantIdAndVersionAndAssertionStatusExclusionList(404269787L, 4L,
                            Arrays.asList("no assertion criteria provided", "no assertion provided", "not classified by submitter"));
            assertTrue(results != null);
            assertTrue(!results.isEmpty());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
