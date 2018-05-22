package com.springboot.influlims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.influlims.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

	UserEntity findByLogin(String login);

}