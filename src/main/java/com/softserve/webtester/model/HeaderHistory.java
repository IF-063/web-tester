package com.softserve.webtester.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;

/**
 * HeaderHistory class representing a database object.
 *
 * @author Viktor Syomka
 */

public class HeaderHistory implements Serializable {

    private static final long serialVersionUID = -8402057973384248296L;

    private long id;
    private String name;
    private String value;
    private ResultHistory resultHistory;

    public HeaderHistory(long id, String name, String value, ResultHistory resultHistory) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.resultHistory = resultHistory;
    }

    public HeaderHistory(){

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

    public ResultHistory getResultHistory() {
        return resultHistory;
    }

    public void setResultHistory(ResultHistory resultHistory) {
        this.resultHistory = resultHistory;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
