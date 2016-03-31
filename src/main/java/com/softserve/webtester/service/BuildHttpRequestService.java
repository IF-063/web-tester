package com.softserve.webtester.service;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.dto.RequestDTO;
import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.Variable;
import com.softserve.webtester.model.VariableDataType;

/**
 * BuildHttpRequestService class allows create http requests based on Request and Environment
 *
 */
@Service
public class BuildHttpRequestService {

    @Autowired
    private RequestExecuteSupportService requestExecuteSupportService;
    
    
    /**
     * Create instance RequestDTO which contains instance of HttpRequest and list of generated Variable
     * @param request
     */
    public RequestDTO getHttpRequest(Request request, String host, Connection dbCon)
            throws URISyntaxException, SQLException, ParseException, IOException {

        RequestDTO requestDTO = new RequestDTO();
        HttpRequestBase httpRequest = request.getRequestMethod().getHttpRequest();

        URI uri = new URIBuilder(host + request.getEndpoint()).build();
        httpRequest.setURI(uri);

        if (request.getHeaders() != null) {
            for (Header header : request.getHeaders()) {
                httpRequest.setHeader(header.getName(), header.getValue());
            }
        }

        if (httpRequest.getMethod().equals("POST")) {
            HttpEntityEnclosingRequestBase httpEntityEnclosingRequest = (HttpEntityEnclosingRequestBase) httpRequest;

            if (request.getVariables() != null) {
                VelocityContext context = new VelocityContext();
                List<Variable> variableList = new ArrayList<Variable>();
                for (Variable variable : request.getVariables()) {
                    if (variable.isSql()) {
                        String variableValue = requestExecuteSupportService.getExecutedQueryValue(dbCon,
                                variable.getValue());
                        variable.setValue(variableValue);
                        variableList.add(variable);
                    } else if (variable.isRandom()) {
                        String variableValue = variable.getValue() + getRandomString(variable, variable.getLength());
                        variable.setValue(variableValue);
                        variableList.add(variable);
                    } else {
                        variableList.add(variable);
                    }
                }
                String logString = "Request body";
                HttpEntity entity = new StringEntity(requestExecuteSupportService
                        .getEvaluatedString(request.getRequestBody(), variableList, logString));
                httpEntityEnclosingRequest.setEntity(entity);
                requestDTO.setHttpRequest(httpEntityEnclosingRequest);
                requestDTO.setVariableList(variableList);
                return requestDTO;
            } else {
                HttpEntity entity = new StringEntity(request.getRequestBody());
                httpEntityEnclosingRequest.setEntity(entity);
                requestDTO.setHttpRequest(httpEntityEnclosingRequest);
                return requestDTO;
            }

        }
        requestDTO.setHttpRequest(httpRequest);
        return requestDTO;
    }

    /**
     * Return generated String given length
     */
    private String getRandomString(Variable variable, int length) {
        String randomString = null;
        randomString = variable.getDataType().getRandomStream(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        if (variable.getDataType() == VariableDataType.DOUBLE) {
            randomString = new StringBuilder(randomString).insert(randomString.length() - 2, ".").toString();
        }
        return randomString;
    }

}
