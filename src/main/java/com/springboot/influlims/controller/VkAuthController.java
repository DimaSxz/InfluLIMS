package com.springboot.influlims.controller;

import com.springboot.influlims.dao.SocialDao;
import com.springboot.influlims.entity.SocialEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.service.SecurityServiceImpl;
import com.springboot.influlims.service.UserServiceImpl;
import com.springboot.influlims.service.VkAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vk-auth")
public class VkAuthController {

	@Autowired
	private VkAuthService vkAuthService;

	@Autowired
	private SocialDao socialDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SecurityServiceImpl securityService;

	@Autowired
	private UserServiceImpl userService;

	private String soult = "_!@#$%^&*(сОлЬ)*&^%$#@!_";

	@GetMapping
	public String vkAuth(String code, String error, String error_description) {
		if(error != null)
			return "redirect:/login?error&vk-error=" + error_description;
		vkAuthService.authorize(code);

		String vkUserId = vkAuthService.getUserId().toString();

		String login;
		String unHashedPassword;

		SocialEntity existsVkUser = socialDao.findByExternalId(vkUserId);
		if(existsVkUser == null) {
			String email = vkAuthService.getEmail();
			String fullName = vkAuthService.getFullName();
			String phone = vkAuthService.getPhone();
			String networkName = vkAuthService.getNetworkName();
			login = soult + vkUserId + vkAuthService.getClientId() + vkAuthService.getClientSecret();
			unHashedPassword = soult + vkUserId + vkAuthService.getScope() + vkAuthService.getClientSecret();
			UserEntity userEntity = new UserEntity(login, unHashedPassword, fullName, email, phone, "");
			userService.save(userEntity);

			SocialEntity socialEntity = new SocialEntity(networkName, vkUserId, userEntity);
			socialDao.save(socialEntity);
		} else {
			login = soult + vkUserId + vkAuthService.getClientId() + vkAuthService.getClientSecret();
			unHashedPassword = soult + vkUserId + vkAuthService.getScope() + vkAuthService.getClientSecret();
		}

		securityService.autoLogin(login, unHashedPassword);
		return "redirect:/main";
	}

}