package org.renci.canvas.dao.var;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.VariantSetLoad;

public interface VariantSetLoadDAO extends BaseDAO<VariantSetLoad, Integer> {

    public List<VariantSetLoad> findByExample(VariantSetLoad variantSetLoad) throws CANVASDAOException;

}
