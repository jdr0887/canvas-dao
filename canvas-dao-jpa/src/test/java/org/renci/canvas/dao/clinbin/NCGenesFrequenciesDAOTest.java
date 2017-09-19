package org.renci.canvas.dao.clinbin;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequenciesPK;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class NCGenesFrequenciesDAOTest {

    @Test
    public void testFindMaxListVersion() {
        try {
            CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
            System.out.println(daoMgr.getDAOBean().getNCGenesFrequenciesDAO().findMaxVersion());
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByPK() {
        try {
            CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
            System.out.println(daoMgr.getDAOBean().getNCGenesFrequenciesDAO().findById(new NCGenesFrequenciesPK(421185709L, "80")));
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
