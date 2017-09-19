package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.GroupingType;

public interface GroupingTypeDAO extends BaseDAO<GroupingType, String> {

    public List<GroupingType> findAll() throws CANVASDAOException;

}
