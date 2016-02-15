package com.softserve.webtester.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * EnvironmentHistory class representing a database object.
 *
 * @author Viktor Syomka
 */

public class EnvironmentHistory implements Serializable {

    private static final long serialVersionUID = -3838842881636539961L;

    private int id;
    private ResultHistory resultHistory;
    private String name;
    private String baseURL;
    private String dbURL;
    private String dbPort;
    private String dbName;
    private Environment environment;

    public EnvironmentHistory(int id, ResultHistory resultHistory, String name, String baseURL, String dbURL,
                              String dbPort, String dbName, Environment environment) {
        this.id = id;
        this.resultHistory = resultHistory;
        this.name = name;
        this.baseURL = baseURL;
        this.dbURL = dbURL;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.environment = environment;
    }

    public EnvironmentHistory(){

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResultHistory getResultHistory() {
        return resultHistory;
    }

    public void setResultHistory(ResultHistory resultHistory) {
        this.resultHistory = resultHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
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
