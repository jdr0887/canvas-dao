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
import org.renci.canvas.dao.clinbin.NCGenesFrequenciesDAO;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequencies;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequenciesPK;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequenciesPK_;
import org.renci.canvas.dao.clinbin.model.NCGenesFrequencies_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { NCGenesFrequenciesDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class NCGenesFrequenciesDAOImpl extends BaseDAOImpl<NCGenesFrequencies, NCGenesFrequenciesPK> implements NCGenesFrequenciesDAO {

    private static final Logger logger = LoggerFactory.getLogger(NCGenesFrequenciesDAOImpl.class);

    public NCGenesFrequenciesDAOImpl() {
        super();
    }

    @Override
    public Class<NCGenesFrequencies> getPersistentClass() {
        return NCGenesFrequencies.class;
    }

    @Override
    public Integer findMaxVersion() throws CANVASDAOException {
        logger.debug("ENTERING findMaxVersion()");
        Integer ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<String> crit = critBuilder.createQuery(String.class);
            Root<NCGenesFrequencies> root = crit.from(getPersistentClass());
            crit.select(root.get(NCGenesFrequencies_.id).get(NCGenesFrequenciesPK_.version));
            crit.distinct(true);
            TypedQuery<String> query = getEntityManager().createQuery(crit);
            List<Integer> intResults = new ArrayList<>();
            query.getResultList().forEach(a -> intResults.add(Integer.valueOf(a)));
            intResults.sort((a, b) -> b.compareTo(a));
            ret = intResults.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
