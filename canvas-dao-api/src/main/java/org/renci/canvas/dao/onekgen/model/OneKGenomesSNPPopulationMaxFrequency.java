package org.renci.canvas.dao.onekgen.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "gen1000", name = "snp_pop_max_freq", indexes = {
        @Index(name = "snp_pop_max_freq_loc_var_id_idx", columnList = "loc_var_id") })
public class OneKGenomesSNPPopulationMaxFrequency implements Persistable<OneKGenomesSNPPopulationMaxFrequencyPK> {

    private static final long serialVersionUID = 7870826168521038137L;

    @EmbeddedId
    private OneKGenomesSNPPopulationMaxFrequencyPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "max_allele_freq")
    private Double maxAlleleFrequency;

    public OneKGenomesSNPPopulationMaxFrequency() {
        super();
    }

    public OneKGenomesSNPPopulationMaxFrequency(OneKGenomesSNPPopulationMaxFrequencyPK id) {
        super();
        this.id = id;
    }

    public OneKGenomesSNPPopulationMaxFrequencyPK getId() {
        return id;
    }

    public void setId(OneKGenomesSNPPopulationMaxFrequencyPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public Double getMaxAlleleFrequency() {
        return maxAlleleFrequency;
    }

    public void setMaxAlleleFrequency(Double maxAlleleFrequency) {
        this.maxAlleleFrequency = maxAlleleFrequency;
    }

    @Override
    public String toString() {
        return String.format("SNPPopulationMaxFrequency [id=%s, maxAlleleFrequency=%s]", id, maxAlleleFrequency);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((maxAlleleFrequency == null) ? 0 : maxAlleleFrequency.hashCode());
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
        OneKGenomesSNPPopulationMaxFrequency other = (OneKGenomesSNPPopulationMaxFrequency) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (maxAlleleFrequency == null) {
            if (other.maxAlleleFrequency != null)
                return false;
        } else if (!maxAlleleFrequency.equals(other.maxAlleleFrequency))
            return false;
        return true;
    }

}
