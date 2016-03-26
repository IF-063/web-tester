package com.softserve.webtester.service;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
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
            throws URISyntaxException, SQLException, UnsupportedEncodingException {

        HttpRequestBase httpRequest = request.getRequestMethod().getHttpRequest();

        URI uri = new URIBuilder(host + request.getEndpoint()).build();
        httpRequest.setURI(uri);
        if (request.getHeaders() != null) {
            for (Header header : request.getHeaders()) {
                httpRequest.setHeader(header.getName(), header.getValue());
            }
        }
        if (httpRequest.getMethod().equals("POST")) {
            
            //Template requestBodyTemplate = velocityEngine.getTemplate(request.getRequestBody());

            VelocityContext context = new VelocityContext();
            if (request.getVariables() != null) {
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
                        VariableDataType varDataType = variable.getDataType();
                        String varValue = getRandomString(variable, variable.getLength());
                        context.put(variable.getName(), varValue);
                    } else {
                        context.put(variable.getName(), variable.getValue());
                    }
                }

                StringWriter writer = new StringWriter();
                velocityEngine.evaluate(context, writer, "requestBody", request.getRequestBody());

                ((HttpEntityEnclosingRequestBase)httpRequest).setEntity(new StringEntity(writer.toString()));

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
