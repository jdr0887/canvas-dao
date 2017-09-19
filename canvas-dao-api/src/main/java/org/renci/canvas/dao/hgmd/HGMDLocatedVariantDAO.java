package org.renci.canvas.dao.hgmd;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariant;
import org.renci.canvas.dao.hgmd.model.HGMDLocatedVariantPK;

public interface HGMDLocatedVariantDAO extends BaseDAO<HGMDLocatedVariant, HGMDLocatedVariantPK> {

    public List<HGMDLocatedVariant> findByLocatedVariantId(Long locatedVariantId) throws CANVASDAOException;

    public Integer findLatestVersion() throws CANVASDAOException;

}
