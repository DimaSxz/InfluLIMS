package com.springboot.influlims.dao;

import com.springboot.influlims.entity.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessDao extends JpaRepository<AccessEntity, Long> {}
