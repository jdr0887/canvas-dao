package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.Variants_48_2;
import org.renci.canvas.dao.refseq.model.Variants_48_2PK;

public interface Variants_48_2_DAO extends BaseDAO<Variants_48_2, Variants_48_2PK> {

    public List<Variants_48_2> findByLocatedVariantId(Long id) throws CANVASDAOException;

}
