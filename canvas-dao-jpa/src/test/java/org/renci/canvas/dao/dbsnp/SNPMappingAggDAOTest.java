package org.renci.canvas.dao.dbsnp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.dbsnp.model.SNPMappingAgg;
import org.renci.canvas.dao.dbsnp.model.SNPMappingAggPK;
import org.renci.canvas.dao.jpa.dbsnp.SNPMappingAggDAOImpl;

public class SNPMappingAggDAOTest {

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
    public void testFindById() throws CANVASDAOException {

        SNPMappingAggDAOImpl snpMappingAggDAO = new SNPMappingAggDAOImpl();
        snpMappingAggDAO.setEntityManager(em);

        SNPMappingAgg snpMappingAgg = snpMappingAggDAO.findById(new SNPMappingAggPK(32126486L));
        System.out.println(snpMappingAgg.toString());

    }

}
