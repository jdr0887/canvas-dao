package org.renci.canvas.dao.var;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.var.model.Assembly;

public class AssemblyTest {

    @Test
    public void testFindBySampleName() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        List<Assembly> assemblyList = daoMgr.getDAOBean().getAssemblyDAO().findBySampleName("NCG_00497");
        assemblyList.forEach(a -> System.out.println(a.toString()));
    }

}
