package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The Request—ollection class represents {@code RequestCollection} entity stored in the database.
 * 
 * @author Yura Lubinec
 * @version 1.0
 */
public class RequestCollection implements Serializable {

    private static final long serialVersionUID = -8452554592409888993L;

    private int id;

    @NotBlank(message = "Collection name cannot be empty")
    private String name;

    @NotBlank(message = "Collection description cannot be empty")
    private String description;

    @Valid
    @NotNull
    private List<Request> requests;

    @Valid
    private List<Label> labels;

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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
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
        RequestCollection other = (RequestCollection) obj;

        return EqualsBuilder.reflectionEquals(this, other, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
