package org.renci.canvas.dao.jpa;

import org.renci.canvas.dao.CANVASDAOBeanService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CANVASDAOManager {

    private static CANVASDAOManager instance;

    private CANVASDAOBeanService daoBean;

    public static CANVASDAOManager getInstance() {
        if (instance == null) {
            instance = new CANVASDAOManager();
        }
        return instance;
    }

    private CANVASDAOManager() {
        super();
        // do not close ctx...will close entity manager
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/dao-context.xml");
        this.daoBean = ctx.getBean(CANVASDAOBeanService.class);
    }

    public CANVASDAOBeanService getDAOBean() {
        return daoBean;
    }

    public void setDAOBean(CANVASDAOBeanService daoBean) {
        this.daoBean = daoBean;
    }
}
