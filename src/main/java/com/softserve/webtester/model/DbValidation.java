package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The DbValidation class represents {@code DbValidation} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 1.0
 */
public class DbValidation implements Serializable {

    private static final long serialVersionUID = 381183695698451969L;

    private long id;
    private String sqlQuery;
    private String expectedValue;
    private Request request;

    public DbValidation() { }

    public DbValidation(String sqlQuery, String expectedValue, Request request) {
	this.sqlQuery = sqlQuery;
	this.expectedValue = expectedValue;
	this.request = request;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getSqlQuery() {
	return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
	this.sqlQuery = sqlQuery;
    }

    public String getExpectedValue() {
	return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
	this.expectedValue = expectedValue;
    }

    public Request getRequest() {
	return request;
    }

    public void setRequest(Request request) {
	this.request = request;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((expectedValue == null) ? 0 : expectedValue.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((request == null) ? 0 : request.hashCode());
	result = prime * result + ((sqlQuery == null) ? 0 : sqlQuery.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DbValidation other = (DbValidation) obj;
	if (expectedValue == null) {
	    if (other.expectedValue != null)
		return false;
	} else if (!expectedValue.equals(other.expectedValue))
	    return false;
	if (id != other.id)
	    return false;
	if (request == null) {
	    if (other.request != null)
		return false;
	} else if (!request.equals(other.request))
	    return false;
	if (sqlQuery == null) {
	    if (other.sqlQuery != null)
		return false;
	} else if (!sqlQuery.equals(other.sqlQuery))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("DbValidation [id=").append(id);
	builder.append(", sqlQuery=").append(sqlQuery);
	builder.append(", expectedValue=").append(expectedValue);
	builder.append(", request=").append(request).append("]");
	return builder.toString();
    }
}