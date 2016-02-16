package com.softserve.webtester.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The User class represents {@code User} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 1.2
 */
public class User implements Serializable{

    private static final long serialVersionUID = 5801084059340063607L;
    
    private int id;
    
    @NotBlank(message="username can't be empty")
    private String username;
    
    @NotBlank(message="password can't be empty")
    @Size(min=4, max=32)
    private String password;
    
    private String firstName;
    
    private String lastName;
    // private Role role;

    public User() { }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
/*
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
*/
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