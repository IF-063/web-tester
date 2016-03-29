package com.softserve.webtester.service;

import com.softserve.webtester.dto.RequestResultDTO;
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


    public int run(int environmentId, int[] requestIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;

        return parseAndWriteService.parseAndWrite(execute(environment, requestIdArray, runId));

    }

    public int run(int environmentId, int buildVersionId, int[] collectionIdArray) {

        Environment environment = environmentService.load(environmentId);

        int runId = resultHistoryService.getMaxId() + 1;
        
        int result = parseAndWriteService.parseAndWrite(execute(environment, buildVersionId, collectionIdArray, runId));

        return parseAndWriteService.parseAndWrite(execute(environment, buildVersionId, collectionIdArray, runId));

    }

    private ResultsDTO execute(Environment environment, int [] requestIdArray, int runId) {

        ResultsDTO resultsDTO = new ResultsDTO();

        resultsDTO.setEnvironment(environment);
        resultsDTO.setBuildVersionId(0);
        resultsDTO.setRunId(runId);

        List<RequestResultDTO> requestResultDTOList = new ArrayList<>();
        requestResultDTOList.addAll(requestExecuteService.executeRequests(environment,
                requestService.loadArray(requestIdArray), false, 0));
        resultsDTO.setRequestResults(requestResultDTOList);

        return resultsDTO;
    }

    private ResultsDTO execute(Environment environment, int buildVersionId, int [] collectionIdArray, int runId) {

        ResultsDTO resultsDTO = new ResultsDTO();

        resultsDTO.setEnvironment(environment);
        resultsDTO.setBuildVersionId(buildVersionId);
        resultsDTO.setRunId(runId);

        List<RequestResultDTO> requestResultDTOList = new ArrayList<>();
        for (int i = 0; i < collectionIdArray.length; i++) {
            requestResultDTOList.addAll(requestExecuteService.executeRequests(environment,
                    requestService.loadFullRequestsByRequestCollectionId(collectionIdArray[i]),
                    (buildVersionId!=0), collectionIdArray[i]));
        }
        resultsDTO.setRequestResults(requestResultDTOList);

        return resultsDTO;
    }

}