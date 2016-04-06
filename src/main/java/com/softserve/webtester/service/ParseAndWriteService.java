package com.softserve.webtester.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.dto.ResultsDTO;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.model.DbValidation;
import com.softserve.webtester.model.DbValidationHistory;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.EnvironmentHistory;
import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.HeaderHistory;
import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;
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

    private static String VELOCITY_LOG = "";

    public int parseAndWrite(ResultsDTO resultsDTO) {

        int runId = resultsDTO.getRunId();
        Environment environment = resultsDTO.getEnvironment();
        int buildVersionId = resultsDTO.getBuildVersionId();
        List<CollectionResultDTO> collectionResultDTOList = resultsDTO.getCollectionResultDTOList();

        ResultHistory resultHistory = new ResultHistory();
        EnvironmentHistory environmentHistory = new EnvironmentHistory();
        HeaderHistory headerHistory = new HeaderHistory();
        DbValidationHistory dbValidationHistory = new DbValidationHistory();
        RequestCollection requestCollection = new RequestCollection();

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

                    // RESULT_HISTORY

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
                        if ((requestDTO.getHttpRequest().getMethod().equals("GET"))
                                ^ (requestDTO.getHttpRequest().getMethod().equals("DELETE"))) {
                            resultHistory.setRequestBody(null);
                        } else
                            resultHistory.setRequestBody(EntityUtils.toString(
                                    ((HttpEntityEnclosingRequestBase) requestDTO.getHttpRequest()).getEntity()));

                        resultHistory.setExpectedResponse(requestExecuteSupportService.getEvaluatedString(
                                request.getExpectedResponse(), requestDTO.getVariableList(), VELOCITY_LOG));
                        resultHistory.setActualResponse(EntityUtils.toString(responseDTO.getResponse().getEntity()));
                        resultHistory.setActualResponse(
                                requestExecuteSupportService.format(resultHistory.getActualResponse()));

                    } catch (ParseException | IOException e1) {
                        LOGGER.info(e1);
                    }
                    resultHistory.setRunId(runId);

                    if (collectionList.getCollectionId() != 0) {
                        int collId = collectionList.getCollectionId();
                        RequestCollection reqColl = requestCollectionService.load(collId);
                        resultHistory.setRequestCollection(reqColl);
                    }

                    if (resultsDTO.getBuildVersionId() != 0) {
                        int bulverId = resultsDTO.getBuildVersionId();
                        BuildVersion bv = metaDataService.loadBuildVersionById(bulverId);
                        resultHistory.setBuildVersion(bv);
                    }

                    resultHistory.setMessage(messages(resultHistory, request, requestDTO, dbValidationHistory));
                    resultHistory.setStatus(requestStatus(responseDTO, resultHistory, request, requestDTO));
                    resultHistoryService.save(resultHistory);

                    // DB-VALIDATION

                    if (CollectionUtils.isNotEmpty(request.getDbValidations())) {
                        List<DbValidation> dbValidations = (request.getDbValidations());
                        for (DbValidation dbValidation : dbValidations) {
                            dbValidationHistory.setSqlQuery(dbValidation.getSqlQuery());
                            dbValidationHistory.setExpectedValue(dbValidation.getExpectedValue());
                            try {
                                dbValidationHistory.setActualValue(requestExecuteSupportService.getExecutedQueryValue(
                                        environmentService.getConnection(environment), dbValidation.getSqlQuery()));
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            LOGGER.info("DB_VALIDATION   " + dbValidationHistory);
                            dbValidationHistory.setResultHistory(resultHistory);
                        }
                        resultHistoryService.saveDbValidationHistory(dbValidationHistory);
                    }

                    // ENVIRONMENT_HISTORY

                    environmentHistory.setResultHistory(resultHistory);
                    environmentHistory.setBaseURL(environment.getBaseUrl());
                    environmentHistory.setDbName(environment.getDbName());
                    environmentHistory.setDbPort(environment.getDbPort());
                    environmentHistory.setDbURL(environment.getDbUrl());
                    environmentHistory.setName(environment.getName());
                    environmentHistory.setEnvironment(environment);
                    LOGGER.info("ENVIRONMENT " + environmentHistory);
                    resultHistoryService.saveEnvironmentHistory(environmentHistory);

                    // HEADER_HISTORY

                    if (request.getHeaders() != null) {
                        List<Header> headers = request.getHeaders();
                        for (Header header : headers) {
                            headerHistory.setName(header.getName());
                            headerHistory.setValue(header.getValue());
                            headerHistory.setResultHistory(resultHistory);
                            LOGGER.info("HEADER_HISTORY " + headerHistory);
                            resultHistoryService.saveHeaderHistory(headerHistory);
                        }
                    }

                    // LABELS

                    List<Label> labels = (request.getLabels());
                    resultHistory.setLabels(labels);
                    LOGGER.info("LABELS " + resultHistory.getLabels());
                    if (resultHistory.getLabels() != null) {
                        resultHistoryService.saveResultHistoryComponents(resultHistory);
                    }
                }
            }

        }

        return runId;
    }

    public String messages(ResultHistory resultHistory, Request request, RequestDTO requestDTO,
            DbValidationHistory dbValidationHistory) {
        String message = null;
        if ((!requestDTO.getHttpRequest().getMethod().equals("GET"))
                && (!requestDTO.getHttpRequest().getMethod().equals("POST"))) {
            message = "Unsupported request method";
        }
        if (!resultHistory.getResponseType().equalsIgnoreCase(request.getResponseType().getTextValue())) {
            LOGGER.info("TYPE " + request.getResponseType().getTextValue());
            message = "Wrong content type";
            
        }
        if (!resultHistory.getExpectedResponse().equalsIgnoreCase(resultHistory.getActualResponse())) {
            message = "Actual response does not match the expected one";
        }
        if (resultHistory.getResponseTime() > resultHistory.getExpectedResponseTime()) {
            message = "Response time exceeded timeout value";
        }
        /*
         * if
         * (dbValidationHistory.getExpectedValue().equals(dbValidationHistory.
         * getActualValue())) { message =
         * "At least one db validation expected value is not equal to the actual one"
         * ; }
         */
        if (message == null) {
            message = "OK";
        }
        return message;
    }

    public boolean requestStatus(ResponseDTO responseDTO, ResultHistory resultHistory, Request request,
            RequestDTO requestDTO) {

        boolean status = false;

        if ((responseDTO.getResponse().getStatusLine().getStatusCode() == 200)
                & (resultHistory.getMessage().equalsIgnoreCase("OK"))) {
            status = true;
        }
        return status;
    }
}