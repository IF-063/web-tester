package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Request class represents {@code Request} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.4
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1620123091542829027L;
    
    private int id;
    private String name;
    private String description;
    private RequestMethod requestMethod;
    private Application application;
    private Service service;
    private List<Label> labels;
    private String endpoint;
    private List<Header> headers;
    private String requestBody;
    private ResponseType responseType;
    private String expectedResponse;
    private int timeout;
    private List<Variable> variables;
    private List<DbValidation> dbValidations;
    
    public Request() { }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public RequestMethod getRequestMethod() {
	return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
	this.requestMethod = requestMethod;
    }

    public Application getApplication() {
	return application;
    }

    public void setApplication(Application application) {
	this.application = application;
    }

    public Service getService() {
	return service;
    }

    public void setService(Service service) {
	this.service = service;
    }

    public List<Label> getLabels() {
	return labels;
    }

    public void setLabels(List<Label> labels) {
	this.labels = labels;
    }

    public String getEndpoint() {
	return endpoint;
    }

    public void setEndpoint(String endpoint) {
	this.endpoint = endpoint;
    }

    public List<Header> getHeaders() {
	return headers;
    }

    public void setHeaders(List<Header> headers) {
	this.headers = headers;
    }

    public String getRequestBody() {
	return requestBody;
    }

    public void setRequestBody(String requestBody) {
	this.requestBody = requestBody;
    }

    public ResponseType getResponseType() {
	return responseType;
    }

    public void setResponseType(ResponseType responseType) {
	this.responseType = responseType;
    }

    public String getExpectedResponse() {
	return expectedResponse;
    }

    public void setExpectedResponse(String expectedResponse) {
	this.expectedResponse = expectedResponse;
    }

    public int getTimeout() {
	return timeout;
    }

    public void setTimeout(int timeout) {
	this.timeout = timeout;
    }

    public List<Variable> getVariables() {
	return variables;
    }

    public void setVariables(List<Variable> variables) {
	this.variables = variables;
    }

    public List<DbValidation> getDbValidations() {
	return dbValidations;
    }

    public void setDbValidations(List<DbValidation> dbValidations) {
	this.dbValidations = dbValidations;
    }
    
    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj);
    }
    
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}