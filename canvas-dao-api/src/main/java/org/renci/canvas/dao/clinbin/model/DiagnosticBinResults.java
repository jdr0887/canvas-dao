package org.renci.canvas.dao.clinbin.model;

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
@Table(schema = "clinbin", name = "diagnostic_bin_results", indexes = {
        @Index(name = "diagnostic_bin_results_class_id_idx", columnList = "class_id"),
        @Index(name = "diagnostic_bin_results_loc_var_id_idx", columnList = "loc_var_id") })
public class DiagnosticBinResults implements Persistable<DiagnosticBinResultsPK> {

    private static final long serialVersionUID = -4066677132203326435L;

    @EmbeddedId
    private DiagnosticBinResultsPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @MapsId("dx")
    @ManyToOne
    @JoinColumn(name = "dx_id")
    private DX dx;

    @MapsId("diseaseClass")
    @ManyToOne
    @JoinColumn(name = "class_id")
    private DiseaseClass diseaseClass;

    public DiagnosticBinResults() {
        super();
    }

    public DiagnosticBinResultsPK getId() {
        return id;
    }

    public void setId(DiagnosticBinResultsPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public DX getDx() {
        return dx;
    }

    public void setDx(DX dx) {
        this.dx = dx;
    }

    public DiseaseClass getDiseaseClass() {
        return diseaseClass;
    }

    public void setDiseaseClass(DiseaseClass diseaseClass) {
        this.diseaseClass = diseaseClass;
    }

    @Override
    public String toString() {
        return String.format("DiagnosticBinResults [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((diseaseClass == null) ? 0 : diseaseClass.hashCode());
        result = prime * result + ((dx == null) ? 0 : dx.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((locatedVariant == null) ? 0 : locatedVariant.hashCode());
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
        DiagnosticBinResults other = (DiagnosticBinResults) obj;
        if (diseaseClass == null) {
            if (other.diseaseClass != null)
                return false;
        } else if (!diseaseClass.equals(other.diseaseClass))
            return false;
        if (dx == null) {
            if (other.dx != null)
                return false;
        } else if (!dx.equals(other.dx))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (locatedVariant == null) {
            if (other.locatedVariant != null)
                return false;
        } else if (!locatedVariant.equals(other.locatedVariant))
            return false;
        return true;
    }

}
