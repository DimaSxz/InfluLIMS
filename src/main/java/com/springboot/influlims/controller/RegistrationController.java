package com.springboot.influlims.controller;

import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.service.SecurityServiceImpl;
import com.springboot.influlims.service.interfaces.SecurityService;
import com.springboot.influlims.service.interfaces.UserService;
import com.springboot.influlims.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private static final String host = "localhost:8080/";

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityServiceImpl securityService;

	@Autowired
	private UserValidator userValidator;

	private String checkAuth(HttpServletRequest request) {
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			String redirect = request.getHeader("referer");
			if(redirect != null && redirect.startsWith(host)) return "redirect:" + redirect;
			return "redirect:/main";
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String registration(Model model, HttpServletRequest request) {

		String checkAuthorisation = checkAuth(request);
		if(checkAuthorisation != null) return checkAuthorisation;

		model.addAttribute("userForm", new UserEntity());

		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		String unHashedPassword = userForm.getPassword();

		userService.save(userForm);

		securityService.autoLogin(userForm.getLogin(), unHashedPassword);

		return "redirect:/main";
	}

}
