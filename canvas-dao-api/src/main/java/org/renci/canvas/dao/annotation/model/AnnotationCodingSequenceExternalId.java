package org.renci.canvas.dao.annotation.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(schema = "annot", name = "cds_external_ids")
public class AnnotationCodingSequenceExternalId implements Persistable<AnnotationCodingSequenceExternalIdPK> {

    private static final long serialVersionUID = 1608955318038314154L;

    @EmbeddedId
    private AnnotationCodingSequenceExternalIdPK id;

    @MapsId("annotationCodingSequence")
    @ManyToOne
    @JoinColumn(name = "cds_id")
    private AnnotationCodingSequence annotationCodingSequence;

    public AnnotationCodingSequenceExternalId() {
        super();
    }

    public AnnotationCodingSequenceExternalIdPK getId() {
        return id;
    }

    public void setId(AnnotationCodingSequenceExternalIdPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("AnnotationCodingSequenceExternalIds [id=%s]", id);
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
        AnnotationCodingSequenceExternalId other = (AnnotationCodingSequenceExternalId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
