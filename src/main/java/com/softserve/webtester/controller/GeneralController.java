package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.softserve.webtester.editor.ApplicationEditor;
import com.softserve.webtester.editor.LabelEditor;
import com.softserve.webtester.editor.ServiceEditor;
import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.Service;

/**
 * ControllerAdvice class defines objects binding in all Controller's RequestMapping methods.
 * 
 * @author Taras Oglabyak
 *
 */
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
	binder.registerCustomEditor(List.class, "labels", labelEditor);
	binder.registerCustomEditor(String.class, new StringTrimmerEditor(" \t\r\n\f", true));
    }
    
   // @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void error(){
	System.out.println("AAAAAAAAAAA!!!!");
    }
}