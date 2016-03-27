package com.softserve.webtester.service;

import com.softserve.webtester.dto.ResultCollectionFilterDTO;
import com.softserve.webtester.dto.ResultFilterDTO;
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

    @Autowired
    private ServiceMapper serviceMapper;

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

    public List<ResultHistory> loadAll(ResultFilterDTO resultFilterDTO) {

        String status = resultFilterDTO.getStatusFilter();
        int[] applications = resultFilterDTO.getApplicationFilter();
        int[] services = resultFilterDTO.getServiceFilter();

        try {
            return resultHistoryMapper.loadAll(status, applications, services);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load request instances", e);
            throw e;
        }
    }

    public String loadBuildVersionName(int id) {

        try {
            return resultHistoryMapper.loadBuildVersionName(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load request instances", e);
            throw e;
        }
    }

    // Loading resultHistory instance from ResultHistory table
    public List<ResultHistory> loadAllCollections(ResultCollectionFilterDTO resultCollectionFilterDTO) {

        String status = resultCollectionFilterDTO.getStatusFilter();
        int[] buildVersions = resultCollectionFilterDTO.getBuildVersionsFilter();
        int[] labelFilter = resultCollectionFilterDTO.getLabelFilter();
        try {
            return resultHistoryMapper.loadAllCollections(status, labelFilter, buildVersions);
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