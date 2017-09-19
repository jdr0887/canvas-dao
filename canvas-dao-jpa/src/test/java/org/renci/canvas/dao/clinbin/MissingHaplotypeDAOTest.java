package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.MissingHaplotype;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class MissingHaplotypeDAOTest {

    private static final CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

    @Test
    public void testFindByParticipantAndIncidentalBinIdAndListVersion() throws CANVASDAOException {
        long startTime = System.currentTimeMillis();
        List<MissingHaplotype> missingHaplotypeList = daoMgr.getDAOBean().getMissingHaplotypeDAO()
                .findByParticipantAndIncidentalBinIdAndListVersion("NCG_00185", 11, 1);
        long endTime = System.currentTimeMillis();
        assertTrue(CollectionUtils.isNotEmpty(missingHaplotypeList));
        System.out.println(missingHaplotypeList.size());
        System.out.printf("duration = %d", ((endTime - startTime) / 1000) / 60);
    }

}
