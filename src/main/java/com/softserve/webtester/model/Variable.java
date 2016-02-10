package com.softserve.webtester.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Variable class represents {@code Variable} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.0
 */
public class Variable implements Serializable {

    private static final long serialVersionUID = 636905701061698929L;
    
    private int id;
    private String name;
    private String value;
    private boolean isSql;
    private boolean isRandom;
    private VariableDataType dataType;
    private int length;
    private Request request;
    
    public Variable() { }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public boolean isSql() {
	return isSql;
    }

    public void setSql(boolean isSql) {
	this.isSql = isSql;
    }

    public boolean isRandom() {
	return isRandom;
    }

    public void setRandom(boolean isRandom) {
	this.isRandom = isRandom;
    }

    public VariableDataType getDataType() {
	return dataType;
    }

    public void setDataType(VariableDataType dataType) {
	this.dataType = dataType;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
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
	    .append(name)
	    .append(value)
	    .append(isSql)
	    .append(isRandom)
	    .append(dataType)
	    .append(length)
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
	
	Variable other = (Variable) obj;
	
	return new EqualsBuilder()
	    .append(id, other.id)
	    .append(name, other.name)
	    .append(value, other.value)
	    .append(isSql, other.isSql)
	    .append(isRandom, other.isRandom)
	    .append(dataType, other.dataType)
	    .append(length, other.length)
	    .append(request, other.request)
	    .isEquals();
    }
    
    @Override
    public String toString() {
	return new ToStringBuilder(this)
	    .append("id", id)
	    .append("name", name)
	    .append("value", value)
	    .append("isSql", isSql)
	    .append("isRandom", isRandom)
	    .append("dataType", dataType)
	    .append("length", length)
	    .append("request", request)
	    .toString();
    }
}