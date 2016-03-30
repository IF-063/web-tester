package com.softserve.webtester.service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.ResultsDTO;
import com.softserve.webtester.model.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * method which responsible for running request or requests list
     *
     * @param environmentId for getting environment data from DB
     * @param requestIdArray list of request id to get request objects from DB
     * @return run id to show results
     */
    public int run(int environmentId, int[] requestIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;

        return parseAndWriteService.parseAndWrite(execute(environment, requestIdArray, runId));

    }

    /**
     * method which responsible for running collections of requests
     *
     * @param environmentId for getting environment data from DB
     * @param buildVersionId tell us to run request contained in collection for 5 times
     * @param collectionIdArray array of collection id, for getting list of request objects from DB
     * @return run id to show results
     */
    public int run(int environmentId, int buildVersionId, int[] collectionIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;

        return parseAndWriteService.parseAndWrite(execute(environment, buildVersionId, collectionIdArray, runId));

    }

    private ResultsDTO execute(Environment environment, int [] requestIdArray, int runId) {

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

    private ResultsDTO execute(Environment environment, int buildVersionId, int [] collectionIdArray, int runId) {

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