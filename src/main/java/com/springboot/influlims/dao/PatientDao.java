package com.springboot.influlims.dao;

import com.springboot.influlims.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Long> {}
