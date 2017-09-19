package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;
import org.renci.canvas.dao.refseq.model.RegionGroupRegionPK;

public interface RegionGroupRegionDAO extends BaseDAO<RegionGroupRegion, RegionGroupRegionPK> {

    public List<RegionGroupRegion> findByRegionGroupId(Integer regionGroupId) throws CANVASDAOException;

    public List<RegionGroupRegion> findByRefSeqCodingSequenceId(Integer refSeqCodingSequenceId) throws CANVASDAOException;

    public List<RegionGroupRegion> findByRefSeqVersionAndTranscriptId(String version, String transcriptId) throws CANVASDAOException;

}
