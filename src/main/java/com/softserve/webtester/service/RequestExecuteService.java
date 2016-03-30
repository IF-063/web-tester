package com.softserve.webtester.service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestExecuteService {

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private BuildHttpRequestService buildHttpRequestService;

    public CollectionResultDTO executeRequests(Environment environment, List<Request> requestList,
                                               boolean ifBuildVerExist, int collectionId) {

        CollectionResultDTO collectionResultDTO = new CollectionResultDTO();
        List<RequestResultDTO> requestResultDTOList = new ArrayList<RequestResultDTO>();

        Connection dbCon;

        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            String host = environment.getBaseUrl();

            for (Request request : requestList) {
                RequestResultDTO requestResultDTO = new RequestResultDTO();
                RequestDTO requestDTO = buildHttpRequestService.getHttpRequest(request, host, dbCon);
                HttpRequestBase requestBase = requestDTO.getHttpRequest();
                List<ResponseDTO> responseDTOList = new ArrayList<>();
                ResponseDTO responseDTO = null;

                if (ifBuildVerExist) {
                    for (int i = 0; i < 5; i++) {
                        responseDTO = executeOneRequest(httpClient, requestBase);
                        responseDTOList.add(responseDTO);
                    }
                } else {
                    responseDTO = executeOneRequest(httpClient, requestBase);
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ResponseDTO executeOneRequest(CloseableHttpClient httpClient, HttpRequestBase requestBase) {
        ResponseDTO responseDTO = new ResponseDTO();
        long start = System.currentTimeMillis();
        try (CloseableHttpResponse response = httpClient.execute(requestBase)) {
            long responseTime = System.currentTimeMillis() - start;
            responseDTO.setResponseTime(responseTime);
            responseDTO.setResponse(response);
            return responseDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}