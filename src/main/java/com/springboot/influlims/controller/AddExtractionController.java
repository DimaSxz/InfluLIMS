package com.springboot.influlims.controller;

import com.springboot.influlims.dao.*;
import com.springboot.influlims.entity.*;
import com.springboot.influlims.entity.enums.ReagentFunction;
import com.springboot.influlims.model.AddExtractionForm;
import com.springboot.influlims.service.FileStorageService;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/add-extraction")
public class AddExtractionController {

	private static final String title = "Экстракция";

	@Autowired
	private Helper helper;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SampleDao sampleDao;

	@Autowired
	private ReagentTypeDao reagentTypeDao;

	@Autowired
	private ReagentDao reagentDao;

	@Autowired
	private ExtractionDao extractionDao;

	@GetMapping
	public String addExtraction(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("samples", sampleDao.findAll());
		Collection<ReagentTypeEntity> reagentTypeEntities = reagentTypeDao.getAllByFunction(ReagentFunction.EXT);
		Collection<ReagentEntity> reagentEntities = new HashSet<>();
		for(ReagentTypeEntity reagentTypeEntity : reagentTypeEntities) {
			reagentEntities.addAll(reagentTypeEntity.getReagentEntities());
		}
		model.addAttribute("reagents", reagentEntities);
		model.addAttribute("addExtractionForm", new AddExtractionForm());
		return "add-extraction";
	}

	@PostMapping
	public String addExtraction(@ModelAttribute("addExtractionForm") AddExtractionForm addExtractionForm, BindingResult bindingResult, Model model, Principal principal) {

		UserEntity userEntity = userDao.findByLogin(principal.getName());

		String fileName = null;
		MultipartFile file = addExtractionForm.getExtractionFile();
		if(file != null) {
			fileName = fileStorageService.saveExtractionFile(file);
		}


		ReagentEntity reagentEntity = reagentDao.getOne(addExtractionForm.getReagentId());

//		TODO Validate if(result < 0)
		Long remain = reagentEntity.getRemainResource() != null ? reagentEntity.getRemainResource() : reagentEntity.getMaxResource();
		remain -= addExtractionForm.getSamples().length;
		reagentEntity.setRemainResource(remain);
		reagentDao.save(reagentEntity);

		Collection<ExtractionEntity> extractionEntities = new HashSet<>(addExtractionForm.getSamples().length);
		for (Long sampleId : addExtractionForm.getSamples()) {
			SampleEntity sampleEntity = sampleDao.getOne(sampleId);
			ExtractionEntity extractionEntity = new ExtractionEntity(userEntity, reagentEntity, sampleEntity, addExtractionForm.getExtractionName(), addExtractionForm.getVolumeIn(), addExtractionForm.getVolumeOut(), fileName, addExtractionForm.getExtractionDate());
			extractionEntities.add(extractionEntity);
		}
		extractionDao.saveAll(extractionEntities);

		return "redirect:/add-extraction?success";
	}

}
