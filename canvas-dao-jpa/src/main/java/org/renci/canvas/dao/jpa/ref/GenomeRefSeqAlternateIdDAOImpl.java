package org.renci.canvas.dao.jpa.ref;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.ref.GenomeRefSeqAlternateIdDAO;
import org.renci.canvas.dao.ref.model.GenomeRefSeqAlternateId;
import org.renci.canvas.dao.ref.model.GenomeRefSeqAlternateIdPK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GenomeRefSeqAlternateIdDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GenomeRefSeqAlternateIdDAOImpl extends BaseDAOImpl<GenomeRefSeqAlternateId, GenomeRefSeqAlternateIdPK>
        implements GenomeRefSeqAlternateIdDAO {

    private static final Logger logger = LoggerFactory.getLogger(GenomeRefSeqAlternateIdDAOImpl.class);

    public GenomeRefSeqAlternateIdDAOImpl() {
        super();
    }

    @Override
    public Class<GenomeRefSeqAlternateId> getPersistentClass() {
        return GenomeRefSeqAlternateId.class;
    }

}
