package org.renci.canvas.dao.jpa.refseq;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.refseq.TranscriptMapsExonsDAO;
import org.renci.canvas.dao.refseq.TranscriptRefSeqVersionDAO;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersion;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersionPK;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersionPK_;
import org.renci.canvas.dao.refseq.model.TranscriptRefSeqVersion_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { TranscriptMapsExonsDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TranscriptRefSeqVersionDAOImpl extends BaseDAOImpl<TranscriptRefSeqVersion, TranscriptRefSeqVersionPK>
        implements TranscriptRefSeqVersionDAO {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptRefSeqVersionDAOImpl.class);

    public TranscriptRefSeqVersionDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptRefSeqVersion> getPersistentClass() {
        return TranscriptRefSeqVersion.class;
    }

    @Override
    public String findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        String ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<String> crit = critBuilder.createQuery(String.class);
            Root<TranscriptRefSeqVersion> root = crit.from(getPersistentClass());
            crit.select(root.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder.desc(root.get(TranscriptRefSeqVersion_.id).get(TranscriptRefSeqVersionPK_.version)));
            TypedQuery<String> query = getEntityManager().createQuery(crit);
            List<String> results = query.getResultList();
            if (CollectionUtils.isNotEmpty(results)) {
                ret = results.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
