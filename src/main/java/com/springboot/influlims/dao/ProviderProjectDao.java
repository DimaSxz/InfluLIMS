package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ProjectEntity;
import com.springboot.influlims.entity.ProviderEntity;
import com.springboot.influlims.entity.ProviderProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderProjectDao extends JpaRepository<ProviderProjectEntity, Long> {

	ProviderProjectEntity getByProviderEntityAndProjectEntity(ProviderEntity providerEntity, ProjectEntity projectEntity);

	ProviderProjectEntity getByProviderIdAndProjectId(Long providerId, Long ProjectId);

}