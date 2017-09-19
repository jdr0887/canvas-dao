package org.renci.canvas.dao.clinvar.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinvar", name = "assertion_ranking")
public class AssertionRanking implements Persistable<String> {

    private static final long serialVersionUID = 7855405654995199169L;

    @Id
    @Column(name = "assertion", length = 100)
    private String id;

    @Column(name = "rank")
    private Integer rank;

    @OneToMany(mappedBy = "assertion", fetch = FetchType.LAZY)
    private Set<ReferenceClinicalAssertion> referenceClinicalAssertions;

    public AssertionRanking() {
        super();
    }

    public AssertionRanking(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Set<ReferenceClinicalAssertion> getReferenceClinicalAssertions() {
        return referenceClinicalAssertions;
    }

    public void setReferenceClinicalAssertions(Set<ReferenceClinicalAssertion> referenceClinicalAssertions) {
        this.referenceClinicalAssertions = referenceClinicalAssertions;
    }

    @Override
    public String toString() {
        return String.format("AssertionRanking [id=%s, rank=%s]", id, rank);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
        AssertionRanking other = (AssertionRanking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rank == null) {
            if (other.rank != null)
                return false;
        } else if (!rank.equals(other.rank))
            return false;
        return true;
    }

}
