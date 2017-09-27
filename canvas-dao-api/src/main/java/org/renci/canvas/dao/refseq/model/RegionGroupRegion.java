package org.renci.canvas.dao.refseq.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "region_group_regions", indexes = {
        @Index(name = "region_group_regions_region_group_id_idx", columnList = "region_group_id") })
// @NamedEntityGraphs({ @NamedEntityGraph(name = "refseq.RegionGroupRegion.includeManyToOnes", attributeNodes = {
// @NamedAttributeNode(value = "regionGroup", subgraph = "regionGroup.includeManyToOnes") }, subgraphs = {
// @NamedSubgraph(name = "regionGroup.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "transcript"),
// @NamedAttributeNode(value = "groupingType") }) }) })
@NamedEntityGraphs({ @NamedEntityGraph(name = "refseq.RegionGroupRegion.includeManyToOnes", attributeNodes = {
        @NamedAttributeNode(value = "regionGroup") }) })
public class RegionGroupRegion implements Persistable<RegionGroupRegionPK> {

    private static final long serialVersionUID = 7705809636894949101L;

    @EmbeddedId
    private RegionGroupRegionPK id;

    @MapsId("regionGroup")
    @ManyToOne
    @JoinColumn(name = "region_group_id")
    private RegionGroup regionGroup;

    public RegionGroupRegion() {
        super();
    }

    public RegionGroupRegion(RegionGroupRegionPK id) {
        super();
        this.id = id;
    }

    public RegionGroupRegionPK getId() {
        return id;
    }

    public void setId(RegionGroupRegionPK id) {
        this.id = id;
    }

    public RegionGroup getRegionGroup() {
        return regionGroup;
    }

    public void setRegionGroup(RegionGroup regionGroup) {
        this.regionGroup = regionGroup;
    }

    @Override
    public String toString() {
        return String.format("RegionGroupRegion [key=%s]", id);
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
        RegionGroupRegion other = (RegionGroupRegion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
