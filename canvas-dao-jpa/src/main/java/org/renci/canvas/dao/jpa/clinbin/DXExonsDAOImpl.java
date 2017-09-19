package org.renci.canvas.dao.jpa.clinbin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.CANVASDAOException;
import org.renci.canvas.dao.clinbin.DXExonsDAO;
import org.renci.canvas.dao.clinbin.model.DXExons;
import org.renci.canvas.dao.clinbin.model.DXExons_;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { DXExonsDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class DXExonsDAOImpl extends BaseDAOImpl<DXExons, Integer> implements DXExonsDAO {

    private static final Logger logger = LoggerFactory.getLogger(DXExonsDAOImpl.class);

    public DXExonsDAOImpl() {
        super();
    }

    @Override
    public Class<DXExons> getPersistentClass() {
        return DXExons.class;
    }

    @Override
    public List<DXExons> findByListVersionAndTranscriptAndExonAndMapNum(Integer listVersion, String transcript, Integer exon, Integer mapNum)
            throws CANVASDAOException {
        logger.debug("ENTERING findByListVersionAndTranscriptAndExon(Integer, String, Integer, Integer)");
        List<DXExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(DXExons_.listVersion), listVersion));
            predicates.add(critBuilder.equal(root.get(DXExons_.transcript), transcript));
            predicates.add(critBuilder.equal(root.get(DXExons_.exon), exon));
            predicates.add(critBuilder.equal(root.get(DXExons_.mapNum), mapNum));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DXExons> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.DXExons.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;

    }

    @Override
    public List<DXExons> findByListVersionAndChromosomeAndTranscriptAndRange(Integer listVersion, String chromosome, String transcript,
            Integer start, Integer end) throws CANVASDAOException {
        logger.debug("ENTERING findByListVersionAndChromosomeAndTranscriptAndRange(Integer, String, String, Integer, Integer)");
        List<DXExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(critBuilder.equal(root.get(DXExons_.intervalStart), start));
            predicates.add(critBuilder.equal(root.get(DXExons_.intervalEnd), end));
            predicates.add(critBuilder.equal(root.get(DXExons_.listVersion), listVersion));
            predicates.add(critBuilder.equal(root.get(DXExons_.chromosome), chromosome));
            predicates.add(critBuilder.equal(root.get(DXExons_.transcript), transcript));

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DXExons> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("clinbin.DXExons.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public Integer findMaxListVersion() throws CANVASDAOException {
        logger.debug("ENTERING findMaxListVersion()");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> crit = critBuilder.createQuery(Integer.class);
        Root<DXExons> root = crit.from(getPersistentClass());
        crit.select(critBuilder.max(root.get(DXExons_.listVersion)));
        TypedQuery<Integer> query = getEntityManager().createQuery(crit);
        Integer ret = query.getSingleResult();
        return ret;
    }

    @Override
    public List<DXExons> findByListVersion(Integer listVersion) throws CANVASDAOException {
        logger.debug("ENTERING findByListVersion(Integer)");
        List<DXExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(DXExons_.listVersion), listVersion));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DXExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DXExons> findByListVersionAndChromosome(Integer listVersion, String chromosome) throws CANVASDAOException {
        logger.debug("ENTERING findByListVersionAndChromosome(Integer, String)");
        List<DXExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(DXExons_.listVersion), listVersion));
            predicates.add(critBuilder.equal(root.get(DXExons_.chromosome), chromosome));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DXExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    @Override
    public List<DXExons> findByListVersionAndChromosomeAndRange(Integer listVersion, String chromosome, Integer start, Integer end)
            throws CANVASDAOException {
        logger.debug("ENTERING findByListVersionAndChromosomeAndRange(Integer, String, Integer, Integer)");
        List<DXExons> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<DXExons> crit = critBuilder.createQuery(getPersistentClass());
            Root<DXExons> root = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(root.get(DXExons_.listVersion), listVersion));
            predicates.add(critBuilder.equal(root.get(DXExons_.chromosome), chromosome));
            predicates.add(critBuilder.greaterThanOrEqualTo(root.get(DXExons_.intervalStart), start));
            predicates.add(critBuilder.lessThanOrEqualTo(root.get(DXExons_.intervalEnd), end));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<DXExons> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

}
