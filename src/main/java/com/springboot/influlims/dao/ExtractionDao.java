package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ExtractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionDao extends JpaRepository<ExtractionEntity, Long> {}