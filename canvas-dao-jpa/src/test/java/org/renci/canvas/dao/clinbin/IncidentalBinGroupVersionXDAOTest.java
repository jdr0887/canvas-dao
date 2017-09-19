package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGroupVersionX;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class IncidentalBinGroupVersionXDAOTest {

    private static final CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

    @Test
    public void testFindByIncidentalBinIdAndGroupVersion() throws CANVASDAOException {
        long startTime = System.currentTimeMillis();
        List<IncidentalBinGroupVersionX> groupVersions = daoMgr.getDAOBean().getIncidentalBinGroupVersionXDAO()
                .findByIncidentalBinIdAndGroupVersion(1, 5);
        long endTime = System.currentTimeMillis();
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

}
