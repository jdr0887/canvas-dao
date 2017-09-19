package org.renci.canvas.dao.annotation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnnotationGeneExternalIdPK implements Serializable {

    private static final long serialVersionUID = 3130221673517905419L;

    @Column(name = "gene_external_id")
    private Integer externalId;

    @Column(name = "gene_id")
    private Integer gene;

    @Column(name = "namespace", length = 31)
    private String namespace;

    @Column(name = "namespace_ver", length = 31)
    private String namespaceVer;

    public AnnotationGeneExternalIdPK() {
        super();
        this.namespaceVer = "";
    }

    public AnnotationGeneExternalIdPK(Integer externalId, Integer gene, String namespace) {
        this();
        this.externalId = externalId;
        this.gene = gene;
        this.namespace = namespace;
    }

    public AnnotationGeneExternalIdPK(Integer externalId, Integer gene, String namespace, String namespaceVer) {
        this(externalId, gene, namespace);
        this.namespaceVer = namespaceVer;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getGene() {
        return gene;
    }

    public void setGene(Integer gene) {
        this.gene = gene;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespaceVer() {
        return namespaceVer;
    }

    public void setNamespaceVer(String namespaceVer) {
        this.namespaceVer = namespaceVer;
    }

    @Override
    public String toString() {
        return String.format("AnnotationGeneExternalIdPK [externalId=%s, gene=%s, namespace=%s, namespaceVer=%s]", externalId, gene,
                namespace, namespaceVer);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
        result = prime * result + ((gene == null) ? 0 : gene.hashCode());
        result = prime * result + ((namespace == null) ? 0 : namespace.hashCode());
        result = prime * result + ((namespaceVer == null) ? 0 : namespaceVer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnnotationGeneExternalIdPK other = (AnnotationGeneExternalIdPK) obj;
        if (externalId == null) {
            if (other.externalId != null)
                return false;
        } else if (!externalId.equals(other.externalId))
            return false;
        if (gene == null) {
            if (other.gene != null)
                return false;
        } else if (!gene.equals(other.gene))
            return false;
        if (namespace == null) {
            if (other.namespace != null)
                return false;
        } else if (!namespace.equals(other.namespace))
            return false;
        if (namespaceVer == null) {
            if (other.namespaceVer != null)
                return false;
        } else if (!namespaceVer.equals(other.namespaceVer))
            return false;
        return true;
    }

}
