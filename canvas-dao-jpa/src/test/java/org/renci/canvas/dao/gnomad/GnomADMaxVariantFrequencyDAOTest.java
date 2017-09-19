package org.renci.canvas.dao.gnomad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.gnomad.GnomADMaxVariantFrequencyDAOImpl;

public class GnomADMaxVariantFrequencyDAOTest {

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
    public void testFindLatestVersion() throws CANVASDAOException {

        GnomADMaxVariantFrequencyDAOImpl gnomADMaxVariantFrequencyDAO = new GnomADMaxVariantFrequencyDAOImpl();
        gnomADMaxVariantFrequencyDAO.setEntityManager(em);

        System.out.println(gnomADMaxVariantFrequencyDAO.findLatestVersion());

    }
}
