package com.springboot.influlims.controller;

import com.springboot.influlims.dao.RoleDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/manage-users")
public class ManageUsersController {

	@Autowired
	private Helper helper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@GetMapping
	public String manageProjects(Model model, HttpServletRequest request) {
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("users", userDao.findAll());
		model.addAttribute("roles", roleDao.findAll());
		return "admin/projects";
	}

}
