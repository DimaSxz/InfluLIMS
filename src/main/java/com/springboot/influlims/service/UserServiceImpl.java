package com.springboot.influlims.service;

import com.springboot.influlims.dao.RoleDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.entity.UserRoleEntity;
import com.springboot.influlims.entity.enums.Role;
import com.springboot.influlims.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserRoleEntity userRoleEntity = new UserRoleEntity(user, roleDao.findByName(Role.ROLE_USER));
		user.getUserRolesEntities().add(userRoleEntity);
		userDao.save(user);
	}

	@Override
	public UserEntity findByLogin(String login) {
		return userDao.findByLogin(login);
	}
}