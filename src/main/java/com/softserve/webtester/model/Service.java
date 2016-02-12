package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The Service class represents {@code Service} entity stored in the database.
 *
 * @author Roman Zolotar
 * @version 1.1
 */

public class Service implements Serializable {
	
	private static final long serialVersionUID = -5386109568829000931L;
	
	private int id;
	private String name;
	private String description;
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

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}

