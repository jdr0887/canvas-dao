package org.renci.canvas.dao.refseq.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "cds_transl_exceptions", indexes = {
        @Index(name = "cds_transl_exceptions_refseq_cds_id_idx", columnList = "refseq_cds_id") })
public class CDSTranslationException implements Persistable<CDSTranslationExceptionPK> {

    private static final long serialVersionUID = 9067053037558120433L;

    @EmbeddedId
    private CDSTranslationExceptionPK id;

    @Column(name = "stop_loc")
    private Integer stopLocation;

    @Column(name = "amino_acid", length = 31)
    private String aminoAcid;

    @MapsId("refseqCodingSequence")
    @ManyToOne
    @JoinColumn(name = "refseq_cds_id", nullable = false)
    private RefSeqCodingSequence refseqCodingSequence;

    public CDSTranslationException() {
        super();
    }

    public CDSTranslationException(CDSTranslationExceptionPK id) {
        super();
        this.id = id;
    }

    public CDSTranslationExceptionPK getId() {
        return id;
    }

    public void setId(CDSTranslationExceptionPK id) {
        this.id = id;
    }

    public String getAminoAcid() {
        return aminoAcid;
    }

    public void setAminoAcid(String aminoAcid) {
        this.aminoAcid = aminoAcid;
    }

    public Integer getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(Integer stopLocation) {
        this.stopLocation = stopLocation;
    }

    public RefSeqCodingSequence getRefseqCodingSequence() {
        return refseqCodingSequence;
    }

    public void setRefseqCodingSequence(RefSeqCodingSequence refseqCodingSequence) {
        this.refseqCodingSequence = refseqCodingSequence;
    }

    @Override
    public String toString() {
        return String.format("CDSTranslationException [id=%s, stopLocation=%s, aminoAcid=%s]", id, stopLocation, aminoAcid);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aminoAcid == null) ? 0 : aminoAcid.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stopLocation == null) ? 0 : stopLocation.hashCode());
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
        CDSTranslationException other = (CDSTranslationException) obj;
        if (aminoAcid == null) {
            if (other.aminoAcid != null)
                return false;
        } else if (!aminoAcid.equals(other.aminoAcid))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stopLocation == null) {
            if (other.stopLocation != null)
                return false;
        } else if (!stopLocation.equals(other.stopLocation))
            return false;
        return true;
    }

}
