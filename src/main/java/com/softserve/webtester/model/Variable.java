package com.softserve.webtester.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The Variable class represents {@code Variable} entity stored in the database.
 * 
 * @author Taras Oglabyak
 */
public class Variable implements Serializable {

    private static final long serialVersionUID = 636905701061698929L;

    private int id;

    @NotBlank(message = "Variable name cannot be empty")
    private String name;

    @NotBlank(message = "Variable value cannot be empty")
    private String value;
    private boolean isSql;
    private boolean isRandom;
    private VariableDataType dataType;
    private Integer length;
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

    public boolean isIsSql() {
        return isSql;
    }

    public void setIsSql(boolean isSql) {
        this.isSql = isSql;
    }

    public boolean isIsRandom() {
        return isRandom;
    }

    public void setIsRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }

    public VariableDataType getDataType() {
        return dataType;
    }

    public void setDataType(VariableDataType dataType) {
        this.dataType = dataType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
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
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}