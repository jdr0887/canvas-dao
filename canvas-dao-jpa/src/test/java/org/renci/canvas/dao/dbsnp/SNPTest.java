package org.renci.canvas.dao.dbsnp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.dbsnp.SNPDAOImpl;

public class SNPTest {

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

        SNPDAOImpl snpDAO = new SNPDAOImpl();
        snpDAO.setEntityManager(em);

        System.out.println(snpDAO.findLatestVersion());

    }
}
