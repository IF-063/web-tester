package com.softserve.webtester.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;

import com.softserve.webtester.model.Variable;

public class PreparedRequestDTO implements Serializable{

    private static final long serialVersionUID = -6980876513363543600L;
    
    private HttpRequestBase httpRequest;
    private List<Variable> variableList;
    // TODO VZ: Add String Request body

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