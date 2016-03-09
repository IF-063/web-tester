package com.softserve.webtester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.webtester.model.Environment;
import com.softserve.webtester.service.EnvironmentService;

@Controller
@RequestMapping("/environments")
public class EnvironmentController {
	
	@Autowired
	private EnvironmentService environmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getEnvironmentsPage(Model model) {
		List<Environment> environments = environmentService.loadAll();
		model.addAttribute("environmentList", environments);
		return "environment/environmentList";
	}
	
	@RequestMapping (value = "/create", method = RequestMethod.GET)
	public String getEnvironmentCreatePage(Model model) {
				
		/*environment.setName("First");
		environment.setDbName("First");
		environment.setBaseUrl("http:\\localhost");
		environment.setDbUrl("127.0.0.1");
		environment.setDbPort("3036");
		environment.setDbUsername("first");
		environment.setDbPassword("first");*/
		
		
		model.addAttribute("pageTask", "Create");
		Environment environment = new Environment();
		environment.setTimeMultiplier(2.0f);
		model.addAttribute("environment", environment);
		return "environment/environmentCreateOrUpdate";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createEnvironment(Environment environment) {
		environmentService.save(environment);
		return "redirect:/environments";
	}
	
	@RequestMapping (value = "/{id}", method = RequestMethod.GET)
	public String getEnvironmentUpdatePage(@PathVariable("id") int id,Model model) {
		model.addAttribute("pageTask", "Update");
		Environment environment = environmentService.load(id);
		model.addAttribute("environment", environment);
		return "environment/environmentCreateOrUpdate";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateEnvironment(Environment environment) {
		environmentService.update(environment);
		return "redirect:/environments";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteEnvironment(Environment environment) {
		environmentService.delete(environment);
		return "redirect:/environments";
	}
	
	@RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
	public String checkEnvironment(@PathVariable("id") int id,Model model) {
		Environment environment = environmentService.load(id);
		
		try{
			environmentService.checkConnection(environment);
			model.addAttribute("success", " Environment: " + environment.getName()+ ", connection was checked successfully");
		} catch (Exception e){
			model.addAttribute("error", " Environment: " + environment.getName()+ " error:" +e.getMessage());
		}
		return getEnvironmentsPage(model);
	}
}
