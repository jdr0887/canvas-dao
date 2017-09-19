package org.renci.canvas.dao.annotation.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.canvas.dao.Persistable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Entity
@Table(schema = "annot", name = "gene_synonyms")
public class AnnotationGeneSynonym implements Persistable<AnnotationGeneSynonymPK> {

    private static final long serialVersionUID = 3309532806166062035L;

    @EmbeddedId
    private AnnotationGeneSynonymPK id;

    @MapsId("gene")
    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    public AnnotationGeneSynonym() {
        super();
    }

    public AnnotationGeneSynonym(AnnotationGeneSynonymPK id) {
        super();
        this.id = id;
    }

    public AnnotationGeneSynonymPK getId() {
        return id;
    }

    public void setId(AnnotationGeneSynonymPK id) {
        this.id = id;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return String.format("AnnotationGeneSynonym [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AnnotationGeneSynonym other = (AnnotationGeneSynonym) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
