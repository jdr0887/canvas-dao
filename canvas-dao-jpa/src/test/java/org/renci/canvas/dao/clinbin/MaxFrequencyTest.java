package org.renci.canvas.dao.clinbin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.model.MaxFrequency;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.refseq.Variants_61_2_DAO;
import org.renci.canvas.dao.refseq.model.Variants_61_2;

public class MaxFrequencyTest {

    @Test
    public void testFindByGeneNameAndMaxAlleleFrequency() {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        MaxFrequencyDAO maxFreqDAO = daoMgr.getDAOBean().getMaxFrequencyDAO();

        Variants_61_2_DAO variants_61_2_DAO = daoMgr.getDAOBean().getVariants_61_2_DAO();

        try {
            List<MaxFrequency> clinbinMaxFreqList = maxFreqDAO.findByGeneNameAndMaxAlleleFrequency("BRCA1", 0.05);
            assertTrue(CollectionUtils.isNotEmpty(clinbinMaxFreqList));
            int count = 0;
            System.out.println(clinbinMaxFreqList.size());
            for (MaxFrequency maxFreq : clinbinMaxFreqList) {
                List<Variants_61_2> variants = variants_61_2_DAO.findByLocatedVariantId(maxFreq.getLocatedVariant().getId());
                assertTrue(CollectionUtils.isNotEmpty(variants));
                System.out.println(variants.size());
                count += variants.size();
            }
            System.out.println(count);
        } catch (CANVASDAOException e) {
            e.printStackTrace();
        }
    }
}
