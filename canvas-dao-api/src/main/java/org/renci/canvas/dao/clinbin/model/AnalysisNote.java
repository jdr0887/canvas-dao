package org.renci.canvas.dao.clinbin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinbin", name = "analysis_note")
public class AnalysisNote implements Persistable<Integer> {

    private static final long serialVersionUID = 6675477865601953249L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_note_analysis_note_id_seq")
    @SequenceGenerator(schema = "clinbin", name = "analysis_note_analysis_note_id_seq", sequenceName = "analysis_note_analysis_note_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "analysis_note_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @Lob
    @Column(name = "hgnc_gene")
    private String gene;

    @ManyToOne
    @JoinColumn(name = "phenotype_id")
    private PhenotypeX phenotype;

    @Lob
    @Column(name = "note")
    private String note;

    @Lob
    @Column(name = "user_name")
    private String userName;

    @Column(name = "timestamp")
    private Date timestamp;

    public AnalysisNote() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public PhenotypeX getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(PhenotypeX phenotype) {
        this.phenotype = phenotype;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public String toString() {
        return String.format("AnalysisNote [id=%s, gene=%s, note=%s, userName=%s, timestamp=%s]", id, gene, note, userName, timestamp);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gene == null) ? 0 : gene.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
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
        AnalysisNote other = (AnalysisNote) obj;
        if (gene == null) {
            if (other.gene != null)
                return false;
        } else if (!gene.equals(other.gene))
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
