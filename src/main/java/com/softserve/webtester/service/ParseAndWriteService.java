package com.softserve.webtester.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.softserve.webtester.dto.*;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.softserve.webtester.model.DbValidation;
import com.softserve.webtester.model.DbValidationHistory;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.EnvironmentHistory;
import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.HeaderHistory;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.ResultHistory;

@Service
public class ParseAndWriteService {

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();

        List<CollectionResultDTO> collectionResultDTOList = resultsDTO.getCollectionResultDTOList();

        ResultHistory resultHistory = new ResultHistory();
        EnvironmentHistory environmentHistory = new EnvironmentHistory();
        HeaderHistory headerHistory = new HeaderHistory();
        DbValidationHistory dbValidationHistory = new DbValidationHistory();
        MetaDataService metaDataService = new MetaDataService();
        Header headers = new Header();
        DbValidation dbValidation = new DbValidation();
        RequestExecuteService requestExecuteService = new RequestExecuteService();
        RequestCollectionService requestCollectionService = new RequestCollectionService();
        ResultHistoryService resultHistoryService = new ResultHistoryService();
        EnvironmentService environmentService = new EnvironmentService();

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
                    System.out.println("RESPONSE   " + response.toString());

                    resultHistory.setApplication(request.getApplication());
                    resultHistory.setService(request.getService());
                    resultHistory.setRequest(request);
                    resultHistory.setRequestName(request.getName());
                    resultHistory.setRequestDescription(request.getDescription());
                    resultHistory.setUrl(requestDTO.getHttpRequest().getURI().toString());
                    resultHistory.setResponseType(request.getResponseType().toString());
                    resultHistory.setRequestBody(request.getRequestBody());
                    resultHistory.setStatusLine(responseDTO.getResponse().getStatusLine().toString());
                    resultHistory.setTimeStart(new Timestamp(System.currentTimeMillis()));
                    resultHistory.setExpectedResponseTime(request.getTimeout());
                    resultHistory.setResponseTime((int) responseDTO.getResponseTime());
                    resultHistory.setExpectedResponse(request.getExpectedResponse());
                    try {
                        resultHistory.setActualResponse(EntityUtils.toString(responseDTO.getResponse().getEntity()));
                    } catch (ParseException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    resultHistory.setMessage(responseDTO.getResponse().getStatusLine().getReasonPhrase().toString());
                    resultHistory.setRunId(runId);
                    if (collectionList.getCollectionId() != 0)
                        resultHistory
                                .setRequestCollection(requestCollectionService.load(collectionList.getCollectionId()));
                    if (resultsDTO.getBuildVersionId() != 0)
                        resultHistory
                                .setBuildVersion(metaDataService.loadBuildVersionById(resultsDTO.getBuildVersionId()));
                    System.out.println(resultHistory);

                }
            }

        }

        return runId;
    }
}