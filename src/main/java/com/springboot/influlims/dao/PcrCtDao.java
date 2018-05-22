package com.springboot.influlims.dao;

import com.springboot.influlims.entity.PcrCtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcrCtDao extends JpaRepository<PcrCtEntity, Long> {}
