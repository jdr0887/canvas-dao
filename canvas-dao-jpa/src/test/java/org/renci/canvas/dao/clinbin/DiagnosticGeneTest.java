package org.renci.canvas.dao.clinbin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneSynonym;
import org.renci.canvas.dao.clinbin.model.DXExons;
import org.renci.canvas.dao.clinbin.model.DiagnosticGene;
import org.renci.canvas.dao.clinbin.model.DiagnosticResultVersion;
import org.renci.canvas.dao.jpa.annotation.AnnotationGeneExternalIdDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DXExonsDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticGeneDAOImpl;
import org.renci.canvas.dao.jpa.clinbin.DiagnosticResultVersionDAOImpl;
import org.renci.canvas.dao.jpa.refseq.RefSeqCodingSequenceDAOImpl;
import org.renci.canvas.dao.jpa.refseq.RefSeqGeneDAOImpl;
import org.renci.canvas.dao.jpa.refseq.RegionGroupRegionDAOImpl;
import org.renci.canvas.dao.jpa.refseq.TranscriptMapsDAOImpl;
import org.renci.canvas.dao.jpa.refseq.TranscriptMapsExonsDAOImpl;
import org.renci.canvas.dao.refseq.model.RefSeqCodingSequence;
import org.renci.canvas.dao.refseq.model.RefSeqGene;
import org.renci.canvas.dao.refseq.model.RegionGroupRegion;
import org.renci.canvas.dao.refseq.model.TranscriptMaps;
import org.renci.canvas.dao.refseq.model.TranscriptMapsExons;

