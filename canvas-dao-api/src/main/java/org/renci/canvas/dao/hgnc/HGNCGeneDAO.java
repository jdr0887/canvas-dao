package org.renci.canvas.dao.hgnc;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.hgnc.model.HGNCGene;

public interface HGNCGeneDAO extends BaseDAO<HGNCGene, Integer> {

    public List<HGNCGene> findByAnnotationGeneExternalIdsNamespace(String namespace) throws CANVASDAOException;

    public List<HGNCGene> findByAnnotationGeneExternalIdsGeneIdsAndNamespace(Integer geneId, String namespace) throws CANVASDAOException;

    public List<HGNCGene> findByName(String name) throws CANVASDAOException;

    public List<HGNCGene> findBySymbol(String symbol) throws CANVASDAOException;

}
