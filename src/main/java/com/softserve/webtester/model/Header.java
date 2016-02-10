package com.softserve.webtester.model;

import java.io.Serializable;

import lombok.Data;

/**
 * The Header class represents {@code Header} entity stored in the database.
 * 
 * @author Taras Oglabyak
 * @version 2.0
 */
public @Data class Header implements Serializable {

    private static final long serialVersionUID = -8653946178775197850L;
    
    private int id;
    private String name;
    private String value;
    private Request request;
    
}