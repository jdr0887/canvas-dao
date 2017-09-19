package org.renci.canvas.dao.jpa.var;

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
import org.renci.canvas.dao.var.VariantSetLoadDAO;
import org.renci.canvas.dao.var.model.VariantSet;
import org.renci.canvas.dao.var.model.VariantSetLoad;
import org.renci.canvas.dao.var.model.VariantSetLoad_;
import org.renci.canvas.dao.var.model.VariantSet_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional
@OsgiServiceProvider(classes = { VariantSetLoadDAO.class })
@javax.transaction.Transactional
@Singleton
public class VariantSetLoadDAOImpl extends BaseDAOImpl<VariantSetLoad, Integer> implements VariantSetLoadDAO {

    private static final Logger logger = LoggerFactory.getLogger(VariantSetLoadDAOImpl.class);

    public VariantSetLoadDAOImpl() {
        super();
    }

    @Override
    public Class<VariantSetLoad> getPersistentClass() {
        return VariantSetLoad.class;
    }

    @Override
    public List<VariantSetLoad> findByExample(VariantSetLoad variantSetLoad) throws CANVASDAOException {
        logger.debug("ENTERING findByExample(VariantSetLoad)");
        List<VariantSetLoad> ret = new ArrayList<>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<VariantSetLoad> crit = critBuilder.createQuery(getPersistentClass());
            Root<VariantSetLoad> root = crit.from(getPersistentClass());

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotEmpty(variantSetLoad.getLoadFilename())) {
                predicates.add(critBuilder.equal(root.get(VariantSetLoad_.loadFilename), variantSetLoad.getLoadFilename()));
            }
            if (StringUtils.isNotEmpty(variantSetLoad.getLoadProgramName())) {
                predicates.add(critBuilder.equal(root.get(VariantSetLoad_.loadProgramName), variantSetLoad.getLoadProgramName()));
            }
            if (StringUtils.isNotEmpty(variantSetLoad.getLoadProgramVersion())) {
                predicates.add(critBuilder.equal(root.get(VariantSetLoad_.loadProgramVersion), variantSetLoad.getLoadProgramVersion()));
            }

            if (variantSetLoad.getVariantSet() != null) {
                Join<VariantSetLoad, VariantSet> variantSetLoadVariantSetJoin = root.join(VariantSetLoad_.variantSet);
                predicates.add(critBuilder.equal(variantSetLoadVariantSetJoin.get(VariantSet_.id), variantSetLoad.getVariantSet().getId()));
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<VariantSetLoad> query = getEntityManager().createQuery(crit).setHint("javax.persistence.fetchgraph",
                    getEntityManager().getEntityGraph("var.VariantSetLoad.includeManyToOnes"));
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new CANVASDAOException(e);
        }
        return ret;
    }

}