public class DiagnosticGeneTest {

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
    public void testFindByGroupVersionAndExternalNamespaceAndVersion() throws Exception {

        DiagnosticResultVersionDAOImpl diagnosticResultVersionDAO = new DiagnosticResultVersionDAOImpl();
        diagnosticResultVersionDAO.setEntityManager(em);

        DiagnosticGeneDAOImpl diagnosticGeneDAO = new DiagnosticGeneDAOImpl();
        diagnosticGeneDAO.setEntityManager(em);

        DXExonsDAOImpl dxExonsDAO = new DXExonsDAOImpl();
        dxExonsDAO.setEntityManager(em);

        AnnotationGeneExternalIdDAOImpl annotationGeneExternalIdDAO = new AnnotationGeneExternalIdDAOImpl();
        annotationGeneExternalIdDAO.setEntityManager(em);

        TranscriptMapsExonsDAOImpl transcriptMapsExonsDAO = new TranscriptMapsExonsDAOImpl();
        transcriptMapsExonsDAO.setEntityManager(em);

        TranscriptMapsDAOImpl transcriptMapsDAO = new TranscriptMapsDAOImpl();
        transcriptMapsDAO.setEntityManager(em);

        RefSeqGeneDAOImpl refseqGeneDAO = new RefSeqGeneDAOImpl();
        refseqGeneDAO.setEntityManager(em);

        RefSeqCodingSequenceDAOImpl refseqCodingSequenceDAO = new RefSeqCodingSequenceDAOImpl();
        refseqCodingSequenceDAO.setEntityManager(em);

        RegionGroupRegionDAOImpl regionGroupRegionDAO = new RegionGroupRegionDAOImpl();
        regionGroupRegionDAO.setEntityManager(em);

        final Set<Interval> intervals = new HashSet<>();

        List<DiagnosticResultVersion> allDiagnosticResultVersions = diagnosticResultVersionDAO.findAll();
        if (CollectionUtils.isNotEmpty(allDiagnosticResultVersions)) {
            allDiagnosticResultVersions.sort((a, b) -> b.getId().compareTo(a.getId()));

            DiagnosticResultVersion latestDiagnosticResultVersion = allDiagnosticResultVersions.get(0);
            List<DiagnosticGene> diagnosticGenes = diagnosticGeneDAO.findByGroupVersionAndExternalNamespaceAndVersion(
                    latestDiagnosticResultVersion.getDiagnosticBinGroupVersion(), "refseq",
                    latestDiagnosticResultVersion.getRefseqVersion().toString());

            List<String> prefixExclude = Arrays.asList("NR_", "XR_");

            int count = 0;

            for (DiagnosticGene diagnosticGene : diagnosticGenes) {

                // if (!Arrays.asList(29).contains(diagnosticGene.getDx().getId())) {
                // continue;
                // }

                try {
                    AnnotationGene annotationGene = diagnosticGene.getGene();

                    List<AnnotationGeneExternalId> externals = annotationGeneExternalIdDAO.findByAnnotationGeneId(annotationGene.getId());

                    if (CollectionUtils.isNotEmpty(externals)) {

                        List<AnnotationGeneExternalId> filteredExternals = externals.stream()
                                .filter(b -> "refseq".equals(b.getId().getNamespace())
                                        && latestDiagnosticResultVersion.getRefseqVersion().toString().equals(b.getId().getNamespaceVer()))
                                .collect(Collectors.toList());

                        if (CollectionUtils.isNotEmpty(filteredExternals)) {

                            for (AnnotationGeneExternalId externalAnnotationGene : filteredExternals) {

                                RefSeqGene refseqGene = refseqGeneDAO.findById(externalAnnotationGene.getId().getExternalId());

                                Optional<AnnotationGeneSynonym> optionalAnnotationGeneSynonym = annotationGene.getSynonyms()
                                        .parallelStream().filter(b -> b.getId().getSynonym().equals(refseqGene.getName())).findAny();

                                String geneName = null;
                                if (optionalAnnotationGeneSynonym.isPresent()
                                        && !refseqGene.getName().equals(diagnosticGene.getGene().getPreferredName())) {
                                    geneName = diagnosticGene.getGene().getPreferredName();
                                } else {
                                    geneName = refseqGene.getName();
                                }

                                List<TranscriptMaps> transcriptMapsList = transcriptMapsDAO.findByGeneIdAndGenomeRefId(refseqGene.getId(),
                                        latestDiagnosticResultVersion.getGenomeRef().getId());

                                List<TranscriptMaps> filteredTranscriptMapsList = transcriptMapsList.stream()
                                        .filter(a -> a.getGenomeRefSeq().getSequenceType().getId().equals("Chromosome")
                                                && !prefixExclude.contains(a.getTranscript().getId().substring(0, 3)))
                                        .collect(Collectors.toList());

                                Map<String, List<TranscriptMaps>> transcript2TranscriptMapsMap = new HashMap<>();

                                for (TranscriptMaps transcriptMaps : filteredTranscriptMapsList) {
                                    String key = transcriptMaps.getTranscript().getId();
                                    if (!transcript2TranscriptMapsMap.containsKey(key)) {
                                        transcript2TranscriptMapsMap.put(key, new ArrayList<>());
                                    }
                                    transcript2TranscriptMapsMap.get(key).add(transcriptMaps);
                                }

                                for (TranscriptMaps transcriptMaps : filteredTranscriptMapsList) {

                                    List<RefSeqCodingSequence> refSeqCodingSequenceList = refseqCodingSequenceDAO
                                            .findByRefSeqVersionAndTranscriptId(latestDiagnosticResultVersion.getRefseqVersion().toString(),
                                                    transcriptMaps.getTranscript().getId());

                                    Range<Integer> proteinRange = null;
                                    if (CollectionUtils.isNotEmpty(refSeqCodingSequenceList)) {
                                        RefSeqCodingSequence refSeqCDS = refSeqCodingSequenceList.get(0);
                                        List<RegionGroupRegion> rgrList = regionGroupRegionDAO
                                                .findByRefSeqCodingSequenceId(refSeqCDS.getId());
                                        if (CollectionUtils.isNotEmpty(rgrList)) {
                                            proteinRange = rgrList.get(0).getId().getRegionRange();
                                        }
                                    }

                                    if ("-".equals(transcriptMaps.getStrand())) {
                                        transcriptMaps.getExons().sort((a, b) -> b.getId().getExonNum().compareTo(a.getId().getExonNum()));
                                    } else {
                                        transcriptMaps.getExons().sort((a, b) -> a.getId().getExonNum().compareTo(b.getId().getExonNum()));
                                    }

                                    int idx = 0;

                                    if (geneName.equals("ORAI1")) {
                                        System.out.println("");
                                    }

                                    for (TranscriptMapsExons exons : transcriptMaps.getExons()) {

                                        Range<Integer> transcriptMapsExonsContigRange = exons.getContigRange();
                                        Range<Integer> transcriptMapsExonsTranscriptRange = exons.getTranscriptRange();

                                        Integer contigStart = transcriptMapsExonsContigRange.getMinimum();
                                        Integer contigEnd = transcriptMapsExonsContigRange.getMaximum();

                                        if (proteinRange != null) {

                                            if (proteinRange.isAfter(transcriptMapsExonsTranscriptRange.getMaximum())) {
                                                continue;
                                            }

                                            if (proteinRange.isBefore(transcriptMapsExonsTranscriptRange.getMinimum())) {
                                                continue;
                                            }

                                            if ("-".equals(transcriptMaps.getStrand())) {

                                                if (transcriptMapsExonsTranscriptRange.contains(proteinRange.getMinimum())) {
                                                    contigEnd = transcriptMapsExonsContigRange.getMaximum()
                                                            - (proteinRange.getMinimum() - transcriptMapsExonsTranscriptRange.getMinimum());
                                                }

                                                if (transcriptMapsExonsTranscriptRange.contains(proteinRange.getMaximum())) {
                                                    contigStart = transcriptMapsExonsContigRange.getMaximum()
                                                            - (proteinRange.getMaximum() - transcriptMapsExonsTranscriptRange.getMinimum());
                                                }

                                            }

                                            if ("+".equals(transcriptMaps.getStrand())) {

                                                if (transcriptMapsExonsTranscriptRange.contains(proteinRange.getMinimum())) {
                                                    contigStart = transcriptMapsExonsContigRange.getMinimum()
                                                            + (proteinRange.getMinimum() - transcriptMapsExonsTranscriptRange.getMinimum());
                                                }

                                                if (transcriptMapsExonsTranscriptRange.contains(proteinRange.getMaximum())) {
                                                    contigEnd = transcriptMapsExonsContigRange.getMaximum()
                                                            - (transcriptMapsExonsTranscriptRange.getMaximum() - proteinRange.getMaximum());
                                                }

                                            }
                                        }

                                        Range<Integer> contigRange = Range.between(contigStart, contigEnd);
                                        intervals.add(new Interval(transcriptMaps.getGenomeRefSeq().getId(), contigRange.getMinimum() - 2,
                                                contigRange.getMaximum() + 2, geneName, idx++, transcriptMaps.getTranscript().getId(),
                                                diagnosticGene.getDx().getId()));

                                        Integer mapNum = transcript2TranscriptMapsMap.get(transcriptMaps.getTranscript().getId())
                                                .indexOf(transcriptMaps) + 1;

                                        List<DXExons> foundExons = dxExonsDAO.findByListVersionAndTranscriptAndExonAndMapNum(
                                                latestDiagnosticResultVersion.getId(), transcriptMaps.getTranscript().getId(), idx, mapNum);

                                        if (CollectionUtils.isEmpty(foundExons)) {
                                            DXExons dxExons = new DXExons(latestDiagnosticResultVersion.getId(), annotationGene,
                                                    transcriptMaps.getTranscript().getId(), idx, transcriptMaps.getGenomeRefSeq().getId(),
                                                    contigRange.getMinimum() - 2, contigRange.getMaximum() + 2, mapNum);
                                            // dxExons.setId(dxExonsDAO.save(dxExons));
                                            System.out.println(dxExons.toString());
                                        }

                                    }
                                }
                            }
                        }
                    }
                } catch (CANVASDAOException e) {
                    e.printStackTrace();
                }
                ++count;
                // if (count == 10) {
                // break;
                // }
            }

            writeDXGeneFile(intervals);

            writeExonsFile(intervals);

            writeExonsBedFile(intervals, latestDiagnosticResultVersion.getId());

            writeDXGeneBedFiles(intervals, latestDiagnosticResultVersion.getId());
        }
    }

