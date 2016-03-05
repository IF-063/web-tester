package com.softserve.webtester.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.model.Application;
import com.softserve.webtester.service.MetaDataService;

@Controller
@RequestMapping(value = "/application")
public class ApplicationController {

	@Autowired
	private MetaDataService metaDataService;

	@RequestMapping(method = RequestMethod.GET)
	public String getApplicationList(Model model) {
		List<Application> applications = metaDataService.applicationLoadAll();
		model.addAttribute("applications", applications);
		return "application/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getApplication(@PathVariable(value = "id") Integer applicationId, Model model) {
		Application application = metaDataService.applicationLoad(applicationId);
		model.addAttribute("title", "Update Application");
		model.addAttribute("application", application);
		return "application/update";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createApplication(Model model) {
		model.addAttribute("title", "Create Application");
		model.addAttribute("application", new Application());
		return "application/update";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveCreatedApplication(@Valid @ModelAttribute("application") Application application,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Create Application");
			return "application/update";
		}
		metaDataService.applicationSave(application);
		return "redirect:/application";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String saveUpdatedApplication(@Valid @ModelAttribute("application") Application application,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Update Application");
			return "application/update";
		}
		metaDataService.applicationUpdate(application);
		return "redirect:/application";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteApplication(@PathVariable(value = "id") Integer id, Model model) {
		metaDataService.applicationSoftDelete(id);
		return "redirect:/application";
	}
}