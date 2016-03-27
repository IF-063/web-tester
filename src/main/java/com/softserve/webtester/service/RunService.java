package com.softserve.webtester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunService {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private ExecutorService executorService;
    
    @Autowired
    private ParseAndWriteIntoDBService parseAndWriteIntoDBService;

    public int run(int environmentId, int[] requestIdArray) {

        int runId = resultHistoryService.getMaxId() + 1;

        return parseAndWriteIntoDBService.parseAndWrite(executorService.execute(environmentId, requestIdArray));

    }

    public int run(int environmentId, int buildVersionId, int[] collectionIdArray) {

        int runId = resultHistoryService.getMaxId() + 1;

        return parseAndWriteIntoDBService.parseAndWrite(executorService.execute(environmentId,
                buildVersionId, collectionIdArray));

    }

}
