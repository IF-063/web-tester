package com.softserve.webtester.model;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * The Service class represents {@code Service} entity stored in the database.
 *
 * @author Roman Zolotar
 * @version 1.3
 */

public class Service implements Serializable {

    private static final long serialVersionUID = -5386109568829000931L;

    private int id;

    @Size(max = 75)
    private String name;

    @Size(max = 255)
    private String description;
    
    @Digits(fraction = 0, integer = 9)
    @DecimalMin("1")
    private Integer sla;

    private boolean deleted;

    public Service() {
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
    
    public Integer getSla(){
        return sla;
    }
    
    public void setSla(Integer sla){
        this.sla = sla;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

}
