package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ReagentDao;
import com.springboot.influlims.dao.ReagentTypeDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.ProjectEntity;
import com.springboot.influlims.entity.ReagentEntity;
import com.springboot.influlims.entity.ReagentTypeEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.entity.enums.ReagentFunction;
import com.springboot.influlims.model.ManageProjectsForm;
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
import java.util.Random;

@Controller
@RequestMapping("/manage-reagents")
public class ManageReagentsController {

	@Autowired
	private Helper helper;

	@Autowired
	private ReagentTypeDao reagentTypeDao;

	@Autowired
	private ReagentDao reagentDao;

	@Autowired
	private UserDao userDao;

	@GetMapping
	public String manageReagents(Model model) {
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("reagentTypes", reagentTypeDao.findAll());
		return "admin/reagents";
	}

	@PostMapping
	public String manageProjects(@ModelAttribute("manageReagentsForm") ManageReagentsForm manageReagentsForm, BindingResult bindingResult, Model model, Principal principal) {

		UserEntity userEntity = userDao.findByLogin(principal.getName());

		switch (manageReagentsForm.getAction()) {
			case "add" :
				ReagentTypeEntity reagentTypeEntity;
				if(manageReagentsForm.getFunction().equals("EXT"))
					 reagentTypeEntity = new ReagentTypeEntity(manageReagentsForm.getReagentName(), userEntity) ;
				else reagentTypeEntity = new ReagentTypeEntity(manageReagentsForm.getReagentName(), "", "", userEntity);
				reagentTypeDao.save(reagentTypeEntity);
				ReagentEntity reagentEntity = new ReagentEntity(userEntity, reagentTypeEntity, String.valueOf(System.currentTimeMillis()), 999L);
				break;
			case "update" :
				if(manageReagentsForm.getReagentName() == null) return "redirect:/manage-reagents?error";
				ReagentTypeEntity reagentTypeEntity1 = reagentTypeDao.getOne(manageReagentsForm.getReagentId());
				reagentTypeEntity1.setFunction(ReagentFunction.valueOf(manageReagentsForm.getFunction()));
				reagentTypeEntity1.setReagentName(manageReagentsForm.getReagentName());
				reagentTypeDao.save(reagentTypeEntity1);
				break;
			case "delete" :
				ReagentTypeEntity reagentTypeEntity2 = reagentTypeDao.getOne(manageReagentsForm.getReagentId());
				reagentTypeDao.delete(reagentTypeEntity2);
		}

		return "redirect:/manage-reagents?success";
	}


}
