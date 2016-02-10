package com.softserve.webtester.model;

import org.apache.ibatis.type.EnumTypeHandler;

/**
 * MyBatis handler is used to retrieve the value of Java {@link RequestMethod} enumeration instance.
 * 
 * @author Taras Oglabyak
 * @version 1.0
 */
public class RequestMethodHandler extends EnumTypeHandler<RequestMethod> {

    public RequestMethodHandler(Class<RequestMethod> type) {
	super(type);
    }
}