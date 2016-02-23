package com.softserve.webtester.service;

import com.softserve.webtester.mapper.*;
import com.softserve.webtester.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ResultHistoryService {

    private static final Logger LOGGER = Logger.getLogger(RequestService.class);

    @Autowired
    private ResultHistoryMapper resultHistoryMapper;

    @Autowired
    private HeaderHistoryMapper headerHistoryMapper;

    @Autowired
    private DbValidationHistoryMapper dbValidationHistoryMapper;

    @Autowired
    private LabelMapper labelMapper;

    // Saving resultHistory instance in ResultHistory table
    public int save(ResultHistory resultHistory) {

        try {
            resultHistoryMapper.save(resultHistory);
            int id = resultHistory.getId();
            LOGGER.info("ResultHistory saved successfully, resultHistory details = " + resultHistory);
            return id;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save resultHistory instance ", e);
            throw e;
        }
    }

    // Loading resultHistory instance from ResultHistory table
    public ResultHistory load(int id) {
        try {
            return resultHistoryMapper.load(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instance with id: " + id, e);
            throw e;
        }
    }

    // Loading resultHistory instance from ResultHistory table
    public List<ResultHistory> loadAll() {

        try {
            return resultHistoryMapper.loadAll();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    // Deleting resultHistory instance from ResultHistory table
    public int delete(int id) {

        try {
            return resultHistoryMapper.detele(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete resultHistory instance with id: " + id, e);
            throw e;
        }
    }

    // Saving DbValidationHistories, headerHistories and labels for the resultHistory instance to the database
    private void saveResultHistoryComponents(ResultHistory resultHistory) {

        List<DBValidationHistory> dbValidationHistories = resultHistory.getDbValidationHistories();
        if (dbValidationHistories!=null && !dbValidationHistories.isEmpty()) {
            dbValidationHistoryMapper.saveByResultHistory(resultHistory);
        }

        List<HeaderHistory> headerHistories = resultHistory.getHeaderHistories();
        if (headerHistories!=null && !headerHistories.isEmpty()) {
            headerHistoryMapper.saveByResultHistory(resultHistory);
        }

        List<Label> labels = resultHistory.getLabels();
        if (labels!=null && !labels.isEmpty()) {
            labelMapper.saveByResultHistory(resultHistory);
        }
    }
}