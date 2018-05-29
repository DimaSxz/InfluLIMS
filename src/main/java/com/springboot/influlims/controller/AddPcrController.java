package com.springboot.influlims.controller;

import com.springboot.influlims.dao.*;
import com.springboot.influlims.entity.*;
import com.springboot.influlims.entity.enums.ReagentFunction;
import com.springboot.influlims.model.AddExtractionForm;
import com.springboot.influlims.model.AddPcrForm;
import com.springboot.influlims.service.FileStorageService;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.ldap.ExtendedRequest;
import javax.swing.plaf.multi.MultiFileChooserUI;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/add-pcr")
public class AddPcrController {

	private static final String title = "ПЦР";

	@Autowired
	private Helper helper;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ReagentTypeDao reagentTypeDao;

	@Autowired
	private ReagentDao reagentDao;

	@Autowired
	private ExtractionDao extractionDao;

	@Autowired
	private PcrDao pcrDao;

	@GetMapping
	public String addPcr(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("extractions", extractionDao.findAll());
		Collection<ReagentTypeEntity> reagentTypeEntities = reagentTypeDao.getAllByFunction(ReagentFunction.PCR);
		Collection<ReagentEntity> reagentEntities = new HashSet<>();
		for(ReagentTypeEntity reagentTypeEntity : reagentTypeEntities) {
			reagentEntities.addAll(reagentTypeEntity.getReagentEntities());
		}
		model.addAttribute("reagents", reagentEntities);
		model.addAttribute("addPcrForm", new AddPcrForm());
		return "add-pcr";
	}

	@PostMapping
	public String addPcr(@ModelAttribute("addPcrForm") AddPcrForm addPcrForm, BindingResult bindingResult, Model model, Principal principal) {

		UserEntity userEntity = userDao.findByLogin(principal.getName());

		String pcrReport = null;
		String pcrProtocol = null;
		MultipartFile pcrReportFile = addPcrForm.getPcrReport();
		MultipartFile pcrProtocolFile = addPcrForm.getPcrProtocol();
		if(pcrReportFile != null)
			pcrReport = fileStorageService.savePcrReportFile(pcrReportFile);
		if(pcrProtocolFile != null)
			pcrProtocol = fileStorageService.savePcrProtocolFile(pcrProtocolFile);

		ReagentEntity reagentEntity = reagentDao.getOne(addPcrForm.getReagentId());

//		TODO Validate if(result < 0)
		Long remain = reagentEntity.getRemainResource() != null ? reagentEntity.getRemainResource() : reagentEntity.getMaxResource();
		remain -= addPcrForm.getExtractions().length;
		reagentEntity.setRemainResource(remain);
		reagentDao.save(reagentEntity);

		Collection<PcrEntity> pcrEntities = new HashSet<>(addPcrForm.getExtractions().length);
		for(Long extractionId : addPcrForm.getExtractions()) {
			ExtractionEntity extractionEntity = extractionDao.getOne(extractionId);
			PcrEntity pcrEntity = new PcrEntity(userEntity, reagentEntity, extractionEntity, addPcrForm.getPcrName(), pcrProtocol, pcrReport, addPcrForm.getPcrDate());
			pcrEntities.add(pcrEntity);
		}
		pcrDao.saveAll(pcrEntities);

		return "redirect:/add-pcr?success";
	}
}
