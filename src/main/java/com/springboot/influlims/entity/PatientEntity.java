package com.springboot.influlims.entity;

import com.springboot.influlims.entity.enums.Gender;
import com.springboot.influlims.entity.enums.Vaccine;
import com.springboot.influlims.entity.supercalsses.RegionUserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "patient")
public class PatientEntity extends RegionUserDependentEntity {

	private static final long serialVersionUID = 8991608775951544971L;

	PatientEntity() {}

	public PatientEntity(Short patientAge, Gender patientGender, UserEntity userEntity, RegionEntity regionEntity) {
		super(userEntity.getId(), regionEntity.getRegionUNID());
		this.patientAge = patientAge;
		this.patientGender = patientGender;
		this.userEntity = userEntity;
		this.regionEntity = regionEntity;
	}

	@OneToMany(mappedBy = "patientEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<SampleEntity> sampleEntities = new ArrayList<>();

	public PatientEntity(String patientName, Short patientAge, Byte patientMonths, Date patiendDOB, Gender patientGender, Vaccine isVaccinated, UserEntity userEntity, RegionEntity regionEntity) {
		super(userEntity.getId(), regionEntity.getRegionUNID());
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientMonths = patientMonths;
		this.patiendDOB = patiendDOB;
		this.patientGender = patientGender;
		this.isVaccinated = isVaccinated;
		this.userEntity = userEntity;
		this.regionEntity = regionEntity;
	}

	@Basic
	@Column
	private String patientName;

	@Basic
	@Column(nullable = false, columnDefinition = "TINYINT(3) UNSIGNED")
	private Short patientAge;

	@Basic
	@Column(columnDefinition = "TINYINT(2) UNSIGNED DEFAULT 0")
	private Byte patientMonths;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "patient_dob")
	private Date patiendDOB;

	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1, columnDefinition = "VARCHAR(1) DEFAULT 'X'")
	private Gender patientGender;

	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1, columnDefinition = "VARCHAR(1) DEFAULT 'X'")
	private Vaccine isVaccinated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_patients_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_unid", foreignKey = @ForeignKey(name = "FK_patients_region", foreignKeyDefinition = "FOREIGN KEY (region_unid) REFERENCES region(region_unid) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private RegionEntity regionEntity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "provider_patient",
			joinColumns = @JoinColumn(
					name = "patient_id",
					nullable = false,
					foreignKey = @ForeignKey(
							name = "FK_patients_provider",
							foreignKeyDefinition = "FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE RESTRICT ON UPDATE CASCADE"
					)
			),
			inverseJoinColumns = @JoinColumn(
					name = "provider_id",
					nullable = false,
					foreignKey = @ForeignKey(
							name = "FK_providers_patient",
							foreignKeyDefinition = "FOREIGN KEY (provider_id) REFERENCES provider(id) ON DELETE RESTRICT ON UPDATE CASCADE"
					)
			)
	)
	public Collection<ProviderEntity> providerEntities = new ArrayList<>();

	public Collection<ProviderEntity> getProviderEntities() {
		return providerEntities;
	}

	public void setProviderEntities(Collection<ProviderEntity> providerEntities) {
		this.providerEntities = providerEntities;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Short getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Short patientAge) {
		this.patientAge = patientAge;
	}

	public Byte getPatientMonths() {
		return patientMonths;
	}

	public void setPatientMonths(Byte patientMonths) {
		this.patientMonths = patientMonths;
	}

	public Date getPatiendDOB() {
		return patiendDOB;
	}

	public void setPatiendDOB(Date patiendDOB) {
		this.patiendDOB = patiendDOB;
	}

	public Gender getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(Gender patientGender) {
		this.patientGender = patientGender;
	}

	public Vaccine getVaccinated() {
		return isVaccinated;
	}

	public void setVaccinated(Vaccine vaccinated) {
		isVaccinated = vaccinated;
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
}
