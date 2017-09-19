package org.renci.canvas.dao.ref.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "ref", name = "genome_ref_seq_loc")
public class GenomeRefSeqLocation implements Persistable<GenomeRefSeqLocationPK> {

    private static final long serialVersionUID = 8237639060154518282L;

    @EmbeddedId
    private GenomeRefSeqLocationPK id;

    @MapsId("genomeRefSeq")
    @ManyToOne
    @JoinColumn(name = "ver_accession", foreignKey = @ForeignKey(name = "genome_ref_seq_ver_accession_fk"))
    private GenomeRefSeq genomeRefSeq;

    @MapsId("genomeRef")
    @ManyToOne
    @JoinColumn(name = "ref_id", foreignKey = @ForeignKey(name = "genome_ref_seq_ref_id_fk"))
    private GenomeRef genomeRef;

    public GenomeRefSeqLocation() {
        super();
    }

    public GenomeRefSeqLocation(GenomeRefSeqLocationPK id) {
        super();
        this.id = id;
    }

    public GenomeRefSeqLocationPK getId() {
        return id;
    }

    public void setId(GenomeRefSeqLocationPK id) {
        this.id = id;
    }

    public GenomeRefSeq getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(GenomeRefSeq genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public GenomeRef getGenomeRef() {
        return genomeRef;
    }

    public void setGenomeRef(GenomeRef genomeRef) {
        this.genomeRef = genomeRef;
    }

    @Override
    public String toString() {
        return String.format("GenomeRefSeqLocation [id=%s]", id);
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
        GenomeRefSeqLocation other = (GenomeRefSeqLocation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
