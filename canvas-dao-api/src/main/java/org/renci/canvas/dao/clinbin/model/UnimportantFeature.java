package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinbin", name = "unimportant_feature")
public class UnimportantFeature implements Persistable<UnimportantFeaturePK> {

    private static final long serialVersionUID = 1561860572156487981L;

    @EmbeddedId
    private UnimportantFeaturePK id;

    @Column(name = "count")
    private Integer count;

    public UnimportantFeature() {
        super();
    }

    public UnimportantFeaturePK getId() {
        return id;
    }

    public void setId(UnimportantFeaturePK id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("UnimportantFeature [count=%s]", count);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((count == null) ? 0 : count.hashCode());
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
        UnimportantFeature other = (UnimportantFeature) obj;
        if (count == null) {
            if (other.count != null)
                return false;
        } else if (!count.equals(other.count))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}