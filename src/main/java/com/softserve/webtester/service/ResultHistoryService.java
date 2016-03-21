package com.softserve.webtester.service;

import com.softserve.webtester.dto.GraphicData;
import com.softserve.webtester.dto.ResultCollectionFilter;
import com.softserve.webtester.dto.ResultFilter;
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
            ResultHistory result=resultHistoryMapper.loadById(id);
            System.out.print(result);
            return result;

        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instance with id: " + id, e);
            throw e;
        }
    }

    // Loading resultHistory instance from ResultHistory table
    public List<ResultHistory> loadAll() {

        try {
            return resultHistoryMapper.loadAll(null, null, null);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    public List<ResultHistory> loadAll(ResultFilter resultFilter) {

        String status = resultFilter.getStatusFilter();
        int[] applications = resultFilter.getApplicationFilter();
        int[] services = resultFilter.getServiceFilter();
        try {
            return resultHistoryMapper.loadAll(status, applications, services);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load request instances", e);
            throw e;
        }
    }

    public List<ResultHistory> loadGraphicResponseTime(GraphicData graphicData) {

        Integer serviceName = graphicData.getServiceName();
        Integer buildVersionMin = graphicData.getBuildVersionMin();
        Integer BuildVersionMax = graphicData.getBuildVersionMax();

        try {
            //return resultHistoryMapper.loadGraphicResponseTime(serviceName, buildVersionMin, BuildVersionMax);
            return null;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load request instances", e);
            throw e;
        }
    }

    // Loading resultHistory instance from ResultHistory table
    public List<ResultHistory> loadAllCollections(ResultCollectionFilter resultCollectionFilter) {

        String status = resultCollectionFilter.getStatusFilter();
        Integer buildVersion = resultCollectionFilter.getBuildVersionFilter();
        int[] labelFilter = resultCollectionFilter.getLabelFilter();
        try {
            return resultHistoryMapper.loadAllCollections(status, labelFilter, buildVersion);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    public List<ResultHistory> loadAllRequestsByCollectionId(int id) {

        try {
            return resultHistoryMapper.loadAllRequestsByCollectionId(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    // Deleting resultHistory instance ByRequestId from ResultHistory table
    public int delete(int id) {

        try {
            return resultHistoryMapper.detele(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete resultHistory instance with requestId: " + id, e);
            throw e;
        }
    }

    // Deleting resultHistory instance ByCollectionId from ResultHistory table
    public int deteleByCollectionId(int id) {

        try {
            return resultHistoryMapper.deteleByCollectionId(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete resultHistory instance with collectionId: " + id, e);
            throw e;
        }
    }

    public int deleteSelectedResults(int[] arr) {
        try {
            return resultHistoryMapper.deleteSelectedResults(arr);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete result instances, results id: " + arr, e);
            throw e;
        }
    }

    public int deleteSelectedCollectionResults(int[] arr) {
        try {
            return resultHistoryMapper.deleteSelectedCollectionResults(arr);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete result instances, results id: " + arr, e);
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