package org.renci.canvas.dao.clinvar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinvar", name = "traitsets")
public class TraitSet implements Persistable<Integer> {

    private static final long serialVersionUID = -936424977677968224L;

    @Id
    @Column(name = "trait_set_id")
    private Integer id;

    @Column(name = "trait_set_type")
    private String type;

    @ManyToMany(targetEntity = Trait.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(schema = "clinvar", name = "traitsets_to_traits", indexes = {
            @Index(name = "traitsets_to_traits_trait_id_idx", columnList = "trait_id"),
            @Index(name = "traitsets_to_traits_trait_set_id_idx", columnList = "trait_set_id") }, joinColumns = @JoinColumn(name = "trait_set_id"), inverseJoinColumns = @JoinColumn(name = "trait_id"))
    private Set<Trait> traits;

    public TraitSet() {
        super();
        this.traits = new HashSet<>();
    }

    public TraitSet(Integer id, String type) {
        this();
        this.id = id;
        this.type = type;
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

    public Set<Trait> getTraits() {
        return traits;
    }

    public void setTraits(Set<Trait> traits) {
        this.traits = traits;
    }

    @Override
    public String toString() {
        return String.format("TraitSet [id=%s, type=%s]", id, type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        TraitSet other = (TraitSet) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
