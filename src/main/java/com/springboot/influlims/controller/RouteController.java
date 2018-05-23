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

	@RequestMapping(value = "/manage-users", method = RequestMethod.GET)
	public String manageEmployees(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "admin/employees";
	}

	@RequestMapping(value = "/manage-providers", method = RequestMethod.GET)
	public String manageProviders(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "admin/providers";
	}

	@RequestMapping(value = "/manage-projects", method = RequestMethod.GET)
	public String manageProjects(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "admin/projects";
	}

	@RequestMapping(value = "/manage-reagents", method = RequestMethod.GET)
	public String manageReagents(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "admin/reagents";
	}



	@RequestMapping(value = "/add-pcr", method = RequestMethod.GET)
	public String addPcr(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "add-pcr";
	}

	@RequestMapping(value = "/samples", method = RequestMethod.GET)
	public String samples(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "samples";
	}
}
