package org.renci.canvas.dao.var.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VariantSetLocationPK implements Serializable {

    private static final long serialVersionUID = -2850126342576220251L;

    @Column(name = "var_set_id")
    private Integer variantSet;

    @Column(name = "ref_ver_accession")
    private String genomeRefSeq;

    @Column(name = "pos")
    private Integer position;

    public VariantSetLocationPK() {
        super();
    }

    public Integer getVariantSet() {
        return variantSet;
    }

    public void setVariantSet(Integer variantSet) {
        this.variantSet = variantSet;
    }

    public String getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(String genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("VariantSetLocationPK [variantSet=%s, genomeRefSeq=%s, position=%s]", variantSet, genomeRefSeq, position);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genomeRefSeq == null) ? 0 : genomeRefSeq.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((variantSet == null) ? 0 : variantSet.hashCode());
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
        VariantSetLocationPK other = (VariantSetLocationPK) obj;
        if (genomeRefSeq == null) {
            if (other.genomeRefSeq != null)
                return false;
        } else if (!genomeRefSeq.equals(other.genomeRefSeq))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (variantSet == null) {
            if (other.variantSet != null)
                return false;
        } else if (!variantSet.equals(other.variantSet))
            return false;
        return true;
    }

}
