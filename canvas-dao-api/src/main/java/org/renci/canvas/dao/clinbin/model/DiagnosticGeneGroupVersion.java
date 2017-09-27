package org.renci.canvas.dao.clinbin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinbin", name = "diagnostic_gene_group_version", indexes = {
        @Index(name = "diagnostic_gene_group_version_dx_id_idx", columnList = "dx_id") })
public class DiagnosticGeneGroupVersion implements Persistable<DiagnosticGeneGroupVersionPK> {

    private static final long serialVersionUID = -7848676259877544883L;

    @EmbeddedId
    private DiagnosticGeneGroupVersionPK id;

    @MapsId("dx")
    @ManyToOne
    @JoinColumn(name = "dx_id")
    private DX dx;

    public DiagnosticGeneGroupVersion() {
        super();
    }

    public DiagnosticGeneGroupVersion(DiagnosticGeneGroupVersionPK id) {
        super();
        this.id = id;
    }

    public DiagnosticGeneGroupVersionPK getId() {
        return id;
    }

    public void setId(DiagnosticGeneGroupVersionPK id) {
        this.id = id;
    }

    public DX getDx() {
        return dx;
    }

    public void setDx(DX dx) {
        this.dx = dx;
    }

    @Override
    public String toString() {
        return String.format("DiagnosticGeneGroupVersion [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dx == null) ? 0 : dx.hashCode());
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
        DiagnosticGeneGroupVersion other = (DiagnosticGeneGroupVersion) obj;
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
        return true;
    }

}
