package org.renci.canvas.dao.clinbin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.annotation.model.AnnotationGene;

@Entity
@Table(schema = "clinbin", name = "incidental_bin_genex")
@NamedEntityGraph(name = "clinbin.IncidentalBinGeneX.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "incidentalBin"),
        @NamedAttributeNode(value = "gene"), @NamedAttributeNode(value = "phenotype"), @NamedAttributeNode(value = "zygosityMode") })
public class IncidentalBinGeneX implements Persistable<IncidentalBinGeneXPK> {

    private static final long serialVersionUID = 8992564182838865854L;

    @EmbeddedId
    private IncidentalBinGeneXPK id;

    @MapsId("incidentalBin")
    @ManyToOne
    @JoinColumn(name = "incidental_bin_id")
    private IncidentalBinX incidentalBin;

    @MapsId("gene")
    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    @ManyToOne
    @JoinColumn(name = "phenotype_id")
    private PhenotypeX phenotype;

    @ManyToOne
    @JoinColumn(name = "zygosity_mode")
    private ZygosityModeType zygosityMode;

    public IncidentalBinGeneX() {
        super();
    }

    public IncidentalBinGeneX(IncidentalBinGeneXPK id) {
        super();
        this.id = id;
    }

    public IncidentalBinGeneXPK getId() {
        return id;
    }

    public void setId(IncidentalBinGeneXPK id) {
        this.id = id;
    }

    public IncidentalBinX getIncidentalBin() {
        return incidentalBin;
    }

    public void setIncidentalBin(IncidentalBinX incidentalBin) {
        this.incidentalBin = incidentalBin;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    public PhenotypeX getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(PhenotypeX phenotype) {
        this.phenotype = phenotype;
    }

    public ZygosityModeType getZygosityMode() {
        return zygosityMode;
    }

    public void setZygosityMode(ZygosityModeType zygosityMode) {
        this.zygosityMode = zygosityMode;
    }

    @Override
    public String toString() {
        return String.format("IncidentalBinGeneX [id=%s]", id);
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
        IncidentalBinGeneX other = (IncidentalBinGeneX) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
