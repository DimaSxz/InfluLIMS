package com.springboot.influlims.entity.supercalsses;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class RegionUserDependentEntity extends UserDependentEntity {

	private static final long serialVersionUID = 1997662895999523253L;

	public RegionUserDependentEntity() {}

	public RegionUserDependentEntity(Long userId, Integer regionUNID) {
		super(userId);
		this.regionUNID = regionUNID;
	}

	@Basic
	@Column(name = "region_unid", nullable = false, insertable = false, updatable = false, columnDefinition = "SMALLINT(3) UNSIGNED")
	protected Integer regionUNID;

	public Integer getRegionUNID() {
		return regionUNID;
	}

	public void setRegionUNID(Integer regionUNID) {
		this.regionUNID = regionUNID;
	}
}
