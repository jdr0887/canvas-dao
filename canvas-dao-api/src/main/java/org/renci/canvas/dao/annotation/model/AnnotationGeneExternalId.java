package org.renci.canvas.dao.annotation.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "annot", name = "gene_external_ids", indexes = { @Index(name = "gene_external_ids_gene_id_idx", columnList = "gene_id") })
@NamedEntityGraph(name = "annot.AnnotationGeneExternalId.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "gene") })
public class AnnotationGeneExternalId implements Persistable<AnnotationGeneExternalIdPK> {

    private static final long serialVersionUID = 5179600096320755261L;

    @EmbeddedId
    private AnnotationGeneExternalIdPK id;

    @MapsId("gene")
    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    @Column(name = "namespace_ver", length = 31)
    private String namespaceVersion;

    public AnnotationGeneExternalId() {
        super();
    }

    public AnnotationGeneExternalId(AnnotationGeneExternalIdPK id) {
        super();
        this.id = id;
    }

    public AnnotationGeneExternalIdPK getId() {
        return id;
    }

    public void setId(AnnotationGeneExternalIdPK id) {
        this.id = id;
    }

    public String getNamespaceVersion() {
        return namespaceVersion;
    }

    public void setNamespaceVersion(String namespaceVersion) {
        this.namespaceVersion = namespaceVersion;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return String.format("AnnotationGeneExternalId [id=%s, namespaceVersion=%s]", id, namespaceVersion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((namespaceVersion == null) ? 0 : namespaceVersion.hashCode());
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
        AnnotationGeneExternalId other = (AnnotationGeneExternalId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (namespaceVersion == null) {
            if (other.namespaceVersion != null)
                return false;
        } else if (!namespaceVersion.equals(other.namespaceVersion))
            return false;
        return true;
    }

}
