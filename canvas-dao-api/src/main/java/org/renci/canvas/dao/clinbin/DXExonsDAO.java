package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.DXExons;

public interface DXExonsDAO extends BaseDAO<DXExons, Integer> {

    public Integer findMaxListVersion() throws CANVASDAOException;

    public List<DXExons> findByListVersion(Integer listVersion) throws CANVASDAOException;

    public List<DXExons> findByListVersionAndChromosome(Integer listVersion, String chromosome) throws CANVASDAOException;

    public List<DXExons> findByListVersionAndChromosomeAndRange(Integer listVersion, String chromosome, Integer start, Integer end)
            throws CANVASDAOException;

    public List<DXExons> findByListVersionAndChromosomeAndTranscriptAndRange(Integer listVersion, String chromosome, String transcript,
            Integer start, Integer end) throws CANVASDAOException;

    public List<DXExons> findByListVersionAndTranscriptAndExonAndMapNum(Integer listVersion, String transcript, Integer exon,
            Integer mapNum) throws CANVASDAOException;

}
