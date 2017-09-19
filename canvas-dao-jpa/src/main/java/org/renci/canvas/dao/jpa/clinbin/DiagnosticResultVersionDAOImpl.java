package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.DiagnosticResultVersionDAO;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DiagnosticResultVersionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DiagnosticResultVersionDAOImpl extends BaseDAOImpl<DiagnosticResultVersion, Integer> implements DiagnosticResultVersionDAO {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosticResultVersionDAOImpl.class);

    public DiagnosticResultVersionDAOImpl() {
        super();
    }

    @Override
    public Class<DiagnosticResultVersion> getPersistentClass() {
        return DiagnosticResultVersion.class;
    }

    @Override
    public Integer findMaxResultVersion() throws CANVASDAOException {
        logger.debug("ENTERING findMaxResultVersion()");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
        Root<DiagnosticResultVersion> root = crit.from(getPersistentClass());
        crit.select(critBuilder.max(root.get(DiagnosticResultVersion_.id)));
        TypedQuery<Integer> query = getEntityManager().createQuery(crit);
        Integer ret = query.getSingleResult();
        return ret;
    }

    @Override
    public List<DiagnosticResultVersion> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<DiagnosticResultVersion> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DiagnosticResultVersion> crit = critBuilder.createQuery(getPersistentClass());
            Root<DiagnosticResultVersion> root = crit.from(getPersistentClass());
            TypedQuery<DiagnosticResultVersion> query = getEntityManager().createQuery(crit);
            query.setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.DiagnosticResultVersion.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
