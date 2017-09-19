package org.renci.canvas.dao.clinbin.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.renci.canvas.dao.Persistable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncidentalStatusType", propOrder = {})
@XmlRootElement(name = "incidentalStatusType")
@Entity
@Table(schema = "clinbin", name = "incidental_status_type")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class IncidentalStatusType implements Persistable<String> {

    private static final long serialVersionUID = 4612957769574414877L;

    @Id
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "status")
    private String id;

    public IncidentalStatusType() {
        super();
    }

    public IncidentalStatusType(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("IncidentalStatusType [id=%s]", id);
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
        IncidentalStatusType other = (IncidentalStatusType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
