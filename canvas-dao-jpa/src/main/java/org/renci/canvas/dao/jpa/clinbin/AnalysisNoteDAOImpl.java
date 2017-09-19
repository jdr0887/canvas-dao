package org.renci.canvas.dao.jpa.clinbin;

import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.canvas.dao.clinbin.AnalysisNoteDAO;
import org.renci.canvas.dao.clinbin.model.AnalysisNote;
import org.renci.canvas.dao.jpa.BaseDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@OsgiServiceProvider(classes = { AnalysisNoteDAO.class })
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@javax.transaction.Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
@Singleton
public class AnalysisNoteDAOImpl extends BaseDAOImpl<AnalysisNote, Integer> implements AnalysisNoteDAO {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisNoteDAOImpl.class);

    public AnalysisNoteDAOImpl() {
        super();
    }

    @Override
    public Class<AnalysisNote> getPersistentClass() {
        return AnalysisNote.class;
    }

}
