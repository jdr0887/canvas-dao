package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.VariantType;

public interface VariantTypeDAO extends BaseDAO<VariantType, String> {

    public List<VariantType> findAll() throws CANVASDAOException;

}
