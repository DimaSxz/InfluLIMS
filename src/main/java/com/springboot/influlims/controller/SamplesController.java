package com.springboot.influlims.controller;

import com.springboot.influlims.dao.SampleDao;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/samples")
public class SamplesController {

	private static final String title = "Список образцов";

	@Autowired
	private Helper helper;

	@Autowired
	private SampleDao sampleDao;

	@GetMapping
	public String samples(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("samples", sampleDao.findAll());
		return "samples";
	}


}
