package org.renci.canvas.dao.hgmd.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "hgmd", name = "hgmd_loc_var", indexes = { @Index(name = "hgmd_loc_var_loc_var_id_idx", columnList = "loc_var_id") })
public class HGMDLocatedVariant implements Persistable<HGMDLocatedVariantPK> {

    private static final long serialVersionUID = -2133260169454893320L;

    @EmbeddedId
    private HGMDLocatedVariantPK id;

    @Column(name = "tag")
    private String tag;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    public HGMDLocatedVariant() {
        super();
    }

    public HGMDLocatedVariantPK getId() {
        return id;
    }

    public void setId(HGMDLocatedVariantPK id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    @Override
    public String toString() {
        return String.format("HGMDLocatedVariant [id=%s, tag=%s]", id, tag);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
        HGMDLocatedVariant other = (HGMDLocatedVariant) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        return true;
    }

}
