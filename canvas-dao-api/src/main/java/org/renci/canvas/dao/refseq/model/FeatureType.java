package org.renci.canvas.dao.refseq.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "feature_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class FeatureType implements Persistable<String> {

    private static final long serialVersionUID = 1657210597612945365L;

    @Id
    @Column(name = "type_name", length = 31)
    private String id;

    public FeatureType() {
        super();
    }

    public FeatureType(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("FeatureTypes [id=%s]", id);
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
        FeatureType other = (FeatureType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
