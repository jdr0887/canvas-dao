package org.renci.canvas.dao.annotation;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.renci.canvas.dao.annotation.model.AnnotationGene;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalId;
import org.renci.canvas.dao.annotation.model.AnnotationGeneExternalIdPK;
import org.renci.canvas.dao.jpa.CANVASDAOManager;

public class AnnotationGeneTest {

    @Test
    public void testSave() throws Exception {
        CANVASDAOManager daoMgr = CANVASDAOManager.getInstance();

        AnnotationGene annotationGene = new AnnotationGene("geneName", "geneDesc");
        List<AnnotationGene> foundAnnotationGenes = daoMgr.getDAOBean().getAnnotationGeneDAO().findByName("geneName");
        if (CollectionUtils.isEmpty(foundAnnotationGenes)) {
            Integer geneId = daoMgr.getDAOBean().getAnnotationGeneDAO().save(annotationGene);
            annotationGene.setId(geneId);
        } else {
            annotationGene = foundAnnotationGenes.get(0);
        }

        List<AnnotationGeneExternalId> annotationGeneExternalIds = daoMgr.getDAOBean().getAnnotationGeneExternalIdDAO()
                .findByExternalIdAndNamespace(1, "HGNC");

        AnnotationGeneExternalIdPK annotationGeneExternalIdPK = new AnnotationGeneExternalIdPK(1, annotationGene.getId(), "HGNC");

        if (CollectionUtils.isEmpty(annotationGeneExternalIds) || (CollectionUtils.isNotEmpty(annotationGeneExternalIds)
                && !annotationGeneExternalIds.stream().filter(a -> a.getId().equals(annotationGeneExternalIdPK)).findAny().isPresent())) {

            AnnotationGeneExternalId annotationGeneExternalId = new AnnotationGeneExternalId(annotationGeneExternalIdPK);
            annotationGeneExternalId.setGene(annotationGene);
            daoMgr.getDAOBean().getAnnotationGeneExternalIdDAO().save(annotationGeneExternalId);

        }

    }

}
