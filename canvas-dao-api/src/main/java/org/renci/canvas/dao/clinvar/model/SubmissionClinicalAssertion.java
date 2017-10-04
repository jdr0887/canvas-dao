package org.renci.canvas.dao.clinvar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinvar", name = "submission_clinical_assertion")
public class SubmissionClinicalAssertion implements Persistable<Long> {

    private static final long serialVersionUID = -2012629830617603120L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submission_clinical_assertion_id_seq")
    @SequenceGenerator(schema = "clinvar", name = "submission_clinical_assertion_id_seq", sequenceName = "submission_clinical_assertion_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "accession", length = 20)
    private String accession;

    @Column(name = "version")
    private Integer version;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_updated")
    private Date updated;

    @Column(name = "review_status", length = 100)
    private String reviewStatus;

    @Column(name = "assertion")
    private String assertion;

    @ManyToMany(mappedBy = "submissionClinicalAssertions")
    private Set<ReferenceClinicalAssertion> referenceClinicalAssertions;

    public SubmissionClinicalAssertion() {
        super();
        this.referenceClinicalAssertions = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    public Set<ReferenceClinicalAssertion> getReferenceClinicalAssertions() {
        return referenceClinicalAssertions;
    }

    public void setReferenceClinicalAssertions(Set<ReferenceClinicalAssertion> referenceClinicalAssertions) {
        this.referenceClinicalAssertions = referenceClinicalAssertions;
    }

    @Override
    public String toString() {
        return String.format("SubmissionClinicalAssertion [id=%s, accession=%s, version=%s, updated=%s, reviewStatus=%s, assertion=%s]", id,
                accession, version, updated, reviewStatus, assertion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
        result = prime * result + ((assertion == null) ? 0 : assertion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((reviewStatus == null) ? 0 : reviewStatus.hashCode());
        result = prime * result + ((updated == null) ? 0 : updated.hashCode());
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
        SubmissionClinicalAssertion other = (SubmissionClinicalAssertion) obj;
        if (accession == null) {
            if (other.accession != null)
                return false;
        } else if (!accession.equals(other.accession))
            return false;
        if (assertion == null) {
            if (other.assertion != null)
                return false;
        } else if (!assertion.equals(other.assertion))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (reviewStatus == null) {
            if (other.reviewStatus != null)
                return false;
        } else if (!reviewStatus.equals(other.reviewStatus))
            return false;
        if (updated == null) {
            if (other.updated != null)
                return false;
        } else if (!updated.equals(other.updated))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
