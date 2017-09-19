package org.renci.canvas.dao.refseq.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "cds_ec_nums")
public class CDSECNumber implements Persistable<CDSECNumberPK> {

    private static final long serialVersionUID = -3476372807341372440L;

    @EmbeddedId
    private CDSECNumberPK id;

    @MapsId("refseqCodingSequence")
    @ManyToOne
    @JoinColumn(name = "refseq_cds_id", nullable = false)
    private RefSeqCodingSequence refseqCodingSequence;

    public CDSECNumber() {
        super();
    }

    public CDSECNumber(CDSECNumberPK id) {
        super();
        this.id = id;
    }

    public CDSECNumberPK getId() {
        return id;
    }

    public void setId(CDSECNumberPK id) {
        this.id = id;
    }

    public RefSeqCodingSequence getRefseqCodingSequence() {
        return refseqCodingSequence;
    }

    public void setRefseqCodingSequence(RefSeqCodingSequence refseqCodingSequence) {
        this.refseqCodingSequence = refseqCodingSequence;
    }

    @Override
    public String toString() {
        return String.format("CodingSequenceEcNums [key=%s]", id);
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
        CDSECNumber other = (CDSECNumber) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
