package com.springboot.influlims.controller;

import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private static final String title = "Новости";

	@Autowired
	private Helper helper;

	@RequestMapping(value = {"/", "/main"})
	public String main(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		return "main";
	}

}
