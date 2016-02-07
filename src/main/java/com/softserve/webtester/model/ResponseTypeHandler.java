package com.softserve.webtester.model;

import org.apache.ibatis.type.EnumTypeHandler;

public class ResponseTypeHandler extends EnumTypeHandler<ResponseType> {

    public ResponseTypeHandler(Class<ResponseType> type) {
	super(type);
    }
}