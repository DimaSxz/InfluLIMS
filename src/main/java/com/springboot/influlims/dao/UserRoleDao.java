package com.springboot.influlims.dao;

import com.springboot.influlims.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRoleEntity, Long> {

	public UserRoleEntity findByUserId(Long userId);

}
