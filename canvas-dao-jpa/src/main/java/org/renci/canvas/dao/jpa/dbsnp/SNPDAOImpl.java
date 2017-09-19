package org.renci.canvas.dao.jpa.dbsnp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion_;
import org.renci.canvas.dao.dbsnp.SNPDAO;
import org.renci.canvas.dao.dbsnp.model.SNP;
import org.renci.canvas.dao.dbsnp.model.SNP_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { SNPDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class SNPDAOImpl extends BaseDAOImpl<SNP, Integer> implements SNPDAO {

    private static final Logger logger = LoggerFactory.getLogger(SNPDAOImpl.class);

    public SNPDAOImpl() {
        super();
    }

    @Override
    public Class<SNP> getPersistentClass() {
        return SNP.class;
    }

    @Override
    public String findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        String ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<String> crit = critBuilder.createQuery(String.class);
            Root<SNP> root = crit.from(getPersistentClass());
            crit.select(root.get(SNP_.version));
            crit.distinct(true);
            crit.orderBy(critBuilder.desc(root.get(SNP_.version)));
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
