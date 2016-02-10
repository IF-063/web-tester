package com.softserve.webtester.model;

import java.io.Serializable;

import lombok.Data;

/**
 * The Variable class represents {@code Variable} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 2.0
 */
public @Data class Variable implements Serializable {
    
    private static final long serialVersionUID = -1705243971031177529L;
    
    private int id;
    private String name;
    private String value;
    private boolean isSql;
    private boolean isRandom;
    private VariableDataType dataType;
    private int length;
    private Request request;

}