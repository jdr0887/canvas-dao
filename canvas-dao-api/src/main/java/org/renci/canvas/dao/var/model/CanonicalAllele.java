package org.renci.canvas.dao.var.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "var", name = "canonical_allele")
public class CanonicalAllele implements Persistable<Integer> {

    private static final long serialVersionUID = 3662167618944075302L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canonical_allele_canonical_allele_id_seq")
    @SequenceGenerator(name = "canonical_allele_canonical_allele_id_seq", schema = "var", sequenceName = "canonical_allele_canonical_allele_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "canonical_allele_id")
    private Integer id;
    
    @ManyToMany(targetEntity = LocatedVariant.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(schema = "var", name = "allele_map", indexes = { @Index(name = "allele_map_loc_var_id_idx", columnList = "loc_var_id"),
            @Index(name = "allele_map_canonical_allele_id_idx", columnList = "canonical_allele_id") }, joinColumns = @JoinColumn(name = "canonical_allele_id"), inverseJoinColumns = @JoinColumn(name = "loc_var_id"))
    private Set<LocatedVariant> locatedVariants;

    public CanonicalAllele() {
        super();
        this.locatedVariants = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<LocatedVariant> getLocatedVariants() {
        return locatedVariants;
    }

    public void setLocatedVariants(Set<LocatedVariant> locatedVariants) {
        this.locatedVariants = locatedVariants;
    }

    @Override
    public String toString() {
        return String.format("CanonicalAllele [id=%s]", id);
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
        CanonicalAllele other = (CanonicalAllele) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
