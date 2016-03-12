package com.softserve.webtester.model;

/**
 * Enumeration of Database types can be used in the Environment instance
 *
 */
public enum EnvironmentDbType {

    // ConnectionPattern: %1 - dbUrl, %2 - dbPort, %3 - dbName, %4 - dbUsername,
    // %5 - dbPassword
    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver", 
            "jdbc:sqlserver://%1$s:%2$s;databaseName=%3$s;user=%4$s;password=%5$s"), 
    MYSQL("com.mysql.jdbc.Driver", 
            "jdbc:mysql://%1$s:%2$s/%3$s?user=%4$s&password=%5$s"), 
    ORACLE("oracle.jdbc.OracleDriver", 
            "jdbc:oracle:thin:%4$s/%5$s@%1$s:%2$s:%3$s");

    private String dbDriver;
    private String connectionPattern;

    private EnvironmentDbType(String dbDriver, String connectionPattern) {
        this.dbDriver = dbDriver;
        this.connectionPattern = connectionPattern;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getConnectionPattern() {
        return connectionPattern;
    }

}
