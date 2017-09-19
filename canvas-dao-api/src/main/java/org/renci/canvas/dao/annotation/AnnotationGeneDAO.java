package org.renci.canvas.dao.annotation;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene;

public interface AnnotationGeneDAO extends BaseDAO<AnnotationGene, Integer> {

    public List<AnnotationGene> findByName(String name) throws CANVASDAOException;

}
