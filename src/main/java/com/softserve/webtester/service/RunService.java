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
    private EnvironmentService environmentService;

    @Autowired
    private RequestService requestService;
    
    @Autowired
    private BuildHttpRequestService buidHttpRequestService;

    public /*int*/ String executor(int environmentId, int[] requestIdArray) {
        
        Environment environment = environmentService.load(environmentId);
        List<Request> requestList = new ArrayList<Request>();
        for (int i = 0; i < requestIdArray.length; i++) {
            requestList.add(requestService.load(requestIdArray[i]));
        }

        // Need to surround with try/catch
        Connection dbCon;
        String host = environment.getBaseUrl();
        try {
            dbCon = environmentService.getConnection(environment);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            for (Request request : requestList) {

                // Need to surround with try/catch
                /*
                 * CloseableHttpResponse response = httpClient
                 * .execute((HttpUriRequest) getHttpRequest(request, host,
                 * dbCon));
                 */
                return buidHttpRequestService.getHttpRequest(request, host, dbCon).toString();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null/* runId */;
    }



    public ResultHistory parseHttpResponse(HttpResponse response) {

        return null;
    }

}
