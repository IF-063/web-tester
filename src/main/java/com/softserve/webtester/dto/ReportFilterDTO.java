package com.softserve.webtester.dto;

import java.io.Serializable;

import com.softserve.webtester.model.ResponseTimeType;

//TODO VS: add JavaDoc.
public class ReportFilterDTO implements Serializable {

    private static final long serialVersionUID = -358128122616172034L;

    private int serviceId;
    private int [] buildVersionId;
    private ResponseTimeType responseTimeFilterMarker;

    public int getServiceId() {
        return serviceId;
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public int[] getBuildVersionId() {
        return buildVersionId;
    }
    public void setBuildVersionId(int[] buildVersionId) {
        this.buildVersionId = buildVersionId;
    }
    public ResponseTimeType getResponseTimeFilterMarker() {
        return responseTimeFilterMarker;
    }
    public void setResponseTimeFilterMarker(ResponseTimeType responseTimeFilterMarker) {
        this.responseTimeFilterMarker = responseTimeFilterMarker;
    }

}