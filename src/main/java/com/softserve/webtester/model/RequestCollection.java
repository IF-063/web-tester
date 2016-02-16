package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RequestCollection implements Serializable{
    
   
    private static final long serialVersionUID = -8452554592409888993L;
    
    private int id;
    private String name;
    private String description;
    private Set<Request> requests;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj){
	    return true;
	} 
	if (obj == null){
	    return false;
	}
	
	if (getClass() != obj.getClass()){
	    return false;
	} 
	RequestCollection other = (RequestCollection) obj;
	
	return EqualsBuilder.reflectionEquals(this, other, true);
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
		
    
}
