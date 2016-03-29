package com.softserve.webtester.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.dto.ResultsDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;

@Service
public class ParseAndWriteService {

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();

        List<RequestResultDTO> requestResultDTOList = resultsDTO.getRequestResults();

        for (RequestResultDTO list : requestResultDTOList) {

            int collectionId = list.getCollectionId();
            Request requestt = list.getRequest();
            List<ResponseDTO> responseDTOList = list.getResponses();

            for (ResponseDTO responseDTO : responseDTOList) {
                HttpResponse response = responseDTO.getResponse();
                long responseTime = responseDTO.getResponseTime();
                System.out.println("TIME   " + responseTime);
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

        return runId;
    }
}