package com.softserve.webtester.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Header class represents {@code Header} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 3.1
 */
public class Header implements Serializable {
    
    private static final long serialVersionUID = 2373605339528716565L;
    
    private int id;
    private String name;
    private String value;
    private Request request;
    
    public Header() { }
    
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
	
	Header other = (Header) obj;
	
	return EqualsBuilder.reflectionEquals(this, other, true);
    }
    
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }   
}