package com.springboot.influlims.controller;

import com.springboot.influlims.dao.*;
import com.springboot.influlims.entity.*;
import com.springboot.influlims.entity.enums.Gender;
import com.springboot.influlims.entity.enums.Vaccine;
import com.springboot.influlims.model.AddSampleForm;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("/add-sample")
public class AddSampleController {

	private static final String title = "Регистрация образца";

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ProviderProjectDao providerProjectDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SampleDao sampleDao;

	@Autowired
	private ContainerDao containerDao;

	@Autowired
	private Helper helper;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public String addSample(ModelMap model, String success) {
		model.addAttribute("title", title);
		if(success != null) model.addAttribute("addSuccess", "Sample successfully added!");
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("providers", providerDao.findAll());
		model.addAttribute("projects", projectDao.findAll());
		model.addAttribute("regions", regionDao.findAll());
		model.addAttribute("addSampleForm", new AddSampleForm());
		return "add-sample";
	}

	//		TODO validate
//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public String addSample(@ModelAttribute("addSampleForm") AddSampleForm addSampleForm, BindingResult bindingResult, Model model, Principal principal) {

		UserEntity userEntity = userDao.findByLogin(principal.getName());

		RegionEntity regionEntity = regionDao.findByRegionUNID(addSampleForm.getRegionUNID());

		ProviderProjectEntity providerProjectEntity = providerProjectDao.getByProviderIdAndProjectId(addSampleForm.getProviderId(), addSampleForm.getProjectId());

		PatientEntity patientEntity = new PatientEntity(addSampleForm.getPatientName(), addSampleForm.getPatientAge(), addSampleForm.getPatientMonths(), addSampleForm.getPatientDOB(), Gender.valueOf(addSampleForm.getPatientGender()), Vaccine.valueOf(addSampleForm.getIsVaccinated()), userEntity, regionEntity);
		patientDao.save(patientEntity);

		Boolean pm = false;
		String samplePm = addSampleForm.getSamplePm();
		if(samplePm != null) {
			pm = true;
			addSampleForm.setSampleType(samplePm);
		}

		SampleEntity sampleEntity = new SampleEntity(userEntity, patientEntity, providerProjectEntity, addSampleForm.getLabNum(), addSampleForm.getCaseHistory(), addSampleForm.getNumInProject(), addSampleForm.getExternalNum(), addSampleForm.getSampleFunc(), pm, addSampleForm.getSampleType(), addSampleForm.getVolumeIn(), addSampleForm.getDateReceipt(), addSampleForm.getDateDisease(), addSampleForm.getDateCollection(), addSampleForm.getSeason(), addSampleForm.getNotes());

		Boolean isPropered = addSampleForm.getIsPropered() != null ? true : false;
		Boolean isMarked = addSampleForm.getIsMarked() != null ? true : false;
		Boolean markDirectionEquals = addSampleForm.getMarkDirectionEquals() != null ? true : false;
		Boolean contamination = addSampleForm.getContamination() != null ? false : true;
		Boolean isEnough = addSampleForm.getIsEnough() != null ? true : false;


		sampleEntity.setContainerEntity(new ContainerEntity(sampleEntity, isPropered, isMarked, markDirectionEquals, contamination, isEnough));

		sampleDao.save(sampleEntity);
//		formValidator.validate(userForm, bindingResult);
		return "redirect:/add-sample?success";
	}

}
