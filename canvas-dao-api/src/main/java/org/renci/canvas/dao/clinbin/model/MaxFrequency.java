package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "max_freq")
@NamedEntityGraph(name = "clinbin.MaxFrequency.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "locatedVariant"),
        @NamedAttributeNode(value = "source") })
public class MaxFrequency implements Persistable<MaxFrequencyPK> {

    private static final long serialVersionUID = -2401541418491242656L;

    @EmbeddedId
    private MaxFrequencyPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "max_allele_freq")
    private Double maxAlleleFreq;

    @ManyToOne
    @JoinColumn(name = "source")
    private MaxFrequencySource source;

    public MaxFrequency() {
        super();
    }

    public MaxFrequency(MaxFrequencyPK id, MaxFrequencySource source) {
        super();
        this.id = id;
        this.source = source;
    }

    public MaxFrequencyPK getId() {
        return id;
    }

    public void setId(MaxFrequencyPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public Double getMaxAlleleFreq() {
        return maxAlleleFreq;
    }

    public void setMaxAlleleFreq(Double maxAlleleFreq) {
        this.maxAlleleFreq = maxAlleleFreq;
    }

    public MaxFrequencySource getSource() {
        return source;
    }

    public void setSource(MaxFrequencySource source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return String.format("MaxFrequency [id=%s, maxAlleleFreq=%s, source=%s]", id, maxAlleleFreq, source);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((maxAlleleFreq == null) ? 0 : maxAlleleFreq.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
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
        MaxFrequency other = (MaxFrequency) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (maxAlleleFreq == null) {
            if (other.maxAlleleFreq != null)
                return false;
        } else if (!maxAlleleFreq.equals(other.maxAlleleFreq))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        return true;
    }

}
