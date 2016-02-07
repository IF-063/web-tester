package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.Set;

public class Request implements Serializable {

    private static final long serialVersionUID = -6950350266768777152L;
    
    private long id;
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

    public Request(String name, String description, RequestMethod requestMethod, Application application,
	    	   Service service, Set<Label> labels, String endpoint, Set<Header> headers, String requestBody,
	    	   ResponseType responseType, String expectedResponse, int timeout, Set<Variable> variables,
	    	   Set<DbValidation> dbValidations) {
	this.name = name;
	this.description = description;
	this.requestMethod = requestMethod;
	this.application = application;
	this.service = service;
	this.labels = labels;
	this.endpoint = endpoint;
	this.headers = headers;
	this.requestBody = requestBody;
	this.responseType = responseType;
	this.expectedResponse = expectedResponse;
	this.timeout = timeout;
	this.variables = variables;
	this.dbValidations = dbValidations;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
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
	final int prime = 31;
	int result = 1;
	result = prime * result + ((application == null) ? 0 : application.hashCode());
	result = prime * result + ((dbValidations == null) ? 0 : dbValidations.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
	result = prime * result + ((expectedResponse == null) ? 0 : expectedResponse.hashCode());
	result = prime * result + ((headers == null) ? 0 : headers.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((labels == null) ? 0 : labels.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((requestBody == null) ? 0 : requestBody.hashCode());
	result = prime * result + ((requestMethod == null) ? 0 : requestMethod.hashCode());
	result = prime * result + ((responseType == null) ? 0 : responseType.hashCode());
	result = prime * result + ((service == null) ? 0 : service.hashCode());
	result = prime * result + timeout;
	result = prime * result + ((variables == null) ? 0 : variables.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Request other = (Request) obj;
	if (application == null) {
	    if (other.application != null)
		return false;
	} else if (!application.equals(other.application))
	    return false;
	if (dbValidations == null) {
	    if (other.dbValidations != null)
		return false;
	} else if (!dbValidations.equals(other.dbValidations))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (endpoint == null) {
	    if (other.endpoint != null)
		return false;
	} else if (!endpoint.equals(other.endpoint))
	    return false;
	if (expectedResponse == null) {
	    if (other.expectedResponse != null)
		return false;
	} else if (!expectedResponse.equals(other.expectedResponse))
	    return false;
	if (headers == null) {
	    if (other.headers != null)
		return false;
	} else if (!headers.equals(other.headers))
	    return false;
	if (id != other.id)
	    return false;
	if (labels == null) {
	    if (other.labels != null)
		return false;
	} else if (!labels.equals(other.labels))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (requestBody == null) {
	    if (other.requestBody != null)
		return false;
	} else if (!requestBody.equals(other.requestBody))
	    return false;
	if (requestMethod != other.requestMethod)
	    return false;
	if (responseType != other.responseType)
	    return false;
	if (service == null) {
	    if (other.service != null)
		return false;
	} else if (!service.equals(other.service))
	    return false;
	if (timeout != other.timeout)
	    return false;
	if (variables == null) {
	    if (other.variables != null)
		return false;
	} else if (!variables.equals(other.variables))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Request [id=").append(id);
	builder.append(", name=").append(name);
	builder.append(", description=").append(description);
	builder.append(", requestMethod=").append(requestMethod);
	builder.append(", application=").append(application);
	builder.append(", service=").append(service);
	builder.append(", labels=").append(labels);
	builder.append(", endpoint=").append(endpoint);
	builder.append(", headers=").append(headers);
	builder.append(", requestBody=").append(requestBody);
	builder.append(", responseType=").append(responseType);
	builder.append(", expectedResponse=").append(expectedResponse);
	builder.append(", timeout=").append(timeout);
	builder.append(", variables=").append(variables);
	builder.append(", dbValidations=").append(dbValidations).append("]");
	return builder.toString();
    }
}