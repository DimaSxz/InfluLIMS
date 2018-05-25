package com.springboot.influlims.dao;

import com.springboot.influlims.entity.ReagentEntity;
import com.springboot.influlims.entity.ReagentTypeEntity;
import com.springboot.influlims.entity.enums.ReagentFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReagentTypeDao extends JpaRepository<ReagentTypeEntity, Long> {
	Collection<ReagentTypeEntity> getAllByFunction(ReagentFunction reagentFunction);
}
