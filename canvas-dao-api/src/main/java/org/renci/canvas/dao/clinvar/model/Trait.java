package org.renci.canvas.dao.clinvar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinvar", name = "traits")
public class Trait implements Persistable<Integer> {

    private static final long serialVersionUID = 909710245439246515L;

    @Id
    @Column(name = "trait_id")
    private Integer id;

    @Column(name = "trait_type")
    private String type;

    @Column(name = "preferred_name")
    private String name;

    @ManyToMany(mappedBy = "traits")
    private Set<TraitSet> traitSets;

    public Trait() {
        super();
        this.traitSets = new HashSet<>();
    }

    public Trait(Integer id, String type, String name) {
        this();
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TraitSet> getTraitSets() {
        return traitSets;
    }

    public void setTraitSets(Set<TraitSet> traitSets) {
        this.traitSets = traitSets;
    }

    @Override
    public String toString() {
        return String.format("Trait [id=%s, type=%s, name=%s]", id, type, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Trait other = (Trait) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
