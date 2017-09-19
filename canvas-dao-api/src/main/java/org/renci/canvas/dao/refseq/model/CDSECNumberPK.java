package org.renci.canvas.dao.refseq.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CDSECNumberPK implements Serializable {

    private static final long serialVersionUID = 8905976400358131037L;

    @Column(name = "refseq_cds_id")
    private Integer refseqCodingSequence;

    @Column(name = "ec_num", length = 31)
    private String ecNumber;

    public CDSECNumberPK() {
        super();
    }

    public CDSECNumberPK(Integer refseqCodingSequence, String ecNumber) {
        super();
        this.refseqCodingSequence = refseqCodingSequence;
        this.ecNumber = ecNumber;
    }

    public Integer getRefseqCodingSequence() {
        return refseqCodingSequence;
    }

    public void setRefseqCodingSequence(Integer refseqCodingSequence) {
        this.refseqCodingSequence = refseqCodingSequence;
    }

    public String getEcNumber() {
        return ecNumber;
    }

    public void setEcNumber(String ecNumber) {
        this.ecNumber = ecNumber;
    }

    @Override
    public String toString() {
        return String.format("CodingSequenceEcNumsPK [refseqCodingSequence=%s, ecNumber=%s]", refseqCodingSequence, ecNumber);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ecNumber == null) ? 0 : ecNumber.hashCode());
        result = prime * result + ((refseqCodingSequence == null) ? 0 : refseqCodingSequence.hashCode());
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
        CDSECNumberPK other = (CDSECNumberPK) obj;
        if (ecNumber == null) {
            if (other.ecNumber != null)
                return false;
        } else if (!ecNumber.equals(other.ecNumber))
            return false;
        if (refseqCodingSequence == null) {
            if (other.refseqCodingSequence != null)
                return false;
        } else if (!refseqCodingSequence.equals(other.refseqCodingSequence))
            return false;
        return true;
    }

}
