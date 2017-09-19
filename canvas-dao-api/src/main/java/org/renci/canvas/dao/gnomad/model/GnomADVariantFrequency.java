package org.renci.canvas.dao.gnomad.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "gnomad", name = "variant_freq")
public class GnomADVariantFrequency implements Persistable<GnomADVariantFrequencyPK> {

    private static final long serialVersionUID = 4359650786462818369L;

    @EmbeddedId
    private GnomADVariantFrequencyPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "alt_allele_freq")
    private Double alternateAlleleFrequency;

    @Column(name = "alt_allele_count")
    private Integer alternateAlleleCount;

    @Column(name = "total_allele_count")
    private Integer totalAlleleCount;

    @Column(name = "hemizygous_count")
    private Integer hemizygousCount;

    @Column(name = "homozygous_count")
    private Integer homozygousCount;

    public GnomADVariantFrequency() {
        super();
    }

    public GnomADVariantFrequency(GnomADVariantFrequencyPK id) {
        super();
        this.id = id;
    }

    public GnomADVariantFrequencyPK getId() {
        return id;
    }

    public void setId(GnomADVariantFrequencyPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public Double getAlternateAlleleFrequency() {
        return alternateAlleleFrequency;
    }

    public void setAlternateAlleleFrequency(Double alternateAlleleFrequency) {
        this.alternateAlleleFrequency = alternateAlleleFrequency;
    }

    public Integer getAlternateAlleleCount() {
        return alternateAlleleCount;
    }

    public void setAlternateAlleleCount(Integer alternateAlleleCount) {
        this.alternateAlleleCount = alternateAlleleCount;
    }

    public Integer getTotalAlleleCount() {
        return totalAlleleCount;
    }

    public void setTotalAlleleCount(Integer totalAlleleCount) {
        this.totalAlleleCount = totalAlleleCount;
    }

    public Integer getHemizygousCount() {
        return hemizygousCount;
    }

    public void setHemizygousCount(Integer hemizygousCount) {
        this.hemizygousCount = hemizygousCount;
    }

    public Integer getHomozygousCount() {
        return homozygousCount;
    }

    public void setHomozygousCount(Integer homozygousCount) {
        this.homozygousCount = homozygousCount;
    }

    @Override
    public String toString() {
        return String.format(
                "GnomADVariantFrequency [id=%s, alternateAlleleFrequency=%s, alternateAlleleCount=%s, totalAlleleCount=%s, hemizygousCount=%s, homozygousCount=%s]",
                id, alternateAlleleFrequency, alternateAlleleCount, totalAlleleCount, hemizygousCount, homozygousCount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alternateAlleleCount == null) ? 0 : alternateAlleleCount.hashCode());
        result = prime * result + ((alternateAlleleFrequency == null) ? 0 : alternateAlleleFrequency.hashCode());
        result = prime * result + ((hemizygousCount == null) ? 0 : hemizygousCount.hashCode());
        result = prime * result + ((homozygousCount == null) ? 0 : homozygousCount.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((totalAlleleCount == null) ? 0 : totalAlleleCount.hashCode());
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
        GnomADVariantFrequency other = (GnomADVariantFrequency) obj;
        if (alternateAlleleCount == null) {
            if (other.alternateAlleleCount != null)
                return false;
        } else if (!alternateAlleleCount.equals(other.alternateAlleleCount))
            return false;
        if (alternateAlleleFrequency == null) {
            if (other.alternateAlleleFrequency != null)
                return false;
        } else if (!alternateAlleleFrequency.equals(other.alternateAlleleFrequency))
            return false;
        if (hemizygousCount == null) {
            if (other.hemizygousCount != null)
                return false;
        } else if (!hemizygousCount.equals(other.hemizygousCount))
            return false;
        if (homozygousCount == null) {
            if (other.homozygousCount != null)
                return false;
        } else if (!homozygousCount.equals(other.homozygousCount))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (totalAlleleCount == null) {
            if (other.totalAlleleCount != null)
                return false;
        } else if (!totalAlleleCount.equals(other.totalAlleleCount))
            return false;
        return true;
    }

}
