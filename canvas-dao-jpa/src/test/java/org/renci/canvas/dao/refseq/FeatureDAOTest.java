package org.renci.canvas.dao.refseq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.refseq.FeatureDAOImpl;
import org.renci.canvas.dao.jpa.refseq.RegionGroupRegionDAOImpl;
import org.renci.canvas.dao.refseq.model.Feature;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;

public class FeatureDAOTest {

    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    @Before
    public void before() {
        this.emf = Persistence.createEntityManagerFactory("canvas_test", null);
        this.em = emf.createEntityManager();
    }

    @After
    public void after() {
        this.em.close();
        this.emf.close();
    }

    @Test
    public void testFindByRefSeqVersionAndTranscriptId() throws CANVASDAOException {
        FeatureDAOImpl featureDAO = new FeatureDAOImpl();
        featureDAO.setEntityManager(em);

        RegionGroupRegionDAOImpl regionGroupRegionDAO = new RegionGroupRegionDAOImpl();
        regionGroupRegionDAO.setEntityManager(em);

        List<Feature> featureList = featureDAO.findByRefSeqVersionAndTranscriptId("61", "NM_000179.2");

        Map<String, List<Feature>> featureMap = new HashMap<String, List<Feature>>();

        for (Feature feature : featureList) {
            List<RegionGroupRegion> rgrList = regionGroupRegionDAO.findByRegionGroupId(feature.getRegionGroup().getId());
            feature.getRegionGroup().getTranscript();

            if (!featureMap.containsKey(feature.getRegionGroup().getTranscript().getId())) {
                featureMap.put(feature.getRegionGroup().getTranscript().getId(), new ArrayList<Feature>());
            }
            featureMap.get(feature.getRegionGroup().getTranscript().getId()).add(feature);
        }

        // List<Feature> toRemove = new ArrayList<>();
        // for (String key : featureMap.keySet()) {
        // if (featureMap.get(key).size() > 1) {
        // featureMap.get(key).sort((a, b) -> a.getRegionGroup().getTranscript().compareTo());
        // }
        //
        // if (CollectionUtils.isNotEmpty(rgrList)) {
        // for (RegionGroupRegion rgr : rgrList) {
        // Range<Integer> rgrRange = Range.between(rgr.getKey().getRegionStart(), rgr.getKey().getRegionEnd());
        // if (rgrRange.contains(transcriptPosition)) {
        // return f.getId();
        // }
        // }
        //
        // }
        //
        // }

        // Map<String, List<TranscriptMaps>> transcriptMap = new HashMap<String, List<TranscriptMaps>>();
        // for (TranscriptMaps tMap : transcriptMapsList) {
        // if (!transcriptMap.containsKey(tMap.getTranscript().getVersionId())) {
        // transcriptMap.put(tMap.getTranscript().getVersionId(), new ArrayList<TranscriptMaps>());
        // }
        // transcriptMap.get(tMap.getTranscript().getVersionId()).add(tMap);
        // }
        // TranscriptMaps remove = null;
        // for (String key : transcriptMap.keySet()) {
        // if (transcriptMap.get(key).size() > 1) {
        // transcriptMap.get(key).sort((a, b) -> a.getMapCount().compareTo(b.getMapCount()));
        // remove = transcriptMap.get(key).get(0);
        // }
        // }
        // transcriptMapsList.remove(remove);

    }

    @Test
    public void testFindByRefSeqVersionAndTranscriptIdAndTranscriptPosition() throws CANVASDAOException {
        FeatureDAOImpl featureDAO = new FeatureDAOImpl();
        featureDAO.setEntityManager(em);
        List<Feature> featureList = featureDAO.findByRefSeqVersionAndTranscriptIdAndTranscriptPosition("61", "NM_152900.2", 2000);
        featureList.forEach(a -> System.out.println(a.toString()));
        featureList = featureDAO.findByRefSeqVersionAndTranscriptIdAndTranscriptPosition("80", "NM_152900.2", 2007);
        featureList.forEach(a -> System.out.println(a.toString()));
    }

}
