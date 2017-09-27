package org.renci.canvas.dao.ref.model;

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
@Table(schema = "ref", name = "genome_ref_seq_alt_ids", indexes = {
        @Index(name = "genome_ref_seq_alt_ids_ver_accession_idx", columnList = "ver_accession") })
public class GenomeRefSeqAlternateId implements Persistable<GenomeRefSeqAlternateIdPK> {

    private static final long serialVersionUID = -2418046550597045333L;

    @EmbeddedId
    private GenomeRefSeqAlternateIdPK id;

    @MapsId("genomeRefSeq")
    @ManyToOne
    @JoinColumn(name = "ver_accession", nullable = false)
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "id")
    private String identifier;

    public GenomeRefSeqAlternateId() {
        super();
    }

    public GenomeRefSeqAlternateId(GenomeRefSeqAlternateIdPK id) {
        super();
        this.id = id;
    }

    public GenomeRefSeqAlternateIdPK getId() {
        return id;
    }

    public void setId(GenomeRefSeqAlternateIdPK id) {
        this.id = id;
    }

    public GenomeRefSeq getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(GenomeRefSeq genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return String.format("GenomeRefSeqAlternateId [id=%s, identifier=%s]", id, identifier);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
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
        GenomeRefSeqAlternateId other = (GenomeRefSeqAlternateId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        return true;
    }

}
