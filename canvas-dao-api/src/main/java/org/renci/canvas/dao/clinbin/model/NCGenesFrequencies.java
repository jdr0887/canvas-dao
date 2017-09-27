package org.renci.canvas.dao.clinbin.model;

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
@Table(schema = "clinbin", name = "ncgenes_frequencies", indexes = {
        @Index(name = "ncgenes_frequencies_loc_var_id_idx", columnList = "loc_var_id") })
public class NCGenesFrequencies implements Persistable<NCGenesFrequenciesPK> {

    private static final long serialVersionUID = -3413773809175450239L;

    @EmbeddedId
    private NCGenesFrequenciesPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "num_hom_ref")
    private Integer numberHomRef;

    @Column(name = "num_het")
    private Integer numHet;

    @Column(name = "num_hom_alt")
    private Integer numHomAlt;

    @Column(name = "alt_allele_freq")
    private Double altAlleleFrequency;

    @Column(name = "hwe_p")
    private Double hweP;

    public NCGenesFrequencies() {
        super();
    }

    public NCGenesFrequencies(NCGenesFrequenciesPK id) {
        super();
        this.id = id;
    }

    public NCGenesFrequenciesPK getId() {
        return id;
    }

    public void setId(NCGenesFrequenciesPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public Integer getNumberHomRef() {
        return numberHomRef;
    }

    public void setNumberHomRef(Integer numberHomRef) {
        this.numberHomRef = numberHomRef;
    }

    public Integer getNumHet() {
        return numHet;
    }

    public void setNumHet(Integer numHet) {
        this.numHet = numHet;
    }

    public Integer getNumHomAlt() {
        return numHomAlt;
    }

    public void setNumHomAlt(Integer numHomAlt) {
        this.numHomAlt = numHomAlt;
    }

    public Double getAltAlleleFrequency() {
        return altAlleleFrequency;
    }

    public void setAltAlleleFrequency(Double altAlleleFrequency) {
        this.altAlleleFrequency = altAlleleFrequency;
    }

    public Double getHweP() {
        return hweP;
    }

    public void setHweP(Double hweP) {
        this.hweP = hweP;
    }

    @Override
    public String toString() {
        return String.format("NCGenesFrequencies [id=%s, numberHomRef=%s, numHet=%s, numHomAlt=%s, altAlleleFrequency=%s, hweP=%s]", id,
                numberHomRef, numHet, numHomAlt, altAlleleFrequency, hweP);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((altAlleleFrequency == null) ? 0 : altAlleleFrequency.hashCode());
        result = prime * result + ((hweP == null) ? 0 : hweP.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((numHet == null) ? 0 : numHet.hashCode());
        result = prime * result + ((numHomAlt == null) ? 0 : numHomAlt.hashCode());
        result = prime * result + ((numberHomRef == null) ? 0 : numberHomRef.hashCode());
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
        NCGenesFrequencies other = (NCGenesFrequencies) obj;
        if (altAlleleFrequency == null) {
            if (other.altAlleleFrequency != null)
                return false;
        } else if (!altAlleleFrequency.equals(other.altAlleleFrequency))
            return false;
        if (hweP == null) {
            if (other.hweP != null)
                return false;
        } else if (!hweP.equals(other.hweP))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (numHet == null) {
            if (other.numHet != null)
                return false;
        } else if (!numHet.equals(other.numHet))
            return false;
        if (numHomAlt == null) {
            if (other.numHomAlt != null)
                return false;
        } else if (!numHomAlt.equals(other.numHomAlt))
            return false;
        if (numberHomRef == null) {
            if (other.numberHomRef != null)
                return false;
        } else if (!numberHomRef.equals(other.numberHomRef))
            return false;
        return true;
    }

}
