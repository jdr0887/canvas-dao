package org.renci.canvas.dao.dbsnp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "dbsnp", name = "snp_mapping_agg")
public class SNPMappingAgg implements Persistable<SNPMappingAggPK> {

    private static final long serialVersionUID = -4600354212595708650L;

    @EmbeddedId
    private SNPMappingAggPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "rs_ids")
    private String rsIds;

    public SNPMappingAgg() {
        super();
    }

    public SNPMappingAgg(SNPMappingAggPK id) {
        super();
        this.id = id;
    }

    public SNPMappingAggPK getId() {
        return id;
    }

    public void setId(SNPMappingAggPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public String getRsIds() {
        return rsIds;
    }

    public void setRsIds(String rsIds) {
        this.rsIds = rsIds;
    }

    @Override
    public String toString() {
        return String.format("SNPMappingAgg [key=%s, rsIds=%s]", id, rsIds);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rsIds == null) ? 0 : rsIds.hashCode());
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
        SNPMappingAgg other = (SNPMappingAgg) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rsIds == null) {
            if (other.rsIds != null)
                return false;
        } else if (!rsIds.equals(other.rsIds))
            return false;
        return true;
    }

}
