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
@Table(schema = "refseq", name = "grouping_type")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class GroupingType implements Persistable<String> {

    private static final long serialVersionUID = 117432818698151907L;

    @Id
    @Column(name = "grouping_type_name", length = 15)
    private String id;

    public GroupingType() {
        super();
    }

    public GroupingType(String id) {
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
        return String.format("GroupingType [id=%s]", id);
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
        GroupingType other = (GroupingType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
