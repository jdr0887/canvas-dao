package org.renci.canvas.dao.clinbin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.clinvar.model.ClinVarVersion;
import org.renci.canvas.dao.ref.model.GenomeRef;

@Entity
@Table(schema = "clinbin", name = "diagnostic_result_version", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "ref_id", "refseq_version", "hgmd_version", "gen1000_snp_version", "gen1000_indel_version",
                "dbin_group_version", "algorithm_version", "dbsnp_version", "vcf_loader_name", "vcf_loader_version" }) })
@NamedEntityGraph(name = "clinbin.DiagnosticResultVersion.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "genomeRef"),
        @NamedAttributeNode(value = "clinvarVersion") })
public class DiagnosticResultVersion implements Persistable<Integer> {

    private static final long serialVersionUID = 8183127521986516469L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnostic_result_version_diagnostic_result_version_seq")
    @SequenceGenerator(schema = "clinbin", name = "diagnostic_result_version_diagnostic_result_version_seq", sequenceName = "diagnostic_result_version_diagnostic_result_version_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "diagnostic_result_version")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ref_id")
    private GenomeRef genomeRef;

    @Column(name = "refseq_version", length = 20)
    private String refseqVersion;

    @Column(name = "hgmd_version")
    private Integer hgmdVersion;

    @Column(name = "gen1000_snp_version")
    private Integer gen1000SnpVersion;

    @Column(name = "gen1000_indel_version")
    private Integer gen1000IndelVersion;

    @Column(name = "gnomad_version", length = 20)
    private String gnomadVersion;

    @Column(name = "dbin_group_version")
    private Integer diagnosticBinGroupVersion;

    @Column(name = "algorithm_version", length = 20)
    private String algorithmVersion;

