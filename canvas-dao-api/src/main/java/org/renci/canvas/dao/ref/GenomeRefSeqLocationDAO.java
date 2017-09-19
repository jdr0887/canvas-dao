package org.renci.canvas.dao.ref;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocation;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocationPK;

public interface GenomeRefSeqLocationDAO extends BaseDAO<GenomeRefSeqLocation, GenomeRefSeqLocationPK> {

    public List<GenomeRefSeqLocation> findByRefIdAndVersionedAccesionAndPosition(Integer refId, String refVerAccession, Integer position)
            throws CANVASDAOException;

    public List<GenomeRefSeqLocation> findByRefIdAndVersionedAccesionAndRange(Integer refId, String refVerAccession, Integer start,
            Integer stop) throws CANVASDAOException;

}
