package com.springboot.influlims.controller;

import com.springboot.influlims.dao.SampleDao;
import com.springboot.influlims.model.AddExtractionForm;
import com.springboot.influlims.model.AddSampleForm;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/add-extraction")
public class AddExtractionController {

	@Autowired
	private Helper helper;

	@Autowired
	protected SampleDao sampleDao;

	@GetMapping
	public String addExtraction(Model model) {
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("samples", sampleDao.findAll());
		model.addAttribute("addExtractionForm", new AddExtractionForm());
		return "add-extraction";
	}

	@PostMapping
	public String addExtraction(@ModelAttribute("addSampleForm") AddSampleForm addSampleForm, BindingResult bindingResult, Model model, Principal principal) {


		return "add-extraction";
	}

}
