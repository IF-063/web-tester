package com.softserve.webtester.model;

import org.apache.ibatis.type.EnumTypeHandler;

public class VariableDataTypeHandler extends EnumTypeHandler<VariableDataType> {

    public VariableDataTypeHandler(Class<VariableDataType> type) {
	super(type);
    }
}