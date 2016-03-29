package com.softserve.webtester.dto;

import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;

import com.softserve.webtester.model.Variable;

public class RequestDTO {

    private HttpRequestBase httpRequest;
    private List<Variable> variableList;

    public RequestDTO() {

    }

    public HttpRequestBase getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequestBase httpRequest) {
        this.httpRequest = httpRequest;
    }

    public List<Variable> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<Variable> variableList) {
        this.variableList = variableList;
    }
}
