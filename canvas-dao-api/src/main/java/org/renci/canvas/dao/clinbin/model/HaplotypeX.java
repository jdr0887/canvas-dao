package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "haplotypex", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "haplotype_id", "loc_var_id", "allele" }) })
public class HaplotypeX implements Persistable<Integer> {

    private static final long serialVersionUID = 2392647483088782553L;

    @Id
    @Column(name = "haplotype_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "allele", length = 1024)
    private String allele;

    @Column(name = "rsid", nullable = false)
    private Integer rsId;

    public HaplotypeX() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public String getAllele() {
        return allele;
    }

    public void setAllele(String allele) {
        this.allele = allele;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    @Override
    public String toString() {
        return String.format("HaplotypeX [id=%s, allele=%s, rsId=%s]", id, allele, rsId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rsId == null) ? 0 : rsId.hashCode());
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
        HaplotypeX other = (HaplotypeX) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rsId == null) {
            if (other.rsId != null)
                return false;
        } else if (!rsId.equals(other.rsId))
            return false;
        return true;
    }

}
