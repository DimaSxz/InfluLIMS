package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ProjectDao;
import com.springboot.influlims.entity.ProjectEntity;
import com.springboot.influlims.model.ManageProjectsForm;
import com.springboot.influlims.model.ManageUsersForm;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/manage-projects")
public class ManageProjectsController {

	private static final String title = "Проекты";

	@Autowired
	private Helper helper;

	@Autowired
	private ProjectDao projectDao;

	@GetMapping
	public String manageProjects(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("projects", projectDao.findAll());
		return "admin/projects";
	}

	@PostMapping
	public String manageProjects(@ModelAttribute("manageProjects") ManageProjectsForm manageProjectsForm, BindingResult bindingResult, Model model, Principal principal) {

		ProjectEntity projectEntity = projectDao.getOne(manageProjectsForm.getProjectId());
		switch (manageProjectsForm.getAction()) {
			case "update" :
				if(manageProjectsForm.getProjectName() == null) return "redirect:/manage-projects?error";
				projectEntity.setProjectName(manageProjectsForm.getProjectName());
				projectDao.save(projectEntity);
				break;
			case "delete" :
				projectDao.delete(projectEntity);
		}

		return "redirect:/manage-projects?success";
	}

}
