package com.springboot.influlims.controller;

import com.springboot.influlims.dao.SocialDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.SocialEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.service.VkAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vk-auth")
public class VkAuthController {

	@Autowired
	private VkAuthService vkAuthService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SocialDao socialDao;

	@GetMapping
	public String vkAuth(String code, String error, String error_description) {
		if(error != null)
			return "redirect:/login?error&vk-error";
		vkAuthService.authorize(code);
		String email = vkAuthService.getEmail();
		String fullName = vkAuthService.getFullName();
		String phone = vkAuthService.getPhone();
//TODO
		UserEntity userEntity = new UserEntity("???", "???", fullName, email, phone, "");

		userDao.save(userEntity);

		String accessToken = vkAuthService.getAccessToken();
		String networkName = vkAuthService.getNetworkName();

		SocialEntity socialEntity = new SocialEntity(networkName, accessToken, userEntity);
		socialDao.save(socialEntity);

		return "redirect:/main";
	}

}
//9be764726f12a154652ed18896dd8a5c0fc62e091b2b3b0cd30c84eed961489c9ccaf04f250cf6018a60c
//8394f88f548fa6e4e9dcc79a8ab79f569b898ff161bf50b170c327920f50a9902d755f953fb19c3427557
