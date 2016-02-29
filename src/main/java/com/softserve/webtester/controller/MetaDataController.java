package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.Service;
import com.softserve.webtester.model.User;
import com.softserve.webtester.service.MetaDataService;
import com.softserve.webtester.service.UserService;

@Controller
@RequestMapping("/metadata")
public class MetaDataController {

	@Autowired
	private MetaDataService metaDataService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getMetaDataPage() {
		ModelAndView modelAndView = new ModelAndView("metadata");

		String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
		// User user = userService.load(authenticationName);
		List<Application> applications = metaDataService.applicationLoadAll();
		List<Service> services = metaDataService.serviceLoadAll();

		modelAndView.addObject("applications", applications);
		modelAndView.addObject("services", services);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getById(@RequestParam(required = false) String applicationId,
			@RequestParam(required = false) String serviceId) {
		ModelAndView modelAndView = new ModelAndView("metadata");
		modelAndView.addObject("applicationId", applicationId);
		modelAndView.addObject("serviceId", serviceId);
		modelAndView.addObject("applications", metaDataService.applicationLoadAll());
		modelAndView.addObject("services", metaDataService.serviceLoadAll());

			String message = null;
			if (serviceId == null && applicationId != null && !applicationId.trim().isEmpty()) {
				try {
					int id = Integer.parseInt(applicationId);
					modelAndView.addObject("application", metaDataService.applicationLoad(id));
				} catch (NumberFormatException e) {
					message = "Input value is not number";
				}
			} else {
				message = "Input value is empty";
				modelAndView.addObject("message", message);
			}
	

			//String message = null;
			if (applicationId == null && serviceId != null && !serviceId.trim().isEmpty()) {
				try {
					int id = Integer.parseInt(serviceId);
					modelAndView.addObject("service", metaDataService.serviceLoad(id));
				} catch (NumberFormatException e) {
					message = "Input value is not number";
				}
			} else {
				message = "Input value is empty";
				modelAndView.addObject("message", message);
			}


		return modelAndView;
	}
}