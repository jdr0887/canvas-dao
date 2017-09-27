package org.renci.canvas.dao.clinbin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "analysis_class_incidental", indexes = {
        @Index(name = "analysis_class_incidental_incidental_bin_id_idx", columnList = "incidental_bin_id"),
        @Index(name = "analysis_class_incidental_loc_var_id_idx", columnList = "loc_var_id") })
public class AnalysisClassIncidental implements Persistable<Integer> {

    private static final long serialVersionUID = -4523788379740204587L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_class_incidental_analysis_class_incidental_id_seq")
    @SequenceGenerator(schema = "clinbin", name = "analysis_class_incidental_analysis_class_incidental_id_seq", sequenceName = "analysis_class_incidental_analysis_class_incidental_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "analysis_class_incidental_id")
    private Integer id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "selected_class")
    private String selectedClass;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "select_class_descr")
    private String selectClassDescription;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "timestamp")
    private Date timestamp;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "hgnc_gene")
    private String hgncGene;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "phenotype_id")
    private String phenotype;

    @ManyToOne
    @JoinColumn(name = "incidental_bin_id")
    private IncidentalBinX incidentalBin;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    public AnalysisClassIncidental() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }

    public String getSelectClassDescription() {
        return selectClassDescription;
    }

    public void setSelectClassDescription(String selectClassDescription) {
        this.selectClassDescription = selectClassDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getHgncGene() {
        return hgncGene;
    }

    public void setHgncGene(String hgncGene) {
        this.hgncGene = hgncGene;
    }

    public String getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(String phenotype) {
        this.phenotype = phenotype;
    }

    public IncidentalBinX getIncidentalBin() {
        return incidentalBin;
    }

    public void setIncidentalBin(IncidentalBinX incidentalBin) {
        this.incidentalBin = incidentalBin;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    @Override
    public String toString() {
        return String.format(
                "AnalysisClassIncidental [id=%s, selectedClass=%s, selectClassDescription=%s, userName=%s, timestamp=%s, hgncGene=%s, phenotype=%s]",
                id, selectedClass, selectClassDescription, userName, timestamp, hgncGene, phenotype);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hgncGene == null) ? 0 : hgncGene.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((phenotype == null) ? 0 : phenotype.hashCode());
        result = prime * result + ((selectClassDescription == null) ? 0 : selectClassDescription.hashCode());
        result = prime * result + ((selectedClass == null) ? 0 : selectedClass.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        AnalysisClassIncidental other = (AnalysisClassIncidental) obj;
        if (hgncGene == null) {
            if (other.hgncGene != null)
                return false;
        } else if (!hgncGene.equals(other.hgncGene))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (phenotype == null) {
            if (other.phenotype != null)
                return false;
        } else if (!phenotype.equals(other.phenotype))
            return false;
        if (selectClassDescription == null) {
            if (other.selectClassDescription != null)
                return false;
        } else if (!selectClassDescription.equals(other.selectClassDescription))
            return false;
        if (selectedClass == null) {
            if (other.selectedClass != null)
                return false;
        } else if (!selectedClass.equals(other.selectedClass))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

}
