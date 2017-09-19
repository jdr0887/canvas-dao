package org.renci.canvas.dao.clinvar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.clinbin.model.IncidentalResultVersionX;

@Entity
@Table(schema = "clinvar", name = "versions")
public class ClinVarVersion implements Persistable<Long> {

    private static final long serialVersionUID = -2775533597974578674L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "versions_clinvar_version_id_seq")
    @SequenceGenerator(schema = "clinvar", name = "versions_clinvar_version_id_seq", sequenceName = "versions_clinvar_version_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "clinvar_version_id")
    private Long id;

    @Column(name = "date_performed")
    private Date datePerformed;

    @Column(name = "clinvar_xml_release", length = 100)
    private String release;

    @OneToMany(mappedBy = "clinvarVersion", fetch = FetchType.LAZY)
    private Set<DiagnosticResultVersion> diagnosticResultVersions;

    @OneToMany(mappedBy = "clinvarVersion", fetch = FetchType.LAZY)
    private Set<IncidentalResultVersionX> incidentalResultVersions;

    @ManyToMany(mappedBy = "versions")
    private Set<ReferenceClinicalAssertion> referenceClinicalAssertions;

    public ClinVarVersion() {
        super();
        this.referenceClinicalAssertions = new HashSet<>();
        this.datePerformed = new Date();
    }

    public ClinVarVersion(String release) {
        this();
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePerformed() {
        return datePerformed;
    }

    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public Set<DiagnosticResultVersion> getDiagnosticResultVersions() {
        return diagnosticResultVersions;
    }

    public void setDiagnosticResultVersions(Set<DiagnosticResultVersion> diagnosticResultVersions) {
        this.diagnosticResultVersions = diagnosticResultVersions;
    }

    public Set<IncidentalResultVersionX> getIncidentalResultVersions() {
        return incidentalResultVersions;
    }

    public void setIncidentalResultVersions(Set<IncidentalResultVersionX> incidentalResultVersions) {
        this.incidentalResultVersions = incidentalResultVersions;
    }

    public Set<ReferenceClinicalAssertion> getReferenceClinicalAssertions() {
        return referenceClinicalAssertions;
    }

    public void setReferenceClinicalAssertions(Set<ReferenceClinicalAssertion> referenceClinicalAssertions) {
        this.referenceClinicalAssertions = referenceClinicalAssertions;
    }

    @Override
    public String toString() {
        return String.format("ClinVarVersion [id=%s, datePerformed=%s, release=%s]", id, datePerformed, release);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((datePerformed == null) ? 0 : datePerformed.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((release == null) ? 0 : release.hashCode());
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
        ClinVarVersion other = (ClinVarVersion) obj;
        if (datePerformed == null) {
            if (other.datePerformed != null)
                return false;
        } else if (!datePerformed.equals(other.datePerformed))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (release == null) {
            if (other.release != null)
                return false;
        } else if (!release.equals(other.release))
            return false;
        return true;
    }

}
