package org.renci.canvas.dao.clinbin;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.MissingHaplotype;
import org.renci.canvas.dao.clinbin.model.MissingHaplotypePK;

public interface MissingHaplotypeDAO extends BaseDAO<MissingHaplotype, MissingHaplotypePK> {

    public List<MissingHaplotype> findByParticipantAndIncidentalBinIdAndListVersion(String participantId, Integer incidentalBinId,
            Integer listVersion) throws CANVASDAOException;

}
