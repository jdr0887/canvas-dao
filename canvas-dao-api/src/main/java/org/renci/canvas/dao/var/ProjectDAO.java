package org.renci.canvas.dao.var;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.Project;

public interface ProjectDAO extends BaseDAO<Project, String> {

    public void delete(Project entity) throws CANVASDAOException;

}
