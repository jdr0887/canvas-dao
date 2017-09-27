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
@Table(schema = "dbsnp", name = "snp_mapping_warning", indexes = { @Index(name = "snp_mapping_warning_snp_id_idx", columnList = "snp_id"),
        @Index(name = "snp_mapping_warning_src_idx", columnList = "src"),
        @Index(name = "snp_mapping_warning_warning_name_idx", columnList = "warning_name") })
public class SNPMappingWarning implements Persistable<SNPMappingWarningPK> {

    private static final long serialVersionUID = 4545180729831869759L;

    @EmbeddedId
    private SNPMappingWarningPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @MapsId("snp")
    @ManyToOne
    @JoinColumn(name = "snp_id")
    private SNP snp;

    @MapsId("mappingWarning")
    @ManyToOne
    @JoinColumn(name = "warning_name")
    private MappingWarning mappingWarning;

    public SNPMappingWarning() {
        super();
    }

    public SNPMappingWarning(SNPMappingWarningPK id) {
        super();
        this.id = id;
    }

    public SNPMappingWarningPK getId() {
        return id;
    }

    public void setId(SNPMappingWarningPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public SNP getSnp() {
        return snp;
    }

    public void setSnp(SNP snp) {
        this.snp = snp;
    }

    public MappingWarning getMappingWarning() {
        return mappingWarning;
    }

    public void setMappingWarning(MappingWarning mappingWarning) {
        this.mappingWarning = mappingWarning;
    }

    @Override
    public String toString() {
        return String.format("SNPMappingWarning [key=%s]", id);
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
        SNPMappingWarning other = (SNPMappingWarning) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
