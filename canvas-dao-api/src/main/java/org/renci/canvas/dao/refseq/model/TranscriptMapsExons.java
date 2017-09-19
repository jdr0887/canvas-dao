package org.renci.canvas.dao.refseq.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.Range;
import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "transcr_maps_exons")
@NamedEntityGraphs({ @NamedEntityGraph(name = "refseq.TranscriptMapsExons.includeManyToOnes", attributeNodes = {
        @NamedAttributeNode(value = "transcriptMaps") }) })
public class TranscriptMapsExons implements Persistable<TranscriptMapsExonsPK> {

    private static final long serialVersionUID = 1925719918965835160L;

    @EmbeddedId
    private TranscriptMapsExonsPK id;

    @MapsId("transcriptMapsId")
    @ManyToOne
    @JoinColumn(name = "refseq_transcr_maps_id", nullable = false)
    private TranscriptMaps transcriptMaps;

    @Transient
    private RegionType regionType = RegionType.EXON;

    @Column(name = "transcr_start")
    private Integer transcriptStart;

    @Column(name = "transcr_end")
    private Integer transcriptEnd;

    @Column(name = "contig_start")
    private Integer contigStart;

    @Column(name = "contig_end")
    private Integer contigEnd;

    public TranscriptMapsExons() {
        super();
    }

    public TranscriptMapsExons(TranscriptMapsExonsPK key) {
        super();
        this.id = key;
    }

    public RegionType getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public TranscriptMaps getTranscriptMaps() {
        return transcriptMaps;
    }

    public void setTranscriptMaps(TranscriptMaps transcriptMaps) {
        this.transcriptMaps = transcriptMaps;
    }

    public TranscriptMapsExonsPK getId() {
        return id;
    }

    public void setId(TranscriptMapsExonsPK id) {
        this.id = id;
    }

    public Integer getTranscriptStart() {
        return transcriptStart;
    }

    public void setTranscriptStart(Integer transcriptStart) {
        this.transcriptStart = transcriptStart;
    }

    public Integer getTranscriptEnd() {
        return transcriptEnd;
    }

    public void setTranscriptEnd(Integer transcriptEnd) {
        this.transcriptEnd = transcriptEnd;
    }

    public Integer getContigStart() {
        return contigStart;
    }

    public void setContigStart(Integer contigStart) {
        this.contigStart = contigStart;
    }

    public Integer getContigEnd() {
        return contigEnd;
    }

    public void setContigEnd(Integer contigEnd) {
        this.contigEnd = contigEnd;
    }

    public Range<Integer> getContigRange() {
        return Range.between(this.contigStart, this.contigEnd);
    }

    public Range<Integer> getTranscriptRange() {
        if (transcriptStart != null && transcriptEnd != null) {
            return Range.between(this.transcriptStart, this.transcriptEnd);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("TranscriptMapsExons [id=%s, transcriptStart=%s, transcriptEnd=%s, contigStart=%s, contigEnd=%s]", id,
                transcriptStart, transcriptEnd, contigStart, contigEnd);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contigEnd == null) ? 0 : contigEnd.hashCode());
        result = prime * result + ((contigStart == null) ? 0 : contigStart.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((transcriptEnd == null) ? 0 : transcriptEnd.hashCode());
        result = prime * result + ((transcriptStart == null) ? 0 : transcriptStart.hashCode());
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
        TranscriptMapsExons other = (TranscriptMapsExons) obj;
        if (contigEnd == null) {
            if (other.contigEnd != null)
                return false;
        } else if (!contigEnd.equals(other.contigEnd))
            return false;
        if (contigStart == null) {
            if (other.contigStart != null)
                return false;
        } else if (!contigStart.equals(other.contigStart))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (transcriptEnd == null) {
            if (other.transcriptEnd != null)
                return false;
        } else if (!transcriptEnd.equals(other.transcriptEnd))
            return false;
        if (transcriptStart == null) {
            if (other.transcriptStart != null)
                return false;
        } else if (!transcriptStart.equals(other.transcriptStart))
            return false;
        return true;
    }

}
