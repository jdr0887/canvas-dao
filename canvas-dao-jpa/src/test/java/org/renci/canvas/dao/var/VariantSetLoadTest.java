package org.renci.canvas.dao.var;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.CANVASDAOManager;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.var.model.Assembly;
import org.renci.canvas.dao.var.model.Lab;
import org.renci.canvas.dao.var.model.Library;
import org.renci.canvas.dao.var.model.Project;
import org.renci.canvas.dao.var.model.Sample;
import org.renci.canvas.dao.var.model.VariantSet;
import org.renci.canvas.dao.var.model.VariantSetLoad;

public class VariantSetLoadTest {

    @Test
    public void testFindByExample() throws CANVASDAOException {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();
        VariantSetLoadDAO variantSetLoadDAO = daoMgr.getDAOBean().getVariantSetLoadDAO();
        AssemblyDAO assemblyDAO = daoMgr.getDAOBean().getAssemblyDAO();

        VariantSetLoad example = new VariantSetLoad();
        example.setLoadFilename("/proj/renci/sequence_analysis/ncgenes/UNCseq0004/gatk.SNVcalls.vcf");
        example.setLoadProgramName("org.renci.sequencing.vcf.VcfLoader");
        example.setLoadProgramVersion("2.0");
        List<VariantSetLoad> results = variantSetLoadDAO.findByExample(example);
        if (CollectionUtils.isNotEmpty(results)) {
            VariantSetLoad vsl = results.get(0);
            VariantSet vs = vsl.getVariantSet();
            List<Assembly> foundAssemblies = assemblyDAO.findByVariantSetId(vs.getId());
            assertTrue(CollectionUtils.isNotEmpty(foundAssemblies));
        }

    }

    @Test
    public void testSave() throws CANVASDAOException {

        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

        Lab lab = new Lab("test-lab");
        Lab foundLab = daoMgr.getDAOBean().getLabDAO().findById(lab.getId());
        if (foundLab == null) {
            daoMgr.getDAOBean().getLabDAO().save(lab);
        } else {
            lab = foundLab;
        }

        Project project = new Project();
        project.setId("test-project");
        project.setLab(lab);
        Project foundProject = daoMgr.getDAOBean().getProjectDAO().findById(project.getId());
        if (foundProject == null) {
            daoMgr.getDAOBean().getProjectDAO().save(project);
        } else {
            project = foundProject;
        }

        Sample sample = new Sample();
        sample.setName("test-sample");
        sample.setProject(project);
        List<Sample> foundSamples = daoMgr.getDAOBean().getSampleDAO().findByNameAndProjectName(sample.getName(),
                sample.getProject().getId());
        if (CollectionUtils.isEmpty(foundSamples)) {
            sample.setId(daoMgr.getDAOBean().getSampleDAO().save(sample));
        } else {
            sample = foundSamples.get(0);
        }

        Library library = new Library();
        library.setName("test-library");
        library.setSample(sample);
        List<Library> foundLibraries = daoMgr.getDAOBean().getLibraryDAO().findByNameAndSampleId(library.getName(), sample.getId());
        if (CollectionUtils.isEmpty(foundLibraries)) {
            library.setId(daoMgr.getDAOBean().getLibraryDAO().save(library));
        } else {
            library = foundLibraries.get(0);
        }

        GenomeRef genomeRef = daoMgr.getDAOBean().getGenomeRefDAO().findById(2);

        VariantSet variantSet = new VariantSet();
        variantSet.setGenomeRef(genomeRef);
        variantSet.setId(daoMgr.getDAOBean().getVariantSetDAO().save(variantSet));

        Assembly assembly = new Assembly();
        assembly.setLibrary(library);
        assembly.setVariantSet(variantSet);
        assembly.setId(daoMgr.getDAOBean().getAssemblyDAO().save(assembly));

        VariantSetLoad vsl = new VariantSetLoad();
        vsl.setLoadFilename("load-filename");
        vsl.setLoadProgramName("load-program-name");
        vsl.setLoadProgramVersion("load-program-version");
        vsl.setLoadTimeStart(new Date());
        vsl.setLoadTimeStop(new Date());
        vsl.setLoadUser("jdr0887");
        vsl.setNotes("notes");
        vsl.setNumberOfDelRows(2);
        vsl.setNumberOfErrorRows(2);
        vsl.setNumberOfFilteredRows(2);
        vsl.setNumberOfInsRows(2);
        vsl.setNumberOfMultiRows(32);
        vsl.setNumberOfSkippedRefRows(3);
        vsl.setNumberOfSNPRows(3);
        vsl.setNumberOfSubRows(12);
        vsl.setVariantSet(variantSet);
        vsl.setId(variantSet.getId());

        daoMgr.getDAOBean().getVariantSetLoadDAO().save(vsl);

    }

}
