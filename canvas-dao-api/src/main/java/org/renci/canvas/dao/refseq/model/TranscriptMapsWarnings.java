package org.renci.canvas.dao.refseq.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "transcr_maps_warnings", indexes = {
        @Index(name = "transcr_maps_warnings_refseq_transcr_maps_id_idx", columnList = "refseq_transcr_maps_id") })
public class TranscriptMapsWarnings implements Persistable<TranscriptMapsWarningsPK> {

    private static final long serialVersionUID = -2355445123640295983L;

    @EmbeddedId
    private TranscriptMapsWarningsPK id;

    public TranscriptMapsWarnings() {
        super();
    }

    public TranscriptMapsWarningsPK getId() {
        return id;
    }

    public void setId(TranscriptMapsWarningsPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("TranscriptMapsWarnings [id=%s]", id);
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
        TranscriptMapsWarnings other = (TranscriptMapsWarnings) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
