package com.softserve.webtester.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.PreparedRequestDTO;
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
import com.softserve.webtester.model.RequestMethod;
import com.softserve.webtester.model.ResultHistory;
import com.softserve.webtester.model.Variable;

// TODO RZ: JavaDoc
@Service
public class ParseAndWriteService {

    private static final Logger LOGGER = Logger.getLogger(ParseAndWriteService.class);

    @Autowired
    private RequestExecuteSupportService requestExecuteSupportService;

    @Autowired
    private ResultHistoryService resultHistoryService;

    @Autowired
    private RequestCollectionService requestCollectionService;

    @Autowired
    private MetaDataService metaDataService;

    private static String VELOCITY_LOG = ""; // TODO RZ: fill Sth

    public int parseAndWrite(ResultsDTO resultsDTO) {
        LOGGER.info("!!RESULTS!! " + resultsDTO);

        int runId = resultsDTO.getRunId();

        List<CollectionResultDTO> collectionResultDTOList = resultsDTO.getCollectionResultDTOList();
        ResultHistory resultHistory = new ResultHistory();
        EnvironmentHistory environmentHistory = new EnvironmentHistory();
        HeaderHistory headerHistory = new HeaderHistory();
        DbValidationHistory dbValidationHistory = new DbValidationHistory();

        for (CollectionResultDTO collectionList : collectionResultDTOList) {
            int collectionId = collectionList.getCollectionId();
            List<RequestResultDTO> requestResultDTOList = collectionList.getRequestResultDTOList();

            for (RequestResultDTO requestResult : requestResultDTOList) {
                Request request = requestResult.getRequest();
                PreparedRequestDTO preparedRequestDTO = requestResult.getPreparedRequestDTO();
                List<ResponseDTO> responseDTOList = requestResult.getResponses();

                savingResultHistory(request, resultHistory, preparedRequestDTO, resultsDTO, responseDTOList,
                        collectionId, dbValidationHistory);
                savingEnvironmentHistory(environmentHistory, resultHistory, resultsDTO);
                savingHeaderHistory(request, headerHistory, resultHistory);
                savingLabelHistory(request, resultHistory);
            }
        }
        return runId;
    }

