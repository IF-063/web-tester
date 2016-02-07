package com.softserve.webtester.model;

public enum ResponseType {

    XML("XML"), JSON("JSON"), PLAINTEXT("text/plain"), UNDEFINED("undefined");

    private String textValue;

    private ResponseType(String textValue) {
	this.textValue = textValue;
    }

    public String textValue() {
	return textValue;
    }

}