    @Column(name = "dbsnp_version")
    private String dbsnpVersion;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "note")
    private String note;

    @Column(name = "vcf_loader_name", length = 1023)
    private String vcfLoaderName;

    @Column(name = "vcf_loader_version")
    private String vcfLoaderVersion;

    @ManyToOne
    @JoinColumn(name = "clinvar_version")
    private ClinVarVersion clinvarVersion;

    @OneToMany(mappedBy = "diagnosticResultVersion", fetch = FetchType.LAZY)
    private Set<BinResultsFinalDiagnostic> binResultsFinalDiagnostics;

    public DiagnosticResultVersion() {
        super();
    }

    public Set<BinResultsFinalDiagnostic> getBinResultsFinalDiagnostics() {
        return binResultsFinalDiagnostics;
    }

    public void setBinResultsFinalDiagnostics(Set<BinResultsFinalDiagnostic> binResultsFinalDiagnostics) {
        this.binResultsFinalDiagnostics = binResultsFinalDiagnostics;
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

    public String getRefseqVersion() {
        return refseqVersion;
    }

    public void setRefseqVersion(String refseqVersion) {
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

    public Integer getDiagnosticBinGroupVersion() {
        return diagnosticBinGroupVersion;
    }

    public void setDiagnosticBinGroupVersion(Integer diagnosticBinGroupVersion) {
        this.diagnosticBinGroupVersion = diagnosticBinGroupVersion;
    }

    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    public void setAlgorithmVersion(String algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }

    public String getDbsnpVersion() {
        return dbsnpVersion;
    }

    public void setDbsnpVersion(String dbsnpVersion) {
        this.dbsnpVersion = dbsnpVersion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVcfLoaderName() {
        return vcfLoaderName;
    }

    public void setVcfLoaderName(String vcfLoaderName) {
        this.vcfLoaderName = vcfLoaderName;
    }

    public String getVcfLoaderVersion() {
        return vcfLoaderVersion;
    }

    public void setVcfLoaderVersion(String vcfLoaderVersion) {
        this.vcfLoaderVersion = vcfLoaderVersion;
    }

    public ClinVarVersion getClinvarVersion() {
        return clinvarVersion;
    }

    public void setClinvarVersion(ClinVarVersion clinvarVersion) {
        this.clinvarVersion = clinvarVersion;
    }

    public String getGnomadVersion() {
        return gnomadVersion;
    }

    public void setGnomadVersion(String gnomadVersion) {
        this.gnomadVersion = gnomadVersion;
    }

    @Override
    public String toString() {
        return String.format(
                "DiagnosticResultVersion [id=%s, genomeRef=%s, refseqVersion=%s, hgmdVersion=%s, gen1000SnpVersion=%s, gen1000IndelVersion=%s, gnomadVersion=%s, diagnosticBinGroupVersion=%s, algorithmVersion=%s, dbsnpVersion=%s, note=%s, vcfLoaderName=%s, vcfLoaderVersion=%s]",
                id, genomeRef, refseqVersion, hgmdVersion, gen1000SnpVersion, gen1000IndelVersion, gnomadVersion, diagnosticBinGroupVersion,
                algorithmVersion, dbsnpVersion, note, vcfLoaderName, vcfLoaderVersion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((algorithmVersion == null) ? 0 : algorithmVersion.hashCode());
        result = prime * result + ((diagnosticBinGroupVersion == null) ? 0 : diagnosticBinGroupVersion.hashCode());
        result = prime * result + ((dbsnpVersion == null) ? 0 : dbsnpVersion.hashCode());
        result = prime * result + ((gen1000IndelVersion == null) ? 0 : gen1000IndelVersion.hashCode());
        result = prime * result + ((gen1000SnpVersion == null) ? 0 : gen1000SnpVersion.hashCode());
        result = prime * result + ((genomeRef == null) ? 0 : genomeRef.hashCode());
        result = prime * result + ((gnomadVersion == null) ? 0 : gnomadVersion.hashCode());
        result = prime * result + ((hgmdVersion == null) ? 0 : hgmdVersion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((refseqVersion == null) ? 0 : refseqVersion.hashCode());
        result = prime * result + ((vcfLoaderName == null) ? 0 : vcfLoaderName.hashCode());
        result = prime * result + ((vcfLoaderVersion == null) ? 0 : vcfLoaderVersion.hashCode());
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
        DiagnosticResultVersion other = (DiagnosticResultVersion) obj;
        if (algorithmVersion == null) {
            if (other.algorithmVersion != null)
                return false;
        } else if (!algorithmVersion.equals(other.algorithmVersion))
            return false;
        if (diagnosticBinGroupVersion == null) {
            if (other.diagnosticBinGroupVersion != null)
                return false;
        } else if (!diagnosticBinGroupVersion.equals(other.diagnosticBinGroupVersion))
            return false;
        if (dbsnpVersion == null) {
            if (other.dbsnpVersion != null)
                return false;
        } else if (!dbsnpVersion.equals(other.dbsnpVersion))
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
        if (genomeRef == null) {
            if (other.genomeRef != null)
                return false;
        } else if (!genomeRef.equals(other.genomeRef))
            return false;
        if (gnomadVersion == null) {
            if (other.gnomadVersion != null)
                return false;
        } else if (!gnomadVersion.equals(other.gnomadVersion))
            return false;
        if (hgmdVersion == null) {
            if (other.hgmdVersion != null)
                return false;
        } else if (!hgmdVersion.equals(other.hgmdVersion))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (refseqVersion == null) {
            if (other.refseqVersion != null)
                return false;
        } else if (!refseqVersion.equals(other.refseqVersion))
            return false;
        if (vcfLoaderName == null) {
            if (other.vcfLoaderName != null)
                return false;
        } else if (!vcfLoaderName.equals(other.vcfLoaderName))
            return false;
        if (vcfLoaderVersion == null) {
            if (other.vcfLoaderVersion != null)
                return false;
        } else if (!vcfLoaderVersion.equals(other.vcfLoaderVersion))
            return false;
        return true;
    }

}
