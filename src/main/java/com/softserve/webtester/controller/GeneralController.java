package com.softserve.webtester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.softserve.webtester.editor.ApplicationEditor;
import com.softserve.webtester.editor.LabelEditor;
import com.softserve.webtester.editor.ServiceEditor;
import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Service;

@ControllerAdvice
public class GeneralController {

    @Autowired
    private ApplicationEditor applicationEditor;

    @Autowired
    private ServiceEditor serviceEditor;

    @Autowired
    private LabelEditor labelEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(Application.class, applicationEditor);
	binder.registerCustomEditor(Service.class, serviceEditor);
	binder.registerCustomEditor(Label.class, labelEditor);
    }
}