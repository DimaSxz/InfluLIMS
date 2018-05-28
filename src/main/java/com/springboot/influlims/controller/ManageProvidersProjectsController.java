package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ProjectDao;
import com.springboot.influlims.dao.ProviderDao;
import com.springboot.influlims.dao.ProviderProjectDao;
import com.springboot.influlims.model.ManageReagentsForm;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/manage-providers-projects")
public class ManageProvidersProjectsController {

	@Autowired
	private Helper helper;

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProviderProjectDao providerProjectDao;

	@GetMapping
	public String manageProvidersProjects(Model model) {
		model.addAttribute("season", helper.getSeason());
		return "admin/providers-project";
	}

	@PostMapping
	public String manageProvidersProjects(@ModelAttribute("manageReagentsForm") ManageReagentsForm manageReagentsForm, BindingResult bindingResult, Model model, Principal principal) {
		model.addAttribute("season", helper.getSeason());

		return "redirect:/manage-providers-projects";
	}

}
