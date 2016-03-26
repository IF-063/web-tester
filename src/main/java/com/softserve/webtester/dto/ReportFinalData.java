package com.softserve.webtester.dto;

import java.io.Serializable;
import java.util.Arrays;


public class ReportFinalData implements Serializable {

    private static final long serialVersionUID = -2171163275081951223L;

    private String serviceName;;
    private int[] responseTimes;
    private String[] buildVersionNames;

    public ReportFinalData(String serviceName, int[] responseTimes, String[] buildVersionNames) {
        this.serviceName = serviceName;
        this.responseTimes = responseTimes;
        this.buildVersionNames = buildVersionNames;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int[] getResponseTimes() {
        return responseTimes;
    }

    public void setResponseTimes(int[] responseTimes) {
        this.responseTimes = responseTimes;
    }

    public String[] getBuildVersionNames() {
        return buildVersionNames;
    }

    public void setBuildVersionNames(String[] buildVersionNames) {
        this.buildVersionNames = buildVersionNames;
    }

    @Override
    public String toString() {
        return "ReportFinalData{" +
                "serviceName='" + serviceName + '\'' +
                ", responseTimes=" + Arrays.toString(responseTimes) +
                ", buildVersionNames=" + Arrays.toString(buildVersionNames) +
                '}';
    }
}