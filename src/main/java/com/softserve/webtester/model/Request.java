package com.softserve.webtester.model;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * The Request class represents {@code Request} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 2.0
 */
public @Data class Request implements Serializable {
    
    private static final long serialVersionUID = -3800058654571055904L;
    
    private int id;
    private String name;
    private String description;
    private RequestMethod requestMethod;
    private Application application;
    private Service service;
    private Set<Label> labels;
    private String endpoint;
    private Set<Header> headers;
    private String requestBody;
    private ResponseType responseType;
    private String expectedResponse;
    private int timeout;
    private Set<Variable> variables;
    private Set<DbValidation> dbValidations;

}