package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.softserve.webtester.editor.LabelEditor;

/**
 * ControllerAdvice class defines objects binding in all Controller's RequestMapping methods.
 * 
 * @author Taras Oglabyak
 *
 */
@ControllerAdvice
public class GeneralController {

    @Autowired
    private LabelEditor labelEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(List.class, "labels", labelEditor);
	binder.registerCustomEditor(String.class, new StringTrimmerEditor(" \t\r\n\f", true));
    }
    
    @ExceptionHandler({ DataIntegrityViolationException.class, java.lang.IllegalStateException.class })
    public String error(){
	return "dataNotFound";
    }
}