    private void writeDXGeneBedFiles(Set<Interval> intervals, Integer listVersion) throws IOException {

        Set<Integer> dxIdSet = new HashSet<>();
        for (Interval interval : intervals) {
            dxIdSet.add(interval.getDxId());
        }

        for (Integer dxId : dxIdSet) {

            File genesByDXIdIntervalListFile = new File("/tmp", String.format("genes_dxid_%s_v_%s.bed", dxId, listVersion));
            try (FileWriter fw = new FileWriter(genesByDXIdIntervalListFile); BufferedWriter bw = new BufferedWriter(fw)) {

                List<Interval> dxFilteredIntervals = intervals.stream().filter(a -> a.getDxId().equals(dxId)).collect(Collectors.toList());

                Map<Pair<String, String>, List<Interval>> map = new HashMap<>();

                for (Interval interval : dxFilteredIntervals) {
                    Pair<String, String> key = Pair.of(interval.getAccession(), interval.getTranscript());
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(interval);
                }

                Set<String> dxGeneIntervalValue = new HashSet<>();

                for (Pair<String, String> key : map.keySet()) {

                    List<Interval> groupedIntervals = map.get(key);

                    Interval max = null;
                    Optional<Interval> optionalInterval = groupedIntervals.stream().max((a, b) -> a.getStop().compareTo(b.getStop()));
                    if (optionalInterval.isPresent()) {
                        max = optionalInterval.get();
                    }

                    Interval min = null;
                    optionalInterval = groupedIntervals.stream().min((a, b) -> a.getStart().compareTo(b.getStart()));
                    if (optionalInterval.isPresent()) {
                        min = optionalInterval.get();
                    }

                    Range<Integer> range = Range.between(min.getStart(), max.getStop());
                    dxGeneIntervalValue.add(String.format("%s\t%s\t%s", key.getLeft(), range.getMinimum(), range.getMaximum()));

                }

                for (String i : dxGeneIntervalValue) {
                    bw.write(i);
                    bw.newLine();
                    bw.flush();
                }

            }

        }

    }

