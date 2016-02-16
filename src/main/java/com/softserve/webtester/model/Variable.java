package com.softserve.webtester.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Variable class represents {@code Variable} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.2
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
	return HashCodeBuilder.reflectionHashCode(this, true);
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
	
	return EqualsBuilder.reflectionEquals(this, other, true);
    }
    
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}