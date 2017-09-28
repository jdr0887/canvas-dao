package org.renci.canvas.dao.omim.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(schema = "omim", name = "phenotype", indexes = { @Index(name = "phenotype_otype_idx", columnList = "otype") })
public class Phenotype {

    @Id
    @Column(name = "omim_phenotype_id")
    private Integer omimPhenotypeId;

    @Column(name = "version")
    private Date version;

    @Column(name = "mixed")
    private Boolean mixed;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "otype")
    private Otype otype;

    public Phenotype() {
        super();
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Integer getOmimPhenotypeId() {
        return omimPhenotypeId;
    }

    public void setOmimPhenotypeId(Integer omimPhenotypeId) {
        this.omimPhenotypeId = omimPhenotypeId;
    }

    public Boolean getMixed() {
        return mixed;
    }

    public void setMixed(Boolean mixed) {
        this.mixed = mixed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Otype getOtype() {
        return otype;
    }

    public void setOtype(Otype otype) {
        this.otype = otype;
    }

    @Override
    public String toString() {
        return String.format("Phenotype [omimPhenotypeId=%s, version=%s, mixed=%s, title=%s]", omimPhenotypeId, version, mixed, title);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mixed == null) ? 0 : mixed.hashCode());
        result = prime * result + ((omimPhenotypeId == null) ? 0 : omimPhenotypeId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        Phenotype other = (Phenotype) obj;
        if (mixed == null) {
            if (other.mixed != null)
                return false;
        } else if (!mixed.equals(other.mixed))
            return false;
        if (omimPhenotypeId == null) {
            if (other.omimPhenotypeId != null)
                return false;
        } else if (!omimPhenotypeId.equals(other.omimPhenotypeId))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
