package com.softserve.webtester.dto;

import java.io.Serializable;

public class ReportDataDTO implements Serializable {

    private static final long serialVersionUID = -4660040607869903349L;
    
    private String serviceName;
    private int [] responseTime;
    private String [] buildVersionName;
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public int[] getResponseTime() {
        return responseTime;
    }
    public void setResponseTime(int[] responseTime) {
        this.responseTime = responseTime;
    }
    public String[] getBuildVersionName() {
        return buildVersionName;
    }
    public void setBuildVersionName(String[] buildVersionName) {
        this.buildVersionName = buildVersionName;
    }
    
    
    
    

}
