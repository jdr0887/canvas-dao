package org.renci.canvas.dao.jpa.refseq;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.CDSTranslationExceptionDAO;
import org.renci.canvas.dao.refseq.model.CDSTranslationException;
import org.renci.canvas.dao.refseq.model.CDSTranslationExceptionPK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { CDSTranslationExceptionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class CDSTranslationExceptionDAOImpl extends BaseDAOImpl<CDSTranslationException, CDSTranslationExceptionPK>
        implements CDSTranslationExceptionDAO {

    private static final Logger logger = LoggerFactory.getLogger(CDSTranslationExceptionDAOImpl.class);

    public CDSTranslationExceptionDAOImpl() {
        super();
    }

    @Override
    public Class<CDSTranslationException> getPersistentClass() {
        return CDSTranslationException.class;
    }

}
