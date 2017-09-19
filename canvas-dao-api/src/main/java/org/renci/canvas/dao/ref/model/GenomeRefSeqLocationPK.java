package org.renci.canvas.dao.ref.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GenomeRefSeqLocationPK implements Serializable {

    private static final long serialVersionUID = 4103404522615560260L;

    @Column(name = "ref_id")
    private Integer genomeRef;

    @Column(name = "ver_accession")
    private String genomeRefSeq;

    @Column(name = "ref_pos")
    private Integer position;

    @Column(name = "ref_base", length = 1)
    private Character base;

    public GenomeRefSeqLocationPK() {
        super();
    }

    public GenomeRefSeqLocationPK(Integer genomeRef, String genomeRefSeq, Integer position, Character base) {
        super();
        this.genomeRef = genomeRef;
        this.genomeRefSeq = genomeRefSeq;
        this.position = position;
        this.base = base;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Character getBase() {
        return base;
    }

    public void setBase(Character base) {
        this.base = base;
    }

    public String getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(String genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public Integer getGenomeRef() {
        return genomeRef;
    }

    public void setGenomeRef(Integer genomeRef) {
        this.genomeRef = genomeRef;
    }

    @Override
    public String toString() {
        return String.format("GenomeRefSeqLocationPK [genomeRef=%s, genomeRefSeq=%s, position=%s, base=%s]", genomeRef, genomeRefSeq,
                position, base);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((base == null) ? 0 : base.hashCode());
        result = prime * result + ((genomeRef == null) ? 0 : genomeRef.hashCode());
        result = prime * result + ((genomeRefSeq == null) ? 0 : genomeRefSeq.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
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
        GenomeRefSeqLocationPK other = (GenomeRefSeqLocationPK) obj;
        if (base == null) {
            if (other.base != null)
                return false;
        } else if (!base.equals(other.base))
            return false;
        if (genomeRef == null) {
            if (other.genomeRef != null)
                return false;
        } else if (!genomeRef.equals(other.genomeRef))
            return false;
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
        return true;
    }

}
