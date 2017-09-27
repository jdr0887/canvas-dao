package org.renci.canvas.dao.dbsnp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "dbsnp", name = "snp_allele", indexes = { @Index(name = "snp_allele_snp_id_idx", columnList = "snp_id") })
public class SNPAllele implements Persistable<SNPAllelePK> {

    private static final long serialVersionUID = -5645621093590174075L;

    @EmbeddedId
    private SNPAllelePK id;

    @MapsId("snp")
    @ManyToOne
    @JoinColumn(name = "snp_id")
    private SNP snp;

    public SNPAllele() {
        super();
    }

    public SNPAllelePK getId() {
        return id;
    }

    public void setId(SNPAllelePK id) {
        this.id = id;
    }

    public SNP getSnp() {
        return snp;
    }

    public void setSnp(SNP snp) {
        this.snp = snp;
    }

    @Override
    public String toString() {
        return String.format("SNPAllele [id=%s]", id);
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
        SNPAllele other = (SNPAllele) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