    public void savingResultHistory(Request request, ResultHistory resultHistory, PreparedRequestDTO preparedRequestDTO,
            ResultsDTO resultsDTO, List<ResponseDTO> responseDTOList, int collectionId,
            DbValidationHistory dbValidationHistory) {

        Environment environment = resultsDTO.getEnvironment();
        final int SUCCESS_CODE = 200;
        boolean statusIndicator = false;
        int buildVersionId = resultsDTO.getBuildVersionId();

        if (buildVersionId != 0) {
            int responsesCount = 0;
            int timeResponsesSum = 0;
            boolean bodyCheck = true;
            for (ResponseDTO responseDTOListElement : responseDTOList) {

                // check if request run was successful
                int statusCode = responseDTOListElement.getStatusCode();
                boolean checkStatusCode = (statusCode >= 200) && (statusCode < 400);

                // check if response body equals to expected one
                String responseBody = requestExecuteSupportService.format(responseDTOListElement.getResponseBody());
                boolean checkResponseBodyInstance = responseBody.equals(requestExecuteSupportService
                        .format(request.getExpectedResponse()));

                if (checkStatusCode) {
                    timeResponsesSum += responseDTOListElement.getResponseTime();
                    responsesCount++;
                    if (!checkResponseBodyInstance) {
                        bodyCheck = false;
                    }
                }


                resultHistory.setStatusLine(responseDTOListElement.getStatusLine());
                resultHistory.setActualResponse(responseBody);

            }

            // calculating average response time for request with buildVersion run
            if (responsesCount != 0) {
                resultHistory.setResponseTime(timeResponsesSum / responsesCount);
            } else {
                resultHistory.setResponseTime(0);
            }


            statusIndicator = ((responsesCount == 5) && (bodyCheck)); // TODO RZ: move to constants use from property file,
                                                          // which Anton should extract
        } else {
            ResponseDTO responseDTO = responseDTOList.get(0);
            resultHistory.setResponseTime(responseDTO.getResponseTime());
            resultHistory.setStatusLine(responseDTO.getStatusLine());
            resultHistory.setActualResponse(requestExecuteSupportService.format(responseDTO.getResponseBody()));
            statusIndicator = (responseDTO.getStatusCode() == SUCCESS_CODE);
        }

        resultHistory.setApplication(request.getApplication());
        resultHistory.setService(request.getService());
        resultHistory.setRequest(request);
        resultHistory.setRequestName(request.getName());
        resultHistory.setRequestDescription(request.getDescription());
        resultHistory.setUrl(preparedRequestDTO.getHttpRequest().getURI().toString());
        resultHistory.setResponseType(request.getResponseType().getTextValue());
        resultHistory.setTimeStart(new Timestamp(System.currentTimeMillis()));
        resultHistory.setExpectedResponseTime(request.getTimeout());
        LOGGER.info("!!RESULT_HISTORY_URL!! " + resultHistory.getUrl());
        if ((preparedRequestDTO.getHttpRequest().getMethod().equals("GET"))
                ^ (preparedRequestDTO.getHttpRequest().getMethod().equals("DELETE"))) {
            resultHistory.setRequestBody(null);
            LOGGER.info("!!RESULT_HISTORY!! " + resultHistory.getExpectedResponse());
        } else {
            resultHistory.setRequestBody(preparedRequestDTO.getPreparedRequestBody());
        }

        try {
            if (request.getExpectedResponse().contains("STUB")) {
                request.setExpectedResponse(request.getExpectedResponse().replace("STUB_VALUE", "TEST_OK"));
            }
            if (CollectionUtils.isNotEmpty(preparedRequestDTO.getVariableList())) {
                resultHistory.setExpectedResponse(requestExecuteSupportService.getEvaluatedString(
                        request.getExpectedResponse(), preparedRequestDTO.getVariableList(), VELOCITY_LOG));
            } else
                resultHistory.setExpectedResponse(request.getExpectedResponse());

            LOGGER.info("!!RESULT_RESPONSE!! " + resultHistory.getExpectedResponse());
        } catch (ParseException e1) {
            LOGGER.info(e1);
        }

        resultHistory.setRunId(resultsDTO.getRunId());
        LOGGER.info("!!RESULT_HISTORY_RunId!! " + resultHistory.getRunId());
        if (collectionId != 0) {
            RequestCollection reqColl = requestCollectionService.load(collectionId);
            resultHistory.setRequestCollection(reqColl);
        }
        if (buildVersionId != 0) {
            BuildVersion bVersion = metaDataService.loadBuildVersionById(buildVersionId);
            resultHistory.setBuildVersion(bVersion);
        }

        StringBuilder validationMessages = getValidationMessages(resultHistory, request, preparedRequestDTO,
                dbValidationHistory);
        resultHistory.setStatus(statusIndicator && isValidResponse(validationMessages));
        resultHistory.setMessage(validationMessages.toString());
        resultHistoryService.save(resultHistory);
        LOGGER.info("!!RESULT_HISTORY!! " + resultHistory);
        savingDbValidation(request, environment, resultHistory, dbValidationHistory, preparedRequestDTO);
    }

    public void savingEnvironmentHistory(EnvironmentHistory environmentHistory, ResultHistory resultHistory,
            ResultsDTO resultsDTO) {

        Environment environment = resultsDTO.getEnvironment();

        environmentHistory.setResultHistory(resultHistory);
        environmentHistory.setBaseURL(environment.getBaseUrl());
        environmentHistory.setDbName(environment.getDbName());
        environmentHistory.setDbPort(environment.getDbPort());
        environmentHistory.setDbURL(environment.getDbUrl());
        environmentHistory.setName(environment.getName());
        environmentHistory.setEnvironment(environment);
        LOGGER.info("ENVIRONMENT " + environmentHistory);
        resultHistoryService.saveEnvironmentHistory(environmentHistory);

    }

    public void savingHeaderHistory(Request request, HeaderHistory headerHistory, ResultHistory resultHistory) {

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
    }

