package org.renci.canvas.dao.clinvar;

import java.util.List;

import org.renci.canvas.dao.BaseDAO;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinvar.model.AssertionRanking;

public interface AssertionRankingDAO extends BaseDAO<AssertionRanking, String> {

    public List<AssertionRanking> findAll() throws CANVASDAOException;

}
