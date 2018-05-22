package com.springboot.influlims.service;

import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.RoleEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UserEntity user = userDao.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


		for(UserRoleEntity userRoleEntity : user.getUserRolesEntities()) {
			RoleEntity role = userRoleEntity.getRoleEntity();
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().name()));
		}

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);

	}
}
