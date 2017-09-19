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
import org.renci.canvas.dao.clinbin.CarrierStatusDAO;
import org.renci.canvas.dao.clinbin.model.CarrierStatus;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { CarrierStatusDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class CarrierStatusDAOImpl extends BaseDAOImpl<CarrierStatus, Integer> implements CarrierStatusDAO {

    private static final Logger logger = LoggerFactory.getLogger(CarrierStatusDAOImpl.class);

    public CarrierStatusDAOImpl() {
        super();
    }

    @Override
    public Class<CarrierStatus> getPersistentClass() {
        return CarrierStatus.class;
    }

    @Override
    public List<CarrierStatus> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<CarrierStatus> ret = new ArrayList<>();
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CarrierStatus> crit = critBuilder.createQuery(getPersistentClass());
        Root<CarrierStatus> root = crit.from(getPersistentClass());
        crit.distinct(true);
        TypedQuery<CarrierStatus> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());
        return ret;
    }

}
