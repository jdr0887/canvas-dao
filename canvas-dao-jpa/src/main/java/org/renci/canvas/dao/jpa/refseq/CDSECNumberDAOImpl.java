package org.renci.canvas.dao.jpa.refseq;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.CDSECNumberDAO;
import org.renci.canvas.dao.refseq.model.CDSECNumber;
import org.renci.canvas.dao.refseq.model.CDSECNumberPK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { CDSECNumberDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class CDSECNumberDAOImpl extends BaseDAOImpl<CDSECNumber, CDSECNumberPK> implements CDSECNumberDAO {

    private static final Logger logger = LoggerFactory.getLogger(CDSECNumberDAOImpl.class);

    public CDSECNumberDAOImpl() {
        super();
    }

    @Override
    public Class<CDSECNumber> getPersistentClass() {
        return CDSECNumber.class;
    }

}
