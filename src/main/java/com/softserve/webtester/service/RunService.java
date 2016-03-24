package com.softserve.webtester.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.ResultHistory;

@Service
public class RunService {

    @Autowired
    public EnvironmentService environmentService;
    
    @Autowired
    public RequestService requestService;

    public /*List<ResultHistory>*/ String executor(int environmentId, int[] requestIdArray) {
        
        Environment environment = environmentService.load(environmentId);
        List<Request> requestList = new ArrayList<Request>();
        for (int i = 0; i < requestIdArray.length; i++) {
            requestList.add(requestService.load(requestIdArray[i]));
        }
        
        List<ResultHistory> resultsHistory = new ArrayList<ResultHistory>();

        // Need to surround with try/catch
        Connection dbCon;
        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            String host = environment.getBaseUrl();

            for (Request request : requestList) {

                // Need to surround with try/catch
                /*CloseableHttpResponse response = httpClient
                        .execute((HttpUriRequest) getHttpRequest(request, host, dbCon));*/
                return getHttpRequest(request, host, dbCon).toString();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null/*resultsHistory*/;
    }

    public HttpRequestBase getHttpRequest(Request request, String host, Connection dbCon) throws URISyntaxException {

        HttpRequestBase httpRequest = request.getRequestMethod().getHttpRequest();

        URI uri = new URIBuilder(host).setPath(request.getEndpoint()).build();
        httpRequest.setURI(uri);
        
        for (Header header : request.getHeaders()) {
            httpRequest.setHeader(header.getName(), header.getValue());
        }
        
        if (httpRequest.getMethod().equals("POST")) {
            
            ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(null);
        }

        return httpRequest;
    }

    public ResultHistory parseHttpResponse(HttpResponse response) {

        return null;
    }

}
