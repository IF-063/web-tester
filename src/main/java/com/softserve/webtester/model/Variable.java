package com.softserve.webtester.model;

import java.io.Serializable;

public class Variable implements Serializable {

    private static final long serialVersionUID = -7548591892971626622L;
    
    private long id;
    private String name;
    private String value;
    private boolean isSql;
    private boolean isRandom;
    private VariableDataType dataType;
    private int length;
    private Request request;

    public Variable() { }

    public Variable(String name, String value, boolean isSql, boolean isRandom, 
	    	    VariableDataType dataType, int length, Request request) {
	this.name = name;
	this.value = value;
	this.isSql = isSql;
	this.isRandom = isRandom;
	this.dataType = dataType;
	this.length = length;
	this.request = request;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
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
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + (isRandom ? 1231 : 1237);
	result = prime * result + (isSql ? 1231 : 1237);
	result = prime * result + length;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((request == null) ? 0 : request.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	Variable other = (Variable) obj;
	if (dataType != other.dataType)
	    return false;
	if (id != other.id)
	    return false;
	if (isRandom != other.isRandom)
	    return false;
	if (isSql != other.isSql)
	    return false;
	if (length != other.length)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (request == null) {
	    if (other.request != null)
		return false;
	} else if (!request.equals(other.request))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Variable [id=").append(id);
	builder.append(", name=").append(name);
	builder.append(", value=").append(value);
	builder.append(", isSql=").append(isSql);
	builder.append(", isRandom=").append(isRandom);
	builder.append(", dataType=").append(dataType);
	builder.append(", length=").append(length);
	builder.append(", request=");
	builder.append(request).append("]");
	return builder.toString();
    }
}