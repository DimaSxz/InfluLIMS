package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ProviderProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderProjectDao extends JpaRepository<ProviderProjectEntity, Long> {}