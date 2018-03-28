package org.renci.canvas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface BaseDAO<T extends Persistable<ID>, ID extends Serializable> {

    public abstract T findById(ID id) throws CANVASDAOException;

    public abstract List<T> findByIdList(List<ID> idList) throws CANVASDAOException;

    public abstract ID save(T t) throws CANVASDAOException;

    public abstract Set<ID> save(Set<T> tSet) throws CANVASDAOException;

}