    public void savingLabelHistory(Request request, ResultHistory resultHistory) {

        List<Label> labels = (request.getLabels());
        resultHistory.setLabels(labels);
        LOGGER.info("LABELS " + resultHistory.getLabels());
        if (resultHistory.getLabels() != null) {
            resultHistoryService.saveResultHistoryComponents(resultHistory);
        }
    }

    public void savingDbValidation(Request request, Environment environment, ResultHistory resultHistory,
            DbValidationHistory dbValidationHistory, PreparedRequestDTO preparedRequestDTO) {

        if (CollectionUtils.isNotEmpty(request.getDbValidations())) {
            List<DbValidation> dbValidations = (request.getDbValidations());
            for (DbValidation dbValidation : dbValidations) {
                String dbValidationSQLQuery = dbValidation.getSqlQuery();
                List<Variable> varibleList = preparedRequestDTO.getVariableList();
                if (CollectionUtils.isNotEmpty(preparedRequestDTO.getVariableList())) {
                    dbValidationSQLQuery = requestExecuteSupportService.getEvaluatedString(dbValidationSQLQuery,
                            varibleList, VELOCITY_LOG);
                    LOGGER.info("!!!QUERY!!! " + dbValidationSQLQuery);
                } else {
                    dbValidationSQLQuery = dbValidation.getSqlQuery();
                    LOGGER.info("!!!DB_QUERY!!!   " + dbValidation.getSqlQuery());
                }
                dbValidationHistory.setSqlQuery(dbValidationSQLQuery);
                dbValidationHistory.setExpectedValue(dbValidation.getExpectedValue());
                try {
                    if(dbValidationSQLQuery != null){
                    dbValidationHistory.setActualValue(
                            requestExecuteSupportService.getExecutedQueryValue(environment, dbValidationSQLQuery));
                    LOGGER.info("DB_VALIDATION_ACTUAL   " + dbValidationHistory.getActualValue());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    // TODO RZ: set NULL and message to the validation
                    // messages
                }
                LOGGER.info("DB_VALIDATION   " + dbValidationHistory);
                dbValidationHistory.setResultHistory(resultHistory);
                if (CollectionUtils.isNotEmpty(request.getDbValidations())) {
                    dbValidationHistory.setResultHistory(resultHistory);
                    resultHistoryService.saveDbValidationHistory(dbValidationHistory);
                }
            }
        }

    }

    private boolean isValidResponse(StringBuilder validationMessages) {

        return validationMessages.toString().equals("OK");
    }

    public StringBuilder getValidationMessages(ResultHistory resultHistory, Request request,
            PreparedRequestDTO preparedRequestDTO, DbValidationHistory dbValidationHistory) {

        StringBuilder message = new StringBuilder();
        String type = null;
        if (resultHistory.getExpectedResponse().startsWith("<")) {
            type = "XML";
        }
        if (resultHistory.getExpectedResponse().startsWith(("["))
                || resultHistory.getExpectedResponse().startsWith(("{"))) {
            type = "JSON";
        }

        if (preparedRequestDTO.getHttpRequest().getMethod().equals(RequestMethod.DELETE.name())) {
            message.append("Unsupported request method; ");
        }
        if (!type.equalsIgnoreCase(request.getResponseType().getTextValue())) {
            LOGGER.info("TYPE " + request.getResponseType().getTextValue());
            message.append("Wrong content type; ");
        }
        if (!resultHistory.getExpectedResponse().equalsIgnoreCase(resultHistory.getActualResponse())
                && (RequestMethod.GET.name().equalsIgnoreCase("GET"))) {
            message.append("Actual response does not match the expected one; ");
        }
        if (resultHistory.getResponseTime() > resultHistory.getExpectedResponseTime()) {
            message.append("Response time exceeded timeout value; ");
        }

        /*
         * if
         * (dbValidationHistory.getExpectedValue().equals(dbValidationHistory.
         * getActualValue())) { message =
         * "At least one db validation expected value is not equal to the actual one"
         * ; }
         */

        if (StringUtils.isBlank(message)) {
            message.append("OK");
        }
        return message;
    }
}