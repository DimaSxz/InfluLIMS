package com.springboot.influlims.controller;

import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String host = "localhost:8080/";

	@Autowired
	private Helper helper;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public String login(Model model, String error, String logout, HttpServletRequest request) {

		String checkAuthorisation = helper.checkAuth(request);
		if(checkAuthorisation != null) return checkAuthorisation;

		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect.");
		}

		if (logout != null) {
			model.addAttribute("message", "Logged out successfully.");
		}

		return "login";
	}

	@PostMapping
	public String login() {
		return null;
	}

}
