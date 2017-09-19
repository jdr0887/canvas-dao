package org.renci.canvas.dao.var;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.var.VariantTypeDAOImpl;

public class VariantTypeTest {

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
    public void testVariantType() {

        try {
            VariantTypeDAOImpl variantTypeDAO = new VariantTypeDAOImpl();
            variantTypeDAO.setEntityManager(em);
            variantTypeDAO.findById("snp");
            variantTypeDAO.findById("ins");
            variantTypeDAO.findById("del");
            variantTypeDAO.findById("snp");
            variantTypeDAO.findById("ins");
            variantTypeDAO.findById("del");
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }

    }

}
