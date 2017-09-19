package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.IncidentalBinHaplotypeX;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class IncidentalBinHaplotypeXDAOTest {

    @Test
    public void testFindAll() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        List<IncidentalBinHaplotypeX> results = daoMgr.getDAOBean().getIncidentalBinHaplotypeXDAO().findAll();
        System.out.println(results.size());
    }

    @Test
    public void testFindByIncidentalBinIdAndVersion() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        List<IncidentalBinHaplotypeX> results = daoMgr.getDAOBean().getIncidentalBinHaplotypeXDAO().findByIncidentalBinIdAndVersion(7, 1);
        assertTrue(CollectionUtils.isNotEmpty(results));
        System.out.println(results.size());
        System.out.println(results.get(0).getHaplotype().getLocatedVariant().getGenomeRefSeq().getId());
    }

    @Test
    public void testFindByIncidentalBinIdAndVersionAndAssemblyIdAndHGMDVersionAndZygosityMode() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        List<String> zygosityModeList = Arrays.asList("AD", "AR", "CX", "XLD", "XLR", "V", "Y", "RISK");
        List<IncidentalBinHaplotypeX> results = daoMgr.getDAOBean().getIncidentalBinHaplotypeXDAO()
                .findByIncidentalBinIdAndVersionAndAssemblyIdAndHGMDVersionAndZygosityMode(7, 1, 33075, 2, zygosityModeList);
        assertTrue(CollectionUtils.isNotEmpty(results));
        System.out.println(results.size());
    }

}
