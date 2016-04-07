package com.softserve.webtester.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;

@Service
public class RequestExecuteService {

    private static final Logger LOGGER = Logger.getLogger(RequestExecuteService.class);

    private static final int SAMPLE_FOR_BUILD_VERSION = 5; // Move to property file

    @Autowired
    private BuildHttpRequestService buildHttpRequestService;

    @Value("${response.timeout.default}")
    private int defaultTimeout;

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

        RequestConfig config = RequestConfig.custom().setConnectTimeout(defaultTimeout * 1000) // Calculate
                                                                                               // once
                                                                                               // and
                                                                                               // reuse
                                                                                               // everywhere
                .setConnectionRequestTimeout(defaultTimeout * 1000).setSocketTimeout(defaultTimeout * 1000).build();

        for (Request request : requestList) {
            // move into lopp below
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
                RequestResultDTO requestResultDTO = new RequestResultDTO();
                RequestDTO requestDTO = buildHttpRequestService.getHttpRequest(request, environment);
                HttpRequestBase requestBase = requestDTO.getHttpRequest();
                List<ResponseDTO> responseDTOList = new ArrayList<>();
                ResponseDTO responseDTO;

                if (ifBuildVerExist) {
                    for (int i = 0; i < SAMPLE_FOR_BUILD_VERSION; i++) {
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
            } catch (Exception e) {
                LOGGER.error("Cannot prepare response for sending: " + e.getMessage(), e);
                // TODO AM: Set RequestDTO and responses in the RequestResultDTO
                // to Null.
                // TODO RZ: Check this in you functionality
            }
        }

        collectionResultDTO.setCollectionId(collectionId);
        collectionResultDTO.setRequestResultDTOList(requestResultDTOList);
        return collectionResultDTO;
    }

    /**
     * method runs one request
     * @param requestBase interface for different types of request
     * @return ResponseDTO, object, which consists of response time and response
     */
    private ResponseDTO executeOneRequest(CloseableHttpClient httpClient, HttpRequestBase requestBase) {

        long start = System.currentTimeMillis();

        try (CloseableHttpResponse response = httpClient.execute(requestBase)) {

            ResponseDTO responseDTO = new ResponseDTO();
            
            int responseTime = (int) (System.currentTimeMillis() - start); // Do not expect to have more than 2147483647, because max response timeout is 60000.

            int statusCode = response.getStatusLine().getStatusCode();

            responseDTO.setResponseTime(responseTime);
            responseDTO.setStatusLine(response.getStatusLine().toString());
            responseDTO.setStatusCode(statusCode);
            responseDTO.setReasonPhrase(response.getStatusLine().getReasonPhrase());

            if ((statusCode >= 200) && (statusCode < 400)) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String responseBody = reader.readLine();
                    responseDTO.setResponseBody(responseBody);
                    reader.close(); // TODO AM: close in finally block
                    inputStream.close();
                    // TODO AM: separate catch for Response body reading
                }
            }

            return responseDTO;

        } catch (IOException e) {
            LOGGER.error("Exception occured during request sending. " + e.getMessage(), e);
        }
        return null;
    }

}