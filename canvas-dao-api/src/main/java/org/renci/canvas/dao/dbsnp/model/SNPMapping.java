package org.renci.canvas.dao.dbsnp.model;

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
@Table(schema = "dbsnp", name = "snp_mapping", indexes = { @Index(name = "snp_mapping_loc_var_id_idx", columnList = "loc_var_id"),
        @Index(name = "snp_mapping_snp_id_idx", columnList = "snp_id") })
public class SNPMapping implements Persistable<SNPMappingPK> {

    private static final long serialVersionUID = -7892149256878922346L;

    @EmbeddedId
    private SNPMappingPK id;

    @MapsId("snp")
    @ManyToOne
    @JoinColumn(name = "snp_id")
    private SNP snp;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    public SNPMapping() {
        super();
    }

    public SNPMapping(SNPMappingPK id) {
        super();
        this.id = id;
    }

    public SNPMappingPK getId() {
        return id;
    }

    public void setId(SNPMappingPK id) {
        this.id = id;
    }

    public SNP getSnp() {
        return snp;
    }

    public void setSnp(SNP snp) {
        this.snp = snp;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    @Override
    public String toString() {
        return String.format("SNPMapping [key=%s]", id);
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
        SNPMapping other = (SNPMapping) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
