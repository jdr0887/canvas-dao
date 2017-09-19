package org.renci.canvas.dao.clinbin;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DXCoverage;
import org.renci.canvas.dao.clinbin.model.DXCoveragePK;
import org.renci.canvas.dao.clinbin.model.DXExons;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class DXCoverageTest {

    @Test
    public void testSave() {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        try {
            DXExons exons = daoMgr.getDAOBean().getDXExonsDAO().findById(1);

            DXCoveragePK key = new DXCoveragePK(exons.getId(), "asdf");
            DXCoverage coverage = new DXCoverage(key);
            coverage.setId(key);
            coverage.setExon(exons);
            coverage.setFractionGreaterThan1(0.629);
            coverage.setFractionGreaterThan5(0.848);
            coverage.setFractionGreaterThan10(0.629);
            coverage.setFractionGreaterThan15(0.629);
            coverage.setFractionGreaterThan30(0.629);
            coverage.setFractionGreaterThan50(0.629);

            DXCoveragePK ret = daoMgr.getDAOBean().getDXCoverageDAO().save(coverage);
            System.out.println(ret.toString());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
