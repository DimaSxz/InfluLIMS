package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.RegionUserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "provider")
public class ProviderEntity extends RegionUserDependentEntity {

	private static final long serialVersionUID = -1813896184358460758L;

	ProviderEntity() {}

	public ProviderEntity(String providerName, UserEntity userEntity, RegionEntity regionEntity) {
		super(userEntity.getId(), regionEntity.getRegionUNID());
		this.providerName = providerName;
		this.userEntity = userEntity;
		this.regionEntity = regionEntity;
	}

	public ProviderEntity(String providerName, String contactPerson, String contacts, UserEntity userEntity, RegionEntity regionEntity) {
		super(userEntity.getId(), regionEntity.getRegionUNID());
		this.providerName = providerName;
		this.contactPerson = contactPerson;
		this.contacts = contacts;
		this.userEntity = userEntity;
		this.regionEntity = regionEntity;
	}

	@OneToMany(mappedBy = "providerEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProviderProjectEntity> providerProjectEntities = new ArrayList<>();


	@Basic
	@Column(nullable = false)
	private String providerName;

	@Basic
	@Column
	private String contactPerson;

	@Basic
	@Column(columnDefinition = "LONGTEXT", length = -1)
	private String contacts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_providers_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_unid", foreignKey = @ForeignKey(name = "FK_providers_region", foreignKeyDefinition = "FOREIGN KEY (region_unid) REFERENCES region(region_unid) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private RegionEntity regionEntity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "provider_patient",
			joinColumns = @JoinColumn(
					name = "provider_id",
					nullable = false,
					foreignKey = @ForeignKey(
							name = "FK_providers_patient",
							foreignKeyDefinition = "FOREIGN KEY (provider_id) REFERENCES provider(id) ON DELETE RESTRICT ON UPDATE CASCADE"
					)
			),
			inverseJoinColumns = @JoinColumn(
					name = "patient_id",
					nullable = false,
					foreignKey = @ForeignKey(
							name = "FK_patients_provider",
							foreignKeyDefinition = "FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE RESTRICT ON UPDATE CASCADE"
					)
			)
	)
	public Collection<PatientEntity> patientEntities = new ArrayList<>();

	public Collection<PatientEntity> getPatientEntities() {
		return patientEntities;
	}

	public void setPatientEntities(Collection<PatientEntity> patientEntities) {
		this.patientEntities = patientEntities;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public RegionEntity getRegionEntity() {
		return regionEntity;
	}

	public void setRegionEntity(RegionEntity regionEntity) {
		this.regionEntity = regionEntity;
	}

	public Collection<ProviderProjectEntity> getProviderProjectEntities() {
		return providerProjectEntities;
	}

	public void setProviderProjectEntities(Collection<ProviderProjectEntity> providerProjectEntities) {
		this.providerProjectEntities = providerProjectEntities;
	}
}
