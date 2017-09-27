package org.renci.canvas.dao.clinvar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.var.model.LocatedVariant;

@Entity
@Table(schema = "clinvar", name = "reference_clinical_assertions", indexes = {
        @Index(name = "reference_clinical_assertions_loc_var_id_idx", columnList = "loc_var_id"),
        @Index(name = "reference_clinical_assertions_assertion_idx", columnList = "assertion"),
        @Index(name = "reference_clinical_assertions_trait_set_id_idx", columnList = "trait_set_id") })
@NamedEntityGraphs({
        @NamedEntityGraph(name = "clinvar.ReferenceClinicalAssertion.includeManyToOnes", attributeNodes = {
                @NamedAttributeNode(value = "locatedVariant"), @NamedAttributeNode(value = "assertion"),
                @NamedAttributeNode(value = "traitSet"), @NamedAttributeNode(value = "traitSet") }),
        @NamedEntityGraph(name = "clinvar.ReferenceClinicalAssertion.includeAll", attributeNodes = {
                @NamedAttributeNode(value = "locatedVariant"), @NamedAttributeNode(value = "assertion"),
                @NamedAttributeNode(value = "traitSet"), @NamedAttributeNode(value = "traitSet"),
                @NamedAttributeNode(value = "submissionClinicalAssertions"), @NamedAttributeNode(value = "versions") }) })
public class ReferenceClinicalAssertion implements Persistable<Long> {

    private static final long serialVersionUID = 1918641526123264979L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_clinical_assertions_assertion_id_seq")
    @SequenceGenerator(schema = "clinvar", name = "reference_clinical_assertions_assertion_id_seq", sequenceName = "reference_clinical_assertions_assertion_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "assertion_id")
    private Long id;

    @Column(name = "accession", length = 20)
    private String accession;

    @Column(name = "version")
    private Integer version;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    private Date created;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_updated")
    private Date updated;

    @Column(name = "record_status", length = 20)
    private String recordStatus;

    @Column(name = "assertion_status", length = 100)
    private String assertionStatus;

    @Column(name = "explanation")
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "assertion")
    private AssertionRanking assertion;

    @Column(name = "assertion_type", length = 100)
    private String assertionType;

    @ManyToOne
    @JoinColumn(name = "loc_var_id", columnDefinition = "int4")
    private LocatedVariant locatedVariant;

    @ManyToOne
    @JoinColumn(name = "trait_set_id")
    private TraitSet traitSet;

    @OneToMany(mappedBy = "referenceClinicalAssertion", fetch = FetchType.LAZY)
    private Set<SubmissionClinicalAssertion> submissionClinicalAssertions;

    @ManyToMany(targetEntity = ClinVarVersion.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(schema = "clinvar", name = "version_accession_map", indexes = {
            @Index(name = "version_accession_map_clinvar_version_id_idx", columnList = "clinvar_version_id") }, inverseJoinColumns = @JoinColumn(name = "clinvar_version_id", referencedColumnName = "clinvar_version_id"), joinColumns = @JoinColumn(name = "clinvar_ref_assertion_id", referencedColumnName = "assertion_id"))
    private Set<ClinVarVersion> versions;

    public ReferenceClinicalAssertion() {
        super();
        this.versions = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getAssertionStatus() {
        return assertionStatus;
    }

    public void setAssertionStatus(String assertionStatus) {
        this.assertionStatus = assertionStatus;
    }

    public AssertionRanking getAssertion() {
        return assertion;
    }

    public void setAssertion(AssertionRanking assertion) {
        this.assertion = assertion;
    }

    public String getAssertionType() {
        return assertionType;
    }

    public void setAssertionType(String assertionType) {
        this.assertionType = assertionType;
    }

    public LocatedVariant getLocatedVariant() {
        return locatedVariant;
    }

    public void setLocatedVariant(LocatedVariant locatedVariant) {
        this.locatedVariant = locatedVariant;
    }

    public TraitSet getTraitSet() {
        return traitSet;
    }

    public void setTraitSet(TraitSet traitSet) {
        this.traitSet = traitSet;
    }

    public Set<ClinVarVersion> getVersions() {
        return versions;
    }

    public void setVersions(Set<ClinVarVersion> versions) {
        this.versions = versions;
    }

    public Set<SubmissionClinicalAssertion> getSubmissionClinicalAssertions() {
        return submissionClinicalAssertions;
    }

    public void setSubmissionClinicalAssertions(Set<SubmissionClinicalAssertion> submissionClinicalAssertions) {
        this.submissionClinicalAssertions = submissionClinicalAssertions;
    }

    @Override
    public String toString() {
        return String.format(
                "ReferenceClinicalAssertion [id=%s, accession=%s, version=%s, created=%s, updated=%s, recordStatus=%s, assertionStatus=%s, explanation=%s, assertion=%s, assertionType=%s]",
                id, accession, version, created, updated, recordStatus, assertionStatus, explanation, assertion, assertionType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
        result = prime * result + ((assertion == null) ? 0 : assertion.hashCode());
        result = prime * result + ((assertionStatus == null) ? 0 : assertionStatus.hashCode());
        result = prime * result + ((assertionType == null) ? 0 : assertionType.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((explanation == null) ? 0 : explanation.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((recordStatus == null) ? 0 : recordStatus.hashCode());
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
        ReferenceClinicalAssertion other = (ReferenceClinicalAssertion) obj;
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
        if (assertionStatus == null) {
            if (other.assertionStatus != null)
                return false;
        } else if (!assertionStatus.equals(other.assertionStatus))
            return false;
        if (assertionType == null) {
            if (other.assertionType != null)
                return false;
        } else if (!assertionType.equals(other.assertionType))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (explanation == null) {
            if (other.explanation != null)
                return false;
        } else if (!explanation.equals(other.explanation))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (recordStatus == null) {
            if (other.recordStatus != null)
                return false;
        } else if (!recordStatus.equals(other.recordStatus))
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
