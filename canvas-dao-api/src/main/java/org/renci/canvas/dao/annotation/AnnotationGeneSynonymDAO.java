package org.renci.canvas.dao.annotation;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonym;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonymPK;

public interface AnnotationGeneSynonymDAO extends BaseDAO<AnnotationGeneSynonym, AnnotationGeneSynonymPK> {

    public List<AnnotationGeneSynonym> findByGeneId(Integer annotationGeneId) throws CANVASDAOException;

}
