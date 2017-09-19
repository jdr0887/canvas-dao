package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Range;
import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.annotation.model.AnnotationGene;

@Entity
@Table(schema = "clinbin", name = "dx_exons", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "list_version", "transcr", "exon", "mapnum" }) })
@NamedEntityGraph(name = "clinbin.DXExons.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "gene") })
public class DXExons implements Persistable<Integer> {

    private static final long serialVersionUID = -1900172062196628883L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dx_exons_dx_exon_id_seq")
    @SequenceGenerator(schema = "clinbin", name = "dx_exons_dx_exon_id_seq", sequenceName = "dx_exons_dx_exon_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "dx_exon_id")
    private Integer id;

    @Column(name = "list_version")
    private Integer listVersion;

    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    @Column(name = "transcr", length = 100)
    private String transcript;

    @Column(name = "exon")
    private Integer exon;

    @Column(name = "chromosome", length = 100)
    private String chromosome;

    @Column(name = "interval_start")
    private Integer intervalStart;

    @Column(name = "interval_end")
    private Integer intervalEnd;

    @Column(name = "mapnum")
    private Integer mapNum = 1;

    public DXExons() {
        super();
    }

    public DXExons(Integer listVersion, AnnotationGene gene, String transcript, Integer exon, String chromosome, Integer intervalStart,
            Integer intervalEnd) {
        super();
        this.listVersion = listVersion;
        this.gene = gene;
        this.transcript = transcript;
        this.exon = exon;
        this.chromosome = chromosome;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }

    public DXExons(Integer listVersion, AnnotationGene gene, String transcript, Integer exon, String chromosome, Integer intervalStart,
            Integer intervalEnd, Integer mapNum) {
        super();
        this.listVersion = listVersion;
        this.gene = gene;
        this.transcript = transcript;
        this.exon = exon;
        this.chromosome = chromosome;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.mapNum = mapNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListVersion() {
        return listVersion;
    }

    public void setListVersion(Integer listVersion) {
        this.listVersion = listVersion;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public Integer getExon() {
        return exon;
    }

    public void setExon(Integer exon) {
        this.exon = exon;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public Integer getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(Integer intervalStart) {
        this.intervalStart = intervalStart;
    }

    public Integer getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(Integer intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public Integer getMapNum() {
        return mapNum;
    }

    public void setMapNum(Integer mapNum) {
        this.mapNum = mapNum;
    }

    public Range<Integer> getIntervalRange() {
        return Range.between(this.intervalStart, this.intervalEnd);
    }

    @Override
    public String toString() {
        return String.format(
                "DXExons [id=%s, listVersion=%s, transcript=%s, exon=%s, chromosome=%s, intervalStart=%s, intervalEnd=%s, mapNum=%s]", id,
                listVersion, transcript, exon, chromosome, intervalStart, intervalEnd, mapNum);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chromosome == null) ? 0 : chromosome.hashCode());
        result = prime * result + ((exon == null) ? 0 : exon.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((intervalEnd == null) ? 0 : intervalEnd.hashCode());
        result = prime * result + ((intervalStart == null) ? 0 : intervalStart.hashCode());
        result = prime * result + ((listVersion == null) ? 0 : listVersion.hashCode());
        result = prime * result + ((mapNum == null) ? 0 : mapNum.hashCode());
        result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
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
        DXExons other = (DXExons) obj;
        if (chromosome == null) {
            if (other.chromosome != null)
                return false;
        } else if (!chromosome.equals(other.chromosome))
            return false;
        if (exon == null) {
            if (other.exon != null)
                return false;
        } else if (!exon.equals(other.exon))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (intervalEnd == null) {
            if (other.intervalEnd != null)
                return false;
        } else if (!intervalEnd.equals(other.intervalEnd))
            return false;
        if (intervalStart == null) {
            if (other.intervalStart != null)
                return false;
        } else if (!intervalStart.equals(other.intervalStart))
            return false;
        if (listVersion == null) {
            if (other.listVersion != null)
                return false;
        } else if (!listVersion.equals(other.listVersion))
            return false;
        if (mapNum == null) {
            if (other.mapNum != null)
                return false;
        } else if (!mapNum.equals(other.mapNum))
            return false;
        if (transcript == null) {
            if (other.transcript != null)
                return false;
        } else if (!transcript.equals(other.transcript))
            return false;
        return true;
    }

}
