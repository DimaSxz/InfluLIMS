package com.springboot.influlims.entity;

import com.springboot.influlims.entity.supercalsses.UserDependentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "sample")
public class SampleEntity extends UserDependentEntity {

	private static final long serialVersionUID = 8318357705847414824L;

	SampleEntity() {}

	public SampleEntity(UserEntity userEntity, PatientEntity patientEntity, ProviderProjectEntity providerProjectEntity, String labNum, Boolean samplePm, String sampleType, Integer volumeIn, Date dateReceipt, Date dateDisease, Date dateCollection, String season) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.patientId = patientEntity.getId();
		this.patientEntity = patientEntity;
		this.providerProjectId = providerProjectEntity.getId();
		this.providerProjectEntity = providerProjectEntity;
		this.labNum = labNum;
		this.samplePm = samplePm;
		this.sampleType = sampleType;
		this.volumeIn = volumeIn;
		this.dateReceipt = dateReceipt;
		this.dateDisease = dateDisease;
		this.dateCollection = dateCollection;
		this.season = season;
	}

	public SampleEntity(UserEntity userEntity, PatientEntity patientEntity, ProviderProjectEntity providerProjectEntity, String labNum, Long caseHistory, Long numInProject, String externalNum, String sampleFunc, Boolean samplePm, String sampleType, Integer volumeIn, Date dateReceipt, Date dateDisease, Date dateCollection, String season, String notes) {
		super(userEntity.getId());
		this.userEntity = userEntity;
		this.patientId = patientEntity.getId();
		this.patientEntity = patientEntity;
		this.providerProjectId = providerProjectEntity.getId();
		this.providerProjectEntity = providerProjectEntity;
		this.labNum = labNum;
		this.caseHistory = caseHistory;
		this.numInProject = numInProject;
		this.externalNum = externalNum;
		this.sampleFunc = sampleFunc;
		this.samplePm = samplePm;
		this.sampleType = sampleType;
		this.volumeIn = volumeIn;
		this.dateReceipt = dateReceipt;
		this.dateDisease = dateDisease;
		this.dateCollection = dateCollection;
		this.season = season;
		this.notes = notes;
	}

	@OneToOne(mappedBy = "sampleEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ContainerEntity containerEntity;

	@OneToMany(mappedBy = "sampleEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ExtractionEntity> extractionEntities = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_samples_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private UserEntity userEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long patientId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId", foreignKey = @ForeignKey(name = "FK_samples_patient", foreignKeyDefinition = "FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private PatientEntity patientEntity;

	@Basic
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long providerProjectId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "providerProjectId", foreignKey = @ForeignKey(name = "FK_samples_provider_project", foreignKeyDefinition = "FOREIGN KEY (provider_project_id) REFERENCES provider_project(id) ON DELETE RESTRICT ON UPDATE CASCADE"))
	private ProviderProjectEntity providerProjectEntity;

	@Basic
	@Column(nullable = false, length = 128)
	private String labNum;

	@Basic
	@Column(columnDefinition = "INT(11) UNSIGNED")
	private Long caseHistory;

	@Basic
	@Column(columnDefinition = "INT(11) UNSIGNED")
	private Long numInProject;

	@Basic
	@Column(length = 128)
	private String externalNum;

	@Basic
	@Column(length = 32)
	private String sampleFunc;

	@Basic
	@Column(nullable = false)
	private Boolean samplePm;

	@Basic
	@Column(nullable = false, length = 32)
	private String sampleType;

	@Basic
	@Column(nullable = false, columnDefinition = "SMALLINT(6) UNSIGNED")
	private Integer volumeIn;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateReceipt;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateDisease;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateCollection;

	@Basic
	@Column(nullable = false, length = 11)
	private String season;

	@Basic
	@Column(columnDefinition = "LONGTEXT", length=-1)
	private String notes;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public PatientEntity getPatientEntity() {
		return patientEntity;
	}

	public void setPatientEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
	}

	public ProviderProjectEntity getProviderProjectEntity() {
		return providerProjectEntity;
	}

	public void setProviderProjectEntity(ProviderProjectEntity providerProjectEntity) {
		this.providerProjectEntity = providerProjectEntity;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getProviderProjectId() {
		return providerProjectId;
	}

	public void setProviderProjectId(Long providerProjectId) {
		this.providerProjectId = providerProjectId;
	}

	public String getLabNum() {
		return labNum;
	}

	public void setLabNum(String labNum) {
		this.labNum = labNum;
	}

	public Long getCaseHistory() {
		return caseHistory;
	}

	public void setCaseHistory(Long caseHistory) {
		this.caseHistory = caseHistory;
	}

	public Long getNumInProject() {
		return numInProject;
	}

	public void setNumInProject(Long numInProject) {
		this.numInProject = numInProject;
	}

	public String getExternalNum() {
		return externalNum;
	}

	public void setExternalNum(String externalNum) {
		this.externalNum = externalNum;
	}

	public String getSampleFunc() {
		return sampleFunc;
	}

	public void setSampleFunc(String sampleFunc) {
		this.sampleFunc = sampleFunc;
	}

	public Boolean getSamplePm() {
		return samplePm;
	}

	public void setSamplePm(Boolean samplePm) {
		this.samplePm = samplePm;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public Integer getVolumeIn() {
		return volumeIn;
	}

	public void setVolumeIn(Integer volumeIn) {
		this.volumeIn = volumeIn;
	}

	public Date getDateReceipt() {
		return dateReceipt;
	}

	public void setDateReceipt(Date dateReceipt) {
		this.dateReceipt = dateReceipt;
	}

	public Date getDateDisease() {
		return dateDisease;
	}

	public void setDateDisease(Date dateDisease) {
		this.dateDisease = dateDisease;
	}

	public Date getDateCollection() {
		return dateCollection;
	}

	public void setDateCollection(Date dateCollection) {
		this.dateCollection = dateCollection;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Collection<ExtractionEntity> getExtractionEntities() {
		return extractionEntities;
	}

	public void setExtractionEntities(Collection<ExtractionEntity> extractionEntities) {
		this.extractionEntities = extractionEntities;
	}

	public ContainerEntity getContainerEntity() {
		return containerEntity;
	}

	public void setContainerEntity(ContainerEntity containerEntity) {
		this.containerEntity = containerEntity;
	}
}
