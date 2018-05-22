package com.springboot.influlims.service.interfaces;

import com.springboot.influlims.entity.UserEntity;

public interface UserService {

	void save(UserEntity userEntity);

	UserEntity findByLogin(String username);

}
