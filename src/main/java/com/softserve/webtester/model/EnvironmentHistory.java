package com.softserve.webtester.model;

public class EnvironmentHistory {

    private long id;
    private ResultHistory resultHistory;
    private String name;
    private String baseURL;
    private String dbURL;
    private String dbPort;
    private String dbName;
    private Environment environment;

    public EnvironmentHistory(long id, ResultHistory resultHistory, String name, String baseURL, String dbURL,
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

    public void setId(long id) {
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
        return "EnvironmentHistory{" +
                "id=" + id +
                ", resultHistory=" + resultHistory +
                ", name='" + name + '\'' +
                ", baseURL='" + baseURL + '\'' +
                ", dbURL='" + dbURL + '\'' +
                ", dbPort='" + dbPort + '\'' +
                ", dbName='" + dbName + '\'' +
                ", environment=" + environment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnvironmentHistory that = (EnvironmentHistory) o;

        if (id != that.id) return false;
        if (resultHistory != null ? !resultHistory.equals(that.resultHistory) : that.resultHistory != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (baseURL != null ? !baseURL.equals(that.baseURL) : that.baseURL != null) return false;
        if (dbURL != null ? !dbURL.equals(that.dbURL) : that.dbURL != null) return false;
        if (dbPort != null ? !dbPort.equals(that.dbPort) : that.dbPort != null) return false;
        if (dbName != null ? !dbName.equals(that.dbName) : that.dbName != null) return false;
        return !(environment != null ? !environment.equals(that.environment) : that.environment != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (resultHistory != null ? resultHistory.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (baseURL != null ? baseURL.hashCode() : 0);
        result = 31 * result + (dbURL != null ? dbURL.hashCode() : 0);
        result = 31 * result + (dbPort != null ? dbPort.hashCode() : 0);
        result = 31 * result + (dbName != null ? dbName.hashCode() : 0);
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        return result;
    }
}
