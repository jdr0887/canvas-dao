package org.renci.canvas.dao.refseq.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "region_group", indexes = {
        @Index(name = "region_group_transcr_ver_id_idx", columnList = "transcr_ver_id"),
        @Index(name = "region_group_grouping_type_idx", columnList = "grouping_type") })
@NamedEntityGraphs({
        @NamedEntityGraph(name = "refseq.RegionGroup.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "transcript"),
                @NamedAttributeNode(value = "groupingType") }),
        @NamedEntityGraph(name = "refseq.RegionGroup.includeRegionGroupRegions", attributeNodes = {
                @NamedAttributeNode(value = "transcript"), @NamedAttributeNode(value = "groupingType"),
                @NamedAttributeNode(value = "regionGroupRegions") }) })
public class RegionGroup implements Persistable<Integer> {

    private static final long serialVersionUID = -6293737799108423842L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_group_region_group_id_seq")
    @SequenceGenerator(name = "region_group_region_group_id_seq", schema = "refseq", sequenceName = "region_group_region_group_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "region_group_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transcr_ver_id")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "grouping_type")
    private GroupingType groupingType;

    @OneToMany(mappedBy = "regionGroup")
    protected Set<RegionGroupRegion> regionGroupRegions;

    @OneToMany(mappedBy = "regionGroup")
    protected Set<Feature> features;

    @ManyToMany(mappedBy = "locations")
    private Set<RefSeqGene> refSeqGenes;

    @ManyToMany(mappedBy = "locations")
    private Set<RefSeqCodingSequence> refSeqCodingSequence;

    public RegionGroup() {
        super();
        this.refSeqCodingSequence = new HashSet<>();
        this.refSeqGenes = new HashSet<>();
    }

    public Set<RefSeqCodingSequence> getRefSeqCodingSequence() {
        return refSeqCodingSequence;
    }

    public void setRefSeqCodingSequence(Set<RefSeqCodingSequence> refSeqCodingSequence) {
        this.refSeqCodingSequence = refSeqCodingSequence;
    }

    public Set<RefSeqGene> getRefSeqGenes() {
        return refSeqGenes;
    }

    public void setRefSeqGenes(Set<RefSeqGene> refSeqGenes) {
        this.refSeqGenes = refSeqGenes;
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

    public GroupingType getGroupingType() {
        return groupingType;
    }

    public void setGroupingType(GroupingType groupingType) {
        this.groupingType = groupingType;
    }

    public Set<RegionGroupRegion> getRegionGroupRegions() {
        return regionGroupRegions;
    }

    public void setRegionGroupRegions(Set<RegionGroupRegion> regionGroupRegions) {
        this.regionGroupRegions = regionGroupRegions;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return String.format("RegionGroup [id=%s]", id);
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
        RegionGroup other = (RegionGroup) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
