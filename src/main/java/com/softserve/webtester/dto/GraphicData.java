package com.softserve.webtester.dto;
import java.io.Serializable;

public class GraphicData implements Serializable {

    private static final long serialVersionUID = -73533802241570478L;

    private Integer serviceName;
    private int[] buildVersions;
    private int[] applications;

    public GraphicData() { }

    public Integer getServiceName() {
        return serviceName;
    }

    public void setServiceName(Integer serviceName) {
        this.serviceName = serviceName;
    }

    public int[] getBuildVersions() {
        return buildVersions;
    }

    public void setBuildVersions(int[] buildVersions) {
        this.buildVersions = buildVersions;
    }

    public int[] getApplications() {
        return applications;
    }

    public void setApplications(int[] applications) {
        this.applications = applications;
    }
}
