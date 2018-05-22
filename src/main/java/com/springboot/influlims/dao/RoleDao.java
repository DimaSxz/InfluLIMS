package com.springboot.influlims.dao;

import com.springboot.influlims.entity.RoleEntity;
import com.springboot.influlims.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByName(Role role);

}