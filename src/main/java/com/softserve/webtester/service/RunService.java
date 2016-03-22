package com.softserve.webtester.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.ResultHistory;

public class RunService {

    @Autowired
    public EnvironmentService environmentService;

    public List<ResultHistory> executor(Date date, List<Request> requestList, Environment environment) {
        List<ResultHistory> resultsHistory = new ArrayList<ResultHistory>();

        // Need to surround with try/catch
        Connection dbCon;
        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            for (Request request : requestList) {

                // Need to surround with try/catch
                CloseableHttpResponse response = httpClient.execute((HttpUriRequest) getHttpRequest(request, dbCon));

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultsHistory;
    }

    public HttpRequest getHttpRequest(Request request, Connection dbCon) {

        HttpRequest httpRequest = new HttpGet();
        return httpRequest;
    }

    public ResultHistory parseHttpResponse(HttpResponse response) {

        return null;
    }

}
