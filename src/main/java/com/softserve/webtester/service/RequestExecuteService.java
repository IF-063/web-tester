package com.softserve.webtester.service;

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

    public List<RequestResultDTO> executeRequests(Environment environment, List<Request> requestList, boolean ifBuildVerExist,
                                             int collectionId) {
        List<RequestResultDTO> requestResultDTOList = new ArrayList<RequestResultDTO>();

        Connection dbCon;

        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            String host = environment.getBaseUrl();

            List<ResponseDTO> responseDTOList = new ArrayList<>();

            for (Request request : requestList) {
                RequestResultDTO requestResultDTO = new RequestResultDTO();
                RequestDTO requestDTO = buildHttpRequestService.getHttpRequest(request, host, dbCon);
                HttpRequestBase requestBase = requestDTO.getHttpRequest();
                ResponseDTO responseDTO = null;

                if (ifBuildVerExist) {
                    long time = 0;
                    int count = 0;
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
                requestResultDTO.setCollectionId(collectionId);
                requestResultDTO.setResponses(responseDTOList);
                requestResultDTOList.add(requestResultDTO);
            }

            return requestResultDTOList;

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