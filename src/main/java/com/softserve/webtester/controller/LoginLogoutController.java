package com.softserve.webtester.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.User;
import com.softserve.webtester.service.UserService;

/**
 * Handles and retrieves login, logout and home pages.
 * 
 * @author Taras Oglabyak
 * 
 */
@Controller
public class LoginLogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) boolean error,
	    		    @RequestParam(value = "logout", required = false) boolean logout,
	    		    ModelMap model) {
	if (error) {
	    model.addAttribute("error", "Invalid username and password!");
	}
	if (logout) {
	    model.addAttribute("msg", "You've been logged out successfully.");
	}
	return "login";
    }

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
	ModelAndView model = new ModelAndView("home");
	String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	User user = userService.load(userId);
	model.addObject("user", user);
	model.addObject("userId", userId);
	return model;
    }

    // TODO Taras O. (maybe you should use spring security XML configuration)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return "redirect:/login?logout=true";
    }
}