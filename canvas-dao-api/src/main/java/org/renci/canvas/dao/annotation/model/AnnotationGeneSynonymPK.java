package org.renci.canvas.dao.annotation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnnotationGeneSynonymPK implements Serializable {

    private static final long serialVersionUID = 3309532806166062036L;

    @Column(name = "gene_id")
    private Integer gene;

    @Column(name = "synonym")
    private String synonym;

    public AnnotationGeneSynonymPK() {
        super();
    }

    public AnnotationGeneSynonymPK(Integer gene, String synonym) {
        super();
        this.gene = gene;
        this.synonym = synonym;
    }

    public Integer getGene() {
        return gene;
    }

    public void setGene(Integer gene) {
        this.gene = gene;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return String.format("AnnotationGeneSynonymPK [gene=%s, synonym=%s]", gene, synonym);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gene == null) ? 0 : gene.hashCode());
        result = prime * result + ((synonym == null) ? 0 : synonym.hashCode());
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
        AnnotationGeneSynonymPK other = (AnnotationGeneSynonymPK) obj;
        if (gene == null) {
            if (other.gene != null)
                return false;
        } else if (!gene.equals(other.gene))
            return false;
        if (synonym == null) {
            if (other.synonym != null)
                return false;
        } else if (!synonym.equals(other.synonym))
            return false;
        return true;
    }

}
