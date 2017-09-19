package org.renci.canvas.dao.jpa.clinvar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.AssertionRankingDAO;
import org.renci.canvas.dao.clinvar.model.AssertionRanking;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { AssertionRankingDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AssertionRankingDAOImpl extends BaseDAOImpl<AssertionRanking, String> implements AssertionRankingDAO {

    private static final Logger logger = LoggerFactory.getLogger(AssertionRankingDAOImpl.class);

    public AssertionRankingDAOImpl() {
        super();
    }

    @Override
    public Class<AssertionRanking> getPersistentClass() {
        return AssertionRanking.class;
    }

    @Override
    public List<AssertionRanking> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<AssertionRanking> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AssertionRanking> crit = critBuilder.createQuery(getPersistentClass());
            Root<AssertionRanking> root = crit.from(getPersistentClass());
            crit.distinct(true);
            TypedQuery<AssertionRanking> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
