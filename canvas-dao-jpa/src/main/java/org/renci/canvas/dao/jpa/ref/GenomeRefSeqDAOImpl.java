package org.renci.canvas.dao.jpa.ref;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.renci.canvas.dao.ref.GenomeRefSeqDAO;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.ref.model.GenomeRefSeq_;
import org.renci.canvas.dao.ref.model.GenomeRef_;
import org.renci.canvas.dao.ref.model.SequenceType_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { GenomeRefSeqDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class GenomeRefSeqDAOImpl extends BaseDAOImpl<GenomeRefSeq, String> implements GenomeRefSeqDAO {

    private static final Logger logger = LoggerFactory.getLogger(GenomeRefSeqDAOImpl.class);

    public GenomeRefSeqDAOImpl() {
        super();
    }

    @Override
    public Class<GenomeRefSeq> getPersistentClass() {
        return GenomeRefSeq.class;
    }

    @Override
    public List<GenomeRefSeq> findAll() throws CANVASDAOException {
        logger.debug("ENTERING findAll()");
        List<GenomeRefSeq> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findBySeqType(String seqType) throws CANVASDAOException {
        logger.debug("ENTERING findBySeqType(String)");
        List<GenomeRefSeq> ret = new ArrayList<GenomeRefSeq>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), seqType));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findByNameAndSourceAndContig(String name, String source, String contig) throws CANVASDAOException {
        List<GenomeRefSeq> ret = new ArrayList<GenomeRefSeq>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.contig), contig));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), "Chromosome"));
            Join<GenomeRefSeq, GenomeRef> genomeRefGenomeRefSeqJoin = root.join(GenomeRefSeq_.genomeRefs);
            predicates.add(critBuilder.equal(genomeRefGenomeRefSeqJoin.get(GenomeRef_.source), source));
            predicates.add(critBuilder.equal(genomeRefGenomeRefSeqJoin.get(GenomeRef_.name), name));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findByGenomeRefId(Integer refId) throws CANVASDAOException {
        List<GenomeRefSeq> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.join(GenomeRefSeq_.genomeRefs).get(GenomeRef_.id), refId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findByGenomeRefIdAndContigAndSeqType(Integer refId, String contig, String seqType) throws CANVASDAOException {
        List<GenomeRefSeq> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.contig), contig));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), seqType));
            predicates.add(critBuilder.equal(root.join(GenomeRefSeq_.genomeRefs).get(GenomeRef_.id), refId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findByGenomeRefIdAndSeqType(Integer refId, String seqType) throws CANVASDAOException {
        List<GenomeRefSeq> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), seqType));
            predicates.add(critBuilder.equal(root.join(GenomeRefSeq_.genomeRefs).get(GenomeRef_.id), refId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeRefSeq> findByGenomeRefIdAndContigAndSeqTypeAndAccessionPrefix(Integer refId, String contig, String seqType,
            String prefix) throws CANVASDAOException {
        List<GenomeRefSeq> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeRefSeq> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeRefSeq> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.contig), contig));
            predicates.add(critBuilder.equal(root.get(GenomeRefSeq_.sequenceType).get(SequenceType_.id), seqType));
            predicates.add(critBuilder.equal(root.join(GenomeRefSeq_.genomeRefs).get(GenomeRef_.id), refId));
            if (StringUtils.isNotEmpty(prefix)) {
                if (!prefix.endsWith("%")) {
                    prefix += "%";
                }
                predicates.add(critBuilder.like(root.get(GenomeRefSeq_.id), prefix));
            }
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<GenomeRefSeq> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
