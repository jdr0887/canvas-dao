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
@Table(schema = "refseq", name = "variant_effect")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class VariantEffect implements Persistable<String> {

    private static final long serialVersionUID = 8661033855192807541L;

    @Id
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "variant_effect")
    private String id;

    @Column(name = "priority")
    private Integer priority;

    public VariantEffect() {
        super();
    }

    public VariantEffect(String id, Integer priority) {
        super();
        this.id = id;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("VariantEffect [id=%s, priority=%s]", id, priority);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
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
        VariantEffect other = (VariantEffect) obj;
        if (priority == null) {
            if (other.priority != null)
                return false;
        } else if (!priority.equals(other.priority))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
