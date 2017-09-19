package org.renci.canvas.dao.refseq.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "feature_qualifiers")
public class FeatureQualifiers implements Persistable<FeatureQualifiersPK> {

    private static final long serialVersionUID = -3311478922232286832L;

    @EmbeddedId
    private FeatureQualifiersPK id;

    public FeatureQualifiers() {
        super();
    }

    public FeatureQualifiersPK getId() {
        return id;
    }

    public void setId(FeatureQualifiersPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("FeatureQualifiers [key=%s]", id);
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
        FeatureQualifiers other = (FeatureQualifiers) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
