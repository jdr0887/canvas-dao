package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinX;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class IncidentalBinXDAOTest {

    private static final CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

    @Test
    public void testFindByHGMDVersion() throws CANVASDAOException {
        long startTime = System.currentTimeMillis();
        List<IncidentalBinX> variants = daoMgr.getDAOBean().getIncidentalBinXDAO().findByHGMDVersion(1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(variants));
        System.out.println(variants.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

}
