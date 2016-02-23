package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.ws.Service;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ResultHistory class representing a database object.
 * @author Viktor Syomka
 */

public class ResultHistory implements Serializable {

    private static final long serialVersionUID = 2316323598058491701L;

    private int id;
    private String status;
    private Application application;
    private Service service;
    private Request request;
    private String requestName;
    private String requestDescription;
    private String url;
    private ResponseType responseType;
    private String requestBody;
    private String statusLine;
    private String timeStart;
    private int expectedResponseTime;
    private int responseTime;
    private String expectedResponse;
    private String actualResponse;
    private String message;
    private int runId;
    private RequestCollection requestCollection;
    private BuildVersion buildVersion;
    private List<Label> labels;
    private List<HeaderHistory> headerHistories;
    private List<DBValidationHistory> dbValidationHistories;

    public ResultHistory() {
    }

    public ResultHistory(int id, String status, Application application, Service service, Request request,
                         String requestName, String requestDescription, String url, ResponseType responseType,
                         String requestBody, String statusLine, String timeStart, int expectedResponseTime, int responseTime,
                         String expectedResponse, String actualResponse, String message, int runId,
                         RequestCollection requestCollection, BuildVersion buildVersion, List<Label> labels,
                         List<HeaderHistory> headerHistories, List<DBValidationHistory> dbValidationHistories) {
        this.id = id;
        this.status = status;
        this.application = application;
        this.service = service;
        this.request = request;
        this.requestName = requestName;
        this.requestDescription = requestDescription;
        this.url = url;
        this.responseType = responseType;
        this.requestBody = requestBody;
        this.statusLine = statusLine;
        this.timeStart = timeStart;
        this.expectedResponseTime = expectedResponseTime;
        this.responseTime = responseTime;
        this.expectedResponse = expectedResponse;
        this.actualResponse = actualResponse;
        this.message = message;
        this.runId = runId;
        this.requestCollection = requestCollection;
        this.buildVersion = buildVersion;
        this.labels = labels;
        this.headerHistories = headerHistories;
        this.dbValidationHistories = dbValidationHistories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public int getExpectedResponseTime() {
        return expectedResponseTime;
    }

    public void setExpectedResponseTime(int expectedResponseTime) {
        this.expectedResponseTime = expectedResponseTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public String getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(String expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    public String getActualResponse() {
        return actualResponse;
    }

    public void setActualResponse(String actualResponse) {
        this.actualResponse = actualResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public RequestCollection getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(RequestCollection requestCollection) {
        this.requestCollection = requestCollection;
    }

    public BuildVersion getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(BuildVersion buildVersion) {
        this.buildVersion = buildVersion;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<HeaderHistory> getHeaderHistories() {
        return headerHistories;
    }

    public void setHeaderHistories(List<HeaderHistory> headerHistories) {
        this.headerHistories = headerHistories;
    }

    public List<DBValidationHistory> getDbValidationHistories() {
        return dbValidationHistories;
    }

    public void setDbValidationHistories(List<DBValidationHistory> dbValidationHistories) {
        this.dbValidationHistories = dbValidationHistories;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
