package com.springboot.influlims.dao;

import com.springboot.influlims.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDao extends JpaRepository<SampleEntity, Long> {}
