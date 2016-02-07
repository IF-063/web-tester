package com.softserve.webtester.model;

import org.apache.ibatis.type.EnumTypeHandler;

public class RequestMethodHandler extends EnumTypeHandler<RequestMethod> {

    public RequestMethodHandler(Class<RequestMethod> type) {
	super(type);
    }
}