    private void writeExonsBedFile(Set<Interval> intervals, Integer listVersion) throws IOException {

        Set<String> intervalRegionSet = new HashSet<>();
        for (Interval interval : intervals) {
            intervalRegionSet.add(interval.toBedFormat());
        }

        File intervalListFile = new File("/tmp", String.format("exons_pm_0_v%s.bed", listVersion));
        try (FileWriter fw = new FileWriter(intervalListFile); BufferedWriter bw = new BufferedWriter(fw)) {
            for (String interval : intervalRegionSet) {
                bw.write(interval);
                bw.newLine();
                bw.flush();
            }
        }
    }

    private void writeExonsFile(Set<Interval> intervals) throws IOException {
        TreeSet<Interval> tsIntervals = new TreeSet<>();
        tsIntervals.addAll(intervals);
        File exonsFile = new File("/tmp", "exons.txt");
        try (FileWriter fw = new FileWriter(exonsFile); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Interval interval : tsIntervals) {
                bw.write(interval.toStringRaw());
                bw.newLine();
                bw.flush();
            }
        }
    }

    private void writeDXGeneFile(Set<Interval> intervals) throws IOException {

        Set<String> geneDXPairSet = new HashSet<>();
        for (Interval interval : intervals) {
            geneDXPairSet.add(String.format("%s\t%s", interval.getGeneName(), interval.getDxId()));
        }

        File dxGenesFile = new File("/tmp", "dx_genes.txt");
        try (FileWriter fw = new FileWriter(dxGenesFile); BufferedWriter bw = new BufferedWriter(fw)) {
            for (String pair : geneDXPairSet) {
                bw.write(pair);
                bw.newLine();
                bw.flush();
            }
        }
    }

    @Test
    public void asdf() throws IOException {
        Set<String> asdf = new HashSet<>();

        File input = new File("/tmp", "genes_dxid_1_v_43.orig.bed.sorted");
        try (FileReader fr = new FileReader(input); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                asdf.add(line);
            }
        }

        File output = new File("/tmp", "genes_dxid_1_v_43.orig.bed.sorted.uniq");
        try (FileWriter fw = new FileWriter(output); BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : asdf) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        }

    }

    class Interval implements Comparable<Interval> {

        private String accession;

        private Integer start;

        private Integer stop;

        private String geneName;

        private Integer index;

        private String transcript;

        private Integer dxId;

        public Interval() {
            super();
        }

        public Interval(String accession, Integer start, Integer stop, String geneName, Integer index, String transcript, Integer dxId) {
            super();
            this.accession = accession;
            this.start = start;
            this.stop = stop;
            this.geneName = geneName;
            this.index = index;
            this.transcript = transcript;
            this.dxId = dxId;
        }

        public Integer getDxId() {
            return dxId;
        }

        public void setDxId(Integer dxId) {
            this.dxId = dxId;
        }

        public String getAccession() {
            return accession;
        }

        public void setAccession(String accession) {
            this.accession = accession;
        }

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getStop() {
            return stop;
        }

        public void setStop(Integer stop) {
            this.stop = stop;
        }

        public String getGeneName() {
            return geneName;
        }

        public void setGeneName(String geneName) {
            this.geneName = geneName;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getTranscript() {
            return transcript;
        }

        public void setTranscript(String transcript) {
            this.transcript = transcript;
        }

        public String toGATKIntervalList() {
            return String.format("%s:%s-%s", accession, start, stop);
        }

        public String toBedFormat() {
            return String.format("%s\t%s\t%s", accession, start, stop);
        }

        @Override
        public String toString() {
            return String.format("Interval [stop=%s, accession=%s, geneName=%s, start=%s, index=%s, transcript=%s]", stop, accession,
                    geneName, start, index, transcript);
        }

        public String toStringRaw() {
            return String.format("%s\t%s\t%s\t%s\t%s\t%s", stop, accession, geneName, start, index, transcript);
        }

        @Override
        public int compareTo(Interval o) {
            int ret = this.accession.compareTo(o.getAccession());

            if (ret == 0) {
                ret = this.transcript.compareTo(o.getTranscript());
            }

            if (ret == 0) {
                ret = this.start.compareTo(o.getStart());
            }

            return ret;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((accession == null) ? 0 : accession.hashCode());
            result = prime * result + ((dxId == null) ? 0 : dxId.hashCode());
            result = prime * result + ((geneName == null) ? 0 : geneName.hashCode());
            result = prime * result + ((index == null) ? 0 : index.hashCode());
            result = prime * result + ((start == null) ? 0 : start.hashCode());
            result = prime * result + ((stop == null) ? 0 : stop.hashCode());
            result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Interval other = (Interval) obj;
            if (accession == null) {
                if (other.accession != null)
                    return false;
            } else if (!accession.equals(other.accession))
                return false;
            if (dxId == null) {
                if (other.dxId != null)
                    return false;
            } else if (!dxId.equals(other.dxId))
                return false;
            if (geneName == null) {
                if (other.geneName != null)
                    return false;
            } else if (!geneName.equals(other.geneName))
                return false;
            if (index == null) {
                if (other.index != null)
                    return false;
            } else if (!index.equals(other.index))
                return false;
            if (start == null) {
                if (other.start != null)
                    return false;
            } else if (!start.equals(other.start))
                return false;
            if (stop == null) {
                if (other.stop != null)
                    return false;
            } else if (!stop.equals(other.stop))
                return false;
            if (transcript == null) {
                if (other.transcript != null)
                    return false;
            } else if (!transcript.equals(other.transcript))
                return false;
            return true;
        }

    }

}
