package com.softserve.webtester.dto;

import java.io.Serializable;
import java.util.List;

import com.softserve.webtester.model.BuildVersion;

public class StatisticFilterDTO implements Serializable {
    
    private static final long serialVersionUID = -8426535580134914033L;
    
    private int[] serviceId;
    private int[] buildVersionId;
    private int responseTimeFilterMarker;
    private List <BuildVersion> buildVersions;

    public int[] getServiceId() {
        return serviceId;
    }

    public void setServiceId(int[] serviceId) {
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
    
    public List<BuildVersion> getBuildVersions() {
        return buildVersions;
    }

    public void setBuildVersions(List<BuildVersion> buildVersions) {
        this.buildVersions = buildVersions;
    }
}