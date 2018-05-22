package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ProjectDao;
import com.springboot.influlims.dao.ProviderDao;
import com.springboot.influlims.dao.RegionDao;
import com.springboot.influlims.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/add-sample")
public class AddSampleController {

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private RegionDao regionDao;

	private static String getSeason() {
		GregorianCalendar cal =
				(GregorianCalendar) GregorianCalendar.getInstance();

		int week = cal.get(Calendar.WEEK_OF_YEAR);
		Integer year = cal.get(Calendar.YEAR);

		return (week > 20 && week < 40)
				? year.toString()
				: String.valueOf(year - 1) + "-" + year.toString();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String addSample(ModelMap model, String success) {
		if(success != null) model.addAttribute("addSuccess", "Sample successfully added!");
		model.addAttribute("season", getSeason());
		model.addAttribute("providers", providerDao.findAll());
		model.addAttribute("projects", projectDao.findAll());
		model.addAttribute("regions", regionDao.findAll());
		return "add-sample";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addSample(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult, Model model) {
//		formValidator.validate(userForm, bindingResult);

//		userService.save(userForm);

		return "redirect:/addSample?success";
	}

}
