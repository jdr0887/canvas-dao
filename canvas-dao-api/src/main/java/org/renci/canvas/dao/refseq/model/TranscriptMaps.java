package org.renci.canvas.dao.refseq.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;

@Entity
@Table(schema = "refseq", name = "transcr_maps")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "refseq.TranscriptMaps.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "transcript"),
                @NamedAttributeNode(value = "genomeRef"),
                @NamedAttributeNode(value = "genomeRefSeq", subgraph = "genomeRefSeq.includeManyToOnes") }, subgraphs = {
                        @NamedSubgraph(name = "genomeRefSeq.includeManyToOnes", attributeNodes = {
                                @NamedAttributeNode(value = "sequenceType") }) }),
        @NamedEntityGraph(name = "refseq.TranscriptMaps.includeAll", attributeNodes = { @NamedAttributeNode(value = "transcript"),
                @NamedAttributeNode(value = "genomeRef"), @NamedAttributeNode(value = "genomeRefSeq"),
                @NamedAttributeNode(value = "exons") }, subgraphs = {
                        @NamedSubgraph(name = "genomeRefSeq.includeManyToOnes", attributeNodes = {
                                @NamedAttributeNode(value = "sequenceType") }) }) })
public class TranscriptMaps implements Persistable<Integer> {

    private static final long serialVersionUID = 8175717803443861686L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transcr_maps_refseq_transcr_maps_id_seq")
    @SequenceGenerator(name = "transcr_maps_refseq_transcr_maps_id_seq", schema = "refseq", sequenceName = "transcr_maps_refseq_transcr_maps_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "refseq_transcr_maps_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "refseq_transcr_ver_id")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "genome_ref_id")
    private GenomeRef genomeRef;

    @Column(name = "map_count")
    private Integer mapCount;

    @Column(name = "strand", length = 1)
    private String strand;

    @Column(name = "score")
    private Double score;

    @Column(name = "ident")
    private Double identity;

    @ManyToOne
    @JoinColumn(name = "seq_ver_accession")
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "exon_count")
    private Integer exonCount;

    @Column(name = "max_contig")
    private Integer maxContig;

    @Column(name = "min_contig")
    private Integer minContig;

    @OneToMany(mappedBy = "transcriptMaps")
    private List<TranscriptMapsExons> exons;

    public TranscriptMaps() {
        super();
        this.exons = new ArrayList<TranscriptMapsExons>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public GenomeRef getGenomeRef() {
        return genomeRef;
    }

    public void setGenomeRef(GenomeRef genomeRef) {
        this.genomeRef = genomeRef;
    }

    public Integer getMapCount() {
        return mapCount;
    }

    public void setMapCount(Integer mapCount) {
        this.mapCount = mapCount;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getIdentity() {
        return identity;
    }

    public void setIdentity(Double identity) {
        this.identity = identity;
    }

    public GenomeRefSeq getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(GenomeRefSeq genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public Integer getExonCount() {
        return exonCount;
    }

    public void setExonCount(Integer exonCount) {
        this.exonCount = exonCount;
    }

    public Integer getMaxContig() {
        return maxContig;
    }

    public void setMaxContig(Integer maxContig) {
        this.maxContig = maxContig;
    }

    public Integer getMinContig() {
        return minContig;
    }

    public void setMinContig(Integer minContig) {
        this.minContig = minContig;
    }

    public List<TranscriptMapsExons> getExons() {
        return exons;
    }

    public void setExons(List<TranscriptMapsExons> exons) {
        this.exons = exons;
    }

    @Override
    public String toString() {
        return String.format(
                "TranscriptMaps [id=%s, mapCount=%s, strand=%s, score=%s, identity=%s, exonCount=%s, maxContig=%s, minContig=%s]", id,
                mapCount, strand, score, identity, exonCount, maxContig, minContig);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((exonCount == null) ? 0 : exonCount.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((identity == null) ? 0 : identity.hashCode());
        result = prime * result + ((mapCount == null) ? 0 : mapCount.hashCode());
        result = prime * result + ((maxContig == null) ? 0 : maxContig.hashCode());
        result = prime * result + ((minContig == null) ? 0 : minContig.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        result = prime * result + ((strand == null) ? 0 : strand.hashCode());
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
        TranscriptMaps other = (TranscriptMaps) obj;
        if (exonCount == null) {
            if (other.exonCount != null)
                return false;
        } else if (!exonCount.equals(other.exonCount))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (identity == null) {
            if (other.identity != null)
                return false;
        } else if (!identity.equals(other.identity))
            return false;
        if (mapCount == null) {
            if (other.mapCount != null)
                return false;
        } else if (!mapCount.equals(other.mapCount))
            return false;
        if (maxContig == null) {
            if (other.maxContig != null)
                return false;
        } else if (!maxContig.equals(other.maxContig))
            return false;
        if (minContig == null) {
            if (other.minContig != null)
                return false;
        } else if (!minContig.equals(other.minContig))
            return false;
        if (score == null) {
            if (other.score != null)
                return false;
        } else if (!score.equals(other.score))
            return false;
        if (strand == null) {
            if (other.strand != null)
                return false;
        } else if (!strand.equals(other.strand))
            return false;
        return true;
    }

}
