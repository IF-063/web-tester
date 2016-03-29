package com.softserve.webtester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunService {

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private ExecutorService executorService;
    

    public int run(int environmentId, int[] requestIdArray) {
        executorService.execute(environmentId, requestIdArray);

        return 1;

    }



}
