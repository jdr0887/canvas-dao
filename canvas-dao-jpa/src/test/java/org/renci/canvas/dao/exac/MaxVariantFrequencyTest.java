package org.renci.canvas.dao.exac;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.exac.model.ExACMaxVariantFrequency;
import org.renci.canvas.dao.jpa.exac.ExACMaxVariantFrequencyDAOImpl;

public class MaxVariantFrequencyTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-canvas", null);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testFindByGeneNameAndMaxAlleleFrequency() {
        ExACMaxVariantFrequencyDAOImpl maxVariantFrequencyDAO = new ExACMaxVariantFrequencyDAOImpl();
        maxVariantFrequencyDAO.setEntityManager(em);

        try {
            List<ExACMaxVariantFrequency> results = maxVariantFrequencyDAO.findByGeneNameAndMaxAlleleFrequency("BRCA1", 0.05);
            assertTrue(CollectionUtils.isNotEmpty(results));
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
