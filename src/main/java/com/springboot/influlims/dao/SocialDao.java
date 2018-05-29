package com.springboot.influlims.dao;

import com.springboot.influlims.entity.SocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialDao extends JpaRepository<SocialEntity, Long> {

	public SocialEntity findByExternalId(String externalId);

}
