package org.renci.canvas.dao.refseq.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CDSTranslationExceptionPK implements Serializable {

    private static final long serialVersionUID = 1188343289598927266L;

    @Column(name = "refseq_cds_id")
    private Integer refseqCodingSequence;

    @Column(name = "start_loc")
    private Integer startLocation;

    public CDSTranslationExceptionPK() {
        super();
    }

    public CDSTranslationExceptionPK(Integer refseqCodingSequence, Integer startLocation) {
        super();
        this.refseqCodingSequence = refseqCodingSequence;
        this.startLocation = startLocation;
    }

    public Integer getRefseqCodingSequence() {
        return refseqCodingSequence;
    }

    public void setRefseqCodingSequence(Integer refseqCodingSequence) {
        this.refseqCodingSequence = refseqCodingSequence;
    }

    public Integer getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Integer startLocation) {
        this.startLocation = startLocation;
    }

    @Override
    public String toString() {
        return String.format("CDSTranslationExceptionPK [refseqCodingSequence=%s, startLocation=%s]", refseqCodingSequence, startLocation);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((refseqCodingSequence == null) ? 0 : refseqCodingSequence.hashCode());
        result = prime * result + ((startLocation == null) ? 0 : startLocation.hashCode());
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
        CDSTranslationExceptionPK other = (CDSTranslationExceptionPK) obj;
        if (refseqCodingSequence == null) {
            if (other.refseqCodingSequence != null)
                return false;
        } else if (!refseqCodingSequence.equals(other.refseqCodingSequence))
            return false;
        if (startLocation == null) {
            if (other.startLocation != null)
                return false;
        } else if (!startLocation.equals(other.startLocation))
            return false;
        return true;
    }

}
