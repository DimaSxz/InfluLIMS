package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDao extends JpaRepository<ProviderEntity, Long> {}
