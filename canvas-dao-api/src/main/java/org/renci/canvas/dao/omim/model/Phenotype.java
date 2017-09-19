package org.renci.canvas.dao.omim.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Type;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phenotype")
@XmlRootElement(name = "phenotype")
@Entity
@Table(schema = "omim", name = "phenotype")
public class Phenotype {

    @Column(name = "version")
    private Date version;

    @Id
    @Column(name = "omim_phenotype_id")
    private Integer omimPhenotypeId;

    @Column(name = "mixed")
    private Boolean mixed;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "title")
    private String title;

    @Column(name = "otype")
    private Integer otype;

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

    public Integer getOtype() {
        return otype;
    }

    public void setOtype(Integer otype) {
        this.otype = otype;
    }

    @Override
    public String toString() {
        return String.format("Phenotype [version=%s, omimPhenotypeId=%s, mixed=%s, title=%s, otype=%s]", version, omimPhenotypeId, mixed,
                title, otype);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mixed == null) ? 0 : mixed.hashCode());
        result = prime * result + ((omimPhenotypeId == null) ? 0 : omimPhenotypeId.hashCode());
        result = prime * result + ((otype == null) ? 0 : otype.hashCode());
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
        if (otype == null) {
            if (other.otype != null)
                return false;
        } else if (!otype.equals(other.otype))
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
