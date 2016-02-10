package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Request class represents {@code Request} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.0
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -8857729780657681509L;
    
    private int id;
    private String name;
    private String description;
    private RequestMethod requestMethod;
    private Application application;
    private Service service;
    private Set<Label> labels;
    private String endpoint;
    private Set<Header> headers;
    private String requestBody;
    private ResponseType responseType;
    private String expectedResponse;
    private int timeout;
    private Set<Variable> variables;
    private Set<DbValidation> dbValidations;
    
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

    public Set<Label> getLabels() {
	return labels;
    }

    public void setLabels(Set<Label> labels) {
	this.labels = labels;
    }

    public String getEndpoint() {
	return endpoint;
    }

    public void setEndpoint(String endpoint) {
	this.endpoint = endpoint;
    }

    public Set<Header> getHeaders() {
	return headers;
    }

    public void setHeaders(Set<Header> headers) {
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

    public Set<Variable> getVariables() {
	return variables;
    }

    public void setVariables(Set<Variable> variables) {
	this.variables = variables;
    }

    public Set<DbValidation> getDbValidations() {
	return dbValidations;
    }

    public void setDbValidations(Set<DbValidation> dbValidations) {
	this.dbValidations = dbValidations;
    }
    

    @Override
    public int hashCode() {
	return new HashCodeBuilder()
	    .append(id)
	    .append(name)
	    .append(description)
	    .append(requestMethod)
	    .append(application)
	    .append(service)
	    .append(labels)
	    .append(endpoint)
	    .append(headers)
	    .append(requestBody)
	    .append(responseType)
	    .append(expectedResponse)
	    .append(timeout)
	    .append(variables)
	    .append(dbValidations)
 	    .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	
	Request other = (Request) obj;
	
	return new EqualsBuilder()
	    .append(id, other.id)
	    .append(name, other.name)
	    .append(description, other.description)
	    .append(requestMethod, other.requestMethod)
	    .append(application, other.application)
	    .append(service, other.service)
	    .append(labels, other.labels)
	    .append(endpoint, other.endpoint)
	    .append(headers, other.headers)
	    .append(requestBody, other.requestBody)
	    .append(responseType, other.responseType)
	    .append(expectedResponse, other.expectedResponse)
	    .append(timeout, other.timeout)
	    .append(variables, other.variables)
	    .append(dbValidations, other.dbValidations)
	    .isEquals();
    }
    
    @Override
    public String toString() {
	return new ToStringBuilder(this)
	    .append("id", id)
	    .append("name", name)
	    .append("description", description)
	    .append("requestMethod", requestMethod)
	    .append("application", application)
	    .append("service", service)
	    .append("labels", labels)
	    .append("endpoint", endpoint)
	    .append("headers", headers)
	    .append("requestBody", requestBody)
	    .append("responseType", responseType)
	    .append("expectedResponse", expectedResponse)
	    .append("timeout", timeout)
	    .append("variables", variables)
	    .append("dbValidations", dbValidations)
	    .toString();
    }
}