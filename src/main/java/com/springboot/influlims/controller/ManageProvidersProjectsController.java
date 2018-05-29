package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ProjectDao;
import com.springboot.influlims.dao.ProviderDao;
import com.springboot.influlims.dao.ProviderProjectDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.ProjectEntity;
import com.springboot.influlims.entity.ProviderEntity;
import com.springboot.influlims.entity.ProviderProjectEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.model.ManageProvidersProjectsForm;
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
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/manage-providers-projects")
public class ManageProvidersProjectsController {

	private static final String title = "Поставщики - Проекты";

	@Autowired
	private Helper helper;

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProviderProjectDao providerProjectDao;

	@Autowired
	private UserDao userDao;

	@GetMapping
	public String manageProvidersProjects(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("providers", providerDao.findAll());
		model.addAttribute("projects", projectDao.findAll());
		return "admin/provider-project";
	}

	@PostMapping
	public String manageProvidersProjects(@ModelAttribute("manageProvidersProjectsForm") ManageProvidersProjectsForm manageProvidersProjectsForm, BindingResult bindingResult, Model model, Principal principal) {
		UserEntity userEntity = userDao.findByLogin(principal.getName());
		ProviderEntity providerEntity = providerDao.getOne(manageProvidersProjectsForm.getProviderId());
		Collection<ProviderProjectEntity> providerProjectEntities = new HashSet<>(manageProvidersProjectsForm.getProjects().length);
		for(Long projectId : manageProvidersProjectsForm.getProjects()) {
			ProjectEntity projectEntity = projectDao.getOne(projectId);
			ProviderProjectEntity providerProjectEntity = new ProviderProjectEntity(userEntity,providerEntity,projectEntity);
			providerProjectEntities.add(providerProjectEntity);
		}
		providerProjectDao.saveAll(providerProjectEntities);
		return "redirect:/manage-providers-projects";
	}

}
