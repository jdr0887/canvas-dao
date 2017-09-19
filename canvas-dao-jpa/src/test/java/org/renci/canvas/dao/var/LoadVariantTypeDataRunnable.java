package org.renci.canvas.dao.var;

import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.var.model.VariantType;

public class LoadVariantTypeDataRunnable implements Runnable {

    private static final CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

    @Override
    public void run() {

        try {
            daoMgr.getDAOBean().getVariantTypeDAO().save(new VariantType("snp"));
            daoMgr.getDAOBean().getVariantTypeDAO().save(new VariantType("ins"));
            daoMgr.getDAOBean().getVariantTypeDAO().save(new VariantType("del"));
            daoMgr.getDAOBean().getVariantTypeDAO().save(new VariantType("sub"));
            daoMgr.getDAOBean().getVariantTypeDAO().save(new VariantType("ref"));
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LoadVariantTypeDataRunnable runnable = new LoadVariantTypeDataRunnable();
        runnable.run();
    }

}
