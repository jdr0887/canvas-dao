package org.renci.canvas.dao.onekgen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.onekgen.OneKGenomesSNPPopulationMaxFrequencyDAOImpl;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesSNPPopulationMaxFrequencyPK;

public class OneKGenomesSNPPopulationMaxFrequencyTest {

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
        OneKGenomesSNPPopulationMaxFrequencyDAOImpl snpPopulationMaxFrequencyDAO = new OneKGenomesSNPPopulationMaxFrequencyDAOImpl();
        snpPopulationMaxFrequencyDAO.setEntityManager(em);
        System.out.println(snpPopulationMaxFrequencyDAO.findLatestVersion());
    }

    @Test
    public void testFindByLocatedVariantIdAndVersion() throws CANVASDAOException {
        OneKGenomesSNPPopulationMaxFrequencyDAOImpl snpPopulationMaxFrequencyDAO = new OneKGenomesSNPPopulationMaxFrequencyDAOImpl();
        snpPopulationMaxFrequencyDAO.setEntityManager(em);
        OneKGenomesSNPPopulationMaxFrequency snpPopulationMaxFrequency = snpPopulationMaxFrequencyDAO
                .findById(new OneKGenomesSNPPopulationMaxFrequencyPK(16297027L, 2));
        if (snpPopulationMaxFrequency != null) {
            System.out.println(snpPopulationMaxFrequency.toString());
        }
    }

}
