package com.softserve.webtester.model;

public class AbstractDomain {

    protected int id;
    protected String name;
    protected String description;
    protected boolean deleted;

    public AbstractDomain() { }

    public AbstractDomain(String name, String description, boolean deleted) {
        this.name = name;
        this.description = description;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
