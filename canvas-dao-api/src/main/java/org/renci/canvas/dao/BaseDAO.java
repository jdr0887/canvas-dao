package org.renci.canvas.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T extends Persistable<ID>, ID extends Serializable> {

    public abstract T findById(ID id) throws CANVASDAOException;

    public abstract List<T> findByIdList(List<ID> idList) throws CANVASDAOException;

    public abstract ID save(T t) throws CANVASDAOException;

    public abstract List<ID> save(List<T> tList) throws CANVASDAOException;

}
