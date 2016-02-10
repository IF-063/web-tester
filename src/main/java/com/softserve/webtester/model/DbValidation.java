package com.softserve.webtester.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The DbValidation class represents {@code DbValidation} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.0
 */
public class DbValidation implements Serializable {

    private static final long serialVersionUID = 185946295991598992L;
    
    private int id;
    private String sqlQuery;
    private String expectedValue;
    private Request request;

    public DbValidation() { }

    public int getId() {
	return id;
    }

    public void setId(int id) {
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
	return new HashCodeBuilder()
	    .append(id)
	    .append(sqlQuery)
	    .append(expectedValue)
	    .append(request)
	    .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	
	DbValidation other = (DbValidation) obj;
	
	return new EqualsBuilder()
	    .append(id, other.id)
	    .append(sqlQuery, other.sqlQuery)
	    .append(expectedValue, other.expectedValue)
	    .append(request, other.request)
	    .isEquals();
    }
    
    @Override
    public String toString() {
	return new ToStringBuilder(this)
	    .append("id", id)
	    .append("sqlQuery", sqlQuery)
	    .append("expectedValue", expectedValue)
	    .append("request", request)
	    .toString();
    }
}