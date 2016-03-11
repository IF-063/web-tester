package com.softserve.webtester.service;

import com.softserve.webtester.mapper.*;
import com.softserve.webtester.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public ResultHistory loadById(int id) {

        try {
            System.out.println("BEFORE invoking loadById() in Service");
            ResultHistory result=resultHistoryMapper.loadById(id);
            System.out.println("AFTER invoking loadById() in Service");
            return result;

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

    // Loading resultHistory instance from ResultHistory table
    public List<ResultHistory> loadAllCollections() {

        try {
            return resultHistoryMapper.loadAllCollections();
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

    // Deleting all resultHistory instances from ResultHistory table
    public int deleteAll() {

        try {
            return resultHistoryMapper.deteleAll();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete all resultHistory instances");
            throw e;
        }
    }

    // Saving DbValidationHistories, headerHistories and labels for the resultHistory instance to the database
    private void saveResultHistoryComponents(ResultHistory resultHistory) {

        List<DbValidationHistory> dbValidationHistories = resultHistory.getDbValidationHistories();
        if (CollectionUtils.isNotEmpty(dbValidationHistories)) {
            dbValidationHistoryMapper.saveByResultHistory(resultHistory);
        }

        List<HeaderHistory> headerHistories = resultHistory.getHeaderHistories();
        if (CollectionUtils.isNotEmpty(headerHistories)) {
            headerHistoryMapper.saveByResultHistory(resultHistory);
        }

        List<Label> labels = resultHistory.getLabels();
        if (CollectionUtils.isNotEmpty(labels)) {
            labelMapper.saveByResultHistory(resultHistory);
        }
    }
}