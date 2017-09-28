package org.renci.canvas.dao.clinbin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "incidental_bin_results", indexes = {
        @Index(name = "incidental_bin_results_gene_id_idx", columnList = "gene_id"),
        @Index(name = "incidental_bin_results_zygosity_mode_idx", columnList = "zygosity_mode"),
        @Index(name = "incidental_bin_results_loc_var_id_idx", columnList = "loc_var_id") })
public class IncidentalBinResults implements Persistable<IncidentalBinResultsPK> {

    private static final long serialVersionUID = -5498414203626044918L;

    @EmbeddedId
    private IncidentalBinResultsPK id;

    @MapsId("locatedVariant")
    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @MapsId("zygosityMode")
    @ManyToOne
    @JoinColumn(name = "zygosity_mode")
    private ZygosityModeType zygosityMode;

    @Column(name = "bin_timestamp")
    private Date binTimestamp;

    @Column(name = "bin_name", length = 1024)
    private String binName;

    @ManyToOne
    @JoinColumn(name = "gene_id")
    private AnnotationGene gene;

    @Column(name = "disease")
    private String disease;

    @Column(name = "incidental_bin", length = 15)
    private String incidentalBin;

    public IncidentalBinResults() {
        super();
    }

    public IncidentalBinResults(IncidentalBinResultsPK id) {
        super();
        this.id = id;
    }

    public IncidentalBinResultsPK getId() {
        return id;
    }

    public void setId(IncidentalBinResultsPK id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public Date getBinTimestamp() {
        return binTimestamp;
    }

    public void setBinTimestamp(Date binTimestamp) {
        this.binTimestamp = binTimestamp;
    }

    public String getBinName() {
        return binName;
    }

    public void setBinName(String binName) {
        this.binName = binName;
    }

    public AnnotationGene getGene() {
        return gene;
    }

    public void setGene(AnnotationGene gene) {
        this.gene = gene;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public ZygosityModeType getZygosityMode() {
        return zygosityMode;
    }

    public void setZygosityMode(ZygosityModeType zygosityMode) {
        this.zygosityMode = zygosityMode;
    }

    public String getIncidentalBin() {
        return incidentalBin;
    }

    public void setIncidentalBin(String incidentalBin) {
        this.incidentalBin = incidentalBin;
    }

    @Override
    public String toString() {
        return String.format("IncidentalBinResults [binTimestamp=%s, binName=%s, disease=%s, incidentalBin=%s]", binTimestamp, binName,
                disease, incidentalBin);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((binName == null) ? 0 : binName.hashCode());
        result = prime * result + ((binTimestamp == null) ? 0 : binTimestamp.hashCode());
        result = prime * result + ((disease == null) ? 0 : disease.hashCode());
        result = prime * result + ((incidentalBin == null) ? 0 : incidentalBin.hashCode());
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
        IncidentalBinResults other = (IncidentalBinResults) obj;
        if (binName == null) {
            if (other.binName != null)
                return false;
        } else if (!binName.equals(other.binName))
            return false;
        if (binTimestamp == null) {
            if (other.binTimestamp != null)
                return false;
        } else if (!binTimestamp.equals(other.binTimestamp))
            return false;
        if (disease == null) {
            if (other.disease != null)
                return false;
        } else if (!disease.equals(other.disease))
            return false;
        if (incidentalBin == null) {
            if (other.incidentalBin != null)
                return false;
        } else if (!incidentalBin.equals(other.incidentalBin))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
