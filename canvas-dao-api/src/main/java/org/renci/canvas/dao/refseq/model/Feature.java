package org.renci.canvas.dao.refseq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "feature", indexes = {
        @Index(name = "feature_feature_type_type_name_idx", columnList = "feature_type_type_name"),
        @Index(name = "feature_loc_region_group_id_idx", columnList = "loc_region_group_id") })
@NamedEntityGraphs({ @NamedEntityGraph(name = "refseq.Feature.includeManyToOnes", attributeNodes = {
        @NamedAttributeNode(value = "regionGroup", subgraph = "regionGroup.includeManyToOnes"),
        @NamedAttributeNode(value = "type") }, subgraphs = { @NamedSubgraph(name = "regionGroup.includeManyToOnes", attributeNodes = {
                @NamedAttributeNode(value = "transcript"), @NamedAttributeNode(value = "groupingType") }) }) })
public class Feature implements Persistable<Integer> {

    private static final long serialVersionUID = -3021365878092927482L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_refseq_feature_id_seq")
    @SequenceGenerator(name = "feature_refseq_feature_id_seq", schema = "refseq", sequenceName = "feature_refseq_feature_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "refseq_feature_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "feature_type_type_name")
    private FeatureType type;

    @Column(name = "refseq_ver")
    private String refseqVersion;

    @Column(name = "note", length = 1023)
    private String note;

    @ManyToOne
    @JoinColumn(name = "loc_region_group_id")
    private RegionGroup regionGroup;

    public Feature() {
        super();
    }

    public Feature(String refseqVersion, String note) {
        this();
        this.refseqVersion = refseqVersion;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FeatureType getType() {
        return type;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }

    public String getRefseqVersion() {
        return refseqVersion;
    }

    public void setRefseqVersion(String refseqVersion) {
        this.refseqVersion = refseqVersion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RegionGroup getRegionGroup() {
        return regionGroup;
    }

    public void setRegionGroup(RegionGroup regionGroup) {
        this.regionGroup = regionGroup;
    }

    @Override
    public String toString() {
        return String.format("Feature [id=%s, refseqVersion=%s, note=%s]", id, refseqVersion, note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((refseqVersion == null) ? 0 : refseqVersion.hashCode());
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
        Feature other = (Feature) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (refseqVersion == null) {
            if (other.refseqVersion != null)
                return false;
        } else if (!refseqVersion.equals(other.refseqVersion))
            return false;
        return true;
    }

}
