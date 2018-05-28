package com.springboot.influlims.controller;

import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteController {

	@Autowired
	private Helper helper;

	@RequestMapping(value = {"/", "/main"})
	public String main(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "main";
	}

	@RequestMapping(value = "/samples", method = RequestMethod.GET)
	public String samples(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "samples";
	}

	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "sample";
	}
}
