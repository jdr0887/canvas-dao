package org.renci.canvas.dao.clinbin;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.UnimportantExonPK;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class UnimportantExonDAOTest {

    @Test
    public void testFindByPK() {
        try {
            CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
            System.out.println(daoMgr.getDAOBean().getUnimportantExonDAO().findById(new UnimportantExonPK("NM_000021.3", 6)));
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }

}
