package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.ConfirmedTrackingDAO;
import org.renci.canvas.dao.clinbin.model.ConfirmedTracking;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@OsgiServiceProvider(classes = { ConfirmedTrackingDAO.class })
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ConfirmedTrackingDAOImpl extends BaseDAOImpl<ConfirmedTracking, Integer> implements ConfirmedTrackingDAO {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmedTrackingDAOImpl.class);

    public ConfirmedTrackingDAOImpl() {
        super();
    }

    @Override
    public Class<ConfirmedTracking> getPersistentClass() {
        return ConfirmedTracking.class;
    }

}
