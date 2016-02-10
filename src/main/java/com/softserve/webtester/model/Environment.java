package com.softserve.webtester.model;

import java.io.Serializable;

public class Environment implements Serializable {

	private static final long serialVersionUID = -8149079996207423153L;

	private int id;
	private String name;
	private String baseUrl;
	private String dbUrl;
	private String dbPort;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private float timeMultiplier;
	private boolean deleted;

	public Environment() {
	}

	public Environment(String name, String baseUrl, String dbUrl, String dbPort, String dbName, String dbUsername,
			String dbPassword, float timeMultiplier, boolean deleted) {
		this.name = name;
		this.baseUrl = baseUrl;
		this.dbUrl = dbUrl;
		this.dbPort = dbPort;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.timeMultiplier = timeMultiplier;
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

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public float getTimeMultiplier() {
		return timeMultiplier;
	}

	public void setTimeMultiplier(float timeMultiplier) {
		this.timeMultiplier = timeMultiplier;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
		result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
		result = prime * result + ((dbPassword == null) ? 0 : dbPassword.hashCode());
		result = prime * result + ((dbPort == null) ? 0 : dbPort.hashCode());
		result = prime * result + ((dbUrl == null) ? 0 : dbUrl.hashCode());
		result = prime * result + ((dbUsername == null) ? 0 : dbUsername.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(timeMultiplier);
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
		Environment other = (Environment) obj;
		if (baseUrl == null) {
			if (other.baseUrl != null)
				return false;
		} else if (!baseUrl.equals(other.baseUrl))
			return false;
		if (dbName == null) {
			if (other.dbName != null)
				return false;
		} else if (!dbName.equals(other.dbName))
			return false;
		if (dbPassword == null) {
			if (other.dbPassword != null)
				return false;
		} else if (!dbPassword.equals(other.dbPassword))
			return false;
		if (dbPort == null) {
			if (other.dbPort != null)
				return false;
		} else if (!dbPort.equals(other.dbPort))
			return false;
		if (dbUrl == null) {
			if (other.dbUrl != null)
				return false;
		} else if (!dbUrl.equals(other.dbUrl))
			return false;
		if (dbUsername == null) {
			if (other.dbUsername != null)
				return false;
		} else if (!dbUsername.equals(other.dbUsername))
			return false;
		if (deleted != other.deleted)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(timeMultiplier) != Float.floatToIntBits(other.timeMultiplier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Environment [id=").append(id);
		builder.append(", name=").append(name);
		builder.append(", baseUrl=").append(baseUrl);
		builder.append(", dbUrl=").append(dbUrl);
		builder.append(", dbPort=").append(dbPort);
		builder.append(", dbName=").append(dbName);
		builder.append(", dbUsername=").append(dbUsername);
		builder.append(", dbPassword=").append(dbPassword);
		builder.append(", timeMultiplier=").append(timeMultiplier);
		builder.append(", deleted=").append(deleted).append("]");
		return builder.toString();
	}
}