package org.renci.canvas.dao.exac.model;

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
@Table(schema = "exac", name = "variant_freq")
public class ExACVariantFrequency implements Persistable<ExACVariantFrequencyPK> {

    private static final long serialVersionUID = 4359650786462818369L;

    @EmbeddedId
    private ExACVariantFrequencyPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "alt_allele_freq")
    private Double alternateAlleleFrequency;

    @Column(name = "alt_allele_count")
    private Integer alternateAlleleCount;

    // @Column(name = "total_allele_count")
    // private Integer totalAlleleCount;

    public ExACVariantFrequency() {
        super();
    }

    public ExACVariantFrequency(ExACVariantFrequencyPK id) {
        super();
        this.id = id;
    }

    public ExACVariantFrequencyPK getId() {
        return id;
    }

    public void setId(ExACVariantFrequencyPK id) {
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

    // public Integer getTotalAlleleCount() {
    // return totalAlleleCount;
    // }
    //
    // public void setTotalAlleleCount(Integer totalAlleleCount) {
    // this.totalAlleleCount = totalAlleleCount;
    // }

    @Override
    public String toString() {
        return String.format("ExACVariantFrequency [id=%s, alternateAlleleFrequency=%s, alternateAlleleCount=%s]", id,
                alternateAlleleFrequency, alternateAlleleCount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alternateAlleleCount == null) ? 0 : alternateAlleleCount.hashCode());
        result = prime * result + ((alternateAlleleFrequency == null) ? 0 : alternateAlleleFrequency.hashCode());
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
        ExACVariantFrequency other = (ExACVariantFrequency) obj;
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
