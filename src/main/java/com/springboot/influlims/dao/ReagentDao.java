package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ReagentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReagentDao extends JpaRepository<ReagentEntity, Long> {}
