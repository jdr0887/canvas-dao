package org.renci.canvas.dao.refseq.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "transcr")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "refseq.Transcript.includeTranscriptRefSeqVers", attributeNodes = {
                @NamedAttributeNode(value = "refseqVersions") }),
        @NamedEntityGraph(name = "refseq.Transcript.includeAll", attributeNodes = { @NamedAttributeNode(value = "refseqVersions"),
                @NamedAttributeNode(value = "transcriptMaps"), @NamedAttributeNode(value = "regionGroups") }) })
public class Transcript implements Persistable<String> {

    private static final long serialVersionUID = -2441504727987883964L;

    @Id
    @Column(name = "ver_id", length = 31)
    private String id;

    @Column(name = "accession", length = 31)
    private String accession;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "seq")
    private String seq;

    @OneToMany(mappedBy = "transcript")
    private Set<TranscriptRefSeqVersion> refseqVersions;

    @OneToMany(mappedBy = "transcript")
    private Set<TranscriptMaps> transcriptMaps;

    @OneToMany(mappedBy = "transcript")
    private Set<RegionGroup> regionGroups;

    public Transcript() {
        super();
        this.transcriptMaps = new HashSet<TranscriptMaps>();
        this.refseqVersions = new HashSet<TranscriptRefSeqVersion>();
        this.regionGroups = new HashSet<RegionGroup>();
    }

    public Transcript(String id, String accession, String seq) {
        this();
        this.id = id;
        this.accession = accession;
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Set<TranscriptRefSeqVersion> getRefseqVersions() {
        return refseqVersions;
    }

    public void setRefseqVersions(Set<TranscriptRefSeqVersion> refseqVersions) {
        this.refseqVersions = refseqVersions;
    }

    public Set<TranscriptMaps> getTranscriptMaps() {
        return transcriptMaps;
    }

    public void setTranscriptMaps(Set<TranscriptMaps> transcriptMaps) {
        this.transcriptMaps = transcriptMaps;
    }

    public Set<RegionGroup> getRegionGroups() {
        return regionGroups;
    }

    public void setRegionGroups(Set<RegionGroup> regionGroups) {
        this.regionGroups = regionGroups;
    }

    @Override
    public String toString() {
        return String.format("Transcript [id=%s, accession=%s]", id, accession);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
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
        Transcript other = (Transcript) obj;
        if (accession == null) {
            if (other.accession != null)
                return false;
        } else if (!accession.equals(other.accession))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
