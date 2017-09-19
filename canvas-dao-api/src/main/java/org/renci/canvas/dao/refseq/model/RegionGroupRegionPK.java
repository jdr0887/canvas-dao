package org.renci.canvas.dao.refseq.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Range;

@Embeddable
public class RegionGroupRegionPK implements Serializable {

    private static final long serialVersionUID = 679645567028669572L;

    @Column(name = "region_start")
    private Integer regionStart;

    @Column(name = "region_end")
    private Integer regionEnd;

    @Column(name = "start_type")
    private String startType;

    @Column(name = "end_type")
    private String endType;

    @Column(name = "region_group_id")
    private Integer regionGroup;

    public RegionGroupRegionPK() {
        super();
    }

    public RegionGroupRegionPK(Integer regionStart, Integer regionEnd, String startType, String endType, Integer regionGroup) {
        super();
        this.regionStart = regionStart;
        this.regionEnd = regionEnd;
        this.startType = startType;
        this.endType = endType;
        this.regionGroup = regionGroup;
    }

    public Integer getRegionGroup() {
        return regionGroup;
    }

    public void setRegionGroup(Integer regionGroup) {
        this.regionGroup = regionGroup;
    }

    public Integer getRegionStart() {
        return regionStart;
    }

    public void setRegionStart(Integer regionStart) {
        this.regionStart = regionStart;
    }

    public Integer getRegionEnd() {
        return regionEnd;
    }

    public void setRegionEnd(Integer regionEnd) {
        this.regionEnd = regionEnd;
    }

    public Range<Integer> getRegionRange() {
        return Range.between(this.regionStart, this.regionEnd);
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    @Override
    public String toString() {
        return String.format("RegionGroupRegionPK [regionStart=%s, regionEnd=%s, startType=%s, endType=%s, regionGroup=%s]", regionStart,
                regionEnd, startType, endType, regionGroup);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endType == null) ? 0 : endType.hashCode());
        result = prime * result + ((regionEnd == null) ? 0 : regionEnd.hashCode());
        result = prime * result + ((regionGroup == null) ? 0 : regionGroup.hashCode());
        result = prime * result + ((regionStart == null) ? 0 : regionStart.hashCode());
        result = prime * result + ((startType == null) ? 0 : startType.hashCode());
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
        RegionGroupRegionPK other = (RegionGroupRegionPK) obj;
        if (endType == null) {
            if (other.endType != null)
                return false;
        } else if (!endType.equals(other.endType))
            return false;
        if (regionEnd == null) {
            if (other.regionEnd != null)
                return false;
        } else if (!regionEnd.equals(other.regionEnd))
            return false;
        if (regionGroup == null) {
            if (other.regionGroup != null)
                return false;
        } else if (!regionGroup.equals(other.regionGroup))
            return false;
        if (regionStart == null) {
            if (other.regionStart != null)
                return false;
        } else if (!regionStart.equals(other.regionStart))
            return false;
        if (startType == null) {
            if (other.startType != null)
                return false;
        } else if (!startType.equals(other.startType))
            return false;
        return true;
    }

}
