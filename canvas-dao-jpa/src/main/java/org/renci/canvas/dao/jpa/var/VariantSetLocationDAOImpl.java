package org.renci.canvas.dao.jpa.var;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.VariantSetLocationDAO;
import org.renci.canvas.dao.var.model.VariantSetLocation;
import org.renci.canvas.dao.var.model.VariantSetLocationPK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { VariantSetLocationDAO.class })
@javax.transaction.Transactional
@Singleton
public class VariantSetLocationDAOImpl extends BaseDAOImpl<VariantSetLocation, VariantSetLocationPK> implements VariantSetLocationDAO {

    private static final Logger logger = LoggerFactory.getLogger(VariantSetLocationDAOImpl.class);

    public VariantSetLocationDAOImpl() {
        super();
    }

    @Override
    public Class<VariantSetLocation> getPersistentClass() {
        return VariantSetLocation.class;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(VariantSetLocation entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(VariantSetLocation)");
        VariantSetLocation foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

}
