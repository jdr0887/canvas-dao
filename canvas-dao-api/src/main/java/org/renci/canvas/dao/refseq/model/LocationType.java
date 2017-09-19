package org.renci.canvas.dao.refseq.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "loc_type")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LocationType implements Persistable<String> {

    private static final long serialVersionUID = -3789288496387120477L;

    @Id
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "loc_type")
    private String id;

    public LocationType() {
        super();
    }

    public LocationType(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    @Override
    public String toString() {
        return String.format("LocationType [id=%s]", id);
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
        LocationType other = (LocationType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
