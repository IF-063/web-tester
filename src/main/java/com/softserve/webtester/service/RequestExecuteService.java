package com.softserve.webtester.service;

import com.softserve.webtester.dto.CollectionResultDTO;
import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.dto.RequestResultDTO;
import com.softserve.webtester.dto.ResponseDTO;
import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(defaultTimeout * 1000)
                .setConnectionRequestTimeout(defaultTimeout * 1000)
                .setSocketTimeout(defaultTimeout * 1000).build();


        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

            for (Request request : requestList) {
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
            }

            collectionResultDTO.setCollectionId(collectionId);
            collectionResultDTO.setRequestResultDTOList(requestResultDTOList);
            return collectionResultDTO;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * method runs one request
     * @param requestBase interface for different types of request
     * @return ResponseDTO, object, which consists of response time and response
     */
    private ResponseDTO executeOneRequest(CloseableHttpClient httpClient, HttpRequestBase requestBase) {

        ResponseDTO responseDTO = new ResponseDTO();

        long start = System.currentTimeMillis();

        try(CloseableHttpResponse response = httpClient.execute(requestBase)) {

            int responseTime = (int)(System.currentTimeMillis() - start);

            int statusCode = response.getStatusLine().getStatusCode();

            responseDTO.setResponseTime(responseTime);
            responseDTO.setStatusLine(response.getStatusLine().toString());
            responseDTO.setStatusCode(statusCode);
            responseDTO.setReasonPhrase(response.getStatusLine().getReasonPhrase());

            if ((statusCode >= 200)&&(statusCode<400)) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String s = reader.readLine();
                    responseDTO.setResponseBody(s);
                    reader.close();
                    inputStream.close();
                }
            }

            return responseDTO;

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

}