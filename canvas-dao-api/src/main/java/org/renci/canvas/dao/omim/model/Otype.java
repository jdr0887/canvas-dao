package org.renci.canvas.dao.omim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(schema = "omim", name = "otype", uniqueConstraints = { @UniqueConstraint(name = "otype_symbol_key", columnNames = "symbol") })
public class Otype {

    @Id
    @Column(name = "type_key")
    private Integer typeKey;

    @Column(name = "symbol", length = 1)
    private String symbol;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "type_name")
    private String typeName;

    public Otype() {
        super();
    }

    public Integer getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(Integer typeKey) {
        this.typeKey = typeKey;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return String.format("Otype [typeKey=%s, symbol=%s, typeName=%s]", typeKey, symbol, typeName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        result = prime * result + ((typeKey == null) ? 0 : typeKey.hashCode());
        result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
        Otype other = (Otype) obj;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        if (typeKey == null) {
            if (other.typeKey != null)
                return false;
        } else if (!typeKey.equals(other.typeKey))
            return false;
        if (typeName == null) {
            if (other.typeName != null)
                return false;
        } else if (!typeName.equals(other.typeName))
            return false;
        return true;
    }

}
