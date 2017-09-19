package org.renci.canvas.dao.annotation;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK;

public interface AnnotationGeneExternalIdDAO extends BaseDAO<AnnotationGeneExternalId, AnnotationGeneExternalIdPK> {

    public List<AnnotationGeneExternalId> findByAnnotationGeneId(Integer geneId) throws CANVASDAOException;

    public List<AnnotationGeneExternalId> findByNamespace(String namespace) throws CANVASDAOException;

    public List<AnnotationGeneExternalId> findByNamespaceAndNamespaceVersion(String namespace, String version) throws CANVASDAOException;

    public List<AnnotationGeneExternalId> findByExternalId(Integer externalId) throws CANVASDAOException;

    public List<AnnotationGeneExternalId> findByExternalIdAndNamespace(Integer externalId, String namespace) throws CANVASDAOException;

}
