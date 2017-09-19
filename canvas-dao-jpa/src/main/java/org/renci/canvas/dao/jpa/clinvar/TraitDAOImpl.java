package org.renci.canvas.dao.jpa.clinvar;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinvar.TraitDAO;
import org.renci.canvas.dao.clinvar.model.Trait;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { TraitDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class TraitDAOImpl extends BaseDAOImpl<Trait, Integer> implements TraitDAO {

    private static final Logger logger = LoggerFactory.getLogger(TraitDAOImpl.class);

    public TraitDAOImpl() {
        super();
    }

    @Override
    public Class<Trait> getPersistentClass() {
        return Trait.class;
    }

}
