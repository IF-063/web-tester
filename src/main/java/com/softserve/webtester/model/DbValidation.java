package com.softserve.webtester.model;

import java.io.Serializable;

import lombok.Data;

/**
 * The DbValidation class represents {@code DbValidation} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 2.0
 */
public @Data class DbValidation implements Serializable {

    private static final long serialVersionUID = -5095611521121572552L;
    
    private int id;
    private String sqlQuery;
    private String expectedValue;
    private Request request;
    
}