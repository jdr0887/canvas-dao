package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.VariantEffect;

public interface VariantEffectDAO extends BaseDAO<VariantEffect, String> {

    public List<VariantEffect> findAll() throws CANVASDAOException;

}
