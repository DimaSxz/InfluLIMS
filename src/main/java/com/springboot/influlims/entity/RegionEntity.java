package com.springboot.influlims.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "region")
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
public class RegionEntity implements Serializable {

	private static final long serialVersionUID = 1106590946331269647L;

	RegionEntity() {}

	public RegionEntity(Integer regionUNID, String regionName) {
		this.regionUNID = regionUNID;
		this.regionName = regionName;
	}

	@OneToMany(mappedBy = "regionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProviderEntity> providerEntities = new ArrayList<>();

	@OneToMany(mappedBy = "regionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PatientEntity> patientEntities = new ArrayList<>();

	@Id
	@Column(name = "region_unid", nullable = false, columnDefinition = "SMALLINT(3) UNSIGNED")
	private Integer regionUNID;

	@Basic
	@Column(nullable = false)
	private String regionName;

	public Integer getRegionUNID() {
		return regionUNID;
	}

	public void setRegionUNID(Integer regionUNID) {
		this.regionUNID = regionUNID;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Collection<ProviderEntity> getProviderEntities() {
		return providerEntities;
	}

	public void setProviderEntities(Collection<ProviderEntity> providerEntities) {
		this.providerEntities = providerEntities;
	}

	public Collection<PatientEntity> getPatientEntities() {
		return patientEntities;
	}

	public void setPatientEntities(Collection<PatientEntity> patientEntities) {
		this.patientEntities = patientEntities;
	}
}
