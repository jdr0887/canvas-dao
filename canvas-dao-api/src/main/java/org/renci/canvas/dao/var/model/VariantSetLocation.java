package org.renci.canvas.dao.var.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;

@Entity
@Table(schema = "var", name = "var_set_loc", indexes = { @Index(name = "var_set_loc_var_set_id_idx", columnList = "var_set_id"),
        @Index(name = "var_set_loc_ref_ver_accession_idx", columnList = "ref_ver_accession") })
public class VariantSetLocation implements Persistable<VariantSetLocationPK> {

    private static final long serialVersionUID = 8718903481376803091L;

    @EmbeddedId
    private VariantSetLocationPK id;

    @MapsId("variantSet")
    @ManyToOne
    @JoinColumn(name = "var_set_id")
    private VariantSet variantSet;

    @MapsId("genomeRefSeq")
    @ManyToOne
    @JoinColumn(name = "ref_ver_accession")
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "vcffilter")
    private String vcfFilter;

    @Column(name = "qual")
    private Double qual;

    public VariantSetLocation() {
        super();
    }

    public VariantSet getVariantSet() {
        return variantSet;
    }

    public void setVariantSet(VariantSet variantSet) {
        this.variantSet = variantSet;
    }

    public GenomeRefSeq getGenomeRefSeq() {
        return genomeRefSeq;
    }

    public void setGenomeRefSeq(GenomeRefSeq genomeRefSeq) {
        this.genomeRefSeq = genomeRefSeq;
    }

    public VariantSetLocationPK getId() {
        return id;
    }

    public void setId(VariantSetLocationPK id) {
        this.id = id;
    }

    public String getVcfFilter() {
        return vcfFilter;
    }

    public void setVcfFilter(String vcfFilter) {
        this.vcfFilter = vcfFilter;
    }

    public Double getQual() {
        return qual;
    }

    public void setQual(Double qual) {
        this.qual = qual;
    }

    @Override
    public String toString() {
        return String.format("VariantSetLocation [id=%s, vcfFilter=%s, qual=%s]", id, vcfFilter, qual);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((qual == null) ? 0 : qual.hashCode());
        result = prime * result + ((vcfFilter == null) ? 0 : vcfFilter.hashCode());
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
        VariantSetLocation other = (VariantSetLocation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (qual == null) {
            if (other.qual != null)
                return false;
        } else if (!qual.equals(other.qual))
            return false;
        if (vcfFilter == null) {
            if (other.vcfFilter != null)
                return false;
        } else if (!vcfFilter.equals(other.vcfFilter))
            return false;
        return true;
    }

}
