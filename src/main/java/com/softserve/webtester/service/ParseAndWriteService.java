package com.softserve.webtester.service;

import java.io.IOException;
import java.util.List;

import com.softserve.webtester.dto.*;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import org.apache.http.HttpResponse;

@Service
public class ParseAndWriteService {

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();

        List<CollectionResultDTO> collectionResultDTOList = resultsDTO.getCollectionResultDTOList();

        for (CollectionResultDTO collectionList : collectionResultDTOList) {

            int collectionId = collectionList.getCollectionId();
            List<RequestResultDTO> requestResultDTOList = collectionList.getRequestResultDTOList();

            for (RequestResultDTO requestResult : requestResultDTOList) {
                Request request = requestResult.getRequest();
                RequestDTO requestDTO = requestResult.getRequestDTO();
                List<ResponseDTO> responseDTOList = requestResult.getResponses();
                for (ResponseDTO responseDTO : responseDTOList) {
                    long responseTime = responseDTO.getResponseTime();
                    HttpResponse response = responseDTO.getResponse();
                    System.out.println("TIME   " + responseTime);
                    System.out.println("RESPONSE" + response.toString());
                    try {
                        System.out.println(EntityUtils.toString(response.getEntity()));
                    } catch (ParseException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    // do what you need with all data as request, response, responseTime, collectionId,
                    // buildVersionId, environment, runId
                }
            }

        }

        return runId;
    }
}