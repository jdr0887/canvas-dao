package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.annotation.model.AnnotationGene;

@Entity
@Table(schema = "clinbin", name = "diagnostic_gene", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "diagnostic_list_version", "dx_id", "gene_id", "tier", "inheritance" }) })
@NamedEntityGraph(name = "clinbin.DiagnosticGene.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "gene"),
        @NamedAttributeNode(value = "dx") })
public class DiagnosticGene implements Persistable<Integer> {

    private static final long serialVersionUID = -6562946115355893577L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnostic_genes_diagnostic_gene_id_seq")
    @SequenceGenerator(schema = "clinbin", name = "diagnostic_genes_diagnostic_gene_id_seq", sequenceName = "diagnostic_genes_diagnostic_gene_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "diagnostic_gene_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    @Column(name = "diagnostic_list_version")
    private Integer diagnosticListVersion;

    @ManyToOne
    @JoinColumn(name = "dx_id")
    private DX dx;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "tier")
    private String tier;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "inheritance")
    private String inheritance;

    public DiagnosticGene() {
        super();
    }

    public DiagnosticGene(AnnotationGene gene, Integer diagnosticListVersion, DX dx, String tier, String inheritance) {
        super();
        this.gene = gene;
        this.diagnosticListVersion = diagnosticListVersion;
        this.dx = dx;
        this.tier = tier;
        this.inheritance = inheritance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    public Integer getDiagnosticListVersion() {
        return diagnosticListVersion;
    }

    public void setDiagnosticListVersion(Integer diagnosticListVersion) {
        this.diagnosticListVersion = diagnosticListVersion;
    }

    public DX getDx() {
        return dx;
    }

    public void setDx(DX dx) {
        this.dx = dx;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getInheritance() {
        return inheritance;
    }

    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    @Override
    public String toString() {
        return String.format("DiagnosticGene [id=%s, diagnosticListVersion=%s, tier=%s, inheritance=%s]", id, diagnosticListVersion, tier,
                inheritance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((diagnosticListVersion == null) ? 0 : diagnosticListVersion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inheritance == null) ? 0 : inheritance.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
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
        DiagnosticGene other = (DiagnosticGene) obj;
        if (diagnosticListVersion == null) {
            if (other.diagnosticListVersion != null)
                return false;
        } else if (!diagnosticListVersion.equals(other.diagnosticListVersion))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (inheritance == null) {
            if (other.inheritance != null)
                return false;
        } else if (!inheritance.equals(other.inheritance))
            return false;
        if (tier == null) {
            if (other.tier != null)
                return false;
        } else if (!tier.equals(other.tier))
            return false;
        return true;
    }

}
