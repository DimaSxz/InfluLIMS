package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ExtractionDao;
import com.springboot.influlims.dao.PcrDao;
import com.springboot.influlims.dao.SampleDao;
import com.springboot.influlims.entity.ExtractionEntity;
import com.springboot.influlims.entity.PcrEntity;
import com.springboot.influlims.entity.SampleEntity;
import com.springboot.influlims.service.FileStorageService;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/sample/**")
public class SampleController {

	private static final String title = "Информация об образце";

	@Autowired
	private Helper helper;

	@Autowired
	private SampleDao sampleDao;

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/sample/{id}", method = RequestMethod.GET)
	public String sample(@PathVariable("id") Long sampleId, Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		SampleEntity sample = sampleDao.getOne(sampleId);
		model.addAttribute("sample", sample);
		Collection<ExtractionEntity> extractionEntities = sample.getExtractionEntities();
		model.addAttribute("exts", extractionEntities);
		Collection<PcrEntity> pcrEntities = new HashSet<>();
		for(ExtractionEntity extractionEntity : extractionEntities) {
			pcrEntities.addAll(extractionEntity.getPcrEntities());
		}
		model.addAttribute("pcrs", pcrEntities);
		model.addAttribute("storage", fileStorageService);
		return "sample";
	}

}
