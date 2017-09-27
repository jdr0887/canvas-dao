package org.renci.canvas.dao.refseq.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "refseq", name = "cds")
@NamedEntityGraph(name = "refseq.RefSeqCodingSequence.includeAll", attributeNodes = {
        @NamedAttributeNode(value = "locations", subgraph = "regionGroup.includeManyToOnes") }, subgraphs = {
                @NamedSubgraph(name = "regionGroup.includeManyToOnes", attributeNodes = { @NamedAttributeNode(value = "transcript"),
                        @NamedAttributeNode(value = "groupingType") }) })
public class RefSeqCodingSequence implements Persistable<Integer> {

    private static final long serialVersionUID = -3518848421271412683L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cds_refseq_cds_id_seq")
    @SequenceGenerator(name = "cds_refseq_cds_id_seq", schema = "refseq", sequenceName = "cds_refseq_cds_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "refseq_cds_id")
    private Integer id;

    @Column(name = "refseq_ver")
    private String version;

    @Column(name = "protein_id", length = 31)
    private String proteinId;

    @Column(name = "product")
    private String product;

    @Column(name = "codon_start")
    private Integer codonStart;

    @Column(name = "descr", length = 65535)
    private String description;

    @Column(name = "transl", length = 65535)
    private String translation;

    @Column(name = "note", length = 1023)
    private String note;

    @ManyToMany(targetEntity = RegionGroup.class)
    @JoinTable(schema = "refseq", name = "cds_locs", indexes = {
            @Index(name = "cds_locs_refseq_cds_id_idx", columnList = "refseq_cds_id") }, joinColumns = @JoinColumn(name = "refseq_cds_id"), inverseJoinColumns = @JoinColumn(name = "loc_region_group_id"))
    private Set<RegionGroup> locations;

    @OneToMany(mappedBy = "refseqCodingSequence")
    private Set<CDSECNumber> cdsECNumbers;

    public RefSeqCodingSequence() {
        super();
        this.locations = new HashSet<>();
    }

    public RefSeqCodingSequence(String version, String proteinId, String product, Integer codonStart, String description,
            String translation, String note) {
        super();
        this.version = version;
        this.proteinId = proteinId;
        this.product = product;
        this.codonStart = codonStart;
        this.description = description;
        this.translation = translation;
        this.note = note;
        this.locations = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProteinId() {
        return proteinId;
    }

    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getCodonStart() {
        return codonStart;
    }

    public void setCodonStart(Integer codonStart) {
        this.codonStart = codonStart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<RegionGroup> getLocations() {
        return locations;
    }

    public void setLocations(Set<RegionGroup> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return String.format("RefSeqCodingSequence [id=%s, version=%s, proteinId=%s, product=%s, codonStart=%s, note=%s]", id, version,
                proteinId, product, codonStart, note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codonStart == null) ? 0 : codonStart.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((proteinId == null) ? 0 : proteinId.hashCode());
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
        RefSeqCodingSequence other = (RefSeqCodingSequence) obj;
        if (codonStart == null) {
            if (other.codonStart != null)
                return false;
        } else if (!codonStart.equals(other.codonStart))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (proteinId == null) {
            if (other.proteinId != null)
                return false;
        } else if (!proteinId.equals(other.proteinId))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
