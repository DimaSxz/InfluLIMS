package com.springboot.influlims.dao;

import com.springboot.influlims.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDao extends JpaRepository<RegionEntity, Integer> {

	RegionEntity findByRegionUNID(Integer regionUNID);

	RegionEntity findByRegionName(String name);

}
