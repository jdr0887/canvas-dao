package org.renci.canvas.dao.var.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssemblyLocationPK implements Serializable {

    private static final long serialVersionUID = 745714824781965526L;

    @Column(name = "asm_id")
    private Integer assembly;

    @Column(name = "ref_ver_accession")
    private String versionedAccession;

    @Column(name = "pos")
    private Integer pos;

    public AssemblyLocationPK() {
        super();
    }

    public Integer getAssembly() {
        return assembly;
    }

    public void setAssembly(Integer assembly) {
        this.assembly = assembly;
    }

    public String getVersionedAccession() {
        return versionedAccession;
    }

    public void setVersionedAccession(String versionedAccession) {
        this.versionedAccession = versionedAccession;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return String.format("AssemblyLocationPK [assembly=%s, versionAccession=%s, pos=%s]", assembly, versionedAccession, pos);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((assembly == null) ? 0 : assembly.hashCode());
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        result = prime * result + ((versionedAccession == null) ? 0 : versionedAccession.hashCode());
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
        AssemblyLocationPK other = (AssemblyLocationPK) obj;
        if (assembly == null) {
            if (other.assembly != null)
                return false;
        } else if (!assembly.equals(other.assembly))
            return false;
        if (pos == null) {
            if (other.pos != null)
                return false;
        } else if (!pos.equals(other.pos))
            return false;
        if (versionedAccession == null) {
            if (other.versionedAccession != null)
                return false;
        } else if (!versionedAccession.equals(other.versionedAccession))
            return false;
        return true;
    }

}
