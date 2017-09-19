package org.renci.canvas.dao.clinbin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DXExons;
import org.renci.canvas.dao.jpa.clinbin.DXExonsDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticGeneDAOImpl;

public class DXExonsDAOTest {

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
    public void testFindMaxListVersion() {
        DXExonsDAOImpl dao = new DXExonsDAOImpl();
        dao.setEntityManager(em);
        try {
            System.out.println(dao.findMaxListVersion().toString());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByListVersionAndChromosomeAndTranscriptAndRange() throws CANVASDAOException {
        DXExonsDAOImpl dxExonsDAO = new DXExonsDAOImpl();
        dxExonsDAO.setEntityManager(em);

        DiagnosticGeneDAOImpl diagnosticGeneDAO = new DiagnosticGeneDAOImpl();
        diagnosticGeneDAO.setEntityManager(em);

        // List<DXExons> foundExons = dxExonsDAO.findByListVersionAndChromosomeAndTranscriptAndRange(42, "NC_000006.12", "NM_139006.2",
        // 26087439, 26087518);

        List<DXExons> foundExons = dxExonsDAO.findByListVersionAndChromosomeAndTranscriptAndRange(42, "NC_000012.12", "NM_032790.3",
                121626746, 121627052);

        foundExons = dxExonsDAO.findByListVersionAndTranscriptAndExonAndMapNum(42, "NM_032790.3", 1, 1);

        // (chromosome, exon, gene_id, interval_end, interval_start, list_version, mapnum, transcr, dx_exon_id) values ('NC_000002.12', 2,
        // 17449, 74464931, 74464894, 42, 1, '', 4627603)

        foundExons = dxExonsDAO.findByListVersionAndTranscriptAndExonAndMapNum(42, "NM_001146158.1", 2, 1);

        foundExons = dxExonsDAO.findByListVersionAndTranscriptAndExonAndMapNum(42, "XM_011538760.1", 2, 1);

        System.out.println("");
    }

}
