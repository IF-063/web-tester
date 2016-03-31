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

    /**
     * saving ResultHistory instance in DB if BuildVersion doesn't equal null, or BuildVersion and requestCollection
     * don't equal null or if BuildVersion and requestCollection instances equal null.
     * @param resultHistory
     * @return
     */
    public int save(ResultHistory resultHistory) {

        try {
            if (!resultHistory.getBuildVersion().equals(null)) {
                resultHistoryMapper.save(resultHistory);
            } else if (!resultHistory.getRequestCollection().equals(null)) {
                resultHistoryMapper.saveCollection(resultHistory);
            } else {
                resultHistoryMapper.saveRequest(resultHistory);
            }
            int id = resultHistory.getId();
            LOGGER.info("ResultHistory saved successfully, resultHistory details = " + resultHistory);
            return id;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save resultHistory instance ", e);
            throw e;
        }
    }

    /**
     * Loading resultHistory instance bt its id from ResultHistory table
     * @param id
     * @return
     */
    public ResultHistory loadById(int id) {

        try {
            ResultHistory result = resultHistoryMapper.loadById(id);
            return result;

        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instance with id: " + id, e);
            throw e;
        }
    }

    /**
     * Loading resultHistory instance from ResultHistory table without using resultFilterDTO filter instance
     * @return
     */
    public List<ResultHistory> loadAll() {

        try {
            return resultHistoryMapper.loadAll(null, null, null);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    /**
     * Loading resultHistory instance from ResultHistory table using resultFilterDTO filter instance
     * @param resultFilterDTO
     * @return
     */
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

    /**
     * Loading BuildVersion instance by its name
     * @param id
     * @return
     */
    public String loadBuildVersionName(int id) {

        try {
            return resultHistoryMapper.loadBuildVersionName(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load request instances", e);
            throw e;
        }
    }

    /**
     * Loading resultHistory instance having collections from ResultHistory table
     * @param resultCollectionFilterDTO
     * @return
     */
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

    /**
     * Loading resultHistory instance referring to the collection with certain id from ResultHistory table
     * @param id
     * @return
     */
    public List<ResultHistory> loadAllRequestsByCollectionId(int id) {

        try {
            return resultHistoryMapper.loadAllRequestsByCollectionId(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    /**
     * Loading requests by runId id from ResultHistory table
     * @param resultFilterDTO
     * @return
     */
    public List<ResultHistory> loadAllRequestsByRunId(ResultFilterDTO resultFilterDTO) {
        int runId = resultFilterDTO.getRunId();

        try {
            return resultHistoryMapper.loadAllRequestsByRunId(runId);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    /**
     * Loading collections by runId id from ResultHistory table
     * @param resultCollectionFilterDTO
     * @return
     */
    public List<ResultHistory> loadAllCollectionsByRunId(ResultCollectionFilterDTO resultCollectionFilterDTO) {
        int runId = resultCollectionFilterDTO.getRunId();

        try {
            return resultHistoryMapper.loadAllCollectionsByRunId(runId);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load resultHistory instances", e);
            throw e;
        }
    }

    /**
     * Deleting resultHistory instance by requestId from ResultHistory table
     * @param id
     * @return
     */
    public int delete(int id) {

        try {
            return resultHistoryMapper.detele(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete resultHistory instance with requestId: " + id, e);
            throw e;
        }
    }

    /**
     * Deleting resultHistory instance by collectionId from ResultHistory table
     * @param id
     * @return
     */
    public int deteleByCollectionId(int id) {

        try {
            return resultHistoryMapper.deteleByCollectionId(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete resultHistory instance with collectionId: " + id, e);
            throw e;
        }
    }

    /**
     * Deleting requests by id array from ResultHistory table
     * @param arr
     * @return
     */
    public int deleteSelectedResults(int[] arr) {
        try {
            return resultHistoryMapper.deleteSelectedResults(arr);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete result instances, results id: " + arr, e);
            throw e;
        }
    }

    /**
     * Deleting collections by id array from ResultHistory table
     * @param arr
     * @return
     */
    public int deleteSelectedCollectionResults(int[] arr) {
        try {
            return resultHistoryMapper.deleteSelectedCollectionResults(arr);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete result instances, results id: " + arr, e);
            throw e;
        }
    }

    /**
     * Deleting max runId from ResultHistory table
     * @return
     */
    public int getMaxId() {
        try {
            return resultHistoryMapper.getMaxId();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to retreive MAXid: " + e);
            throw e;
        }
    }

    /**
     * Saving DbValidationHistories, headerHistories and labels for the resultHistory instance to the database
     * @param resultHistory
     */
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