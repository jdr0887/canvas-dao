package org.renci.canvas.dao.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.canvas.dao.Persistable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Entity
@Table(schema = "annot", name = "transcr_map_warnings")
public class AnnotationTranscriptionMapWarnings implements Persistable<String> {

    private static final long serialVersionUID = -1670395489469856406L;

    @Id
    @Column(name = "warning_name")
    private String id;

    public AnnotationTranscriptionMapWarnings() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("AnnotationTranscriptionMapWarnings [id=%s]", id);
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
        AnnotationTranscriptionMapWarnings other = (AnnotationTranscriptionMapWarnings) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
