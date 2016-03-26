package com.softserve.webtester.dto;

import java.io.Serializable;

public class ReportFilterDTO implements Serializable {

    private static final long serialVersionUID = -358128122616172034L;

    private int serviceId;
    private int [] buildVersionId;
    private int responseTimeFilterMarker;

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
    public int getResponseTimeFilterMarker() {
        return responseTimeFilterMarker;
    }
    public void setResponseTimeFilterMarker(int responseTimeFilterMarker) {
        this.responseTimeFilterMarker = responseTimeFilterMarker;
    }


}