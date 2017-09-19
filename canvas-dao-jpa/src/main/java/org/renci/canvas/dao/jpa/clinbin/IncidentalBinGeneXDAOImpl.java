package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.IncidentalBinGeneXDAO;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGeneX;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGeneXPK;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGeneXPK_;
import org.renci.canvas.dao.clinbin.model.IncidentalBinGeneX_;
import org.renci.canvas.dao.clinbin.model.ZygosityModeType_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { IncidentalBinGeneXDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class IncidentalBinGeneXDAOImpl extends BaseDAOImpl<IncidentalBinGeneX, IncidentalBinGeneXPK> implements IncidentalBinGeneXDAO {

    private static final Logger logger = LoggerFactory.getLogger(IncidentalBinGeneXDAOImpl.class);

    public IncidentalBinGeneXDAOImpl() {
        super();
    }

    @Override
    public Class<IncidentalBinGeneX> getPersistentClass() {
        return IncidentalBinGeneX.class;
    }

    @Override
    public List<IncidentalBinGeneX> findByIncidentalBinIdAndVersionAndZygosityModes(Integer incidentalBinId, Integer incidentalBinVersion,
            List<String> zygosityModes) throws CANVASDAOException {
        logger.debug("ENTERING findByIncidentalBinIdAndVersionAndZygosityModes(Integer, Integer, List<String>)");
        List<IncidentalBinGeneX> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<IncidentalBinGeneX> crit = critBuilder.createQuery(getPersistentClass());
            Root<IncidentalBinGeneX> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(IncidentalBinGeneX_.id).get(IncidentalBinGeneXPK_.incidentalBin), incidentalBinId));
            predicates.add(critBuilder.equal(root.get(IncidentalBinGeneX_.id).get(IncidentalBinGeneXPK_.version), incidentalBinVersion));

            predicates.add(root.join(IncidentalBinGeneX_.zygosityMode, JoinType.LEFT).get(ZygosityModeType_.id).in(zygosityModes));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<IncidentalBinGeneX> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.IncidentalBinGeneX.includeManyToOnes"));
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<IncidentalBinGeneX> findByIncidentalBinIdAndVersion(Integer incidentalBinId, Integer incidentalBinVersion)
            throws CANVASDAOException {
        logger.debug("ENTERING findByIncidentalBinIdAndVersion(Integer, Integer)");
        List<IncidentalBinGeneX> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<IncidentalBinGeneX> crit = critBuilder.createQuery(getPersistentClass());
            Root<IncidentalBinGeneX> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(IncidentalBinGeneX_.id).get(IncidentalBinGeneXPK_.incidentalBin), incidentalBinId));
            predicates.add(critBuilder.equal(root.get(IncidentalBinGeneX_.id).get(IncidentalBinGeneXPK_.version), incidentalBinVersion));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);

            TypedQuery<IncidentalBinGeneX> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
