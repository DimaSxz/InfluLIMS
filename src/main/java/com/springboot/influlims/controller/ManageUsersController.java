package com.springboot.influlims.controller;

import com.springboot.influlims.dao.RoleDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.dao.UserRoleDao;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.entity.UserRoleEntity;
import com.springboot.influlims.model.ManageUsersForm;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/manage-users")
public class ManageUsersController {

	private static final String title = "Сотрудники";

	@Autowired
	private Helper helper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@GetMapping
	public String manageProjects(Model model, HttpServletRequest request) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("users", userDao.findAll());
		model.addAttribute("roles", roleDao.findAll());
		return "admin/users";
	}

	@PostMapping
	public String manageProjects(@ModelAttribute("manageUsersForm")ManageUsersForm manageUsersForm, BindingResult bindingResult, Model model, Principal principal) {

		switch(manageUsersForm.getAction()) {
			case "update":
				if(manageUsersForm.getRoleId() == null) return "redirect:/manage-users?error";
				UserRoleEntity userRoleEntity = userRoleDao.findByUserId(manageUsersForm.getUserId());
				userRoleEntity.setRoleEntity(roleDao.getOne(manageUsersForm.getRoleId()));
				userRoleDao.save(userRoleEntity);
				break;
			case "delete":
				UserEntity userEntity = userDao.getOne(manageUsersForm.getUserId());
				userDao.delete(userEntity);
		}

		return "redirect:/manage-users?success";
	}

}
