package org.renci.canvas.dao.ref.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GenomeRefSeqAlternateIdPK implements Serializable {

    private static final long serialVersionUID = 4103404522615560260L;

    @Column(name = "ver_accession")
    private String genomeRefSeq;

    @Column(name = "id_type")
    private String idType;

    public GenomeRefSeqAlternateIdPK() {
        super();
    }

    public GenomeRefSeqAlternateIdPK(String genomeRefSeq, String idType) {
        super();
        this.genomeRefSeq = genomeRefSeq;
        this.idType = idType;
    }

    public String getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(String genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return String.format("GenomeRefSeqAlternateIdPK [genomeRefSeq=%s, idType=%s]", genomeRefSeq, idType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genomeRefSeq == null) ? 0 : genomeRefSeq.hashCode());
        result = prime * result + ((idType == null) ? 0 : idType.hashCode());
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
        GenomeRefSeqAlternateIdPK other = (GenomeRefSeqAlternateIdPK) obj;
        if (genomeRefSeq == null) {
            if (other.genomeRefSeq != null)
                return false;
        } else if (!genomeRefSeq.equals(other.genomeRefSeq))
            return false;
        if (idType == null) {
            if (other.idType != null)
                return false;
        } else if (!idType.equals(other.idType))
            return false;
        return true;
    }

}
