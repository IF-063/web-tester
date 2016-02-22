package com.softserve.webtester.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.service.MetaDataService;

@Component
public class LabelEditor extends PropertyEditorSupport {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	Label instance = metaDataService.loadLabelById(Integer.parseInt(text));
	System.out.println(instance);
	this.setValue(instance);
    }
}