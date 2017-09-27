package org.renci.canvas.dao.var.model;

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
@Table(schema = "var", name = "asm_loc_var_qc", indexes = { @Index(name = "asm_loc_var_qc_asm_id_idx", columnList = "asm_id"),
        @Index(name = "asm_loc_var_qc_loc_var_id_idx", columnList = "loc_var_id") })
public class AssemblyLocatedVariantQC implements Persistable<AssemblyLocatedVariantQCPK> {

    private static final long serialVersionUID = -5626899616641958084L;

    @EmbeddedId
    private AssemblyLocatedVariantQCPK id;

    @MapsId("assembly")
    @ManyToOne
    @JoinColumn(name = "asm_id")
    private Assembly assembly;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "qd")
    private Double qualityByDepth;

    @Column(name = "read_pos_rank_sum")
    private Double readPosRankSum;

    @Column(name = "frac_reads_with_dels")
    private Double fracReadsWithDels;

    @Column(name = "hrun")
    private Integer homopolymerRun;

    @Column(name = "strand_score")
    private Double strandScore;

    @Column(name = "ref_depth")
    private Integer refDepth;

    @Column(name = "alt_depth")
    private Integer altDepth;

    public AssemblyLocatedVariantQC() {
        super();
    }

    public AssemblyLocatedVariantQC(AssemblyLocatedVariantQCPK id) {
        super();
        this.id = id;
    }

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public AssemblyLocatedVariantQCPK getId() {
        return id;
    }

    public void setId(AssemblyLocatedVariantQCPK id) {
        this.id = id;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Double getReadPosRankSum() {
        return readPosRankSum;
    }

    public void setReadPosRankSum(Double readPosRankSum) {
        this.readPosRankSum = readPosRankSum;
    }

    public Double getFracReadsWithDels() {
        return fracReadsWithDels;
    }

    public void setFracReadsWithDels(Double fracReadsWithDels) {
        this.fracReadsWithDels = fracReadsWithDels;
    }

    public Double getStrandScore() {
        return strandScore;
    }

    public void setStrandScore(Double strandScore) {
        this.strandScore = strandScore;
    }

    public Integer getRefDepth() {
        return refDepth;
    }

    public void setRefDepth(Integer refDepth) {
        this.refDepth = refDepth;
    }

    public Integer getAltDepth() {
        return altDepth;
    }

    public void setAltDepth(Integer altDepth) {
        this.altDepth = altDepth;
    }

    public Double getQualityByDepth() {
        return qualityByDepth;
    }

    public void setQualityByDepth(Double qualityByDepth) {
        this.qualityByDepth = qualityByDepth;
    }

    public Integer getHomopolymerRun() {
        return homopolymerRun;
    }

    public void setHomopolymerRun(Integer homopolymerRun) {
        this.homopolymerRun = homopolymerRun;
    }

    @Override
    public String toString() {
        return String.format(
                "AssemblyLocatedVariantQC [id=%s, depth=%s, qualityByDepth=%s, readPosRankSum=%s, fracReadsWithDels=%s, homopolymerRun=%s, strandScore=%s, refDepth=%s, altDepth=%s]",
                id, depth, qualityByDepth, readPosRankSum, fracReadsWithDels, homopolymerRun, strandScore, refDepth, altDepth);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((altDepth == null) ? 0 : altDepth.hashCode());
        result = prime * result + ((depth == null) ? 0 : depth.hashCode());
        result = prime * result + ((fracReadsWithDels == null) ? 0 : fracReadsWithDels.hashCode());
        result = prime * result + ((homopolymerRun == null) ? 0 : homopolymerRun.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((qualityByDepth == null) ? 0 : qualityByDepth.hashCode());
        result = prime * result + ((readPosRankSum == null) ? 0 : readPosRankSum.hashCode());
        result = prime * result + ((refDepth == null) ? 0 : refDepth.hashCode());
        result = prime * result + ((strandScore == null) ? 0 : strandScore.hashCode());
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
        AssemblyLocatedVariantQC other = (AssemblyLocatedVariantQC) obj;
        if (altDepth == null) {
            if (other.altDepth != null)
                return false;
        } else if (!altDepth.equals(other.altDepth))
            return false;
        if (depth == null) {
            if (other.depth != null)
                return false;
        } else if (!depth.equals(other.depth))
            return false;
        if (fracReadsWithDels == null) {
            if (other.fracReadsWithDels != null)
                return false;
        } else if (!fracReadsWithDels.equals(other.fracReadsWithDels))
            return false;
        if (homopolymerRun == null) {
            if (other.homopolymerRun != null)
                return false;
        } else if (!homopolymerRun.equals(other.homopolymerRun))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (qualityByDepth == null) {
            if (other.qualityByDepth != null)
                return false;
        } else if (!qualityByDepth.equals(other.qualityByDepth))
            return false;
        if (readPosRankSum == null) {
            if (other.readPosRankSum != null)
                return false;
        } else if (!readPosRankSum.equals(other.readPosRankSum))
            return false;
        if (refDepth == null) {
            if (other.refDepth != null)
                return false;
        } else if (!refDepth.equals(other.refDepth))
            return false;
        if (strandScore == null) {
            if (other.strandScore != null)
                return false;
        } else if (!strandScore.equals(other.strandScore))
            return false;
        return true;
    }

}
