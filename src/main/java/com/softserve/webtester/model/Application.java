package com.softserve.webtester.model;

import java.io.Serializable;

/**
 * The Application class represents {@code Application} entity stored in the database.
 *
 * @author Roman Zolotar
 * @version 1.1
 */

public class Application implements Serializable {
	
	private static final long serialVersionUID = -6038553268823619415L;
	
	private int id;
	private String name;
	private String description;
	private boolean deleted;
	
	public Application() {
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
