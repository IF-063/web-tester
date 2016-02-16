package com.softserve.webtester.model;

/**
 * Enumeration of the expected response types can be returned by the {@link Request}.
 * 
 * @author Taras Oglabyak
 * @version 1.1
 */
public enum ResponseType {

    XML("XML"), JSON("JSON"), PLAINTEXT("text/plain"), UNDEFINED("undefined");

    private String textValue;

    private ResponseType(String textValue) {
	this.textValue = textValue;
    }

    public String getTextValue() {
	return textValue;
    }
}