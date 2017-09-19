package org.renci.canvas.dao.refseq;

import java.util.List;

import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.refseq.model.Transcript;

public class TranscriptTest {

    @Test
    public void testFindByGenomeRefIdAndRefSeqVersion() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        TranscriptDAO transcriptDAO = daoMgr.getDAOBean().getTranscriptDAO();
        List<Transcript> transcriptList = transcriptDAO.findByGenomeRefIdAndRefSeqVersion(2, "61");
        System.out.println(transcriptList.size());
        // transcriptList.forEach(a -> System.out.printf("%s%n", a.toString()));
    }

}
