package org.renci.canvas.dao.dbsnp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "dbsnp", name = "snp_allele_freq", indexes = { @Index(name = "snp_allele_freq_snp_id_idx", columnList = "snp_id") })
public class SNPAlleleFrequency implements Persistable<SNPAlleleFrequencyPK> {

    private static final long serialVersionUID = 3304971565225295358L;

    @EmbeddedId
    private SNPAlleleFrequencyPK id;

    @MapsId("snp")
    @ManyToOne
    @JoinColumn(name = "snp_id")
    private SNP snp;

    @Column(name = "chr_count")
    private Integer chromosomeCount;

    @Column(name = "allele_freq")
    private Double alleleFrequency;

    public SNPAlleleFrequency() {
        super();
    }

    public SNPAlleleFrequency(SNPAlleleFrequencyPK id) {
        super();
        this.id = id;
    }

    public SNPAlleleFrequencyPK getId() {
        return id;
    }

    public void setId(SNPAlleleFrequencyPK id) {
        this.id = id;
    }

    public SNP getSnp() {
        return snp;
    }

    public void setSnp(SNP snp) {
        this.snp = snp;
    }

    public Integer getChromosomeCount() {
        return chromosomeCount;
    }

    public void setChromosomeCount(Integer chromosomeCount) {
        this.chromosomeCount = chromosomeCount;
    }

    public Double getAlleleFrequency() {
        return alleleFrequency;
    }

    public void setAlleleFrequency(Double alleleFrequency) {
        this.alleleFrequency = alleleFrequency;
    }

    @Override
    public String toString() {
        return String.format("SNPAlleleFrequency [id=%s, chromosomeCount=%s, alleleFrequency=%s]", id, chromosomeCount, alleleFrequency);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alleleFrequency == null) ? 0 : alleleFrequency.hashCode());
        result = prime * result + ((chromosomeCount == null) ? 0 : chromosomeCount.hashCode());
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
        SNPAlleleFrequency other = (SNPAlleleFrequency) obj;
        if (alleleFrequency == null) {
            if (other.alleleFrequency != null)
                return false;
        } else if (!alleleFrequency.equals(other.alleleFrequency))
            return false;
        if (chromosomeCount == null) {
            if (other.chromosomeCount != null)
                return false;
        } else if (!chromosomeCount.equals(other.chromosomeCount))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
