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
@Table(schema = "omim", name = "gene", indexes = { @Index(name = "gene_otype_idx", columnList = "otype") })
public class OMIMGene {

    @Id
    @Column(name = "omim_gene_id")
    private Integer omimGeneId;

    @Column(name = "version")
    private Date version;

    @Column(name = "mixed")
    private Boolean mixed;

    @ManyToOne
    @JoinColumn(name = "otype")
    private Otype otype;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "name")
    private String name;

    public OMIMGene() {
        super();
    }

    public Integer getOmimGeneId() {
        return omimGeneId;
    }

    public void setOmimGeneId(Integer omimGeneId) {
        this.omimGeneId = omimGeneId;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Boolean getMixed() {
        return mixed;
    }

    public void setMixed(Boolean mixed) {
        this.mixed = mixed;
    }

    public Otype getOtype() {
        return otype;
    }

    public void setOtype(Otype otype) {
        this.otype = otype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("OMIMGene [omimGeneId=%s, version=%s, mixed=%s, name=%s]", omimGeneId, version, mixed, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mixed == null) ? 0 : mixed.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((omimGeneId == null) ? 0 : omimGeneId.hashCode());
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
        OMIMGene other = (OMIMGene) obj;
        if (mixed == null) {
            if (other.mixed != null)
                return false;
        } else if (!mixed.equals(other.mixed))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (omimGeneId == null) {
            if (other.omimGeneId != null)
                return false;
        } else if (!omimGeneId.equals(other.omimGeneId))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
