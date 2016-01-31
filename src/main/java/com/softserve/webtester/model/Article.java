package com.softserve.webtester.model;

public class Article {

    private int id;
    private String name;

    public Article() { }

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

    @Override
    public String toString() {
	return "Article [id=" + id + ", name=" + name + "]";
    }

}