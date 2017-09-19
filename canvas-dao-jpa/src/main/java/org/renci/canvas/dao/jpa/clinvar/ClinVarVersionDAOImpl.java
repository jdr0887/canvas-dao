package org.renci.canvas.dao.jpa.clinvar;

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
import org.renci.canvas.dao.clinvar.ClinVarVersionDAO;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ClinVarVersionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class ClinVarVersionDAOImpl extends BaseDAOImpl<ClinVarVersion, Long> implements ClinVarVersionDAO {

    private static final Logger logger = LoggerFactory.getLogger(ClinVarVersionDAOImpl.class);

    public ClinVarVersionDAOImpl() {
        super();
    }

    @Override
    public Class<ClinVarVersion> getPersistentClass() {
        return ClinVarVersion.class;
    }

    @Override
    public ClinVarVersion findLatestVersion() throws CANVASDAOException {
        logger.debug("ENTERING findLatestVersion()");
        ClinVarVersion ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ClinVarVersion> crit = critBuilder.createQuery(getPersistentClass());
            Root<ClinVarVersion> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.isNotNull(root.get(ClinVarVersion_.datePerformed)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.desc(root.get(ClinVarVersion_.id)));
            TypedQuery<ClinVarVersion> query = getEntityManager().createQuery(crit);
            List<ClinVarVersion> results = query.getResultList();
            if (CollectionUtils.isNotEmpty(results)) {
                ret = results.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
