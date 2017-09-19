package org.renci.canvas.dao.refseq.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TranscriptRefSeqVersionPK implements Serializable {

    private static final long serialVersionUID = 8905976400358131037L;

    @Column(name = "ver_id")
    private String transcript;

    @Column(name = "refseq_ver")
    private String version;

    public TranscriptRefSeqVersionPK() {
        super();
    }

    public TranscriptRefSeqVersionPK(String transcript, String version) {
        super();
        this.transcript = transcript;
        this.version = version;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("TranscriptRefSeqVersPK [transcript=%s, version=%s]", transcript, version);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
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
        TranscriptRefSeqVersionPK other = (TranscriptRefSeqVersionPK) obj;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        if (transcript == null) {
            if (other.transcript != null)
                return false;
        } else if (!transcript.equals(other.transcript))
            return false;
        return true;
    }

}
