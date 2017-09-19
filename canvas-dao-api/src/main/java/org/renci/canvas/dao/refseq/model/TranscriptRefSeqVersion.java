package org.renci.canvas.dao.refseq.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "transcr_refseq_vers")
public class TranscriptRefSeqVersion implements Persistable<TranscriptRefSeqVersionPK> {

    private static final long serialVersionUID = 7323721337345220492L;

    @EmbeddedId
    private TranscriptRefSeqVersionPK id;

    @MapsId("transcript")
    @ManyToOne
    @JoinColumn(name = "ver_id")
    private Transcript transcript;

    public TranscriptRefSeqVersion() {
        super();
    }

    public TranscriptRefSeqVersion(TranscriptRefSeqVersionPK id) {
        super();
        this.id = id;
    }

    public TranscriptRefSeqVersionPK getId() {
        return id;
    }

    public void setId(TranscriptRefSeqVersionPK id) {
        this.id = id;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    @Override
    public String toString() {
        return String.format("TranscriptRefSeqVersion [id=%s]", id);
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
        TranscriptRefSeqVersion other = (TranscriptRefSeqVersion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
