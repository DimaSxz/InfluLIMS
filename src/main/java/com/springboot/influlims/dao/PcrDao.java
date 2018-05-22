package com.springboot.influlims.dao;

import com.springboot.influlims.entity.PcrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcrDao extends JpaRepository<PcrEntity, Long> {}
