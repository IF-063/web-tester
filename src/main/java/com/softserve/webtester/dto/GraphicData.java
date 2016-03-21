package com.softserve.webtester.dto;
import java.io.Serializable;

public class GraphicData implements Serializable {

    private static final long serialVersionUID = 8735689471532008238L;

    private Integer serviceName;
    private Integer buildVersionMin;
    private Integer buildVersionMax;

    public GraphicData() { }

    public Integer getServiceName() {
        return serviceName;
    }

    public void setServiceName(Integer serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getBuildVersionMin() {
        return buildVersionMin;
    }

    public void setBuildVersionMin(Integer buildVersionMin) {
        this.buildVersionMin = buildVersionMin;
    }

    public Integer getBuildVersionMax() {
        return buildVersionMax;
    }

    public void setBuildVersionMax(Integer buildVersionMax) {
        this.buildVersionMax = buildVersionMax;
    }
}
