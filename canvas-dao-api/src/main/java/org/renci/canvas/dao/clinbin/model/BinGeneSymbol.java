package org.renci.canvas.dao.clinbin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.renci.canvas.dao.Persistable;

@Entity
@Table(schema = "clinbin", name = "bin_gene_symbol")
public class BinGeneSymbol implements Persistable<BinGeneSymbolPK> {

    private static final long serialVersionUID = -2225220140087770254L;

    @EmbeddedId
    private BinGeneSymbolPK id;

    public BinGeneSymbol() {
        super();
    }

    public BinGeneSymbolPK getId() {
        return id;
    }

    public void setId(BinGeneSymbolPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("BinGeneSymbol [id=%s]", id);
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
        BinGeneSymbol other = (BinGeneSymbol) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
