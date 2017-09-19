package org.renci.canvas.dao.ref.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "ref", name = "genome_ref_seq", indexes = { @Index(name = "genome_ref_seq_contig_idx", columnList = "contig") })
public class GenomeRefSeq implements Persistable<String> {

    private static final long serialVersionUID = 8237639060154518282L;

    @Id
    @Column(name = "ver_accession")
    private String id;

    @Column(name = "contig")
    private String contig;

    @ManyToOne
    @JoinColumn(name = "seq_type")
    private SequenceType sequenceType;

    @Column(name = "descr", length = 1023)
    private String description;

    @ManyToMany(mappedBy = "genomeRefSeqs", fetch = FetchType.LAZY)
    private Set<GenomeRef> genomeRefs;

    @OneToMany(mappedBy = "genomeRefSeq", fetch = FetchType.LAZY)
    private Set<GenomeRefSeqLocation> locations;

    public GenomeRefSeq() {
        super();
    }

    public GenomeRefSeq(String id, String contig, String description) {
        super();
        this.id = id;
        this.contig = contig;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContig() {
        return contig;
    }

    public void setContig(String contig) {
        this.contig = contig;
    }

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GenomeRef> getGenomeRefs() {
        return genomeRefs;
    }

    public void setGenomeRefs(Set<GenomeRef> genomeRefs) {
        this.genomeRefs = genomeRefs;
    }

    public Set<GenomeRefSeqLocation> getLocations() {
        return locations;
    }

    public void setLocations(Set<GenomeRefSeqLocation> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return String.format("GenomeRefSeq [id=%s, contig=%s, description=%s]", id, contig, description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contig == null) ? 0 : contig.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        GenomeRefSeq other = (GenomeRefSeq) obj;
        if (contig == null) {
            if (other.contig != null)
                return false;
        } else if (!contig.equals(other.contig))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
