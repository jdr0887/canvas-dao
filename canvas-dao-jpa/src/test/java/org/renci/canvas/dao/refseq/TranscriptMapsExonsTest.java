package org.renci.canvas.dao.refseq;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons;

public class TranscriptMapsExonsTest {

    @Test
    public void testFindByGenomeRefIdAndRefSeqVersion() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        TranscriptMapsExonsDAO transcriptMapsExonsDAO = daoMgr.getDAOBean().getTranscriptMapsExonsDAO();
        List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByGenomeRefIdAndRefSeqVersion(2, "61");
        transcriptMapsExonsList.forEach(a -> System.out.printf("%s%n", a.getId().toString()));
    }

    @Test
    public void testFindByGenomeRefIdAndRefSeqVersionAndAccession() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        TranscriptMapsExonsDAO transcriptMapsExonsDAO = daoMgr.getDAOBean().getTranscriptMapsExonsDAO();
        List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByGenomeRefIdAndRefSeqVersionAndAccession(2, "61",
                "NM_182701.1");
        transcriptMapsExonsList.forEach(a -> System.out.printf("%s%n", a.toString()));
    }

    @Test
    public void testFindByTranscriptMapsId() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        TranscriptMapsExonsDAO transcriptMapsExonsDAO = daoMgr.getDAOBean().getTranscriptMapsExonsDAO();
        List<TranscriptMapsExons> transcriptMapsExonsList = transcriptMapsExonsDAO.findByTranscriptMapsId(365544);
        transcriptMapsExonsList.forEach(a -> System.out.printf("%s%n", a.toString()));
    }

    @Test
    public void testFindByMaxContig() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        TranscriptMapsExonsDAO transcriptMapsExonsDAO = daoMgr.getDAOBean().getTranscriptMapsExonsDAO();
        Integer result = transcriptMapsExonsDAO.findMaxContig(2, "61", "NC_000001.10", 380407);
        System.out.println(result);
    }

}
