package com.springboot.influlims.controller;

import com.springboot.influlims.dao.ProviderDao;
import com.springboot.influlims.dao.RegionDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.ProviderEntity;
import com.springboot.influlims.entity.RegionEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.model.ManageProvidersForm;
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

@Controller
@RequestMapping("/manage-providers")
public class ManageProvidersController {

	@Autowired
	private Helper helper;

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private UserDao userDao;

	@GetMapping
	public String manageProviders(Model model) {
		model.addAttribute("season", helper.getSeason());
		model.addAttribute("providers", providerDao.findAll());
		model.addAttribute("regions", regionDao.findAll());
		return "admin/providers";
	}

	@PostMapping
	public String manageProviders(@ModelAttribute("manageProvidersForm") ManageProvidersForm manageProvidersForm, BindingResult bindingResult, Model model, Principal principal) {

		UserEntity userEntity = userDao.findByLogin(principal.getName());
		RegionEntity regionEntity = regionDao.findByRegionUNID(manageProvidersForm.getRegionId());

		switch (manageProvidersForm.getAction()) {
			case "add":
				ProviderEntity providerEntity = new ProviderEntity(manageProvidersForm.getProviderName(), manageProvidersForm.getContactPerson(), manageProvidersForm.getContacts(), userEntity, regionEntity);
				providerDao.save(providerEntity);
				break;
			case "update":
				ProviderEntity providerEntity1 = providerDao.getOne(manageProvidersForm.getProviderId());
				if(manageProvidersForm.getProviderName() != null)
					providerEntity1.setProviderName(manageProvidersForm.getProviderName());
				if(manageProvidersForm.getContactPerson() != null)
					providerEntity1.setContactPerson(manageProvidersForm.getContactPerson());
				if(manageProvidersForm.getContacts() != null)
					providerEntity1.setContacts(manageProvidersForm.getContacts());
				if(manageProvidersForm.getRegionId() != null)
					providerEntity1.setRegionEntity(regionEntity);
				providerDao.save(providerEntity1);
				break;
			case "delete":
				ProviderEntity providerEntity2 = providerDao.getOne(manageProvidersForm.getProviderId());
				providerDao.delete(providerEntity2);
		}

		return "redirect:/manage-providers?success";
	}

}
