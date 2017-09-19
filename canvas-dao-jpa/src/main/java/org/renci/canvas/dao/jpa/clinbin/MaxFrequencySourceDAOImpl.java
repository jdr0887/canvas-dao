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
import org.renci.canvas.dao.clinbin.MaxFrequencySourceDAO;
import org.renci.canvas.dao.clinbin.model.MaxFrequencySource;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { MaxFrequencySourceDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class MaxFrequencySourceDAOImpl extends BaseDAOImpl<MaxFrequencySource, String> implements MaxFrequencySourceDAO {

    private static final Logger logger = LoggerFactory.getLogger(MaxFrequencySourceDAOImpl.class);

    public MaxFrequencySourceDAOImpl() {
        super();
    }

    @Override
    public Class<MaxFrequencySource> getPersistentClass() {
        return MaxFrequencySource.class;
    }

    @Override
    public List<MaxFrequencySource> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<MaxFrequencySource> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<MaxFrequencySource> crit = critBuilder.createQuery(getPersistentClass());
            Root<MaxFrequencySource> root = crit.from(getPersistentClass());
            TypedQuery<MaxFrequencySource> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
