package com.softserve.webtester.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * Retrieves login page.
     * 
     * @param error error flag, if true error message will be shown
     * @param logout logout flag, if true logout message will be shown
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "logout", required = false) boolean logout, Model model) {
        if (error) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (logout) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }

    /**
     * Retrieves home page.
     * 
     * @param session HTTP session instance
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.load(userId);
        model.addAttribute("user", user);
        return "home";
    }

    /**
     * Handles logout process.
     * 
     * @param request {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance
     * @return redirect to login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}