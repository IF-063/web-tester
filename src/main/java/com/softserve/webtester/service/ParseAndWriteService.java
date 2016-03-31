package com.softserve.webtester.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.softserve.webtester.dto.*;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private RequestExecuteSupportService requestExecuteSupportService;
    
    @Autowired
    private ResultHistoryService resultHistoryService;
    
    @Autowired
    private RequestCollectionService requestCollectionService;
    
    @Autowired
    private RequestExecuteService requestExecuteService;
    
    @Autowired
    private EnvironmentService environmentService;
    
    @Autowired
    private MetaDataService metaDataService;
    
    private static final Logger LOGGER = Logger.getLogger(ParseAndWriteService.class);
    
    private static String VELOCITY_LOG=""; 

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();
        List<CollectionResultDTO> collectionResultDTOList = resultsDTO.getCollectionResultDTOList();
        ResultHistory resultHistory = new ResultHistory();
        EnvironmentHistory environmentHistory = new EnvironmentHistory();
        HeaderHistory headerHistory = new HeaderHistory();
        DbValidationHistory dbValidationHistory = new DbValidationHistory();
        Header headers = new Header();
        DbValidation dbValidation = new DbValidation();
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
                    LOGGER.info("TIME   " + responseTime);
                    LOGGER.info("RESPONSE   " + response.toString());
                    resultHistory.setStatus("1");
                    resultHistory.setApplication(request.getApplication());
                    resultHistory.setService(request.getService());
                    resultHistory.setRequest(request);
                    resultHistory.setRequestName(request.getName());
                    resultHistory.setRequestDescription(request.getDescription());
                    resultHistory.setUrl(requestDTO.getHttpRequest().getURI().toString());
                    resultHistory.setResponseType(request.getResponseType().toString());
                    resultHistory.setStatusLine(responseDTO.getResponse().getStatusLine().toString());
                    resultHistory.setTimeStart(new Timestamp(System.currentTimeMillis()));
                    resultHistory.setExpectedResponseTime(request.getTimeout());
                    resultHistory.setResponseTime((int) responseDTO.getResponseTime());
                    
                    try {
                        resultHistory.setRequestBody(EntityUtils
                                .toString(((HttpEntityEnclosingRequestBase) requestDTO.getHttpRequest()).getEntity()));
                        resultHistory.setExpectedResponse(requestExecuteSupportService.getEvaluatedString(
                                request.getExpectedResponse(), requestDTO.getVariableList(), VELOCITY_LOG));
                        resultHistory.setActualResponse(EntityUtils.toString(responseDTO.getResponse().getEntity()));
                    } catch (ParseException | IOException e1) {
                        LOGGER.info(e1);
                    }
                    resultHistory.setMessage(responseDTO.getResponse().getStatusLine().getReasonPhrase().toString());
                    resultHistory.setRunId(runId);
                    if (collectionList.getCollectionId() != 0)
                        resultHistory
                                .setRequestCollection(requestCollectionService.load(collectionList.getCollectionId()));
                    if (resultsDTO.getBuildVersionId() != 0)
                        resultHistory
                                .setBuildVersion(metaDataService.loadBuildVersionById(resultsDTO.getBuildVersionId()));
                    LOGGER.info(resultHistory);
                    
                    
                    
                    resultHistoryService.save(resultHistory);
                    
                    
                }
            }

        }

        return runId;
    }
}