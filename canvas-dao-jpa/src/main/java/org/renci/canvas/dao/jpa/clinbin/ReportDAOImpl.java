package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.ReportDAO;
import org.renci.canvas.dao.clinbin.model.Report;
import org.renci.canvas.dao.clinbin.model.ReportPK;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ReportDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ReportDAOImpl extends BaseDAOImpl<Report, ReportPK> implements ReportDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReportDAOImpl.class);

    public ReportDAOImpl() {
        super();
    }

    @Override
    public Class<Report> getPersistentClass() {
        return Report.class;
    }

}
