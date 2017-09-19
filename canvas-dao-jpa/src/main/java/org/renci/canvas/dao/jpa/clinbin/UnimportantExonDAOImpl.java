package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.UnimportantExonDAO;
import org.renci.canvas.dao.clinbin.model.UnimportantExon;
import org.renci.canvas.dao.clinbin.model.UnimportantExonPK;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { UnimportantExonDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class UnimportantExonDAOImpl extends BaseDAOImpl<UnimportantExon, UnimportantExonPK> implements UnimportantExonDAO {

    private static final Logger logger = LoggerFactory.getLogger(UnimportantExonDAOImpl.class);

    public UnimportantExonDAOImpl() {
        super();
    }

    @Override
    public Class<UnimportantExon> getPersistentClass() {
        return UnimportantExon.class;
    }

}
