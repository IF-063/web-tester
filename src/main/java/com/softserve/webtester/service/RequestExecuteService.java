package com.softserve.webtester.service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestExecuteService {

    private static final int SAMPLE_FOR_BUILD_VERSION = 5;
    private static final Logger LOGGER = Logger.getLogger(RequestExecuteService.class);

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private BuildHttpRequestService buildHttpRequestService;

    /**
     * this method responsible for running request or request list
     *
     * @param environment from here we get host name and DB connection for running request
     * @param requestList request list fot running
     * @param ifBuildVerExist trigger which tell us run collection with buildversion or not
     * @param collectionId for getting list of requests from DB
     * @return CollectionResultDTO, object which consists of collection id, list of RequestResultDTO
     */
    public CollectionResultDTO executeRequests(Environment environment, List<Request> requestList,
                                               boolean ifBuildVerExist, int collectionId) {

        CollectionResultDTO collectionResultDTO = new CollectionResultDTO();
        List<RequestResultDTO> requestResultDTOList = new ArrayList<>();

        Connection dbCon;

        try {
            dbCon = environmentService.getConnection(environment);

            String host = environment.getBaseUrl();

            for (Request request : requestList) {
                RequestResultDTO requestResultDTO = new RequestResultDTO();
                RequestDTO requestDTO = buildHttpRequestService.getHttpRequest(request, host, dbCon);
                HttpRequestBase requestBase = requestDTO.getHttpRequest();
                List<ResponseDTO> responseDTOList = new ArrayList<>();
                ResponseDTO responseDTO;

                if (ifBuildVerExist) {
                    for (int i = 0; i < SAMPLE_FOR_BUILD_VERSION; i++) {
                        responseDTO = executeOneRequest(requestBase);
                        responseDTOList.add(responseDTO);
                    }
                } else {
                    responseDTO = executeOneRequest(requestBase);
                    responseDTOList.add(responseDTO);
                }
                requestResultDTO.setRequest(request);
                requestResultDTO.setRequestDTO(requestDTO);
                requestResultDTO.setResponses(responseDTOList);
                requestResultDTOList.add(requestResultDTO);
            }

            collectionResultDTO.setCollectionId(collectionId);
            collectionResultDTO.setRequestResultDTOList(requestResultDTOList);
            return collectionResultDTO;

        } catch (URISyntaxException e) {
            LOGGER.error("URISyntaxException - " + e.getMessage(), e);
        } catch (SQLException e) {
            LOGGER.error("SQLException - " + e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error("IOException - " + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("Exception - " + e.getMessage(), e);
        }

        return null;
    }

    /**
     * method runs one request
     * @param requestBase interface for different types of request
     * @return ResponseDTO, object, which consists of response time and response
     */
    private ResponseDTO executeOneRequest(HttpRequestBase requestBase) {
        ResponseDTO responseDTO = new ResponseDTO();
        long start = System.currentTimeMillis();
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpResponse response = httpClient.execute(requestBase);
            long responseTime = System.currentTimeMillis() - start;
            responseDTO.setResponseTime(responseTime);
            responseDTO.setResponse(response);
            return responseDTO;
        } catch (IOException e) {
            LOGGER.error("IOException - " + e.getMessage(), e);
        }
        return null;
    }

}