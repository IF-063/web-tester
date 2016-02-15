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

public class DBValidationHistory implements Serializable {

    private static final long serialVersionUID = 660145260667703463L;

    private long id;
    private String sqlQuery;
    private String expectedValue;
    private String actualValue;
    private ResultHistory resultHistory;

    public DBValidationHistory(long id, String sqlQuery, String expectedValue, String actualValue,
                               ResultHistory resultHistory) {
        this.id = id;
        this.sqlQuery = sqlQuery;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.resultHistory = resultHistory;
    }

    public DBValidationHistory(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
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
