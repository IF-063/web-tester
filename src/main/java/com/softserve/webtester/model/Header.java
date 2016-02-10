package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The Header class represents {@code Header} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 1.0
 */
public class Header implements Serializable {

    private static final long serialVersionUID = 8014208566637870078L;

    private long id;
    private String name;
    private String value;
    private Request request;

    public Header() { }

    public Header(String name, String value, Request request) {
	this.name = name;
	this.value = value;
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
	result = prime * result + (int) (id ^ (id >>> 32));
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
	Header other = (Header) obj;
	if (id != other.id)
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
	builder.append("Header [id=").append(id);
	builder.append(", name=").append(name);
	builder.append(", value=").append(value);
	builder.append(", request=").append(request).append("]");
	return builder.toString();
    }
}