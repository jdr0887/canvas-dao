package org.renci.canvas.dao.jpa.var;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.AssemblyLocationDAO;
import org.renci.canvas.dao.var.model.AssemblyLocation;
import org.renci.canvas.dao.var.model.AssemblyLocationPK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { AssemblyLocationDAO.class })
@javax.transaction.Transactional
@Singleton
public class AssemblyLocationDAOImpl extends BaseDAOImpl<AssemblyLocation, AssemblyLocationPK> implements AssemblyLocationDAO {

    private static final Logger logger = LoggerFactory.getLogger(AssemblyLocationDAOImpl.class);

    public AssemblyLocationDAOImpl() {
        super();
    }

    @Override
    public Class<AssemblyLocation> getPersistentClass() {
        return AssemblyLocation.class;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(AssemblyLocation entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(AssemblyLocation)");
        AssemblyLocation foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

}
