package com.springboot.influlims.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String host = "localhost:8080/";

	private String checkAuth(HttpServletRequest request) {
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			String redirect = request.getHeader("referer");
			if(redirect != null && redirect.startsWith(host)) return "redirect:" + redirect;
			return "redirect:/main";
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, String error, String logout, HttpServletRequest request) {

		String checkAuthorisation = checkAuth(request);
		if(checkAuthorisation != null) return checkAuthorisation;

		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect.");
		}

		if (logout != null) {
			model.addAttribute("message", "Logged out successfully.");
		}

		return "login";
	}

}
