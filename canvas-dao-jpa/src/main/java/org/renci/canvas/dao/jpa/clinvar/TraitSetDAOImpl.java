package org.renci.canvas.dao.jpa.clinvar;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinvar.TraitSetDAO;
import org.renci.canvas.dao.clinvar.model.TraitSet;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { TraitSetDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TraitSetDAOImpl extends BaseDAOImpl<TraitSet, Integer> implements TraitSetDAO {

    private static final Logger logger = LoggerFactory.getLogger(TraitSetDAOImpl.class);

    public TraitSetDAOImpl() {
        super();
    }

    @Override
    public Class<TraitSet> getPersistentClass() {
        return TraitSet.class;
    }

}