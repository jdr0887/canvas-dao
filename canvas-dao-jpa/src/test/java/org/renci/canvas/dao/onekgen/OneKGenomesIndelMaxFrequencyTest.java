package org.renci.canvas.dao.onekgen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.onekgen.OneKGenomesIndelMaxFrequencyDAOImpl;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequency;
import org.renci.canvas.dao.onekgen.model.OneKGenomesIndelMaxFrequencyPK;

public class OneKGenomesIndelMaxFrequencyTest {

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
        OneKGenomesIndelMaxFrequencyDAOImpl indelMaxFrequencyDAO = new OneKGenomesIndelMaxFrequencyDAOImpl();
        indelMaxFrequencyDAO.setEntityManager(em);
        System.out.println(indelMaxFrequencyDAO.findLatestVersion());
    }

    @Test
    public void testFindByLocatedVariantIdAndVersion() throws CANVASDAOException {
        OneKGenomesIndelMaxFrequencyDAOImpl indelMaxFrequencyDAO = new OneKGenomesIndelMaxFrequencyDAOImpl();
        indelMaxFrequencyDAO.setEntityManager(em);

        OneKGenomesIndelMaxFrequency indelMaxFrequency = indelMaxFrequencyDAO.findById(new OneKGenomesIndelMaxFrequencyPK(16297027L, 2));
        if (indelMaxFrequency != null) {
            System.out.println(indelMaxFrequency.toString());
        }
    }

}
