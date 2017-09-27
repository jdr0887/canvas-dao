package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.ref.model.GenomeRef;

@Entity
@Table(schema = "clinbin", name = "incidental_result_version", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "ref_id", "refseq_version", "hgmd_version", "gen1000_snp_version", "gen1000_indel_version",
                "ibin_group_version", "binning_algorithm_version" }) }, indexes = {
                        @Index(name = "incidental_result_version_ref_id_idx", columnList = "ref_id") })
public class IncidentalResultVersion implements Persistable<Integer> {

    private static final long serialVersionUID = 8796340369982243885L;

    @Id
    @Column(name = "binning_result_version")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ref_id")
    private GenomeRef genomeRef;

    @Column(name = "refseq_version")
    private Integer refseqVersion;

    @Column(name = "hgmd_version")
    private Integer hgmdVersion;

    @Column(name = "gen1000_snp_version")
    private Integer gen1000SnpVersion;

    @Column(name = "gen1000_indel_version")
    private Integer gen1000IndelVersion;

    @Column(name = "ibin_group_version")
    private Integer ibinGroupVersion;

    @Column(name = "binning_algorithm_version")
    private Integer binningAlgorithmVersion;

    public IncidentalResultVersion() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GenomeRef getGenomeRef() {
        return genomeRef;
    }

    public void setGenomeRef(GenomeRef genomeRef) {
        this.genomeRef = genomeRef;
    }

    public Integer getRefseqVersion() {
        return refseqVersion;
    }

    public void setRefseqVersion(Integer refseqVersion) {
        this.refseqVersion = refseqVersion;
    }

    public Integer getHgmdVersion() {
        return hgmdVersion;
    }

    public void setHgmdVersion(Integer hgmdVersion) {
        this.hgmdVersion = hgmdVersion;
    }

    public Integer getGen1000SnpVersion() {
        return gen1000SnpVersion;
    }

    public void setGen1000SnpVersion(Integer gen1000SnpVersion) {
        this.gen1000SnpVersion = gen1000SnpVersion;
    }

    public Integer getGen1000IndelVersion() {
        return gen1000IndelVersion;
    }

    public void setGen1000IndelVersion(Integer gen1000IndelVersion) {
        this.gen1000IndelVersion = gen1000IndelVersion;
    }

    public Integer getIbinGroupVersion() {
        return ibinGroupVersion;
    }

    public void setIbinGroupVersion(Integer ibinGroupVersion) {
        this.ibinGroupVersion = ibinGroupVersion;
    }

    public Integer getBinningAlgorithmVersion() {
        return binningAlgorithmVersion;
    }

    public void setBinningAlgorithmVersion(Integer binningAlgorithmVersion) {
        this.binningAlgorithmVersion = binningAlgorithmVersion;
    }

    @Override
    public String toString() {
        return String.format(
                "IncidentalResultVersion [id=%s, refseqVersion=%s, hgmdVersion=%s, gen1000SnpVersion=%s, gen1000IndelVersion=%s, ibinGroupVersion=%s, binningAlgorithmVersion=%s]",
                id, refseqVersion, hgmdVersion, gen1000SnpVersion, gen1000IndelVersion, ibinGroupVersion, binningAlgorithmVersion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((binningAlgorithmVersion == null) ? 0 : binningAlgorithmVersion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((gen1000IndelVersion == null) ? 0 : gen1000IndelVersion.hashCode());
        result = prime * result + ((gen1000SnpVersion == null) ? 0 : gen1000SnpVersion.hashCode());
        result = prime * result + ((hgmdVersion == null) ? 0 : hgmdVersion.hashCode());
        result = prime * result + ((ibinGroupVersion == null) ? 0 : ibinGroupVersion.hashCode());
        result = prime * result + ((refseqVersion == null) ? 0 : refseqVersion.hashCode());
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
        IncidentalResultVersion other = (IncidentalResultVersion) obj;
        if (binningAlgorithmVersion == null) {
            if (other.binningAlgorithmVersion != null)
                return false;
        } else if (!binningAlgorithmVersion.equals(other.binningAlgorithmVersion))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (gen1000IndelVersion == null) {
            if (other.gen1000IndelVersion != null)
                return false;
        } else if (!gen1000IndelVersion.equals(other.gen1000IndelVersion))
            return false;
        if (gen1000SnpVersion == null) {
            if (other.gen1000SnpVersion != null)
                return false;
        } else if (!gen1000SnpVersion.equals(other.gen1000SnpVersion))
            return false;
        if (hgmdVersion == null) {
            if (other.hgmdVersion != null)
                return false;
        } else if (!hgmdVersion.equals(other.hgmdVersion))
            return false;
        if (ibinGroupVersion == null) {
            if (other.ibinGroupVersion != null)
                return false;
        } else if (!ibinGroupVersion.equals(other.ibinGroupVersion))
            return false;
        if (refseqVersion == null) {
            if (other.refseqVersion != null)
                return false;
        } else if (!refseqVersion.equals(other.refseqVersion))
            return false;
        return true;
    }

}
