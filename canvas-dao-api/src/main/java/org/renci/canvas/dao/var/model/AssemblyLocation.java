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
import org.renci.canvas.dao.ref.model.GenomeRefSeq;

@Entity
@Table(schema = "var", name = "asm_loc", indexes = { @Index(name = "asm_loc_asm_id_idx", columnList = "asm_id"),
        @Index(name = "asm_loc_ref_ver_accession_idx", columnList = "ref_ver_accession") })
public class AssemblyLocation implements Persistable<AssemblyLocationPK> {

    private static final long serialVersionUID = -6485178807017764493L;

    @EmbeddedId
    private AssemblyLocationPK id;

    @MapsId("assembly")
    @ManyToOne
    @JoinColumn(name = "asm_id")
    private Assembly assembly;

    @MapsId("versionedAccession")
    @ManyToOne
    @JoinColumn(name = "ref_ver_accession")
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "homozygous")
    private Boolean homozygous;

    @Column(name = "genotype_qual")
    private Double genotypeQual;

    public AssemblyLocation() {
        super();
    }

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }

    public GenomeRefSeq getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(GenomeRefSeq genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public AssemblyLocationPK getId() {
        return id;
    }

    public void setId(AssemblyLocationPK id) {
        this.id = id;
    }

    public Boolean getHomozygous() {
        return homozygous;
    }

    public void setHomozygous(Boolean homozygous) {
        this.homozygous = homozygous;
    }

    public Double getGenotypeQual() {
        return genotypeQual;
    }

    public void setGenotypeQual(Double genotypeQual) {
        this.genotypeQual = genotypeQual;
    }

    @Override
    public String toString() {
        return String.format("AssemblyLocation [id=%s, homozygous=%s, genotypeQual=%s]", id, homozygous, genotypeQual);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genotypeQual == null) ? 0 : genotypeQual.hashCode());
        result = prime * result + ((homozygous == null) ? 0 : homozygous.hashCode());
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
        AssemblyLocation other = (AssemblyLocation) obj;
        if (genotypeQual == null) {
            if (other.genotypeQual != null)
                return false;
        } else if (!genotypeQual.equals(other.genotypeQual))
            return false;
        if (homozygous == null) {
            if (other.homozygous != null)
                return false;
        } else if (!homozygous.equals(other.homozygous))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
