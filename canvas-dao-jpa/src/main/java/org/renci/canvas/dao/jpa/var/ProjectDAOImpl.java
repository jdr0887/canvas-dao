package org.renci.canvas.dao.jpa.var;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.var.ProjectDAO;
import org.renci.canvas.dao.var.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { ProjectDAO.class })
@javax.transaction.Transactional
@Singleton
public class ProjectDAOImpl extends BaseDAOImpl<Project, String> implements ProjectDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

    public ProjectDAOImpl() {
        super();
    }

    @Override
    public Class<Project> getPersistentClass() {
        return Project.class;
    }

    @org.springframework.transaction.annotation.Transactional
    @javax.transaction.Transactional
    @Override
    public void delete(Project entity) throws CANVASDAOException {
        logger.debug("ENTERING delete(T)");
        Project foundEntity = getEntityManager().find(getPersistentClass(), entity.getId());
        getEntityManager().remove(foundEntity);
    }

}
