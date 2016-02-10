package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.Set;

public class RequestCollection implements Serializable{
    
    private int id;
    private String name;
    private String description;
    private Set<Request> requests;
	
    public RequestCollection(){
    }

    public RequestCollection(int id, String name, String description, Set<Request> requests) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.requests = requests;
    }

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
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((requests == null) ? 0 : requests.hashCode());
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
	RequestCollection other = (RequestCollection) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (id != other.id)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (requests == null) {
	    if (other.requests != null)
		return false;
	} else if (!requests.equals(other.requests))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "RequestCollection [id=" + id + ", name=" + name + ", description=" + description + ", requests="
		+ requests + "]";
    }
		
    
}
