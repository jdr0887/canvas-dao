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
@Table(schema = "clinbin", name = "confirmed_tracking")
public class ConfirmedTracking implements Persistable<Integer> {

    private static final long serialVersionUID = -6611989996091211456L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmed_tracking_confirmed_tracking_id_seq")
    @SequenceGenerator(schema = "clinbin", name = "confirmed_tracking_confirmed_tracking_id_seq", sequenceName = "confirmed_tracking_confirmed_tracking_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "confirmed_tracking_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocatedVariant locatedVariant;

    @ManyToOne
    @JoinColumn(name = "phenotype_id")
    private PhenotypeX phenotype;

    @Lob
    @Column(name = "user_name")
    private String userName;

    @Lob
    @Column(name = "hgnc_gene")
    private String gene;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "confirmed")
    private Integer confirmed;

    @Column(name = "bin_type")
    private Integer binType;

    @Column(name = "bin_id")
    private Integer binId;

    public ConfirmedTracking() {
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

    public PhenotypeX getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(PhenotypeX phenotype) {
        this.phenotype = phenotype;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getBinType() {
        return binType;
    }

    public void setBinType(Integer binType) {
        this.binType = binType;
    }

    public Integer getBinId() {
        return binId;
    }

    public void setBinId(Integer binId) {
        this.binId = binId;
    }

    @Override
    public String toString() {
        return String.format("ConfirmedTracking [id=%s, userName=%s, gene=%s, timestamp=%s, confirmed=%s, binType=%s, binId=%s]", id,
                userName, gene, timestamp, confirmed, binType, binId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((binId == null) ? 0 : binId.hashCode());
        result = prime * result + ((binType == null) ? 0 : binType.hashCode());
        result = prime * result + ((confirmed == null) ? 0 : confirmed.hashCode());
        result = prime * result + ((gene == null) ? 0 : gene.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ConfirmedTracking other = (ConfirmedTracking) obj;
        if (binId == null) {
            if (other.binId != null)
                return false;
        } else if (!binId.equals(other.binId))
            return false;
        if (binType == null) {
            if (other.binType != null)
                return false;
        } else if (!binType.equals(other.binType))
            return false;
        if (confirmed == null) {
            if (other.confirmed != null)
                return false;
        } else if (!confirmed.equals(other.confirmed))
            return false;
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
