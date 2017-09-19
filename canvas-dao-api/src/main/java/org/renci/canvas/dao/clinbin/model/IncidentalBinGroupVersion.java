package org.renci.canvas.dao.clinbin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinbin", name = "incidental_bin_group_version")
public class IncidentalBinGroupVersion implements Persistable<IncidentalBinGroupVersionPK> {

    private static final long serialVersionUID = 2212074020390950607L;

    @EmbeddedId
    private IncidentalBinGroupVersionPK id;

    @MapsId("incidentalBin")
    @ManyToOne
    @JoinColumn(name = "incidental_bin_id")
    private IncidentalBin incidentalBin;

    public IncidentalBinGroupVersion() {
        super();
    }

    public IncidentalBinGroupVersionPK getId() {
        return id;
    }

    public void setId(IncidentalBinGroupVersionPK id) {
        this.id = id;
    }

    public IncidentalBin getIncidentalBin() {
        return incidentalBin;
    }

    public void setIncidentalBin(IncidentalBin incidentalBin) {
        this.incidentalBin = incidentalBin;
    }

    @Override
    public String toString() {
        return String.format("IncidentalBinGroupVersion [incidentalBin=%s]", incidentalBin);
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
        IncidentalBinGroupVersion other = (IncidentalBinGroupVersion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
