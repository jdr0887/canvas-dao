package org.renci.canvas.dao;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable {

    public ID getId();
    
}
