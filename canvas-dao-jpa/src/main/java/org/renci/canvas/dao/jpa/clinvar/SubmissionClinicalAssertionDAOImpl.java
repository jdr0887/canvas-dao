package org.renci.canvas.dao.jpa.clinvar;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinvar.ReferenceClinicalAssertionDAO;
import org.renci.canvas.dao.clinvar.SubmissionClinicalAssertionDAO;
import org.renci.canvas.dao.clinvar.model.SubmissionClinicalAssertion;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@OsgiServiceProvider(classes = { ReferenceClinicalAssertionDAO.class })
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class SubmissionClinicalAssertionDAOImpl extends BaseDAOImpl<SubmissionClinicalAssertion, Long>
        implements SubmissionClinicalAssertionDAO {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionClinicalAssertionDAOImpl.class);

    public SubmissionClinicalAssertionDAOImpl() {
        super();
    }

    @Override
    public Class<SubmissionClinicalAssertion> getPersistentClass() {
        return SubmissionClinicalAssertion.class;
    }

}
