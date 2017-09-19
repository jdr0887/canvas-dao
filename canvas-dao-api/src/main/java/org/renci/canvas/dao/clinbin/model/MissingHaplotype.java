package org.renci.canvas.dao.clinbin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "missing_haps")
public class MissingHaplotype implements Persistable<MissingHaplotypePK> {

    private static final long serialVersionUID = -2316188402006932713L;

    @EmbeddedId
    private MissingHaplotypePK id;

    @MapsId("incidentalBin")
    @ManyToOne
    @JoinColumn(name = "incidental_bin_id")
    private IncidentalBinX incidentalBin;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    public MissingHaplotype() {
        super();
    }

    public MissingHaplotype(MissingHaplotypePK id) {
        super();
        this.id = id;
    }

    public MissingHaplotypePK getId() {
        return id;
    }

    public void setId(MissingHaplotypePK id) {
        this.id = id;
    }

    public IncidentalBinX getIncidentalBin() {
        return incidentalBin;
    }

    public void setIncidentalBin(IncidentalBinX incidentalBin) {
        this.incidentalBin = incidentalBin;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    @Override
    public String toString() {
        return String.format("MissingHaplotypes [id=%s]", id);
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
        MissingHaplotype other = (MissingHaplotype) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
