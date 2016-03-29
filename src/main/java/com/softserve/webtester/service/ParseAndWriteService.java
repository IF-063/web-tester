package com.softserve.webtester.service;

import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.dto.ResultsDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import org.apache.http.HttpResponse;

import java.util.List;

public class ParseAndWriteService {

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();

        List<RequestResultDTO> requestResultDTOList = resultsDTO.getRequestResults();

        for (RequestResultDTO list : requestResultDTOList) {

            int collectionId = list.getCollectionId();
            List<Request> requestList = list.getRequests();
            List<ResponseDTO> responseDTOList = list.getResponses();

            for (int i = 0; i < requestList.size(); i++) {
                Request request = requestList.get(i);
                ResponseDTO responseDTO = responseDTOList.get(i);
                List<HttpResponse> response = responseDTO.getResponse();
                List<Long> responseTime = responseDTO.getResponseTime();

                // do what you need with all data as request, response, responseTime, collectionId,
                // buildVersionId, environment, runId
            }
        }

        return runId;
    }
}