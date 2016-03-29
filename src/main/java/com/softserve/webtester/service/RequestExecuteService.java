package com.softserve.webtester.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;

@Service
public class RequestExecuteService {

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private BuildHttpRequestService buildHttpRequestService;

    public List<ResponseDTO> executeRequests(int environmentId, List<Request> requestList, boolean ifBuildVerExist) {

        Environment environment = environmentService.load(environmentId);
        List<ResponseDTO> responseDTOList = new ArrayList<>();
        Connection dbCon;

        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            String host = environment.getBaseUrl();

            for (Request request : requestList) {

                List<HttpResponse> responses = new ArrayList<>();
                List<Long> listResponseTime = new ArrayList<>();
                RequestDTO requestDTO = buildHttpRequestService.getHttpRequest(request, host, dbCon);
                HttpRequestBase requestBase = requestDTO.getHttpRequest();

                if (ifBuildVerExist) {
                    for (int i = 0; i < 5; i++) {
                        Date start = new Date();
                        try (CloseableHttpResponse response = httpClient.execute(requestBase)) {
                            Date stop = new Date();
                            long responseTime = stop.getTime() - start.getTime();
                            responses.add(response);
                            listResponseTime.add(responseTime);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setRequest(request);
                    responseDTO.setResponse(responses);
                    responseDTO.setResponseTime(listResponseTime);
                    responseDTOList.add(responseDTO);
                } else {
                    Date start = new Date();
                    try (CloseableHttpResponse response = httpClient.execute(requestBase)) {
                        Date stop = new Date();
                        long responseTime = stop.getTime() - start.getTime();
                        responses.add(response);
                        listResponseTime.add(responseTime);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setRequest(request);
                    responseDTO.setResponse(responses);
                    responseDTO.setResponseTime(listResponseTime);
                    responseDTOList.add(responseDTO);
                }
            }

            return responseDTOList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
