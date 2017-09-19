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
import org.renci.canvas.dao.clinbin.DiseaseClassDAO;
import org.renci.canvas.dao.clinbin.model.DiseaseClass;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DiseaseClassDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DiseaseClassDAOImpl extends BaseDAOImpl<DiseaseClass, Integer> implements DiseaseClassDAO {

    private static final Logger logger = LoggerFactory.getLogger(DiseaseClassDAOImpl.class);

    public DiseaseClassDAOImpl() {
        super();
    }

    @Override
    public Class<DiseaseClass> getPersistentClass() {
        return DiseaseClass.class;
    }

    @Override
    public List<DiseaseClass> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<DiseaseClass> ret = new ArrayList<>();
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DiseaseClass> crit = critBuilder.createQuery(getPersistentClass());
        Root<DiseaseClass> root = crit.from(getPersistentClass());
        crit.distinct(true);
        TypedQuery<DiseaseClass> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());
        return ret;
    }

}
