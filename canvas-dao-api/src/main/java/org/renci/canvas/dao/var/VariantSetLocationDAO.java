package org.renci.canvas.dao.var;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.var.model.VariantSetLocation;
import org.renci.canvas.dao.var.model.VariantSetLocationPK;

public interface VariantSetLocationDAO extends BaseDAO<VariantSetLocation, VariantSetLocationPK> {

    public void delete(VariantSetLocation entity) throws CANVASDAOException;

}
