package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ReagentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReagentTypeDao extends JpaRepository<ReagentTypeEntity, Long> {}
