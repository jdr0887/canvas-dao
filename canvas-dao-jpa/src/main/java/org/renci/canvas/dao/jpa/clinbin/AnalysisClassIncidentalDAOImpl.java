package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.AnalysisClassIncidentalDAO;
import org.renci.canvas.dao.clinbin.model.AnalysisClassIncidental;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@OsgiServiceProvider(classes = { AnalysisClassIncidentalDAO.class })
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AnalysisClassIncidentalDAOImpl extends BaseDAOImpl<AnalysisClassIncidental, Integer> implements AnalysisClassIncidentalDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisClassIncidentalDAOImpl.class);

    public AnalysisClassIncidentalDAOImpl() {
        super();
    }

    @Override
    public Class<AnalysisClassIncidental> getPersistentClass() {
        return AnalysisClassIncidental.class;
    }

}
