package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.AnalysisClassDAO;
import org.renci.canvas.dao.clinbin.model.AnalysisClass;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@OsgiServiceProvider(classes = { AnalysisClassDAO.class })
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AnalysisClassDAOImpl extends BaseDAOImpl<AnalysisClass, Integer> implements AnalysisClassDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisClassDAOImpl.class);

    public AnalysisClassDAOImpl() {
        super();
    }

    @Override
    public Class<AnalysisClass> getPersistentClass() {
        return AnalysisClass.class;
    }

}
