package org.renci.canvas.dao.var;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.var.CanonicalAlleleDAOImpl;
import org.renci.canvas.dao.var.model.CanonicalAllele;

public class CanonicalAlleleTest {

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
    public void testFindLiftOverSibling() throws CANVASDAOException {
        CanonicalAlleleDAOImpl canonicalAlleleDAO = new CanonicalAlleleDAOImpl();
        canonicalAlleleDAO.setEntityManager(em);

        List<CanonicalAllele> caList = canonicalAlleleDAO.findByLocatedVariantId(575218668L);
        assertTrue(CollectionUtils.isNotEmpty(caList));


        // LocatedVariantDAOImpl locatedVariantDAO = new LocatedVariantDAOImpl();
        // locatedVariantDAO.setEntityManager(em);
        // LocatedVariant lv = locatedVariantDAO.findById(575218668L);
        // List<LocatedVariant> locatedVariantList = locatedVariantDAO.findLiftOverSibling(2, lv);
        // locatedVariantList.forEach(a -> System.out.println(a.toString()));
    }

}
