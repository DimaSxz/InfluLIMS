package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ReagentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReagentDao extends JpaRepository<ReagentEntity, Long> {
	Collection<ReagentEntity> findAllByReagentTypeId(Long reagentTypeId);
}
