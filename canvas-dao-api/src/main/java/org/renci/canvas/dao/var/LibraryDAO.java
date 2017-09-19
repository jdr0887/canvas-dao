package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.Library;

public interface LibraryDAO extends BaseDAO<Library, Integer> {

    public void delete(Library entity) throws CANVASDAOException;

    public List<Library> findByNameAndSampleId(String name, Integer sampleId) throws CANVASDAOException;

}
