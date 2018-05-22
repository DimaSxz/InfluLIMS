package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends JpaRepository<ProjectEntity, Long> {}
