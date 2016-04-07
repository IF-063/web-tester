package com.softserve.webtester.service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.ResultsDTO;
import com.softserve.webtester.model.Environment;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RunService {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestExecuteService requestExecuteService;

    @Autowired
    private EnvironmentService environmentService;
    
    @Autowired
    private ParseAndWriteService parseAndWriteService;
    
    private static final Logger LOGGER = Logger.getLogger(RunService.class);

    /**
     * method which responsible for running request or requests list of requests and parsing, writing into DB results
     *
     * @param environmentId for getting environment data from DB
     * @param requestIdArray list of request id to get request objects from DB
     * @return run id to show results
     */
    public int run(int environmentId, int[] requestIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;
        LOGGER.info("Generated run id: " + runId);

        return parseAndWriteService.parseAndWrite(execute(environment, requestIdArray, runId));

    }

    /**
     * method which responsible for running collections of requests and parsing, writing into DB results
     *
     * @param environmentId for getting environment data from DB
     * @param buildVersionId tell us to run request contained in collection for 5 times
     * @param collectionIdArray array of collection id, for getting list of request objects from DB
     * @return run id to show results
     */
    public int run(int environmentId, int buildVersionId, int[] collectionIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;
        LOGGER.info("Generated run id: " + runId);

        return parseAndWriteService.parseAndWrite(execute(environment, buildVersionId, collectionIdArray, runId));

    }

    /**
     * execute request or requests list
     *
     * @param environment data for running requests
     * @param requestIdArray array of request id to get request objects from DB
     * @param runId for getting run results from DB
     * @return ResultsDTO instance
     */
    private ResultsDTO execute(Environment environment, int [] requestIdArray, int runId) {
        LOGGER.info("Start requests executing: " + Arrays.toString(requestIdArray));
        ResultsDTO resultsDTO = new ResultsDTO();

        resultsDTO.setEnvironment(environment);
        resultsDTO.setBuildVersionId(0);
        resultsDTO.setRunId(runId);

        List<CollectionResultDTO> collectionResultDTOList = new ArrayList<>();

        CollectionResultDTO collectionResultDTO = requestExecuteService.executeRequests(environment,
                requestService.loadArray(requestIdArray), false, 0);

        collectionResultDTOList.add(collectionResultDTO);

        resultsDTO.setCollectionResultDTOList(collectionResultDTOList);

        return resultsDTO;
    }

    /**
     * execute collection or collections list
     *
     * @param environment data for running requests
     * @param buildVersionId tell us to run request contained in collection for 5 times
     * @param collectionIdArray array of collection id to get requests lists from DB
     * @param runId for getting run results from DB
     * @return ResultsDTO instance
     */
    private ResultsDTO execute(Environment environment, int buildVersionId, int [] collectionIdArray, int runId) {
        LOGGER.info("Start collections executing: " + Arrays.toString(collectionIdArray));
        ResultsDTO resultsDTO = new ResultsDTO();

        resultsDTO.setEnvironment(environment);
        resultsDTO.setBuildVersionId(buildVersionId);
        resultsDTO.setRunId(runId);

        List<CollectionResultDTO> collectionResultDTOList = new ArrayList<>();

        for (int i = 0; i < collectionIdArray.length; i++) {
            CollectionResultDTO collectionResultDTO = requestExecuteService.executeRequests(environment,
                    requestService.loadFullRequestsByRequestCollectionId(collectionIdArray[i]),
                    (buildVersionId!=0), collectionIdArray[i]);
            collectionResultDTOList.add(collectionResultDTO);
        }

        resultsDTO.setCollectionResultDTOList(collectionResultDTOList);

        return resultsDTO;
    }

}