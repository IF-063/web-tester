package com.softserve.webtester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.webtester.model.User;
import com.softserve.webtester.service.UserService;

/**
 * Handles and retrieves user-account page depending on the URI template. A user must be log-in first he 
 * can access this page.
 * 
 * @author Taras Oglabyak
 * @version 1.2
 */
@Controller
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    @Qualifier("applicationAuthenticationManager")
//    private AuthenticationManager authenticationManager;
//    
//    @Autowired
//    @Qualifier("sessionRegistry")
//    private SessionRegistry sessionRegistry;

    /**
     * Retrieves user-account page. 
     * 
     * @param success using to detect successfully updating information about the user.
     * @return ModelAndView instance with 'account' view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUserAccountPage(@RequestParam(value = "success", required = false) String success) {
	ModelAndView modelAndView = new ModelAndView("account");
	if (success != null) {
	    modelAndView.addObject("success", "Account has been successfully updated!");
	}
	String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	User user = userService.load(userId);
	modelAndView.addObject("user", user);
	return modelAndView;
    }

    /**
     * Handles changes of user-account information and saves it to the database. In case of validation errors
     * forwards user to user-account page and shows validation result's messages, otherwise redirects the user to 
     * user-account page with 'success' request parameter.
     * 
     * @param user {@link User} instance should be updated
     * @param result BindingResult instance for result of validation 
     * @return name of view will be returned
     */
    @RequestMapping(method = RequestMethod.POST)
    public String editAccount(@Validated @ModelAttribute("user") User user, BindingResult result) {
	if (result.hasErrors()) {
	    return "account";
	}
	Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
	String currentUsername = currentAuthentication.getName();
	user.setId(Integer.parseInt(currentUsername));
	userService.update(user);
/*
	if (!currentUsername.equals(user.getUsername())) {
	   // new SecurityContextLogoutHandler().logout(request, response, currentAuthentication);
	    
	    Object currentPrincipal = currentAuthentication.getPrincipal();
	    List<SessionInformation> userSessions = sessionRegistry.getAllSessions(currentPrincipal, false);
	    String currentSessionId = request.getSession().getId();
	    System.out.println("currentSessionId: "+currentSessionId);
	    System.out.println("All:");
	    userSessions.forEach(usession -> System.out.println(usession.getSessionId()));
	    for (SessionInformation userSession : userSessions) {
		if (!currentSessionId.equals(userSession.getSessionId())) {
		    System.out.println("DELETED: "+userSession.getSessionId());
		    userSession.expireNow();
		}
	    }
	            
	    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
		user.getUsername(), user.getPassword());
	    Authentication authentication = authenticationManager.authenticate(authRequest);
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    HttpSession session = request.getSession(true);
	    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	}*/
	return "redirect:/account?success";
    }
}