package org.renci.canvas.dao.refseq;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.refseq.model.RegionGroup;

public interface RegionGroupDAO extends BaseDAO<RegionGroup, Integer> {

    public List<RegionGroup> findByRefSeqVersionAndTranscriptId(String refSeqVersion, String transcriptId) throws CANVASDAOException;

    public List<RegionGroup> findByTranscriptIdAndGroupingType(String transcriptVersionId, String groupingType) throws CANVASDAOException;

}
