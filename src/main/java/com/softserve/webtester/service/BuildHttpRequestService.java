package com.softserve.webtester.service;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.Variable;
import com.softserve.webtester.model.VariableDataType;

@Service
public class BuildHttpRequestService {

    @Autowired
    private VelocityEngine velocityEngine;

    public HttpRequestBase getHttpRequest(Request request, String host, Connection dbCon)
            throws URISyntaxException, SQLException, ParseException, IOException {

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
                for (Variable variable : request.getVariables()) {
                    if (variable.isSql()) {
                        String variableValue = null;
                        Statement statement = null;
                        String sql = variable.getValue();
                        statement = dbCon.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        variableValue = rs.getString(1);
                        context.put(variable.getName(), variableValue);
                    } else if (variable.isRandom()) {
                        String varValue = variable.getValue() + getRandomString(variable, variable.getLength());
                        context.put(variable.getName(), varValue);
                    } else {
                        context.put(variable.getName(), variable.getValue());
                    }
                }
                StringWriter writer = new StringWriter();
                velocityEngine.evaluate(context, writer, request.getName() + "requestBody", request.getRequestBody());
                HttpEntity entity = new StringEntity(writer.toString()/*,
                        ContentType.create(request.getResponseType().getTextValue(), Consts.UTF_8)*/);
                httpEntityEnclosingRequest.setEntity(entity);
                return httpEntityEnclosingRequest;
            } else {
                HttpEntity entity = new StringEntity(request.getRequestBody());
                httpEntityEnclosingRequest.setEntity(entity);
                return httpEntityEnclosingRequest;
            }
            
        }
        return httpRequest;
    }

    private String getRandomString(Variable variable, int length) {
        String randomString = null;
        randomString = variable.getDataType().getRandomStream().limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        if (variable.getDataType() == VariableDataType.DOUBLE) {
            randomString = getRandomString(variable, length + 2);
            randomString = new StringBuilder(randomString).insert(randomString.length() - 2, ".").toString();
        }
        return randomString;
    }

}
