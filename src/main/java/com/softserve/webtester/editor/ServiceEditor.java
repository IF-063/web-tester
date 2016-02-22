package com.softserve.webtester.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.webtester.model.Service;
import com.softserve.webtester.service.MetaDataService;

@Component
public class ServiceEditor extends PropertyEditorSupport {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	Service instance = metaDataService.serviceLoad(Integer.parseInt(text));
	this.setValue(instance);
    }
}