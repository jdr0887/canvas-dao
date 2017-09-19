package org.renci.canvas.dao.ref;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.ref.model.SequenceType;

public interface SequenceTypeDAO extends BaseDAO<SequenceType, String> {

    public List<SequenceType> findAll() throws CANVASDAOException;

}
