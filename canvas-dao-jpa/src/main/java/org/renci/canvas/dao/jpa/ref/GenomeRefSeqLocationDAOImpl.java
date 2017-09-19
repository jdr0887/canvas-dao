package org.renci.canvas.dao.jpa.ref;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.ref.GenomeRefSeqLocationDAO;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocation;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocationPK;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocationPK_;
import org.renci.canvas.dao.ref.model.GenomeRefSeqLocation_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GenomeRefSeqLocationDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GenomeRefSeqLocationDAOImpl extends BaseDAOImpl<GenomeRefSeqLocation, GenomeRefSeqLocationPK>
        implements GenomeRefSeqLocationDAO {

    private static final Logger logger = LoggerFactory.getLogger(GenomeRefSeqLocationDAOImpl.class);

    public GenomeRefSeqLocationDAOImpl() {
        super();
    }

    @Override
    public Class<GenomeRefSeqLocation> getPersistentClass() {
        return GenomeRefSeqLocation.class;
    }

    @Override
    public List<GenomeRefSeqLocation> findByRefIdAndVersionedAccesionAndPosition(Integer refId, String verAccession, Integer position)
            throws CANVASDAOException {
        logger.debug("ENTERING findByRefIdAndVersionedAccesionAndPosition(Integer, String, Integer)");
        List<GenomeRefSeqLocation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeqLocation> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeqLocation> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.genomeRef), refId));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.genomeRefSeq), verAccession));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.position), position));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GenomeRefSeqLocation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeqLocation> findByRefIdAndVersionedAccesionAndRange(Integer refId, String verAccession, Integer start,
            Integer stop) throws CANVASDAOException {
        logger.debug("ENTERING findByRefIdAndVersionedAccesionAndPosition(Integer, String, Integer, Integer)");
        List<GenomeRefSeqLocation> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeqLocation> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeqLocation> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.genomeRef), refId));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.genomeRefSeq), verAccession));
            predicates.add(critBuilder.between(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.position), start, stop));
            crit.orderBy(critBuilder.asc(root.get(GenomeRefSeqLocation_.id).get(GenomeRefSeqLocationPK_.position)));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GenomeRefSeqLocation> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
